package org.jose4j.jwk;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.jose4j.http.Get;
import org.jose4j.http.SimpleGet;
import org.jose4j.http.SimpleResponse;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsJwks {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpsJwks.class);
    private volatile Cache cache = new Cache(Collections.emptyList(), 0);
    private volatile long defaultCacheDuration = 3600;
    private final String location;
    private final ReentrantLock refreshLock = new ReentrantLock();
    private long refreshReprieveThreshold = 300;
    private volatile long retainCacheOnErrorDurationMills = 0;
    private volatile SimpleGet simpleHttpGet = new Get();

    public HttpsJwks(String str) {
        this.location = str;
    }

    public void setDefaultCacheDuration(long j) {
        this.defaultCacheDuration = j;
    }

    public void setRetainCacheOnErrorDuration(long j) {
        this.retainCacheOnErrorDurationMills = j * 1000;
    }

    public void setSimpleHttpGet(SimpleGet simpleGet) {
        this.simpleHttpGet = simpleGet;
    }

    public String getLocation() {
        return this.location;
    }

    public void setRefreshReprieveThreshold(long j) {
        this.refreshReprieveThreshold = j;
    }

    /* JADX INFO: finally extract failed */
    public List<JsonWebKey> getJsonWebKeys() throws JoseException, IOException {
        Cache cache2;
        long currentTimeMillis = System.currentTimeMillis();
        Cache cache3 = this.cache;
        if (cache3.exp > currentTimeMillis) {
            return cache3.keys;
        }
        if (!this.refreshLock.tryLock()) {
            if (!cache3.keys.isEmpty()) {
                return cache3.keys;
            }
            this.refreshLock.lock();
        }
        try {
            refresh();
            cache2 = this.cache;
            this.refreshLock.unlock();
        } catch (Exception e) {
            if (this.retainCacheOnErrorDurationMills <= 0 || cache3.keys.isEmpty()) {
                throw e;
            }
            Cache cache4 = new Cache(cache3.keys, currentTimeMillis + this.retainCacheOnErrorDurationMills);
            this.cache = cache4;
            log.info("Because of {} unable to refresh JWKS content from {} so will continue to use cached keys for more {} seconds until about {} -> {}", ExceptionHelp.toStringWithCauses(e), this.location, Long.valueOf(this.retainCacheOnErrorDurationMills / 1000), new Date(cache4.exp), cache4.keys);
            this.refreshLock.unlock();
            cache2 = cache4;
        } catch (Throwable th) {
            this.refreshLock.unlock();
            throw th;
        }
        return cache2.keys;
    }

    public void refresh() throws JoseException, IOException {
        this.refreshLock.lock();
        try {
            long currentTimeMillis = System.currentTimeMillis() - this.cache.created;
            if (currentTimeMillis >= this.refreshReprieveThreshold || this.cache.keys.isEmpty()) {
                Logger logger = log;
                logger.debug("Refreshing/loading JWKS from {}", (Object) this.location);
                SimpleResponse simpleResponse = this.simpleHttpGet.get(this.location);
                List<JsonWebKey> jsonWebKeys = new JsonWebKeySet(simpleResponse.getBody()).getJsonWebKeys();
                long cacheLife = getCacheLife(simpleResponse);
                if (cacheLife <= 0) {
                    logger.debug("Will use default cache duration of {} seconds for content from {}", (Object) Long.valueOf(this.defaultCacheDuration), (Object) this.location);
                    cacheLife = this.defaultCacheDuration;
                }
                long currentTimeMillis2 = System.currentTimeMillis() + (1000 * cacheLife);
                logger.debug("Updated JWKS content from {} will be cached for {} seconds until about {} -> {}", this.location, Long.valueOf(cacheLife), new Date(currentTimeMillis2), jsonWebKeys);
                this.cache = new Cache(jsonWebKeys, currentTimeMillis2);
            } else {
                log.debug("NOT refreshing/loading JWKS from {} because it just happened {} mills ago", (Object) this.location, (Object) Long.valueOf(currentTimeMillis));
            }
        } finally {
            this.refreshLock.unlock();
        }
    }

    static long getDateHeaderValue(SimpleResponse simpleResponse, String str, long j) {
        Iterator<String> it = getHeaderValues(simpleResponse, str).iterator();
        while (it.hasNext()) {
            String next = it.next();
            try {
                if (!next.endsWith("GMT")) {
                    next = next + " GMT";
                }
                return Date.parse(next);
            } catch (Exception unused) {
            }
        }
        return j;
    }

    private static List<String> getHeaderValues(SimpleResponse simpleResponse, String str) {
        List<String> headerValues = simpleResponse.getHeaderValues(str);
        return headerValues == null ? Collections.emptyList() : headerValues;
    }

    static long getExpires(SimpleResponse simpleResponse) {
        return getDateHeaderValue(simpleResponse, "expires", 0);
    }

    static long getCacheLife(SimpleResponse simpleResponse) {
        return getCacheLife(simpleResponse, System.currentTimeMillis());
    }

    static long getCacheLife(SimpleResponse simpleResponse, long j) {
        String str;
        long expires = (getExpires(simpleResponse) - j) / 1000;
        for (String next : getHeaderValues(simpleResponse, "cache-control")) {
            if (next == null) {
                str = "";
            } else {
                try {
                    str = next.toLowerCase();
                } catch (Exception unused) {
                }
            }
            int indexOf = str.indexOf("max-age");
            int indexOf2 = str.indexOf(44, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = str.length();
            }
            String substring = str.substring(indexOf, indexOf2);
            return Long.parseLong(substring.substring(substring.indexOf(61) + 1).trim());
        }
        return expires;
    }

    private static class Cache {
        /* access modifiers changed from: private */
        public final long created;
        /* access modifiers changed from: private */
        public final long exp;
        /* access modifiers changed from: private */
        public final List<JsonWebKey> keys;

        private Cache(List<JsonWebKey> list, long j) {
            this.created = System.currentTimeMillis();
            this.keys = list;
            this.exp = j;
        }
    }
}

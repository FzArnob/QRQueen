package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.CloudDBJedisListener;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.Constants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.exceptions.JedisNoScriptException;

@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Non-visible component allowing you to store data on a Internet connected database server (using Redis software). This allows the users of your App to share data with each other. By default data will be stored in a server maintained by MIT, however you can setup and run your own server. Set the \"RedisServer\" property and \"RedisPort\" Property to access your own server.", designerHelpDescription = "Non-visible component that communicates with CloudDB server to store and retrieve information.", iconName = "images/cloudDB.png", nonVisible = true, version = 1)
@UsesLibraries({"jedis.jar"})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public final class CloudDB extends AndroidNonvisibleComponent implements Component, OnClearListener, OnDestroyListener {
    private static final String APPEND_SCRIPT = "local key = KEYS[1];local toAppend = cjson.decode(ARGV[1]);local project = ARGV[2];local currentValue = redis.call('get', project .. \":\" .. key);local newTable;local subTable = {};local subTable1 = {};if (currentValue == false) then   newTable = {};else   newTable = cjson.decode(currentValue);  if not (type(newTable) == 'table') then     return error('You can only append to a list');  end end table.insert(newTable, toAppend);local newValue = cjson.encode(newTable);redis.call('set', project .. \":\" .. key, newValue);table.insert(subTable1, newValue);table.insert(subTable, key);table.insert(subTable, subTable1);redis.call(\"publish\", project, cjson.encode(subTable));return newValue;";
    private static final String APPEND_SCRIPT_SHA1 = "d6cc0f65b29878589f00564d52c8654967e9bcf8";
    private static final String COMODO_ROOT = "-----BEGIN CERTIFICATE-----\nMIIENjCCAx6gAwIBAgIBATANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJTRTEU\nMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFkZFRydXN0IEV4dGVybmFs\nIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBFeHRlcm5hbCBDQSBSb290\nMB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFowbzELMAkGA1UEBhMCU0Ux\nFDASBgNVBAoTC0FkZFRydXN0IEFCMSYwJAYDVQQLEx1BZGRUcnVzdCBFeHRlcm5h\nbCBUVFAgTmV0d29yazEiMCAGA1UEAxMZQWRkVHJ1c3QgRXh0ZXJuYWwgQ0EgUm9v\ndDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALf3GjPm8gAELTngTlvt\nH7xsD821+iO2zt6bETOXpClMfZOfvUq8k+0DGuOPz+VtUFrWlymUWoCwSXrbLpX9\nuMq/NzgtHj6RQa1wVsfwTz/oMp50ysiQVOnGXw94nZpAPA6sYapeFI+eh6FqUNzX\nmk6vBbOmcZSccbNQYArHE504B4YCqOmoaSYYkKtMsE8jqzpPhNjfzp/haW+710LX\na0Tkx63ubUFfclpxCDezeWWkWaCUN/cALw3CknLa0Dhy2xSoRcRdKn23tNbE7qzN\nE0S3ySvdQwAl+mG5aWpYIxG3pzOPVnVZ9c0p10a3CitlttNCbxWyuHv77+ldU9U0\nWicCAwEAAaOB3DCB2TAdBgNVHQ4EFgQUrb2YejS0Jvf6xCZU7wO94CTLVBowCwYD\nVR0PBAQDAgEGMA8GA1UdEwEB/wQFMAMBAf8wgZkGA1UdIwSBkTCBjoAUrb2YejS0\nJvf6xCZU7wO94CTLVBqhc6RxMG8xCzAJBgNVBAYTAlNFMRQwEgYDVQQKEwtBZGRU\ncnVzdCBBQjEmMCQGA1UECxMdQWRkVHJ1c3QgRXh0ZXJuYWwgVFRQIE5ldHdvcmsx\nIjAgBgNVBAMTGUFkZFRydXN0IEV4dGVybmFsIENBIFJvb3SCAQEwDQYJKoZIhvcN\nAQEFBQADggEBALCb4IUlwtYj4g+WBpKdQZic2YR5gdkeWxQHIzZlj7DYd7usQWxH\nYINRsPkyPef89iYTx4AWpb9a/IfPeHmJIZriTAcKhjW88t5RxNKWt9x+Tu5w/Rw5\n6wwCURQtjr0W4MHfRnXnJK3s9EK0hZNwEGe6nQY1ShjTK3rMUUKhemPR5ruhxSvC\nNr4TDea9Y355e6cJDUCrat2PisP29owaQgVR1EX1n6diIWgVIEM8med8vSTYqZEX\nc4g/VhsxOBi0cQ+azcgOno4uG+GMmIPLHzHxREzGBHNJdmAPx/i9F4BrLunMTA5a\nmnkPIAou1Z5jJh5VkpTYghdae9C8x49OhgQ=\n-----END CERTIFICATE-----\n";
    private static final String COMODO_USRTRUST = "-----BEGIN CERTIFICATE-----\nMIIFdzCCBF+gAwIBAgIQE+oocFv07O0MNmMJgGFDNjANBgkqhkiG9w0BAQwFADBv\nMQswCQYDVQQGEwJTRTEUMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFk\nZFRydXN0IEV4dGVybmFsIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBF\neHRlcm5hbCBDQSBSb290MB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFow\ngYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\nZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\nVQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIICIjAN\nBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgBJlFzYOw9sIs9CsVw127c0n00yt\nUINh4qogTQktZAnczomfzD2p7PbPwdzx07HWezcoEStH2jnGvDoZtF+mvX2do2NC\ntnbyqTsrkfjib9DsFiCQCT7i6HTJGLSR1GJk23+jBvGIGGqQIjy8/hPwhxR79uQf\njtTkUcYRZ0YIUcuGFFQ/vDP+fmyc/xadGL1RjjWmp2bIcmfbIWax1Jt4A8BQOujM\n8Ny8nkz+rwWWNR9XWrf/zvk9tyy29lTdyOcSOk2uTIq3XJq0tyA9yn8iNK5+O2hm\nAUTnAU5GU5szYPeUvlM3kHND8zLDU+/bqv50TmnHa4xgk97Exwzf4TKuzJM7UXiV\nZ4vuPVb+DNBpDxsP8yUmazNt925H+nND5X4OpWaxKXwyhGNVicQNwZNUMBkTrNN9\nN6frXTpsNVzbQdcS2qlJC9/YgIoJk2KOtWbPJYjNhLixP6Q5D9kCnusSTJV882sF\nqV4Wg8y4Z+LoE53MW4LTTLPtW//e5XOsIzstAL81VXQJSdhJWBp/kjbmUZIO8yZ9\nHE0XvMnsQybQv0FfQKlERPSZ51eHnlAfV1SoPv10Yy+xUGUJ5lhCLkMaTLTwJUdZ\n+gQek9QmRkpQgbLevni3/GcV4clXhB4PY9bpYrrWX1Uu6lzGKAgEJTm4Diup8kyX\nHAc/DVL17e8vgg8CAwEAAaOB9DCB8TAfBgNVHSMEGDAWgBStvZh6NLQm9/rEJlTv\nA73gJMtUGjAdBgNVHQ4EFgQUU3m/WqorSs9UgOHYm8Cd8rIDZsswDgYDVR0PAQH/\nBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wEQYDVR0gBAowCDAGBgRVHSAAMEQGA1Ud\nHwQ9MDswOaA3oDWGM2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9BZGRUcnVzdEV4\ndGVybmFsQ0FSb290LmNybDA1BggrBgEFBQcBAQQpMCcwJQYIKwYBBQUHMAGGGWh0\ndHA6Ly9vY3NwLnVzZXJ0cnVzdC5jb20wDQYJKoZIhvcNAQEMBQADggEBAJNl9jeD\nlQ9ew4IcH9Z35zyKwKoJ8OkLJvHgwmp1ocd5yblSYMgpEg7wrQPWCcR23+WmgZWn\nRtqCV6mVksW2jwMibDN3wXsyF24HzloUQToFJBv2FAY7qCUkDrvMKnXduXBBP3zQ\nYzYhBx9G/2CkkeFnvN4ffhkUyWNnkepnB2u0j4vAbkN9w6GAbLIevFOFfdyQoaS8\nLe9Gclc1Bb+7RrtubTeZtv8jkpHGbkD4jylW6l/VXxRTrPBPYer3IsynVgviuDQf\nJtl7GQVoP7o81DgGotPmjw7jtHFtQELFhLRAlSv0ZaBIefYdgWOWnU914Ph85I6p\n0fKtirOMxyHNwu8=\n-----END CERTIFICATE-----\n";
    private static final boolean DEBUG = false;
    private static final String DST_ROOT_X3 = "-----BEGIN CERTIFICATE-----\nMIIDSjCCAjKgAwIBAgIQRK+wgNajJ7qJMDmGLvhAazANBgkqhkiG9w0BAQUFADA/\nMSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\nDkRTVCBSb290IENBIFgzMB4XDTAwMDkzMDIxMTIxOVoXDTIxMDkzMDE0MDExNVow\nPzEkMCIGA1UEChMbRGlnaXRhbCBTaWduYXR1cmUgVHJ1c3QgQ28uMRcwFQYDVQQD\nEw5EU1QgUm9vdCBDQSBYMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAN+v6ZdQCINXtMxiZfaQguzH0yxrMMpb7NnDfcdAwRgUi+DoM3ZJKuM/IUmTrE4O\nrz5Iy2Xu/NMhD2XSKtkyj4zl93ewEnu1lcCJo6m67XMuegwGMoOifooUMM0RoOEq\nOLl5CjH9UL2AZd+3UWODyOKIYepLYYHsUmu5ouJLGiifSKOeDNoJjj4XLh7dIN9b\nxiqKqy69cK3FCxolkHRyxXtqqzTWMIn/5WgTe1QLyNau7Fqckh49ZLOMxt+/yUFw\n7BZy1SbsOFU5Q9D8/RhcQPGX69Wam40dutolucbY38EVAjqr2m7xPi71XAicPNaD\naeQQmxkqtilX4+U9m5/wAl0CAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAOBgNV\nHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFMSnsaR7LHH62+FLkHX/xBVghYkQMA0GCSqG\nSIb3DQEBBQUAA4IBAQCjGiybFwBcqR7uKGY3Or+Dxz9LwwmglSBd49lZRNI+DT69\nikugdB/OEIKcdBodfpga3csTS7MgROSR6cz8faXbauX+5v3gTt23ADq1cEmv8uXr\nAvHRAosZy5Q6XkjEGB5YGV8eAlrwDPGxrancWYaLbumR9YbK+rlmM6pZW87ipxZz\nR8srzJmwN0jP41ZL9c8PDHIyh8bwRLtTcm1D9SZImlJnt1ir/md2cXjbDaJWFBM5\nJDGFoqgCWjBH4d1QB7wCCZAA62RjYJsWvIjJEubSfZGL+T0yjWW06XyxV3bqxbYo\nOb8VZRzI9neWagqNdwvYkQsEjgfbKbYK7p2CNTUQ\n-----END CERTIFICATE-----\n";
    private static final String LOG_TAG = "CloudDB";
    private static final String POP_FIRST_SCRIPT = "local key = KEYS[1];local project = ARGV[1];local currentValue = redis.call('get', project .. \":\" .. key);local decodedValue = cjson.decode(currentValue);local subTable = {};local subTable1 = {};if (type(decodedValue) == 'table') then   local removedValue = table.remove(decodedValue, 1);  local newValue = cjson.encode(decodedValue);  redis.call('set', project .. \":\" .. key, newValue);  table.insert(subTable, key);  table.insert(subTable1, newValue);  table.insert(subTable, subTable1);  redis.call(\"publish\", project, cjson.encode(subTable));  return cjson.encode(removedValue);else   return error('You can only remove elements from a list');end";
    private static final String POP_FIRST_SCRIPT_SHA1 = "ed4cb4717d157f447848fe03524da24e461028e1";
    private static final String SET_SUB_SCRIPT = "local key = KEYS[1];local value = ARGV[1];local topublish = cjson.decode(ARGV[2]);local project = ARGV[3];local newtable = {};table.insert(newtable, key);table.insert(newtable, topublish);redis.call(\"publish\", project, cjson.encode(newtable));return redis.call('set', project .. \":\" .. key, value);";
    private static final String SET_SUB_SCRIPT_SHA1 = "765978e4c340012f50733280368a0ccc4a14dfb7";
    private Jedis INSTANCE = null;
    private SSLSocketFactory SslSockFactory = null;
    private final Activity activity;
    /* access modifiers changed from: private */
    public Handler androidUIHandler = new Handler();
    /* access modifiers changed from: private */
    public volatile ExecutorService background = Executors.newSingleThreadExecutor();
    private ConnectivityManager cm;
    /* access modifiers changed from: private */
    public volatile CloudDBJedisListener currentListener;
    /* access modifiers changed from: private */
    public volatile boolean dead = false;
    private String defaultRedisServer = null;
    private boolean importProject = false;
    private boolean isPublic = false;
    /* access modifiers changed from: private */
    public volatile boolean listenerRunning = false;
    /* access modifiers changed from: private */
    public String projectID = "";
    private volatile int redisPort;
    private volatile String redisServer = "DEFAULT";
    /* access modifiers changed from: private */
    public volatile boolean shutdown = false;
    /* access modifiers changed from: private */
    public final List<a> storeQueue = Collections.synchronizedList(new ArrayList());
    private String token = "";
    private boolean useDefault = true;
    private volatile boolean useSSL = true;

    static class a {
        JSONArray hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        String tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;

        a(String str, JSONArray jSONArray) {
            this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = str;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = jSONArray;
        }
    }

    public CloudDB(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.projectID = "";
        this.token = "";
        this.redisPort = 6381;
        this.cm = (ConnectivityManager) this.form.$context().getSystemService("connectivity");
    }

    public final void Initialize() {
        if (this.currentListener == null) {
            startListener();
        }
        this.form.registerForOnClear(this);
        this.form.registerForOnDestroy(this);
    }

    private void stopListener() {
        if (this.currentListener != null) {
            this.currentListener.terminate();
            this.currentListener = null;
            this.listenerRunning = false;
        }
    }

    public final void onClear() {
        this.shutdown = true;
        flushJedis(false);
    }

    public final void onDestroy() {
        onClear();
    }

    /* access modifiers changed from: private */
    public synchronized void startListener() {
        if (!this.listenerRunning) {
            this.listenerRunning = true;
            new Thread() {
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0037 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void run() {
                    /*
                        r8 = this;
                        com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this
                        r1 = 1
                        redis.clients.jedis.Jedis r0 = r0.getJedis(r1)
                        r2 = 0
                        r3 = 3000(0xbb8, double:1.482E-320)
                        if (r0 == 0) goto L_0x003d
                        com.google.appinventor.components.runtime.CloudDB r5 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.util.CloudDBJedisListener r6 = new com.google.appinventor.components.runtime.util.CloudDBJedisListener     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.CloudDB r7 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x002c }
                        r6.<init>(r7)     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.util.CloudDBJedisListener unused = r5.currentListener = r6     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.CloudDB r5 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.util.CloudDBJedisListener r5 = r5.currentListener     // Catch:{ Exception -> 0x002c }
                        java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ Exception -> 0x002c }
                        com.google.appinventor.components.runtime.CloudDB r6 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x002c }
                        java.lang.String r6 = r6.projectID     // Catch:{ Exception -> 0x002c }
                        r1[r2] = r6     // Catch:{ Exception -> 0x002c }
                        r0.subscribe(r5, r1)     // Catch:{ Exception -> 0x002c }
                        goto L_0x0040
                    L_0x002c:
                        r1 = move-exception
                        java.lang.String r5 = "CloudDB"
                        java.lang.String r6 = "Error in listener thread"
                        android.util.Log.e(r5, r6, r1)
                        r0.close()     // Catch:{ Exception -> 0x0037 }
                    L_0x0037:
                        java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x003b }
                        goto L_0x0040
                    L_0x003b:
                        goto L_0x0040
                    L_0x003d:
                        java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x003b }
                    L_0x0040:
                        com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this
                        boolean unused = r0.listenerRunning = r2
                        com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this
                        boolean r0 = r0.dead
                        if (r0 != 0) goto L_0x005a
                        com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this
                        boolean r0 = r0.shutdown
                        if (r0 != 0) goto L_0x005a
                        com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this
                        r0.startListener()
                    L_0x005a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CloudDB.AnonymousClass1.run():void");
                }
            }.start();
        }
    }

    @DesignerProperty(defaultValue = "DEFAULT", editorType = "string")
    public final void RedisServer(String str) {
        if (!str.equals("DEFAULT")) {
            this.useDefault = false;
            if (!str.equals(this.redisServer)) {
                this.redisServer = str;
                flushJedis(true);
            }
        } else if (!this.useDefault) {
            this.useDefault = true;
            String str2 = this.defaultRedisServer;
            if (str2 != null) {
                this.redisServer = str2;
            }
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server to use to store data. A setting of \"DEFAULT\" means that the MIT server will be used.")
    public final String RedisServer() {
        if (this.redisServer.equals(this.defaultRedisServer)) {
            return "DEFAULT";
        }
        return this.redisServer;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Default Redis Server to use.", userVisible = false)
    public final void DefaultRedisServer(String str) {
        this.defaultRedisServer = str;
        if (this.useDefault) {
            this.redisServer = str;
        }
    }

    @DesignerProperty(defaultValue = "6381", editorType = "integer")
    public final void RedisPort(int i) {
        if (i != this.redisPort) {
            this.redisPort = i;
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server port to use. Defaults to 6381")
    public final int RedisPort() {
        return this.redisPort;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectID for this CloudDB project.")
    public final String ProjectID() {
        checkProjectIDNotBlank();
        return this.projectID;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    public final void ProjectID(String str) {
        if (!this.projectID.equals(str)) {
            this.projectID = str;
        }
        if (this.projectID.equals("")) {
            throw new RuntimeException("CloudDB ProjectID property cannot be blank.");
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    public final void Token(String str) {
        if (!this.token.equals(str)) {
            this.token = str;
        }
        if (this.token.equals("")) {
            throw new RuntimeException("CloudDB Token property cannot be blank.");
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This field contains the authentication token used to login to the backed Redis server. For the \"DEFAULT\" server, do not edit this value, the system will fill it in for you. A system administrator may also provide a special value to you which can be used to share data between multiple projects from multiple people. If using your own Redis server, set a password in the server's config and enter it here.", userVisible = false)
    public final String Token() {
        checkProjectIDNotBlank();
        return this.token;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void UseSSL(boolean z) {
        if (this.useSSL != z) {
            this.useSSL = z;
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set to true to use SSL to talk to CloudDB/Redis server. This should be set to True for the \"DEFAULT\" server.", userVisible = false)
    public final boolean UseSSL() {
        return this.useSSL;
    }

    @SimpleFunction(description = "Store a value at a tag.")
    public final void StoreValue(String str, Object obj) {
        String str2;
        checkProjectIDNotBlank();
        NetworkInfo activeNetworkInfo = this.cm.getActiveNetworkInfo();
        boolean z = true;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (obj != null) {
            try {
                String obj2 = obj.toString();
                if (!obj2.startsWith("file:///")) {
                    if (!obj2.startsWith("/storage")) {
                        str2 = JsonUtil.getJsonRepresentation(obj);
                    }
                }
                str2 = JsonUtil.getJsonRepresentation(readFile(obj2));
            } catch (JSONException unused) {
                throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
            }
        } else {
            str2 = "";
        }
        if (z2) {
            synchronized (this.storeQueue) {
                if (this.storeQueue.size() != 0) {
                    z = false;
                }
                JSONArray jSONArray = new JSONArray();
                try {
                    jSONArray.put(0, str2);
                    this.storeQueue.add(new a(str, jSONArray));
                    if (z) {
                        this.background.submit(new Runnable() {
                            /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
                                if (r1 == null) goto L_?;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                                r0 = r2.toString();
                                r2 = r14.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                r2.jEval(com.google.appinventor.components.runtime.CloudDB.SET_SUB_SCRIPT, com.google.appinventor.components.runtime.CloudDB.SET_SUB_SCRIPT_SHA1, 1, r1, r3, r0, com.google.appinventor.components.runtime.CloudDB.access$100(r2));
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
                                r0 = move-exception;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
                                r14.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CloudDBError(r0.getMessage());
                                com.google.appinventor.components.runtime.CloudDB.access$700(r14.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
                                return;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
                                return;
                             */
                            /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
                                return;
                             */
                            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void run() {
                                /*
                                    r14 = this;
                                    r0 = 0
                                    r1 = r0
                                    r2 = r1
                                    r3 = r2
                                L_0x0004:
                                    com.google.appinventor.components.runtime.CloudDB r4 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    java.util.List r4 = r4.storeQueue     // Catch:{ Exception -> 0x00ca }
                                    monitor-enter(r4)     // Catch:{ Exception -> 0x00ca }
                                    com.google.appinventor.components.runtime.CloudDB r5 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ all -> 0x00c7 }
                                    java.util.List r5 = r5.storeQueue     // Catch:{ all -> 0x00c7 }
                                    int r5 = r5.size()     // Catch:{ all -> 0x00c7 }
                                    r6 = 0
                                    if (r5 != 0) goto L_0x001a
                                    r5 = r0
                                    goto L_0x0026
                                L_0x001a:
                                    com.google.appinventor.components.runtime.CloudDB r5 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ all -> 0x00c7 }
                                    java.util.List r5 = r5.storeQueue     // Catch:{ all -> 0x00c7 }
                                    java.lang.Object r5 = r5.remove(r6)     // Catch:{ all -> 0x00c7 }
                                    com.google.appinventor.components.runtime.CloudDB$a r5 = (com.google.appinventor.components.runtime.CloudDB.a) r5     // Catch:{ all -> 0x00c7 }
                                L_0x0026:
                                    monitor-exit(r4)     // Catch:{ all -> 0x00c7 }
                                    r4 = 3
                                    r7 = 2
                                    r8 = 4
                                    r9 = 1
                                    if (r5 != 0) goto L_0x0067
                                    if (r1 == 0) goto L_0x0066
                                    java.lang.String r0 = r2.toString()     // Catch:{ JedisException -> 0x004b }
                                    com.google.appinventor.components.runtime.CloudDB r2 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ JedisException -> 0x004b }
                                    java.lang.String r5 = "local key = KEYS[1];local value = ARGV[1];local topublish = cjson.decode(ARGV[2]);local project = ARGV[3];local newtable = {};table.insert(newtable, key);table.insert(newtable, topublish);redis.call(\"publish\", project, cjson.encode(newtable));return redis.call('set', project .. \":\" .. key, value);"
                                    java.lang.String r10 = "765978e4c340012f50733280368a0ccc4a14dfb7"
                                    java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ JedisException -> 0x004b }
                                    r8[r6] = r1     // Catch:{ JedisException -> 0x004b }
                                    r8[r9] = r3     // Catch:{ JedisException -> 0x004b }
                                    r8[r7] = r0     // Catch:{ JedisException -> 0x004b }
                                    java.lang.String r0 = r2.projectID     // Catch:{ JedisException -> 0x004b }
                                    r8[r4] = r0     // Catch:{ JedisException -> 0x004b }
                                    r2.jEval(r5, r10, r9, r8)     // Catch:{ JedisException -> 0x004b }
                                    goto L_0x0066
                                L_0x004b:
                                    r0 = move-exception
                                    com.google.appinventor.components.runtime.CloudDB r1 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ca }
                                    r2.<init>()     // Catch:{ Exception -> 0x00ca }
                                    java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00ca }
                                    r2.append(r0)     // Catch:{ Exception -> 0x00ca }
                                    java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00ca }
                                    r1.CloudDBError(r0)     // Catch:{ Exception -> 0x00ca }
                                    com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    r0.flushJedis(r9)     // Catch:{ Exception -> 0x00ca }
                                L_0x0066:
                                    return
                                L_0x0067:
                                    java.lang.String r10 = r5.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag     // Catch:{ Exception -> 0x00ca }
                                    org.json.JSONArray r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x00ca }
                                    if (r1 != 0) goto L_0x0074
                                    java.lang.String r3 = r5.getString(r6)     // Catch:{ Exception -> 0x00ca }
                                L_0x0071:
                                    r2 = r5
                                    r1 = r10
                                    goto L_0x0004
                                L_0x0074:
                                    boolean r11 = r1.equals(r10)     // Catch:{ Exception -> 0x00ca }
                                    if (r11 == 0) goto L_0x0082
                                    java.lang.String r3 = r5.getString(r6)     // Catch:{ Exception -> 0x00ca }
                                    r2.put(r3)     // Catch:{ Exception -> 0x00ca }
                                    goto L_0x0004
                                L_0x0082:
                                    java.lang.String r2 = r2.toString()     // Catch:{ JedisException -> 0x00a2 }
                                    com.google.appinventor.components.runtime.CloudDB r11 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ JedisException -> 0x00a2 }
                                    java.lang.String r12 = "local key = KEYS[1];local value = ARGV[1];local topublish = cjson.decode(ARGV[2]);local project = ARGV[3];local newtable = {};table.insert(newtable, key);table.insert(newtable, topublish);redis.call(\"publish\", project, cjson.encode(newtable));return redis.call('set', project .. \":\" .. key, value);"
                                    java.lang.String r13 = "765978e4c340012f50733280368a0ccc4a14dfb7"
                                    java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ JedisException -> 0x00a2 }
                                    r8[r6] = r1     // Catch:{ JedisException -> 0x00a2 }
                                    r8[r9] = r3     // Catch:{ JedisException -> 0x00a2 }
                                    r8[r7] = r2     // Catch:{ JedisException -> 0x00a2 }
                                    java.lang.String r1 = r11.projectID     // Catch:{ JedisException -> 0x00a2 }
                                    r8[r4] = r1     // Catch:{ JedisException -> 0x00a2 }
                                    r11.jEval(r12, r13, r9, r8)     // Catch:{ JedisException -> 0x00a2 }
                                    java.lang.String r3 = r5.getString(r6)     // Catch:{ Exception -> 0x00ca }
                                    goto L_0x0071
                                L_0x00a2:
                                    r0 = move-exception
                                    com.google.appinventor.components.runtime.CloudDB r1 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ca }
                                    r2.<init>()     // Catch:{ Exception -> 0x00ca }
                                    java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x00ca }
                                    r2.append(r0)     // Catch:{ Exception -> 0x00ca }
                                    java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00ca }
                                    r1.CloudDBError(r0)     // Catch:{ Exception -> 0x00ca }
                                    com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    r0.flushJedis(r9)     // Catch:{ Exception -> 0x00ca }
                                    com.google.appinventor.components.runtime.CloudDB r0 = com.google.appinventor.components.runtime.CloudDB.this     // Catch:{ Exception -> 0x00ca }
                                    java.util.List r0 = r0.storeQueue     // Catch:{ Exception -> 0x00ca }
                                    r0.clear()     // Catch:{ Exception -> 0x00ca }
                                    return
                                L_0x00c7:
                                    r0 = move-exception
                                    monitor-exit(r4)     // Catch:{ all -> 0x00c7 }
                                    throw r0     // Catch:{ Exception -> 0x00ca }
                                L_0x00ca:
                                    r0 = move-exception
                                    java.lang.String r1 = "CloudDB"
                                    java.lang.String r2 = "Exception in store worker!"
                                    android.util.Log.e(r1, r2, r0)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CloudDB.AnonymousClass4.run():void");
                            }
                        });
                    }
                } catch (JSONException unused2) {
                    throw new YailRuntimeError("JSON Error putting value.", "value is not convertable");
                }
            }
            return;
        }
        CloudDBError("Cannot store values off-line.");
    }

    @SimpleFunction(description = "Get the Value for a tag, doesn't return the value but will cause a GotValue event to fire when the value is looked up.")
    public final void GetValue(final String str, final Object obj) {
        checkProjectIDNotBlank();
        final AtomicReference atomicReference = new AtomicReference();
        NetworkInfo activeNetworkInfo = this.cm.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            this.background.submit(new Runnable() {
                public final void run() {
                    Jedis jedis = CloudDB.this.getJedis();
                    try {
                        String str = jedis.get(CloudDB.this.projectID + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str);
                        if (str != null) {
                            String jsonRepresentationIfValueFileName = JsonUtil.getJsonRepresentationIfValueFileName(CloudDB.this.form, str);
                            if (jsonRepresentationIfValueFileName != null) {
                                atomicReference.set(jsonRepresentationIfValueFileName);
                            } else {
                                atomicReference.set(str);
                            }
                        } else {
                            atomicReference.set(JsonUtil.getJsonRepresentation(obj));
                        }
                        CloudDB.this.androidUIHandler.post(new Runnable() {
                            public final void run() {
                                CloudDB.this.GotValue(str, atomicReference.get());
                            }
                        });
                    } catch (JSONException unused) {
                        CloudDB cloudDB = CloudDB.this;
                        cloudDB.CloudDBError("JSON conversion error for " + str);
                    } catch (NullPointerException unused2) {
                        CloudDB cloudDB2 = CloudDB.this;
                        cloudDB2.CloudDBError("System Error getting tag " + str);
                        CloudDB.this.flushJedis(true);
                    } catch (JedisException e) {
                        Log.e(CloudDB.LOG_TAG, "Exception in GetValue", e);
                        CloudDB cloudDB3 = CloudDB.this;
                        cloudDB3.CloudDBError(e.getMessage());
                        CloudDB.this.flushJedis(true);
                    } catch (Exception e2) {
                        Log.e(CloudDB.LOG_TAG, "Exception in GetValue", e2);
                        CloudDB.this.CloudDBError(e2.getMessage());
                        CloudDB.this.flushJedis(true);
                    }
                }
            });
        } else {
            CloudDBError("Cannot fetch variables while off-line.");
        }
    }

    @SimpleFunction(description = "returns True if we are on the network and will likely be able to connect to the CloudDB server.")
    public final boolean CloudConnected() {
        NetworkInfo activeNetworkInfo = this.cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @SimpleEvent(description = "Event triggered by the \"RemoveFirstFromList\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
    public final void FirstRemoved(final Object obj) {
        checkProjectIDNotBlank();
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    obj = JsonUtil.getObjectFromJson((String) obj, true);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "error while converting to JSON...", e);
                return;
            }
        }
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(CloudDB.this, "FirstRemoved", obj);
            }
        });
    }

    @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
    public final void RemoveFirstFromList(final String str) {
        checkProjectIDNotBlank();
        this.background.submit(new Runnable() {
            public final void run() {
                CloudDB.this.getJedis();
                try {
                    CloudDB cloudDB = CloudDB.this;
                    cloudDB.FirstRemoved(cloudDB.jEval(CloudDB.POP_FIRST_SCRIPT, CloudDB.POP_FIRST_SCRIPT_SHA1, 1, str, cloudDB.projectID));
                } catch (JedisException e) {
                    CloudDB cloudDB2 = CloudDB.this;
                    cloudDB2.CloudDBError(e.getMessage());
                    CloudDB.this.flushJedis(true);
                }
            }
        });
    }

    @SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
    public final void AppendValueToList(final String str, Object obj) {
        checkProjectIDNotBlank();
        Object obj2 = new Object();
        if (obj != null) {
            try {
                obj2 = JsonUtil.getJsonRepresentation(obj);
            } catch (JSONException unused) {
                throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
            }
        }
        final String str2 = (String) obj2;
        this.background.submit(new Runnable() {
            public final void run() {
                CloudDB.this.getJedis();
                try {
                    CloudDB cloudDB = CloudDB.this;
                    cloudDB.jEval(CloudDB.APPEND_SCRIPT, CloudDB.APPEND_SCRIPT_SHA1, 1, str, str2, cloudDB.projectID);
                } catch (JedisException e) {
                    CloudDB cloudDB2 = CloudDB.this;
                    cloudDB2.CloudDBError(e.getMessage());
                    CloudDB.this.flushJedis(true);
                }
            }
        });
    }

    @SimpleEvent
    public final void GotValue(String str, Object obj) {
        checkProjectIDNotBlank();
        if (obj == null) {
            CloudDBError("Trouble getting " + str + " from the server.");
            return;
        }
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    obj = JsonUtil.getObjectFromJson((String) obj, true);
                }
            } catch (JSONException unused) {
                throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
            }
        }
        EventDispatcher.dispatchEvent(this, "GotValue", str, obj);
    }

    @SimpleFunction(description = "Remove the tag from CloudDB")
    public final void ClearTag(final String str) {
        checkProjectIDNotBlank();
        this.background.submit(new Runnable() {
            public final void run() {
                try {
                    Jedis jedis = CloudDB.this.getJedis();
                    jedis.del(CloudDB.this.projectID + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str);
                } catch (Exception e) {
                    CloudDB cloudDB = CloudDB.this;
                    cloudDB.CloudDBError(e.getMessage());
                    CloudDB.this.flushJedis(true);
                }
            }
        });
    }

    @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
    public final void GetTagList() {
        checkProjectIDNotBlank();
        NetworkInfo activeNetworkInfo = this.cm.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            this.background.submit(new Runnable() {
                public final void run() {
                    Jedis jedis = CloudDB.this.getJedis();
                    try {
                        final ArrayList arrayList = new ArrayList(jedis.keys(CloudDB.this.projectID + ":*"));
                        for (int i = 0; i < arrayList.size(); i++) {
                            arrayList.set(i, ((String) arrayList.get(i)).substring((CloudDB.this.projectID + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).length()));
                        }
                        CloudDB.this.androidUIHandler.post(new Runnable() {
                            public final void run() {
                                CloudDB.this.TagList(arrayList);
                            }
                        });
                    } catch (JedisException e) {
                        CloudDB cloudDB = CloudDB.this;
                        cloudDB.CloudDBError(e.getMessage());
                        CloudDB.this.flushJedis(true);
                    }
                }
            });
        } else {
            CloudDBError("Not connected to the Internet, cannot list tags");
        }
    }

    @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
    public final void TagList(List<String> list) {
        checkProjectIDNotBlank();
        EventDispatcher.dispatchEvent(this, "TagList", list);
    }

    @SimpleEvent
    public final void DataChanged(final String str, Object obj) {
        final Object obj2;
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    obj2 = JsonUtil.getObjectFromJson((String) obj, true);
                    this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            EventDispatcher.dispatchEvent(CloudDB.this, "DataChanged", str, obj2);
                        }
                    });
                }
            } catch (JSONException unused) {
                throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
            }
        }
        obj2 = "";
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(CloudDB.this, "DataChanged", str, obj2);
            }
        });
    }

    @SimpleEvent(description = "Indicates that an error occurred while communicating with the CloudDB Redis server.")
    public final void CloudDBError(final String str) {
        Log.e(LOG_TAG, str);
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                if (!EventDispatcher.dispatchEvent(CloudDB.this, "CloudDBError", str)) {
                    Notifier notifier = new Notifier(CloudDB.this.form);
                    notifier.ShowAlert("CloudDBError: " + str);
                }
            }
        });
    }

    private void checkProjectIDNotBlank() {
        if (this.projectID.equals("")) {
            throw new RuntimeException("CloudDB ProjectID property cannot be blank.");
        } else if (this.token.equals("")) {
            throw new RuntimeException("CloudDB Token property cannot be blank");
        }
    }

    public final Form getForm() {
        return this.form;
    }

    public final Jedis getJedis(boolean z) {
        Jedis jedis;
        if (this.dead) {
            return null;
        }
        try {
            if (this.useSSL) {
                ensureSslSockFactory();
                jedis = new Jedis(this.redisServer, this.redisPort, true, this.SslSockFactory, (SSLParameters) null, (HostnameVerifier) null);
            } else {
                jedis = new Jedis(this.redisServer, this.redisPort, false);
            }
            if (this.token.substring(0, 1).equals("%")) {
                jedis.auth(this.token.substring(1));
            } else {
                jedis.auth(this.token);
            }
            return jedis;
        } catch (JedisConnectionException e) {
            Log.e(LOG_TAG, "in getJedis()", e);
            CloudDBError(e.getMessage());
            return null;
        } catch (JedisDataException e2) {
            Log.e(LOG_TAG, "in getJedis()", e2);
            CloudDBError(e2.getMessage() + " CloudDB disabled, restart to re-enable.");
            this.dead = true;
            return null;
        }
    }

    public final synchronized Jedis getJedis() {
        if (this.INSTANCE == null) {
            this.INSTANCE = getJedis(true);
        }
        return this.INSTANCE;
    }

    /* access modifiers changed from: private */
    public void flushJedis(boolean z) {
        Jedis jedis = this.INSTANCE;
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception unused) {
            }
            this.INSTANCE = null;
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    CloudDB.this.background.shutdownNow();
                    ExecutorService unused = CloudDB.this.background = Executors.newSingleThreadExecutor();
                }
            });
            stopListener();
            if (z) {
                startListener();
            }
        }
    }

    private YailList readFile(String str) {
        try {
            String substring = str.startsWith("file://") ? str.substring(7) : str;
            if (substring.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                return YailList.makeList(new Object[]{".".concat(String.valueOf(getFileExtension(substring))), Base64.encodeToString(FileUtil.readFile(this.form, substring), 0)});
            }
            throw new YailRuntimeError("Invalid fileName, was ".concat(String.valueOf(str)), "ReadFrom");
        } catch (Exception e) {
            throw new YailRuntimeError(e.getMessage(), "Read");
        }
    }

    private String getFileExtension(String str) {
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public final ExecutorService getBackground() {
        return this.background;
    }

    public final Object jEval(String str, String str2, int i, String... strArr) throws JedisException {
        Jedis jedis = getJedis();
        try {
            return jedis.evalsha(str2, i, strArr);
        } catch (JedisNoScriptException unused) {
            return jedis.eval(str, i, strArr);
        }
    }

    private synchronized void ensureSslSockFactory() {
        if (this.SslSockFactory == null) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(COMODO_ROOT.getBytes("UTF-8"));
                Certificate generateCertificate = instance.generateCertificate(byteArrayInputStream);
                byteArrayInputStream.close();
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(COMODO_USRTRUST.getBytes("UTF-8"));
                Certificate generateCertificate2 = instance.generateCertificate(byteArrayInputStream2);
                byteArrayInputStream2.close();
                ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(DST_ROOT_X3.getBytes("UTF-8"));
                Certificate generateCertificate3 = instance.generateCertificate(byteArrayInputStream3);
                byteArrayInputStream3.close();
                KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
                instance2.load((InputStream) null, (char[]) null);
                int i = 1;
                for (X509Certificate certificateEntry : getSystemCertificates()) {
                    instance2.setCertificateEntry("root".concat(String.valueOf(i)), certificateEntry);
                    i++;
                }
                instance2.setCertificateEntry("comodo", generateCertificate);
                instance2.setCertificateEntry("inter", generateCertificate2);
                instance2.setCertificateEntry("dstx3", generateCertificate3);
                TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance3.init(instance2);
                SSLContext instance4 = SSLContext.getInstance("TLS");
                instance4.init((KeyManager[]) null, instance3.getTrustManagers(), (SecureRandom) null);
                this.SslSockFactory = instance4.getSocketFactory();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Could not setup SSL Trust Store for CloudDB", e);
                throw new YailRuntimeError("Could Not setup SSL Trust Store for CloudDB: ", e.getMessage());
            }
        }
    }

    private X509Certificate[] getSystemCertificates() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            return ((X509TrustManager) instance.getTrustManagers()[0]).getAcceptedIssuers();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Getting System Certificates", e);
            return new X509Certificate[0];
        }
    }
}

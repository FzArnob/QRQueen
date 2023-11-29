package org.jose4j.jwk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleJwkFilter {
    private static final String[] EMPTY = new String[2];
    public static boolean OMITTED_OKAY = true;
    public static boolean VALUE_REQUIRED = false;
    private Criteria alg;
    private boolean allowThumbsFallbackDeriveFromX5c;
    private Criteria crv;
    private KeyOpsCriteria keyOps;
    private Criteria kid;
    private Criteria kty;
    private Criteria use;
    private Criteria x5t;
    private Criteria x5tS256;

    public void setKid(String str, boolean z) {
        this.kid = new Criteria(str, z);
    }

    public void setKty(String str) {
        this.kty = new Criteria(str, false);
    }

    public void setUse(String str, boolean z) {
        this.use = new Criteria(str, z);
    }

    public void setKeyOperations(String[] strArr, boolean z) {
        this.keyOps = new KeyOpsCriteria(strArr, z);
    }

    public void setAlg(String str, boolean z) {
        this.alg = new Criteria(str, z);
    }

    public void setX5t(String str, boolean z) {
        this.x5t = new Criteria(str, z);
    }

    public void setX5tS256(String str, boolean z) {
        this.x5tS256 = new Criteria(str, z);
    }

    public void setAllowFallbackDeriveFromX5cForX5Thumbs(boolean z) {
        this.allowThumbsFallbackDeriveFromX5c = z;
    }

    public void setCrv(String str, boolean z) {
        this.crv = new Criteria(str, z);
    }

    public List<JsonWebKey> filter(Collection<JsonWebKey> collection) {
        ArrayList arrayList = new ArrayList();
        for (JsonWebKey next : collection) {
            boolean isMatch = isMatch(this.kid, next.getKeyId()) & isMatch(this.kty, next.getKeyType()) & isMatch(this.use, next.getUse()) & isMatch(this.alg, next.getAlgorithm());
            String[] thumbs = getThumbs(next, this.allowThumbsFallbackDeriveFromX5c);
            boolean z = false;
            boolean isMatch2 = isMatch & isMatch(this.x5t, thumbs[0]) & isMatch(this.x5tS256, thumbs[1]) & isMatch(this.crv, getCrv(next));
            KeyOpsCriteria keyOpsCriteria = this.keyOps;
            if (keyOpsCriteria == null || keyOpsCriteria.meetsCriteria(next.getKeyOps())) {
                z = true;
            }
            if (isMatch2 && z) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean isMatch(Criteria criteria, String str) {
        return criteria == null || criteria.meetsCriteria(str);
    }

    /* access modifiers changed from: package-private */
    public String getCrv(JsonWebKey jsonWebKey) {
        if (jsonWebKey instanceof EllipticCurveJsonWebKey) {
            return ((EllipticCurveJsonWebKey) jsonWebKey).getCurveName();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String[] getThumbs(JsonWebKey jsonWebKey, boolean z) {
        if (this.x5t == null && this.x5tS256 == null) {
            return EMPTY;
        }
        if (!(jsonWebKey instanceof PublicJsonWebKey)) {
            return EMPTY;
        }
        PublicJsonWebKey publicJsonWebKey = (PublicJsonWebKey) jsonWebKey;
        return new String[]{publicJsonWebKey.getX509CertificateSha1Thumbprint(z), publicJsonWebKey.getX509CertificateSha256Thumbprint(z)};
    }

    private static class Criteria {
        boolean noValueOk;
        String value;

        private Criteria(String str, boolean z) {
            this.value = str;
            this.noValueOk = z;
        }

        public boolean meetsCriteria(String str) {
            if (str == null) {
                return this.noValueOk;
            }
            return str.equals(this.value);
        }
    }

    private static class KeyOpsCriteria {
        boolean noValueOk;
        String[] values;

        private KeyOpsCriteria(String[] strArr, boolean z) {
            this.values = strArr;
            this.noValueOk = z;
        }

        public boolean meetsCriteria(List<String> list) {
            if (list == null) {
                return this.noValueOk;
            }
            for (String contains : this.values) {
                if (list.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
    }
}

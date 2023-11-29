package org.jose4j.jwk;

import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.JsonHelp;

public abstract class PublicJsonWebKey extends JsonWebKey {
    public static final String X509_CERTIFICATE_CHAIN_PARAMETER = "x5c";
    public static final String X509_SHA256_THUMBPRINT_PARAMETER = "x5t#S256";
    public static final String X509_THUMBPRINT_PARAMETER = "x5t";
    public static final String X509_URL_PARAMETER = "x5u";
    private List<X509Certificate> certificateChain;
    protected String jcaProvider;
    protected PrivateKey privateKey;
    protected boolean writeOutPrivateKeyToJson;
    private String x5t;
    private String x5tS256;
    private String x5u;

    /* access modifiers changed from: protected */
    public abstract void fillPrivateTypeSpecificParams(Map<String, Object> map);

    /* access modifiers changed from: protected */
    public abstract void fillPublicTypeSpecificParams(Map<String, Object> map);

    protected PublicJsonWebKey(PublicKey publicKey) {
        super((Key) publicKey);
    }

    protected PublicJsonWebKey(Map<String, Object> map) throws JoseException {
        this(map, (String) null);
    }

    protected PublicJsonWebKey(Map<String, Object> map, String str) throws JoseException {
        super(map);
        this.jcaProvider = str;
        if (map.containsKey("x5c")) {
            List<String> stringArray = JsonHelp.getStringArray(map, "x5c");
            this.certificateChain = new ArrayList(stringArray.size());
            X509Util x509Util = X509Util.getX509Util(str);
            for (String fromBase64Der : stringArray) {
                this.certificateChain.add(x509Util.fromBase64Der(fromBase64Der));
            }
        }
        this.x5t = getString(map, "x5t");
        this.x5tS256 = getString(map, "x5t#S256");
        this.x5u = getString(map, "x5u");
        removeFromOtherParams("x5c", "x5t#S256", "x5t", "x5u");
    }

    /* access modifiers changed from: protected */
    public void fillTypeSpecificParams(Map<String, Object> map, JsonWebKey.OutputControlLevel outputControlLevel) {
        fillPublicTypeSpecificParams(map);
        if (this.certificateChain != null) {
            X509Util x509Util = new X509Util();
            ArrayList arrayList = new ArrayList(this.certificateChain.size());
            for (X509Certificate base64 : this.certificateChain) {
                arrayList.add(x509Util.toBase64(base64));
            }
            map.put("x5c", arrayList);
        }
        putIfNotNull("x5t", this.x5t, map);
        putIfNotNull("x5t#S256", this.x5tS256, map);
        putIfNotNull("x5u", this.x5u, map);
        if (this.writeOutPrivateKeyToJson || outputControlLevel == JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE) {
            fillPrivateTypeSpecificParams(map);
        }
    }

    public PublicKey getPublicKey() {
        return (PublicKey) this.key;
    }

    public void setWriteOutPrivateKeyToJson(boolean z) {
        this.writeOutPrivateKeyToJson = z;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey2) {
        this.privateKey = privateKey2;
    }

    public List<X509Certificate> getCertificateChain() {
        return this.certificateChain;
    }

    public X509Certificate getLeafCertificate() {
        List<X509Certificate> list = this.certificateChain;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.certificateChain.get(0);
    }

    public String getX509CertificateSha1Thumbprint() {
        return getX509CertificateSha1Thumbprint(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r2 = getLeafCertificate();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getX509CertificateSha1Thumbprint(boolean r2) {
        /*
            r1 = this;
            java.lang.String r0 = r1.x5t
            if (r0 != 0) goto L_0x0010
            if (r2 == 0) goto L_0x0010
            java.security.cert.X509Certificate r2 = r1.getLeafCertificate()
            if (r2 == 0) goto L_0x0010
            java.lang.String r0 = org.jose4j.keys.X509Util.x5t(r2)
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwk.PublicJsonWebKey.getX509CertificateSha1Thumbprint(boolean):java.lang.String");
    }

    public String getX509CertificateSha256Thumbprint() {
        return getX509CertificateSha256Thumbprint(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r2 = getLeafCertificate();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getX509CertificateSha256Thumbprint(boolean r2) {
        /*
            r1 = this;
            java.lang.String r0 = r1.x5tS256
            if (r0 != 0) goto L_0x0010
            if (r2 == 0) goto L_0x0010
            java.security.cert.X509Certificate r2 = r1.getLeafCertificate()
            if (r2 == 0) goto L_0x0010
            java.lang.String r0 = org.jose4j.keys.X509Util.x5tS256(r2)
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwk.PublicJsonWebKey.getX509CertificateSha256Thumbprint(boolean):java.lang.String");
    }

    public String getX509Url() {
        return this.x5u;
    }

    public void setCertificateChain(List<X509Certificate> list) {
        checkForBareKeyCertMismatch();
        this.certificateChain = list;
    }

    public void setX509CertificateSha1Thumbprint(String str) {
        this.x5t = str;
    }

    public void setX509CertificateSha256Thumbprint(String str) {
        this.x5tS256 = str;
    }

    public void setX509Url(String str) {
        this.x5u = str;
    }

    /* access modifiers changed from: package-private */
    public void checkForBareKeyCertMismatch() {
        X509Certificate leafCertificate = getLeafCertificate();
        if (leafCertificate != null && !leafCertificate.getPublicKey().equals(getPublicKey())) {
            throw new IllegalArgumentException("The key in the first certificate MUST match the bare public key represented by other members of the JWK. Public key = " + getPublicKey() + " cert = " + leafCertificate);
        }
    }

    public void setCertificateChain(X509Certificate... x509CertificateArr) {
        setCertificateChain((List<X509Certificate>) Arrays.asList(x509CertificateArr));
    }

    /* access modifiers changed from: package-private */
    public BigInteger getBigIntFromBase64UrlEncodedParam(Map<String, Object> map, String str, boolean z) throws JoseException {
        return BigEndianBigInteger.fromBase64Url(getString(map, str, z));
    }

    /* access modifiers changed from: package-private */
    public void putBigIntAsBase64UrlEncodedParam(Map<String, Object> map, String str, BigInteger bigInteger) {
        map.put(str, BigEndianBigInteger.toBase64Url(bigInteger));
    }

    /* access modifiers changed from: package-private */
    public void putBigIntAsBase64UrlEncodedParam(Map<String, Object> map, String str, BigInteger bigInteger, int i) {
        map.put(str, BigEndianBigInteger.toBase64Url(bigInteger, i));
    }

    public static class Factory {
        public static PublicJsonWebKey newPublicJwk(Map<String, Object> map, String str) throws JoseException {
            String stringRequired = JsonWebKey.getStringRequired(map, JsonWebKey.KEY_TYPE_PARAMETER);
            stringRequired.hashCode();
            if (stringRequired.equals("EC")) {
                return new EllipticCurveJsonWebKey(map, str);
            }
            if (stringRequired.equals("RSA")) {
                return new RsaJsonWebKey(map, str);
            }
            throw new JoseException("Unknown key type (for public keys): '" + stringRequired + "'");
        }

        public static PublicJsonWebKey newPublicJwk(Map<String, Object> map) throws JoseException {
            return newPublicJwk(map, (String) null);
        }

        public static PublicJsonWebKey newPublicJwk(Key key) throws JoseException {
            return (PublicJsonWebKey) JsonWebKey.Factory.newJwk(key);
        }

        public static PublicJsonWebKey newPublicJwk(String str) throws JoseException {
            return newPublicJwk(str, (String) null);
        }

        public static PublicJsonWebKey newPublicJwk(String str, String str2) throws JoseException {
            return newPublicJwk(JsonUtil.parseJson(str), str2);
        }
    }
}

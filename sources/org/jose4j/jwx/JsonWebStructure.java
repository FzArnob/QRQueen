package org.jose4j.jwx;

import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.Algorithm;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.JoseException;

public abstract class JsonWebStructure {
    private static final ProviderContext DEFAULT_PROVIDER_CONTEXT = new ProviderContext();
    private AlgorithmConstraints algorithmConstraints = AlgorithmConstraints.NO_CONSTRAINTS;
    protected Base64Url base64url = new Base64Url();
    protected boolean doKeyValidation = true;
    protected Headers headers = new Headers();
    private byte[] integrity;
    private Key key;
    private Set<String> knownCriticalHeaders = Collections.emptySet();
    private ProviderContext providerCtx = DEFAULT_PROVIDER_CONTEXT;
    protected String rawCompactSerialization;

    public abstract Algorithm getAlgorithm() throws InvalidAlgorithmException;

    public abstract Algorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException;

    public abstract String getCompactSerialization() throws JoseException;

    public abstract String getPayload() throws JoseException;

    /* access modifiers changed from: protected */
    public boolean isSupportedCriticalHeader(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onNewKey() {
    }

    /* access modifiers changed from: protected */
    public abstract void setCompactSerializationParts(String[] strArr) throws JoseException;

    public abstract void setPayload(String str);

    public static JsonWebStructure fromCompactSerialization(String str) throws JoseException {
        JsonWebStructure jsonWebStructure;
        String[] deserialize = CompactSerializer.deserialize(str);
        if (deserialize.length == 5) {
            jsonWebStructure = new JsonWebEncryption();
        } else if (deserialize.length == 3) {
            jsonWebStructure = new JsonWebSignature();
        } else {
            throw new JoseException("Invalid JOSE Compact Serialization. Expecting either 3 or 5 parts for JWS or JWE respectively but was " + deserialize.length + ".");
        }
        jsonWebStructure.setCompactSerializationParts(deserialize);
        jsonWebStructure.rawCompactSerialization = str;
        return jsonWebStructure;
    }

    public void setCompactSerialization(String str) throws JoseException {
        setCompactSerializationParts(CompactSerializer.deserialize(str));
        this.rawCompactSerialization = str;
    }

    public String getHeader() {
        return getHeaders().getFullHeaderAsJsonString();
    }

    /* access modifiers changed from: protected */
    public String getEncodedHeader() {
        return this.headers.getEncodedHeader();
    }

    public void setHeader(String str, String str2) {
        this.headers.setStringHeaderValue(str, str2);
    }

    /* access modifiers changed from: protected */
    public void setEncodedHeader(String str) throws JoseException {
        checkNotEmptyPart(str, "Encoded Header");
        this.headers.setEncodedHeader(str);
    }

    public Headers getHeaders() {
        return this.headers;
    }

    /* access modifiers changed from: protected */
    public void checkNotEmptyPart(String str, String str2) throws JoseException {
        if (str == null || str.length() == 0) {
            throw new JoseException("The " + str2 + " cannot be empty.");
        }
    }

    public String getHeader(String str) {
        return this.headers.getStringHeaderValue(str);
    }

    public void setHeader(String str, Object obj) {
        this.headers.setObjectHeaderValue(str, obj);
    }

    public Object getObjectHeader(String str) {
        return this.headers.getObjectHeaderValue(str);
    }

    public void setAlgorithmHeaderValue(String str) {
        setHeader("alg", str);
    }

    public String getAlgorithmHeaderValue() {
        return getHeader("alg");
    }

    public void setContentTypeHeaderValue(String str) {
        setHeader(HeaderParameterNames.CONTENT_TYPE, str);
    }

    public String getContentTypeHeaderValue() {
        return getHeader(HeaderParameterNames.CONTENT_TYPE);
    }

    public void setKeyIdHeaderValue(String str) {
        setHeader("kid", str);
    }

    public String getKeyIdHeaderValue() {
        return getHeader("kid");
    }

    public PublicJsonWebKey getJwkHeader() throws JoseException {
        return this.headers.getPublicJwkHeaderValue(HeaderParameterNames.JWK, (String) null);
    }

    public void setJwkHeader(PublicJsonWebKey publicJsonWebKey) {
        this.headers.setJwkHeaderValue(HeaderParameterNames.JWK, publicJsonWebKey);
    }

    public X509Certificate getLeafCertificateHeaderValue() throws JoseException {
        List<X509Certificate> certificateChainHeaderValue = getCertificateChainHeaderValue();
        if (certificateChainHeaderValue == null || certificateChainHeaderValue.isEmpty()) {
            return null;
        }
        return certificateChainHeaderValue.get(0);
    }

    public List<X509Certificate> getCertificateChainHeaderValue() throws JoseException {
        Object objectHeaderValue = this.headers.getObjectHeaderValue("x5c");
        if (!(objectHeaderValue instanceof List)) {
            return null;
        }
        List<String> list = (List) objectHeaderValue;
        ArrayList arrayList = new ArrayList(list.size());
        X509Util x509Util = new X509Util();
        for (String fromBase64Der : list) {
            arrayList.add(x509Util.fromBase64Der(fromBase64Der));
        }
        return arrayList;
    }

    public void setCertificateChainHeaderValue(X509Certificate... x509CertificateArr) {
        ArrayList arrayList = new ArrayList();
        X509Util x509Util = new X509Util();
        for (X509Certificate base64 : x509CertificateArr) {
            arrayList.add(x509Util.toBase64(base64));
        }
        this.headers.setObjectHeaderValue("x5c", arrayList);
    }

    public String getX509CertSha1ThumbprintHeaderValue() {
        return getHeader("x5t");
    }

    public void setX509CertSha1ThumbprintHeaderValue(String str) {
        setHeader("x5t", str);
    }

    public void setX509CertSha1ThumbprintHeaderValue(X509Certificate x509Certificate) {
        setX509CertSha1ThumbprintHeaderValue(X509Util.x5t(x509Certificate));
    }

    public String getX509CertSha256ThumbprintHeaderValue() {
        return getHeader("x5t#S256");
    }

    public void setX509CertSha256ThumbprintHeaderValue(String str) {
        setHeader("x5t#S256", str);
    }

    public void setX509CertSha256ThumbprintHeaderValue(X509Certificate x509Certificate) {
        setX509CertSha256ThumbprintHeaderValue(X509Util.x5tS256(x509Certificate));
    }

    public Key getKey() {
        return this.key;
    }

    public void setKey(Key key2) {
        boolean z = true;
        Key key3 = this.key;
        if (key2 != null ? key3 == null || !key2.equals(key3) : key3 != null) {
            z = false;
        }
        if (!z) {
            onNewKey();
        }
        this.key = key2;
    }

    /* access modifiers changed from: protected */
    public byte[] getIntegrity() {
        return this.integrity;
    }

    /* access modifiers changed from: protected */
    public void setIntegrity(byte[] bArr) {
        this.integrity = bArr;
    }

    public boolean isDoKeyValidation() {
        return this.doKeyValidation;
    }

    public void setDoKeyValidation(boolean z) {
        this.doKeyValidation = z;
    }

    /* access modifiers changed from: protected */
    public AlgorithmConstraints getAlgorithmConstraints() {
        return this.algorithmConstraints;
    }

    public void setAlgorithmConstraints(AlgorithmConstraints algorithmConstraints2) {
        this.algorithmConstraints = algorithmConstraints2;
    }

    public void setCriticalHeaderNames(String... strArr) {
        this.headers.setObjectHeaderValue(HeaderParameterNames.CRITICAL, strArr);
    }

    public void setKnownCriticalHeaders(String... strArr) {
        this.knownCriticalHeaders = new HashSet(Arrays.asList(strArr));
    }

    /* access modifiers changed from: protected */
    public void checkCrit() throws JoseException {
        List<String> list;
        Object objectHeaderValue = this.headers.getObjectHeaderValue(HeaderParameterNames.CRITICAL);
        if (objectHeaderValue != null) {
            if (objectHeaderValue instanceof List) {
                list = (List) objectHeaderValue;
            } else if (objectHeaderValue instanceof String[]) {
                list = Arrays.asList((String[]) objectHeaderValue);
            } else {
                throw new JoseException("crit header value not an array (" + objectHeaderValue.getClass() + ").");
            }
            for (String str : list) {
                if (!this.knownCriticalHeaders.contains(str) && !isSupportedCriticalHeader(str)) {
                    throw new JoseException("Unrecognized header '" + str + "' marked as critical.");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ProviderContext getProviderCtx() {
        return this.providerCtx;
    }

    public void setProviderContext(ProviderContext providerContext) {
        this.providerCtx = providerContext;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(getHeaders().getFullHeaderAsJsonString());
        if (this.rawCompactSerialization != null) {
            sb.append("->");
            sb.append(this.rawCompactSerialization);
        }
        return sb.toString();
    }
}

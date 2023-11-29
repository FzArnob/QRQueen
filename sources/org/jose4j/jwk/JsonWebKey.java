package org.jose4j.jwk;

import java.io.Serializable;
import java.security.Key;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.HashUtil;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.JsonHelp;
import org.jose4j.lang.StringUtil;

public abstract class JsonWebKey implements Serializable {
    public static final String ALGORITHM_PARAMETER = "alg";
    public static final String KEY_ID_PARAMETER = "kid";
    public static final String KEY_OPERATIONS = "key_ops";
    public static final String KEY_TYPE_PARAMETER = "kty";
    public static final String USE_PARAMETER = "use";
    private String algorithm;
    protected Key key;
    private String keyId;
    private List<String> keyOps;
    protected Map<String, Object> otherParameters;
    private String use;

    public enum OutputControlLevel {
        INCLUDE_PRIVATE,
        INCLUDE_SYMMETRIC,
        PUBLIC_ONLY
    }

    /* access modifiers changed from: protected */
    public abstract void fillTypeSpecificParams(Map<String, Object> map, OutputControlLevel outputControlLevel);

    public abstract String getKeyType();

    /* access modifiers changed from: protected */
    public abstract String produceThumbprintHashInput();

    protected JsonWebKey(Key key2) {
        this.otherParameters = new LinkedHashMap();
        this.key = key2;
    }

    protected JsonWebKey(Map<String, Object> map) throws JoseException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.otherParameters = linkedHashMap;
        linkedHashMap.putAll(map);
        removeFromOtherParams(KEY_TYPE_PARAMETER, USE_PARAMETER, "kid", "alg", KEY_OPERATIONS);
        setUse(getString(map, USE_PARAMETER));
        setKeyId(getString(map, "kid"));
        setAlgorithm(getString(map, "alg"));
        if (map.containsKey(KEY_OPERATIONS)) {
            this.keyOps = JsonHelp.getStringArray(map, KEY_OPERATIONS);
        }
    }

    public PublicKey getPublicKey() {
        try {
            return (PublicKey) this.key;
        } catch (Exception unused) {
            return null;
        }
    }

    public Key getKey() {
        return this.key;
    }

    public String getUse() {
        return this.use;
    }

    public void setUse(String str) {
        this.use = str;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String str) {
        this.algorithm = str;
    }

    public List<String> getKeyOps() {
        return this.keyOps;
    }

    public void setKeyOps(List<String> list) {
        this.keyOps = list;
    }

    public void setOtherParameter(String str, Object obj) {
        this.otherParameters.put(str, obj);
    }

    public <T> T getOtherParameterValue(String str, Class<T> cls) {
        return cls.cast(this.otherParameters.get(str));
    }

    /* access modifiers changed from: protected */
    public void removeFromOtherParams(String... strArr) {
        for (String remove : strArr) {
            this.otherParameters.remove(remove);
        }
    }

    public Map<String, Object> toParams(OutputControlLevel outputControlLevel) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(KEY_TYPE_PARAMETER, getKeyType());
        putIfNotNull("kid", getKeyId(), linkedHashMap);
        putIfNotNull(USE_PARAMETER, getUse(), linkedHashMap);
        putIfNotNull(KEY_OPERATIONS, this.keyOps, linkedHashMap);
        putIfNotNull("alg", getAlgorithm(), linkedHashMap);
        fillTypeSpecificParams(linkedHashMap, outputControlLevel);
        linkedHashMap.putAll(this.otherParameters);
        return linkedHashMap;
    }

    public String toJson() {
        return toJson(OutputControlLevel.INCLUDE_SYMMETRIC);
    }

    public String toJson(OutputControlLevel outputControlLevel) {
        return JsonUtil.toJson(toParams(outputControlLevel));
    }

    public String toString() {
        return getClass().getName() + toParams(OutputControlLevel.PUBLIC_ONLY);
    }

    public String calculateBase64urlEncodedThumbprint(String str) {
        return Base64Url.encode(calculateThumbprint(str));
    }

    public byte[] calculateThumbprint(String str) {
        return HashUtil.getMessageDigest(str).digest(StringUtil.getBytesUtf8(produceThumbprintHashInput()));
    }

    /* access modifiers changed from: protected */
    public void putIfNotNull(String str, Object obj, Map<String, Object> map) {
        if (obj != null) {
            map.put(str, obj);
        }
    }

    protected static String getString(Map<String, Object> map, String str) throws JoseException {
        return JsonHelp.getStringChecked(map, str);
    }

    protected static String getStringRequired(Map<String, Object> map, String str) throws JoseException {
        return getString(map, str, true);
    }

    protected static String getString(Map<String, Object> map, String str, boolean z) throws JoseException {
        String string = getString(map, str);
        if (string != null || !z) {
            return string;
        }
        throw new JoseException("Missing required '" + str + "' parameter.");
    }

    public static class Factory {
        public static JsonWebKey newJwk(Map<String, Object> map) throws JoseException {
            String stringRequired = JsonWebKey.getStringRequired(map, JsonWebKey.KEY_TYPE_PARAMETER);
            stringRequired.hashCode();
            char c = 65535;
            switch (stringRequired.hashCode()) {
                case 2206:
                    if (stringRequired.equals("EC")) {
                        c = 0;
                        break;
                    }
                    break;
                case 81440:
                    if (stringRequired.equals("RSA")) {
                        c = 1;
                        break;
                    }
                    break;
                case 109856:
                    if (stringRequired.equals(OctetSequenceJsonWebKey.KEY_TYPE)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return new EllipticCurveJsonWebKey(map);
                case 1:
                    return new RsaJsonWebKey(map);
                case 2:
                    return new OctetSequenceJsonWebKey(map);
                default:
                    throw new JoseException("Unknown key type algorithm: '" + stringRequired + "'");
            }
        }

        public static JsonWebKey newJwk(Key key) throws JoseException {
            if (RSAPublicKey.class.isInstance(key)) {
                return new RsaJsonWebKey((RSAPublicKey) key);
            }
            if (ECPublicKey.class.isInstance(key)) {
                return new EllipticCurveJsonWebKey((ECPublicKey) key);
            }
            if (!PublicKey.class.isInstance(key)) {
                return new OctetSequenceJsonWebKey(key);
            }
            throw new JoseException("Unsupported or unknown public key " + key);
        }

        public static JsonWebKey newJwk(String str) throws JoseException {
            return newJwk(JsonUtil.parseJson(str));
        }
    }
}

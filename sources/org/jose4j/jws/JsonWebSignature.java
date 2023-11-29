package org.jose4j.jws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmFactoryFactory;
import org.jose4j.jwx.CompactSerializer;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.IntegrityException;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;

public class JsonWebSignature extends JsonWebStructure {
    public static final short COMPACT_SERIALIZATION_PARTS = 3;
    private String encodedPayload;
    private byte[] payloadBytes;
    private String payloadCharEncoding = "UTF-8";
    private Boolean validSignature;

    public JsonWebSignature() {
        if (!Boolean.getBoolean("org.jose4j.jws.default-allow-none")) {
            setAlgorithmConstraints(AlgorithmConstraints.DISALLOW_NONE);
        }
    }

    public void setPayload(String str) {
        this.payloadBytes = StringUtil.getBytesUnchecked(str, this.payloadCharEncoding);
    }

    public byte[] getPayloadBytes() throws JoseException {
        if (verifySignature()) {
            return this.payloadBytes;
        }
        throw new IntegrityException("JWS signature is invalid.");
    }

    public byte[] getUnverifiedPayloadBytes() {
        return this.payloadBytes;
    }

    public void setPayloadBytes(byte[] bArr) {
        this.payloadBytes = bArr;
    }

    /* access modifiers changed from: protected */
    public void setCompactSerializationParts(String[] strArr) throws JoseException {
        if (strArr.length == 3) {
            setEncodedHeader(strArr[0]);
            if (isRfc7797UnencodedPayload()) {
                setPayload(strArr[1]);
            } else {
                setEncodedPayload(strArr[1]);
            }
            setSignature(this.base64url.base64UrlDecode(strArr[2]));
            return;
        }
        throw new JoseException("A JWS Compact Serialization must have exactly 3 parts separated by period ('.') characters");
    }

    public String getCompactSerialization() throws JoseException {
        String str;
        sign();
        if (isRfc7797UnencodedPayload()) {
            str = getStringPayload();
            if (str.contains(".")) {
                throw new JoseException("per https://tools.ietf.org/html/rfc7797#section-5.2 when using the JWS Compact Serialization, unencoded non-detached payloads using period ('.') characters would cause parsing errors; such payloads MUST NOT be used with the JWS Compact Serialization.");
            }
        } else {
            str = getEncodedPayload();
        }
        return CompactSerializer.serialize(getEncodedHeader(), str, getEncodedSignature());
    }

    public String getDetachedContentCompactSerialization() throws JoseException {
        sign();
        return CompactSerializer.serialize(getEncodedHeader(), "", getEncodedSignature());
    }

    public void sign() throws JoseException {
        JsonWebSignatureAlgorithm algorithm = getAlgorithm();
        Key key = getKey();
        if (isDoKeyValidation()) {
            algorithm.validateSigningKey(key);
        }
        setSignature(algorithm.sign(key, getSigningInputBytes(), getProviderCtx()));
    }

    /* access modifiers changed from: protected */
    public void onNewKey() {
        this.validSignature = null;
    }

    public boolean verifySignature() throws JoseException {
        JsonWebSignatureAlgorithm algorithm = getAlgorithm();
        Key key = getKey();
        if (isDoKeyValidation()) {
            algorithm.validateVerificationKey(key);
        }
        if (this.validSignature == null) {
            checkCrit();
            this.validSignature = Boolean.valueOf(algorithm.verifySignature(getSignature(), key, getSigningInputBytes(), getProviderCtx()));
        }
        return this.validSignature.booleanValue();
    }

    /* access modifiers changed from: protected */
    public boolean isSupportedCriticalHeader(String str) {
        return HeaderParameterNames.BASE64URL_ENCODE_PAYLOAD.equals(str);
    }

    public JsonWebSignatureAlgorithm getAlgorithm() throws InvalidAlgorithmException {
        return getAlgorithm(true);
    }

    public JsonWebSignatureAlgorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException {
        return getAlgorithm(false);
    }

    private JsonWebSignatureAlgorithm getAlgorithm(boolean z) throws InvalidAlgorithmException {
        String algorithmHeaderValue = getAlgorithmHeaderValue();
        if (algorithmHeaderValue != null) {
            if (z) {
                getAlgorithmConstraints().checkConstraint(algorithmHeaderValue);
            }
            return AlgorithmFactoryFactory.getInstance().getJwsAlgorithmFactory().getAlgorithm(algorithmHeaderValue);
        }
        throw new InvalidAlgorithmException("Signature algorithm header (alg) not set.");
    }

    private byte[] getSigningInputBytes() throws JoseException {
        if (!isRfc7797UnencodedPayload()) {
            return StringUtil.getBytesAscii(CompactSerializer.serialize(getEncodedHeader(), getEncodedPayload()));
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(StringUtil.getBytesAscii(getEncodedHeader()));
            byteArrayOutputStream.write(46);
            byteArrayOutputStream.write(this.payloadBytes);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new JoseException("This should never happen from a ByteArrayOutputStream", e);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isRfc7797UnencodedPayload() {
        Object objectHeaderValue = this.headers.getObjectHeaderValue(HeaderParameterNames.BASE64URL_ENCODE_PAYLOAD);
        return objectHeaderValue != null && (objectHeaderValue instanceof Boolean) && !((Boolean) objectHeaderValue).booleanValue();
    }

    public String getPayload() throws JoseException {
        if (Boolean.getBoolean("org.jose4j.jws.getPayload-skip-verify") || verifySignature()) {
            return getStringPayload();
        }
        throw new IntegrityException("JWS signature is invalid.");
    }

    public String getUnverifiedPayload() {
        return getStringPayload();
    }

    private String getStringPayload() {
        return StringUtil.newString(this.payloadBytes, this.payloadCharEncoding);
    }

    public String getPayloadCharEncoding() {
        return this.payloadCharEncoding;
    }

    public void setPayloadCharEncoding(String str) {
        this.payloadCharEncoding = str;
    }

    public String getKeyType() throws InvalidAlgorithmException {
        return getAlgorithmNoConstraintCheck().getKeyType();
    }

    public KeyPersuasion getKeyPersuasion() throws InvalidAlgorithmException {
        return getAlgorithmNoConstraintCheck().getKeyPersuasion();
    }

    public void setEncodedPayload(String str) {
        this.encodedPayload = str;
        this.payloadBytes = this.base64url.base64UrlDecode(str);
    }

    public String getEncodedPayload() {
        String str = this.encodedPayload;
        return str != null ? str : this.base64url.base64UrlEncode(this.payloadBytes);
    }

    public String getEncodedSignature() {
        return this.base64url.base64UrlEncode(getSignature());
    }

    /* access modifiers changed from: protected */
    public byte[] getSignature() {
        return getIntegrity();
    }

    /* access modifiers changed from: protected */
    public void setSignature(byte[] bArr) {
        setIntegrity(bArr);
    }
}

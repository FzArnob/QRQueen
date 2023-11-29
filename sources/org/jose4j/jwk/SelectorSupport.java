package org.jose4j.jwk;

import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;

class SelectorSupport {
    private static final String[] DECRYPT_OPS = {KeyOperations.DECRYPT, KeyOperations.DERIVE_KEY, KeyOperations.UNWRAP_KEY};
    private static final String[] VERIFY_OPS = {KeyOperations.VERIFY};

    SelectorSupport() {
    }

    public static SimpleJwkFilter filterForInboundSigned(JsonWebSignature jsonWebSignature) throws JoseException {
        SimpleJwkFilter commonFilterForInbound = commonFilterForInbound(jsonWebSignature);
        commonFilterForInbound.setUse(Use.SIGNATURE, SimpleJwkFilter.OMITTED_OKAY);
        commonFilterForInbound.setKeyOperations(VERIFY_OPS, SimpleJwkFilter.OMITTED_OKAY);
        return commonFilterForInbound;
    }

    public static SimpleJwkFilter filterForInboundEncrypted(JsonWebEncryption jsonWebEncryption) throws JoseException {
        SimpleJwkFilter commonFilterForInbound = commonFilterForInbound(jsonWebEncryption);
        commonFilterForInbound.setUse("enc", SimpleJwkFilter.OMITTED_OKAY);
        commonFilterForInbound.setKeyOperations(DECRYPT_OPS, SimpleJwkFilter.OMITTED_OKAY);
        return commonFilterForInbound;
    }

    private static SimpleJwkFilter commonFilterForInbound(JsonWebStructure jsonWebStructure) throws JoseException {
        SimpleJwkFilter simpleJwkFilter = new SimpleJwkFilter();
        String keyIdHeaderValue = jsonWebStructure.getKeyIdHeaderValue();
        if (keyIdHeaderValue != null) {
            simpleJwkFilter.setKid(keyIdHeaderValue, SimpleJwkFilter.VALUE_REQUIRED);
        }
        String x509CertSha1ThumbprintHeaderValue = jsonWebStructure.getX509CertSha1ThumbprintHeaderValue();
        String x509CertSha256ThumbprintHeaderValue = jsonWebStructure.getX509CertSha256ThumbprintHeaderValue();
        simpleJwkFilter.setAllowFallbackDeriveFromX5cForX5Thumbs(true);
        if (x509CertSha1ThumbprintHeaderValue != null) {
            simpleJwkFilter.setX5t(x509CertSha1ThumbprintHeaderValue, SimpleJwkFilter.OMITTED_OKAY);
        }
        if (x509CertSha256ThumbprintHeaderValue != null) {
            simpleJwkFilter.setX5tS256(x509CertSha256ThumbprintHeaderValue, SimpleJwkFilter.OMITTED_OKAY);
        }
        simpleJwkFilter.setKty(jsonWebStructure.getAlgorithmNoConstraintCheck().getKeyType());
        return simpleJwkFilter;
    }
}

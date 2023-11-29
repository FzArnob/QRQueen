package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;

public class JwksVerificationKeyResolver implements VerificationKeyResolver {
    private boolean disambiguateWithVerifySignature;
    private List<JsonWebKey> jsonWebKeys;
    private VerificationJwkSelector selector = new VerificationJwkSelector();

    public JwksVerificationKeyResolver(List<JsonWebKey> list) {
        this.jsonWebKeys = list;
    }

    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        JsonWebKey jsonWebKey;
        try {
            if (this.disambiguateWithVerifySignature) {
                jsonWebKey = this.selector.selectWithVerifySignatureDisambiguate(jsonWebSignature, this.jsonWebKeys);
            } else {
                jsonWebKey = this.selector.select(jsonWebSignature, this.jsonWebKeys);
            }
            if (jsonWebKey != null) {
                return jsonWebKey.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + this.jsonWebKeys);
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") selecting from keys: " + this.jsonWebKeys, e);
        }
    }

    public void setDisambiguateWithVerifySignature(boolean z) {
        this.disambiguateWithVerifySignature = z;
    }
}

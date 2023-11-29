package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.DecryptionJwkSelector;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;

public class JwksDecryptionKeyResolver implements DecryptionKeyResolver {
    private List<JsonWebKey> jsonWebKeys;
    private DecryptionJwkSelector selector = new DecryptionJwkSelector();

    public JwksDecryptionKeyResolver(List<JsonWebKey> list) {
        this.jsonWebKeys = list;
    }

    public Key resolveKey(JsonWebEncryption jsonWebEncryption, List<JsonWebStructure> list) throws UnresolvableKeyException {
        try {
            JsonWebKey select = this.selector.select(jsonWebEncryption, this.jsonWebKeys);
            if (select != null) {
                return select instanceof PublicJsonWebKey ? ((PublicJsonWebKey) select).getPrivateKey() : select.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable key for JWE w/ header " + jsonWebEncryption.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + this.jsonWebKeys);
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable key for JWE w/ header " + jsonWebEncryption.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") selecting from keys: " + this.jsonWebKeys, e);
        }
    }
}

package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;

public class EmbeddedJwkVerificationKeyResolver implements VerificationKeyResolver {
    private PublicJsonWebKey jwk;

    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        try {
            PublicJsonWebKey jwkHeader = jsonWebSignature.getJwkHeader();
            this.jwk = jwkHeader;
            if (jwkHeader != null) {
                return jwkHeader.getKey();
            }
            throw new UnresolvableKeyException("No jwk in JWS header");
        } catch (JoseException e) {
            throw new UnresolvableKeyException("Problem processing jwk from JWS header", e);
        }
    }

    public PublicJsonWebKey getJwk() {
        return this.jwk;
    }
}

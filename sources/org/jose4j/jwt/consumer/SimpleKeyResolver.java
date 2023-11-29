package org.jose4j.jwt.consumer;

import java.security.Key;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;

class SimpleKeyResolver implements VerificationKeyResolver, DecryptionKeyResolver {
    private Key key;

    SimpleKeyResolver(Key key2) {
        this.key = key2;
    }

    public Key resolveKey(JsonWebEncryption jsonWebEncryption, List<JsonWebStructure> list) {
        return this.key;
    }

    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) {
        return this.key;
    }
}

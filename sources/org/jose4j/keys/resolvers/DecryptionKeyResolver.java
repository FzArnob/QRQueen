package org.jose4j.keys.resolvers;

import java.security.Key;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.UnresolvableKeyException;

public interface DecryptionKeyResolver {
    Key resolveKey(JsonWebEncryption jsonWebEncryption, List<JsonWebStructure> list) throws UnresolvableKeyException;
}

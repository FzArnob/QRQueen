package org.jose4j.jwk;

import java.util.Collection;
import java.util.List;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.lang.JoseException;

public class DecryptionJwkSelector {
    public JsonWebKey select(JsonWebEncryption jsonWebEncryption, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebEncryption, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        return selectList.get(0);
    }

    public List<JsonWebKey> selectList(JsonWebEncryption jsonWebEncryption, Collection<JsonWebKey> collection) throws JoseException {
        return SelectorSupport.filterForInboundEncrypted(jsonWebEncryption).filter(collection);
    }
}

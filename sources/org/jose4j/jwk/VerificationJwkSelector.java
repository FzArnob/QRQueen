package org.jose4j.jwk;

import java.util.Collection;
import java.util.List;
import org.jose4j.jws.EcdsaUsingShaAlgorithm;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

public class VerificationJwkSelector {
    public JsonWebKey select(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebSignature, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        return selectList.get(0);
    }

    public List<JsonWebKey> selectList(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        SimpleJwkFilter filterForInboundSigned = SelectorSupport.filterForInboundSigned(jsonWebSignature);
        List<JsonWebKey> filter = filterForInboundSigned.filter(collection);
        if (hasMoreThanOne(filter)) {
            filterForInboundSigned.setAlg(jsonWebSignature.getAlgorithmHeaderValue(), SimpleJwkFilter.OMITTED_OKAY);
            filter = filterForInboundSigned.filter(filter);
        }
        if (!hasMoreThanOne(filter) || !"EC".equals(jsonWebSignature.getKeyType())) {
            return filter;
        }
        filterForInboundSigned.setCrv(((EcdsaUsingShaAlgorithm) jsonWebSignature.getAlgorithmNoConstraintCheck()).getCurveName(), SimpleJwkFilter.OMITTED_OKAY);
        return filterForInboundSigned.filter(filter);
    }

    public JsonWebKey selectWithVerifySignatureDisambiguate(JsonWebSignature jsonWebSignature, Collection<JsonWebKey> collection) throws JoseException {
        List<JsonWebKey> selectList = selectList(jsonWebSignature, collection);
        if (selectList.isEmpty()) {
            return null;
        }
        if (selectList.size() == 1) {
            return selectList.get(0);
        }
        for (JsonWebKey next : selectList) {
            jsonWebSignature.setKey(next.getKey());
            if (jsonWebSignature.verifySignature()) {
                return next;
            }
        }
        return null;
    }

    private boolean hasMoreThanOne(List<JsonWebKey> list) {
        return list.size() > 1;
    }
}

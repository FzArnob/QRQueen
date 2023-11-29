package org.jose4j.keys.resolvers;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UnresolvableKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsJwksVerificationKeyResolver implements VerificationKeyResolver {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpsJwksVerificationKeyResolver.class);
    private boolean disambiguateWithVerifySignature;
    private HttpsJwks httpsJkws;
    private VerificationJwkSelector verificationJwkSelector = new VerificationJwkSelector();

    public HttpsJwksVerificationKeyResolver(HttpsJwks httpsJwks) {
        this.httpsJkws = httpsJwks;
    }

    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        try {
            List<JsonWebKey> jsonWebKeys = this.httpsJkws.getJsonWebKeys();
            JsonWebKey select = select(jsonWebSignature, jsonWebKeys);
            if (select == null) {
                log.debug("Refreshing JWKs from {} as no suitable verification key for JWS w/ header {} was found in {}", this.httpsJkws.getLocation(), jsonWebSignature.getHeaders().getFullHeaderAsJsonString(), jsonWebKeys);
                this.httpsJkws.refresh();
                jsonWebKeys = this.httpsJkws.getJsonWebKeys();
                select = select(jsonWebSignature, jsonWebKeys);
            }
            if (select != null) {
                return select.getKey();
            }
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " from JWKs " + jsonWebKeys + " obtained from " + this.httpsJkws.getLocation());
        } catch (IOException | JoseException e) {
            throw new UnresolvableKeyException("Unable to find a suitable verification key for JWS w/ header " + jsonWebSignature.getHeaders().getFullHeaderAsJsonString() + " due to an unexpected exception (" + e + ") while obtaining or using keys from JWKS endpoint at " + this.httpsJkws.getLocation(), e);
        }
    }

    /* access modifiers changed from: protected */
    public JsonWebKey select(JsonWebSignature jsonWebSignature, List<JsonWebKey> list) throws JoseException {
        if (this.disambiguateWithVerifySignature) {
            return this.verificationJwkSelector.selectWithVerifySignatureDisambiguate(jsonWebSignature, list);
        }
        return this.verificationJwkSelector.select(jsonWebSignature, list);
    }

    public void setDisambiguateWithVerifySignature(boolean z) {
        this.disambiguateWithVerifySignature = z;
    }
}

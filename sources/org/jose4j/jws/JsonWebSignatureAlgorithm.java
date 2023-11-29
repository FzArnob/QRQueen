package org.jose4j.jws;

import java.security.Key;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.Algorithm;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;

public interface JsonWebSignatureAlgorithm extends Algorithm {
    byte[] sign(Key key, byte[] bArr, ProviderContext providerContext) throws JoseException;

    void validateSigningKey(Key key) throws InvalidKeyException;

    void validateVerificationKey(Key key) throws InvalidKeyException;

    boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException;
}

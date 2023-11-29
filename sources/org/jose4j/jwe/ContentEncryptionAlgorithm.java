package org.jose4j.jwe;

import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.Algorithm;
import org.jose4j.jwx.Headers;
import org.jose4j.lang.JoseException;

public interface ContentEncryptionAlgorithm extends Algorithm {
    byte[] decrypt(ContentEncryptionParts contentEncryptionParts, byte[] bArr, byte[] bArr2, Headers headers, ProviderContext providerContext) throws JoseException;

    ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, Headers headers, byte[] bArr4, ProviderContext providerContext) throws JoseException;

    ContentEncryptionKeyDescriptor getContentEncryptionKeyDescriptor();
}

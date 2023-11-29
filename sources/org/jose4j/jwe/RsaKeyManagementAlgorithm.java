package org.jose4j.jwe;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.HashUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;

public class RsaKeyManagementAlgorithm extends WrappingKeyManagementAlgorithm implements KeyManagementAlgorithm {
    public RsaKeyManagementAlgorithm(String str, String str2) {
        super(str, str2);
        setKeyType("RSA");
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
    }

    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize((PublicKey) KeyValidationSupport.castKey(key, PublicKey.class));
    }

    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize((PrivateKey) KeyValidationSupport.castKey(key, RSAPrivateKey.class));
    }

    public boolean isAvailable() {
        try {
            return CipherUtil.getCipher(getJavaAlgorithm(), (String) null) != null;
        } catch (JoseException unused) {
            return false;
        }
    }

    public static class RsaOaep extends RsaKeyManagementAlgorithm implements KeyManagementAlgorithm {
        public RsaOaep() {
            super("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        }
    }

    public static class RsaOaep256 extends RsaKeyManagementAlgorithm implements KeyManagementAlgorithm {
        public RsaOaep256() {
            super("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            setAlgorithmParameterSpec(new OAEPParameterSpec(HashUtil.SHA_256, "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        }

        public boolean isAvailable() {
            try {
                JsonWebKey newJwk = JsonWebKey.Factory.newJwk("{\"kty\":\"RSA\",\"n\":\"sXchDaQebHnPiGvyDOAT4saGEUetSyo9MKLOoWFsueri23bOdgWp4Dy1WlUzewbgBHod5pcM9H95GQRV3JDXboIRROSBigeC5yjU1hGzHHyXss8UDprecbAYxknTcQkhslANGRUZmdTOQ5qTRsLAt6BTYuyvVRdhS8exSZEy_c4gs_7svlJJQ4H9_NxsiIoLwAEk7-Q3UXERGYw_75IDrGA84-lA_-Ct4eTlXHBIY2EaV7t7LjJaynVJCpkv4LKjTTAumiGUIuQhrNhZLuF_RJLqHpM2kgWFLU7-VTdL1VbC2tejvcI2BlMkEpk1BzBZI0KQB0GaDWFLN-aEAw3vRw\",\"e\":\"AQAB\"}");
                if (manageForEncrypt(newJwk.getKey(), new ContentEncryptionKeyDescriptor(16, AesKey.ALGORITHM), (Headers) null, (byte[]) null, new ProviderContext()) != null) {
                    return true;
                }
                return false;
            } catch (JoseException e) {
                Logger logger = this.log;
                logger.debug(getAlgorithmIdentifier() + " is not available due to " + ExceptionHelp.toStringWithCauses(e));
                return false;
            }
        }
    }

    public static class Rsa1_5 extends RsaKeyManagementAlgorithm implements KeyManagementAlgorithm {
        public Rsa1_5() {
            super("RSA/ECB/PKCS1Padding", KeyManagementAlgorithmIdentifiers.RSA1_5);
        }
    }
}

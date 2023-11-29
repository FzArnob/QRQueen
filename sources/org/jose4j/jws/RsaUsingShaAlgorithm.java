package org.jose4j.jws;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.lang.InvalidKeyException;

public class RsaUsingShaAlgorithm extends BaseSignatureAlgorithm implements JsonWebSignatureAlgorithm {
    static final String MGF1 = "MGF1";
    public static final String RSASSA_PSS = "RSASSA-PSS";
    static final int TRAILER = 1;

    public RsaUsingShaAlgorithm(String str, String str2) {
        super(str, str2, "RSA");
    }

    public void validatePublicKey(PublicKey publicKey) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize(publicKey);
    }

    public void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException {
        KeyValidationSupport.checkRsaKeySize(privateKey);
    }

    public static class RsaPssSha256 extends RsaUsingShaAlgorithm {
        public RsaPssSha256() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA256, choosePssAlgorithmName("SHA256withRSAandMGF1"));
            MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA256;
            setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), RsaUsingShaAlgorithm.MGF1, mGF1ParameterSpec, 32, 1));
        }
    }

    public static class RsaPssSha384 extends RsaUsingShaAlgorithm {
        public RsaPssSha384() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA384, choosePssAlgorithmName("SHA384withRSAandMGF1"));
            MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA384;
            setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), RsaUsingShaAlgorithm.MGF1, mGF1ParameterSpec, 48, 1));
        }
    }

    public static class RsaPssSha512 extends RsaUsingShaAlgorithm {
        public RsaPssSha512() {
            super(AlgorithmIdentifiers.RSA_PSS_USING_SHA512, choosePssAlgorithmName("SHA512withRSAandMGF1"));
            MGF1ParameterSpec mGF1ParameterSpec = MGF1ParameterSpec.SHA512;
            setAlgorithmParameterSpec(new PSSParameterSpec(mGF1ParameterSpec.getDigestAlgorithm(), RsaUsingShaAlgorithm.MGF1, mGF1ParameterSpec, 64, 1));
        }
    }

    public static class RsaSha256 extends RsaUsingShaAlgorithm {
        public RsaSha256() {
            super(AlgorithmIdentifiers.RSA_USING_SHA256, "SHA256withRSA");
        }
    }

    public static class RsaSha384 extends RsaUsingShaAlgorithm {
        public RsaSha384() {
            super(AlgorithmIdentifiers.RSA_USING_SHA384, "SHA384withRSA");
        }
    }

    public static class RsaSha512 extends RsaUsingShaAlgorithm {
        public RsaSha512() {
            super(AlgorithmIdentifiers.RSA_USING_SHA512, "SHA512withRSA");
        }
    }

    static String choosePssAlgorithmName(String str) {
        for (String next : Security.getAlgorithms("Signature")) {
            if (RSASSA_PSS.equalsIgnoreCase(next)) {
                return next;
            }
        }
        return str;
    }
}

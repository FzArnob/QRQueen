package org.jose4j.jca;

import java.security.SecureRandom;

public class ProviderContext {
    private Context generalProviderContext = new Context();
    private SecureRandom secureRandom;
    private Context suppliedKeyProviderContext = new Context();

    public Context getSuppliedKeyProviderContext() {
        return this.suppliedKeyProviderContext;
    }

    public Context getGeneralProviderContext() {
        return this.generalProviderContext;
    }

    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public void setSecureRandom(SecureRandom secureRandom2) {
        this.secureRandom = secureRandom2;
    }

    public class Context {
        private String cipherProvider;
        private String generalProvider;
        private String keyAgreementProvider;
        private String keyFactoryProvider;
        private String keyPairGeneratorProvider;
        private String macProvider;
        private String messageDigestProvider;
        private String signatureProvider;

        public Context() {
        }

        public String getGeneralProvider() {
            return this.generalProvider;
        }

        public void setGeneralProvider(String str) {
            this.generalProvider = str;
        }

        public String getKeyPairGeneratorProvider() {
            return select(this.keyPairGeneratorProvider);
        }

        public void setKeyPairGeneratorProvider(String str) {
            this.keyPairGeneratorProvider = str;
        }

        public String getKeyAgreementProvider() {
            return select(this.keyAgreementProvider);
        }

        public void setKeyAgreementProvider(String str) {
            this.keyAgreementProvider = str;
        }

        public String getCipherProvider() {
            return select(this.cipherProvider);
        }

        public void setCipherProvider(String str) {
            this.cipherProvider = str;
        }

        public String getSignatureProvider() {
            return select(this.signatureProvider);
        }

        public void setSignatureProvider(String str) {
            this.signatureProvider = str;
        }

        public String getMacProvider() {
            return select(this.macProvider);
        }

        public void setMacProvider(String str) {
            this.macProvider = str;
        }

        public String getMessageDigestProvider() {
            return select(this.messageDigestProvider);
        }

        public void setMessageDigestProvider(String str) {
            this.messageDigestProvider = str;
        }

        public String getKeyFactoryProvider() {
            return select(this.keyFactoryProvider);
        }

        public void setKeyFactoryProvider(String str) {
            this.keyFactoryProvider = str;
        }

        private String select(String str) {
            return str == null ? this.generalProvider : str;
        }
    }
}

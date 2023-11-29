package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.CertificateExpiredException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class CryptoUtils {
    static final ICryptoFactory DEFAULT_CRYPTO_FACTORY = new ICryptoFactory() {
        public IKeyGenerator getKeyGenerator(String str, String str2) throws Exception {
            final KeyGenerator instance = KeyGenerator.getInstance(str, str2);
            return new IKeyGenerator() {
                public void init(AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
                    instance.init(algorithmParameterSpec);
                }

                public void generateKey() {
                    instance.generateKey();
                }
            };
        }

        public ICipher getCipher(String str, String str2) throws Exception {
            final Cipher cipher;
            if (str2 != null) {
                cipher = Cipher.getInstance(str, str2);
            } else {
                cipher = Cipher.getInstance(str);
            }
            return new ICipher() {
                public void init(int i, Key key) throws Exception {
                    cipher.init(i, key);
                }

                public void init(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
                    cipher.init(i, key, algorithmParameterSpec);
                }

                public byte[] doFinal(byte[] bArr) throws Exception {
                    return cipher.doFinal(bArr);
                }

                public byte[] doFinal(byte[] bArr, int i, int i2) throws Exception {
                    return cipher.doFinal(bArr, i, i2);
                }

                public byte[] getIV() {
                    return cipher.getIV();
                }

                public int getBlockSize() {
                    return cipher.getBlockSize();
                }

                public String getAlgorithm() {
                    return cipher.getAlgorithm();
                }

                public String getProvider() {
                    return cipher.getProvider().getName();
                }
            };
        }
    };
    private static final String M_KEY_EXPIRED_EXCEPTION = "android.security.keystore.KeyExpiredException";
    private static CryptoUtils sInstance;
    private final int mApiLevel;
    private final Context mContext;
    private final ICryptoFactory mCryptoFactory;
    private final Map<String, CryptoHandlerEntry> mCryptoHandlers;
    private final KeyStore mKeyStore;

    interface ICipher {
        byte[] doFinal(byte[] bArr) throws Exception;

        byte[] doFinal(byte[] bArr, int i, int i2) throws Exception;

        String getAlgorithm();

        int getBlockSize();

        byte[] getIV();

        String getProvider();

        void init(int i, Key key) throws Exception;

        void init(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec) throws Exception;
    }

    interface ICryptoFactory {
        ICipher getCipher(String str, String str2) throws Exception;

        IKeyGenerator getKeyGenerator(String str, String str2) throws Exception;
    }

    interface IKeyGenerator {
        void generateKey();

        void init(AlgorithmParameterSpec algorithmParameterSpec) throws Exception;
    }

    private CryptoUtils(Context context) {
        this(context, DEFAULT_CRYPTO_FACTORY, Build.VERSION.SDK_INT);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048 A[SYNTHETIC, Splitter:B:18:0x0048] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    CryptoUtils(android.content.Context r3, com.microsoft.appcenter.utils.crypto.CryptoUtils.ICryptoFactory r4, int r5) {
        /*
            r2 = this;
            java.lang.String r0 = "AppCenter"
            r2.<init>()
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            r2.mCryptoHandlers = r1
            android.content.Context r3 = r3.getApplicationContext()
            r2.mContext = r3
            r2.mCryptoFactory = r4
            r2.mApiLevel = r5
            r3 = 0
            java.lang.String r4 = "AndroidKeyStore"
            java.security.KeyStore r4 = java.security.KeyStore.getInstance(r4)     // Catch:{ Exception -> 0x0022 }
            r4.load(r3)     // Catch:{ Exception -> 0x0021 }
            goto L_0x0028
        L_0x0021:
            r3 = r4
        L_0x0022:
            java.lang.String r4 = "Cannot use secure keystore on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r0, r4)
            r4 = r3
        L_0x0028:
            r2.mKeyStore = r4
            if (r4 == 0) goto L_0x0046
            r3 = 23
            if (r5 < r3) goto L_0x0046
            com.microsoft.appcenter.utils.crypto.CryptoAesAndEtmHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoAesAndEtmHandler     // Catch:{ Exception -> 0x0041 }
            r3.<init>()     // Catch:{ Exception -> 0x0041 }
            r2.registerHandler(r3)     // Catch:{ Exception -> 0x0041 }
            com.microsoft.appcenter.utils.crypto.CryptoAesHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoAesHandler     // Catch:{ Exception -> 0x0041 }
            r3.<init>()     // Catch:{ Exception -> 0x0041 }
            r2.registerHandler(r3)     // Catch:{ Exception -> 0x0041 }
            goto L_0x0046
        L_0x0041:
            java.lang.String r3 = "Cannot use modern encryption on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r0, r3)
        L_0x0046:
            if (r4 == 0) goto L_0x0056
            com.microsoft.appcenter.utils.crypto.CryptoRsaHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoRsaHandler     // Catch:{ Exception -> 0x0051 }
            r3.<init>()     // Catch:{ Exception -> 0x0051 }
            r2.registerHandler(r3)     // Catch:{ Exception -> 0x0051 }
            goto L_0x0056
        L_0x0051:
            java.lang.String r3 = "Cannot use old encryption on this device."
            com.microsoft.appcenter.utils.AppCenterLog.error(r0, r3)
        L_0x0056:
            com.microsoft.appcenter.utils.crypto.CryptoNoOpHandler r3 = new com.microsoft.appcenter.utils.crypto.CryptoNoOpHandler
            r3.<init>()
            java.util.Map<java.lang.String, com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry> r4 = r2.mCryptoHandlers
            java.lang.String r5 = r3.getAlgorithm()
            com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry r0 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry
            r1 = 0
            r0.<init>(r1, r3)
            r4.put(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.crypto.CryptoUtils.<init>(android.content.Context, com.microsoft.appcenter.utils.crypto.CryptoUtils$ICryptoFactory, int):void");
    }

    public static CryptoUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CryptoUtils(context);
        }
        return sInstance;
    }

    /* access modifiers changed from: package-private */
    public ICryptoFactory getCryptoFactory() {
        return this.mCryptoFactory;
    }

    private void registerHandler(CryptoHandler cryptoHandler) throws Exception {
        int i = 0;
        String alias = getAlias(cryptoHandler, 0);
        String alias2 = getAlias(cryptoHandler, 1);
        Date creationDate = this.mKeyStore.getCreationDate(alias);
        Date creationDate2 = this.mKeyStore.getCreationDate(alias2);
        if (creationDate2 != null && creationDate2.after(creationDate)) {
            alias = alias2;
            i = 1;
        }
        if (this.mCryptoHandlers.isEmpty() && !this.mKeyStore.containsAlias(alias)) {
            AppCenterLog.debug("AppCenter", "Creating alias: " + alias);
            cryptoHandler.generateKey(this.mCryptoFactory, alias, this.mContext);
        }
        AppCenterLog.debug("AppCenter", "Using " + alias);
        this.mCryptoHandlers.put(cryptoHandler.getAlgorithm(), new CryptoHandlerEntry(i, cryptoHandler));
    }

    private String getAlias(CryptoHandler cryptoHandler, int i) {
        return "appcenter." + i + "." + cryptoHandler.getAlgorithm();
    }

    private KeyStore.Entry getKeyStoreEntry(CryptoHandlerEntry cryptoHandlerEntry) throws Exception {
        return getKeyStoreEntry(cryptoHandlerEntry.mCryptoHandler, cryptoHandlerEntry.mAliasIndex);
    }

    private KeyStore.Entry getKeyStoreEntry(CryptoHandler cryptoHandler, int i) throws Exception {
        if (this.mKeyStore == null) {
            return null;
        }
        return this.mKeyStore.getEntry(getAlias(cryptoHandler, i), (KeyStore.ProtectionParameter) null);
    }

    public String encrypt(String str) {
        CryptoHandlerEntry next;
        CryptoHandler cryptoHandler;
        if (str == null) {
            return null;
        }
        try {
            next = this.mCryptoHandlers.values().iterator().next();
            cryptoHandler = next.mCryptoHandler;
            String encodeToString = Base64.encodeToString(cryptoHandler.encrypt(this.mCryptoFactory, this.mApiLevel, getKeyStoreEntry(next), str.getBytes("UTF-8")), 0);
            return cryptoHandler.getAlgorithm() + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + encodeToString;
        } catch (InvalidKeyException e) {
            if (!(e.getCause() instanceof CertificateExpiredException)) {
                if (!M_KEY_EXPIRED_EXCEPTION.equals(e.getClass().getName())) {
                    throw e;
                }
            }
            AppCenterLog.debug("AppCenter", "Alias expired: " + next.mAliasIndex);
            next.mAliasIndex = next.mAliasIndex ^ 1;
            String alias = getAlias(cryptoHandler, next.mAliasIndex);
            if (this.mKeyStore.containsAlias(alias)) {
                AppCenterLog.debug("AppCenter", "Deleting alias: " + alias);
                this.mKeyStore.deleteEntry(alias);
            }
            AppCenterLog.debug("AppCenter", "Creating alias: " + alias);
            cryptoHandler.generateKey(this.mCryptoFactory, alias, this.mContext);
            return encrypt(str);
        } catch (Exception unused) {
            AppCenterLog.error("AppCenter", "Failed to encrypt data.");
            return str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        return getDecryptedData(r3, r2.mAliasIndex ^ 1, r1[1]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        com.microsoft.appcenter.utils.AppCenterLog.error("AppCenter", "Failed to decrypt data.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        return new com.microsoft.appcenter.utils.crypto.CryptoUtils.DecryptedData(r10, (java.lang.String) null);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.microsoft.appcenter.utils.crypto.CryptoUtils.DecryptedData decrypt(java.lang.String r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0009
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r10.<init>(r0, r0)
            return r10
        L_0x0009:
            java.lang.String r1 = ":"
            java.lang.String[] r1 = r10.split(r1)
            int r2 = r1.length
            r3 = 2
            if (r2 != r3) goto L_0x001f
            java.util.Map<java.lang.String, com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry> r2 = r9.mCryptoHandlers
            r3 = 0
            r3 = r1[r3]
            java.lang.Object r2 = r2.get(r3)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$CryptoHandlerEntry r2 = (com.microsoft.appcenter.utils.crypto.CryptoUtils.CryptoHandlerEntry) r2
            goto L_0x0020
        L_0x001f:
            r2 = r0
        L_0x0020:
            if (r2 != 0) goto L_0x0024
            r3 = r0
            goto L_0x0026
        L_0x0024:
            com.microsoft.appcenter.utils.crypto.CryptoHandler r3 = r2.mCryptoHandler
        L_0x0026:
            java.lang.String r4 = "Failed to decrypt data."
            java.lang.String r5 = "AppCenter"
            if (r3 != 0) goto L_0x0035
            com.microsoft.appcenter.utils.AppCenterLog.error(r5, r4)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r1 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r1.<init>(r10, r0)
            return r1
        L_0x0035:
            r6 = 1
            int r7 = r2.mAliasIndex     // Catch:{ Exception -> 0x003f }
            r8 = r1[r6]     // Catch:{ Exception -> 0x003f }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = r9.getDecryptedData(r3, r7, r8)     // Catch:{ Exception -> 0x003f }
            return r10
        L_0x003f:
            int r2 = r2.mAliasIndex     // Catch:{ Exception -> 0x0049 }
            r2 = r2 ^ r6
            r1 = r1[r6]     // Catch:{ Exception -> 0x0049 }
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r10 = r9.getDecryptedData(r3, r2, r1)     // Catch:{ Exception -> 0x0049 }
            return r10
        L_0x0049:
            com.microsoft.appcenter.utils.AppCenterLog.error(r5, r4)
            com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData r1 = new com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData
            r1.<init>(r10, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.utils.crypto.CryptoUtils.decrypt(java.lang.String):com.microsoft.appcenter.utils.crypto.CryptoUtils$DecryptedData");
    }

    private DecryptedData getDecryptedData(CryptoHandler cryptoHandler, int i, String str) throws Exception {
        String str2 = new String(cryptoHandler.decrypt(this.mCryptoFactory, this.mApiLevel, getKeyStoreEntry(cryptoHandler, i), Base64.decode(str, 0)), "UTF-8");
        return new DecryptedData(str2, cryptoHandler != this.mCryptoHandlers.values().iterator().next().mCryptoHandler ? encrypt(str2) : null);
    }

    static class CryptoHandlerEntry {
        int mAliasIndex;
        final CryptoHandler mCryptoHandler;

        CryptoHandlerEntry(int i, CryptoHandler cryptoHandler) {
            this.mAliasIndex = i;
            this.mCryptoHandler = cryptoHandler;
        }
    }

    public static class DecryptedData {
        final String mDecryptedData;
        final String mNewEncryptedData;

        public DecryptedData(String str, String str2) {
            this.mDecryptedData = str;
            this.mNewEncryptedData = str2;
        }

        public String getDecryptedData() {
            return this.mDecryptedData;
        }

        public String getNewEncryptedData() {
            return this.mNewEncryptedData;
        }
    }
}

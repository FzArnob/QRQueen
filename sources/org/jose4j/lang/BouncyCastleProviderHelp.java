package org.jose4j.lang;

import java.security.Provider;
import java.security.Security;

public class BouncyCastleProviderHelp {
    private static final String BC_PROVIDER_FQCN = "org.bouncycastle.jce.provider.BouncyCastleProvider";

    public static boolean enableBouncyCastleProvider() {
        try {
            Class<?> cls = Class.forName(BC_PROVIDER_FQCN);
            for (Provider isInstance : Security.getProviders()) {
                if (cls.isInstance(isInstance)) {
                    return true;
                }
            }
            Security.addProvider((Provider) cls.newInstance());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}

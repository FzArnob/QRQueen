package com.microsoft.appcenter.analytics;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.HashUtils;
import com.microsoft.appcenter.utils.TicketCache;
import java.util.Date;
import org.jose4j.jwk.RsaJsonWebKey;

public class AuthenticationProvider {
    private static final long REFRESH_THRESHOLD = 600000;
    private AuthenticationCallback mCallback;
    private Date mExpiryDate;
    private final String mTicketKey;
    private final String mTicketKeyHash;
    private final TokenProvider mTokenProvider;
    private final Type mType;

    public interface AuthenticationCallback {
        void onAuthenticationResult(String str, Date date);
    }

    public interface TokenProvider {
        void acquireToken(String str, AuthenticationCallback authenticationCallback);
    }

    public AuthenticationProvider(Type type, String str, TokenProvider tokenProvider) {
        String str2;
        this.mType = type;
        this.mTicketKey = str;
        if (str == null) {
            str2 = null;
        } else {
            str2 = HashUtils.sha256(str);
        }
        this.mTicketKeyHash = str2;
        this.mTokenProvider = tokenProvider;
    }

    /* access modifiers changed from: package-private */
    public Type getType() {
        return this.mType;
    }

    /* access modifiers changed from: package-private */
    public String getTicketKey() {
        return this.mTicketKey;
    }

    /* access modifiers changed from: package-private */
    public String getTicketKeyHash() {
        return this.mTicketKeyHash;
    }

    /* access modifiers changed from: package-private */
    public TokenProvider getTokenProvider() {
        return this.mTokenProvider;
    }

    /* access modifiers changed from: package-private */
    public synchronized void acquireTokenAsync() {
        if (this.mCallback == null) {
            AppCenterLog.debug(Analytics.LOG_TAG, "Calling token provider=" + this.mType + " callback.");
            AnonymousClass1 r0 = new AuthenticationCallback() {
                public void onAuthenticationResult(String str, Date date) {
                    AuthenticationProvider.this.handleTokenUpdate(str, date, this);
                }
            };
            this.mCallback = r0;
            this.mTokenProvider.acquireToken(this.mTicketKey, r0);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void handleTokenUpdate(String str, Date date, AuthenticationCallback authenticationCallback) {
        if (this.mCallback != authenticationCallback) {
            AppCenterLog.debug(Analytics.LOG_TAG, "Ignore duplicate authentication callback calls, provider=" + this.mType);
            return;
        }
        this.mCallback = null;
        AppCenterLog.debug(Analytics.LOG_TAG, "Got result back from token provider=" + this.mType);
        if (str == null) {
            AppCenterLog.error(Analytics.LOG_TAG, "Authentication failed for ticketKey=" + this.mTicketKey);
        } else if (date == null) {
            AppCenterLog.error(Analytics.LOG_TAG, "No expiry date provided for ticketKey=" + this.mTicketKey);
        } else {
            String str2 = this.mTicketKeyHash;
            TicketCache.putTicket(str2, this.mType.mTokenPrefix + str);
            this.mExpiryDate = date;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void checkTokenExpiry() {
        Date date = this.mExpiryDate;
        if (date != null && date.getTime() <= System.currentTimeMillis() + REFRESH_THRESHOLD) {
            acquireTokenAsync();
        }
    }

    public enum Type {
        MSA_COMPACT(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME),
        MSA_DELEGATE("d");
        
        /* access modifiers changed from: private */
        public final String mTokenPrefix;

        private Type(String str) {
            this.mTokenPrefix = str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR;
        }
    }
}

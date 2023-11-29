package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.pollfish.classes.SurveyInfo;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishCompletedSurveyListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishReceivedSurveyListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.interfaces.PollfishUserRejectedSurveyListener;
import com.pollfish.main.PollFish;

@DesignerComponent(category = ComponentCategory.MONETIZATION_GENERAL, iconName = "images/pollfish.png", nonVisible = true, version = 2)
@UsesLibraries({"pollfish.jar", "pollfish.aar", "play-services-base.jar", "play-services-base.aar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public final class KodularPollfish extends AndroidNonvisibleComponent implements Component, OnResumeListener, PollfishClosedListener, PollfishCompletedSurveyListener, PollfishOpenedListener, PollfishReceivedSurveyListener, PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener, PollfishUserRejectedSurveyListener {
    private ComponentContainer container;
    private String o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = "";
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = "";
    private boolean testMode = true;
    private String xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = ComponentConstants.DEFAULT_POLLFISH_MODE;

    public KodularPollfish(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.container = componentContainer;
        String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.form.getApplicationContext());
        TestMode(true);
        SurveyMode(this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6);
        RequestUUID(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    @SimpleFunction(description = "Show Survey")
    public final void ShowSurvey() {
        PollFish.show();
    }

    @SimpleProperty(description = "Returns value of Pollfish API Key")
    public final String APIKey() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public final void APIKey(final String str) {
        if (!this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp.equals(str)) {
            if (!(this.form instanceof ReplForm) && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME().getString(str, (String) null) == null) {
                new Thread(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ba  */
                    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c1  */
                    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final void run() {
                        /*
                            r7 = this;
                            r0 = 0
                            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.lang.String r3 = "https://api.creator.kodular.io/pollfish/v1?appid="
                            r2.<init>(r3)     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.lang.String r3 = r3     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            r2.append(r3)     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            r1.<init>(r2)     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x009f, all -> 0x009a }
                            java.lang.String r0 = "PUT"
                            r1.setRequestMethod(r0)     // Catch:{ Exception -> 0x0098 }
                            int r0 = r1.getResponseCode()     // Catch:{ Exception -> 0x0098 }
                            r2 = 200(0xc8, float:2.8E-43)
                            if (r0 != r2) goto L_0x0092
                            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0098 }
                            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0098 }
                            java.io.InputStream r3 = r1.getInputStream()     // Catch:{ Exception -> 0x0098 }
                            r2.<init>(r3)     // Catch:{ Exception -> 0x0098 }
                            r0.<init>(r2)     // Catch:{ Exception -> 0x0098 }
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0098 }
                            r2.<init>()     // Catch:{ Exception -> 0x0098 }
                        L_0x003c:
                            java.lang.String r3 = r0.readLine()     // Catch:{ Exception -> 0x0098 }
                            if (r3 == 0) goto L_0x0046
                            r2.append(r3)     // Catch:{ Exception -> 0x0098 }
                            goto L_0x003c
                        L_0x0046:
                            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0098 }
                            r0.<init>(r2)     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r2 = "success"
                            boolean r2 = r0.getBoolean(r2)     // Catch:{ Exception -> 0x0098 }
                            if (r2 == 0) goto L_0x0092
                            java.lang.String r2 = "data"
                            org.json.JSONObject r0 = r0.getJSONObject(r2)     // Catch:{ Exception -> 0x0098 }
                            if (r0 == 0) goto L_0x0092
                            java.lang.String r2 = "status"
                            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r3 = "message"
                            r0.getString(r3)     // Catch:{ Exception -> 0x0098 }
                            com.google.appinventor.components.runtime.KodularPollfish r0 = com.google.appinventor.components.runtime.KodularPollfish.this     // Catch:{ Exception -> 0x0098 }
                            android.content.SharedPreferences r0 = com.google.appinventor.components.runtime.KodularPollfish.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.KodularPollfish) r0)     // Catch:{ Exception -> 0x0098 }
                            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x0098 }
                            com.google.appinventor.components.runtime.KodularPollfish r3 = com.google.appinventor.components.runtime.KodularPollfish.this     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r3 = com.google.appinventor.components.runtime.KodularPollfish.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.KodularPollfish) r3)     // Catch:{ Exception -> 0x0098 }
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0098 }
                            r4.<init>()     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r5 = "ok"
                            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x0098 }
                            r4.append(r2)     // Catch:{ Exception -> 0x0098 }
                            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0098 }
                            r0.putString(r3, r2)     // Catch:{ Exception -> 0x0098 }
                            r0.commit()     // Catch:{ Exception -> 0x0098 }
                        L_0x0092:
                            if (r1 == 0) goto L_0x00bd
                            r1.disconnect()
                            return
                        L_0x0098:
                            r0 = move-exception
                            goto L_0x00a3
                        L_0x009a:
                            r1 = move-exception
                            r6 = r1
                            r1 = r0
                            r0 = r6
                            goto L_0x00bf
                        L_0x009f:
                            r1 = move-exception
                            r6 = r1
                            r1 = r0
                            r0 = r6
                        L_0x00a3:
                            java.lang.String r2 = "Pollfish"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
                            r3.<init>()     // Catch:{ all -> 0x00be }
                            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00be }
                            r3.append(r0)     // Catch:{ all -> 0x00be }
                            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x00be }
                            android.util.Log.e(r2, r0)     // Catch:{ all -> 0x00be }
                            if (r1 == 0) goto L_0x00bd
                            r1.disconnect()
                        L_0x00bd:
                            return
                        L_0x00be:
                            r0 = move-exception
                        L_0x00bf:
                            if (r1 == 0) goto L_0x00c4
                            r1.disconnect()
                        L_0x00c4:
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularPollfish.AnonymousClass1.run():void");
                    }
                }).start();
            }
            this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
        }
    }

    private SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        return this.container.$context().getSharedPreferences(ComponentConstants.KODULAR_PREF, 0);
    }

    @DesignerProperty(defaultValue = "Single Survey", editorType = "pollfish_options")
    public final void SurveyMode(String str) {
        if (!this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6.equals(str)) {
            this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = str;
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
        }
    }

    @SimpleProperty(description = "Survey mode. It can either be Single Survey or Survey Offerwall")
    public final String SurveyMode() {
        return this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6;
    }

    @SimpleProperty(description = "Sets a unique id to identify a user and be passed through server-to-server callbacks")
    public final void RequestUUID(String str) {
        if (!this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX.equals(str)) {
            this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = str;
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
        }
    }

    @SimpleProperty
    public final String RequestUUID() {
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then set this property to true and before publishing to Play Store, set back to false.")
    public final void TestMode(boolean z) {
        if (this.testMode != z) {
            this.testMode = z;
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean TestMode() {
        return this.testMode;
    }

    private void F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho() {
        PollFish.ParamsBuilder pollfishUserRejectedSurveyListener = new PollFish.ParamsBuilder(this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp).releaseMode(!this.testMode).rewardMode(true).offerWallMode(SurveyMode().toLowerCase().contains("offerwall")).pollfishCompletedSurveyListener(this).pollfishReceivedSurveyListener(this).pollfishOpenedListener(this).pollfishClosedListener(this).pollfishSurveyNotAvailableListener(this).pollfishUserNotEligibleListener(this).pollfishUserRejectedSurveyListener(this);
        String str = this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
        if (str != "") {
            pollfishUserRejectedSurveyListener.requestUUID(str);
        }
        PollFish.initWith(this.container.$context(), pollfishUserRejectedSurveyListener.build());
    }

    public final void onResume() {
        F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
    }

    @SimpleEvent(description = "Called when Pollfish Survey was closed.")
    public final void SurveyClosed() {
        EventDispatcher.dispatchEvent(this, "SurveyClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish survey was completed. surveyInfo is CSV list containing CPA, LOI, IR. Empty for Survey Offerwall.")
    public final void SurveyCompleted(String str) {
        EventDispatcher.dispatchEvent(this, "SurveyCompleted", str);
    }

    @SimpleEvent(description = "Called when Survey was opened.")
    public final void SurveyOpened() {
        EventDispatcher.dispatchEvent(this, "SurveyOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when device receives survey and user can be prompted to start the survey.surveyInfo is CSV list containing CPA, LOI, IR. Empty for Survey Offerwall.")
    public final void SurveyReceived(String str) {
        EventDispatcher.dispatchEvent(this, "SurveyReceived", str);
    }

    @SimpleEvent(description = "Called when Pollfish Survey was not available.")
    public final void SurveyNotAvailable() {
        EventDispatcher.dispatchEvent(this, "SurveyNotAvailable", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish user was not eligible.")
    public final void PollfishUserNotEligible() {
        EventDispatcher.dispatchEvent(this, "PollfishUserNotEligible", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish user was rejected.")
    public final void PollfishUserRejected() {
        EventDispatcher.dispatchEvent(this, "PollfishUserRejected", new Object[0]);
    }

    public final void onPollfishClosed() {
        SurveyClosed();
    }

    public final void onPollfishSurveyCompleted(SurveyInfo surveyInfo) {
        SurveyCompleted(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(surveyInfo));
    }

    public final void onPollfishOpened() {
        SurveyOpened();
    }

    public final void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_POLLFISH, KodularAnalyticsUtil.Ads.FORMAT_SURVEY, this.form);
        SurveyReceived(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(surveyInfo));
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SurveyInfo surveyInfo) {
        if (surveyInfo == null) {
            return "";
        }
        return "CPA=" + surveyInfo.getSurveyCPA() + ",LOI=" + surveyInfo.getSurveyLOI() + ",IR=" + surveyInfo.getSurveyIR() + ",Reward Name=" + surveyInfo.getRewardName() + ",Reward Value=" + surveyInfo.getRewardValue();
    }

    public final void onPollfishSurveyNotAvailable() {
        SurveyNotAvailable();
    }

    public final void onUserNotEligible() {
        PollfishUserNotEligible();
    }

    public final void onUserRejectedSurvey() {
        PollfishUserRejected();
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (Exception unused) {
            return "";
        }
    }
}

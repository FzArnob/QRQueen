package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.InternalTextToSpeech;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "The TestToSpeech component speaks a given text aloud.  You can set the pitch and the rate of speech. <p>You can also set a language by supplying a language code.  This changes the pronounciation of words, not the actual language spoken.  For example, setting the language to French and speaking English text will sound like someone speaking English (en) with a French accent.</p> <p>You can also specify a country by supplying a country code. This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.</p> <p>The languages and countries available depend on the particular device, and can be listed with the AvailableLanguages and AvailableCountries properties.</p>", iconName = "images/textToSpeech.png", nonVisible = true, version = 5)
public class TextToSpeech extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnResumeListener, OnStopListener {
    private static final Map<String, Locale> F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = Maps.newHashMap();
    private static final Map<String, Locale> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Maps.newHashMap();
    private float DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = 1.0f;
    private String KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw;
    private YailList LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private String f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7;
    private ArrayList<String> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private final ITextToSpeech hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private float iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = 1.0f;
    private boolean q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4;
    private String siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL;
    /* access modifiers changed from: private */
    public boolean symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = false;
    private YailList vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    private String vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ;
    private ArrayList<String> vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|4|(1:6)|7|8|(2:10|15)(1:16)|11) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0025 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f A[Catch:{ MissingResourceException -> 0x0034 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034 A[SYNTHETIC] */
    static {
        /*
            java.util.HashMap r0 = com.google.appinventor.components.runtime.collect.Maps.newHashMap()
            KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r0
            java.util.HashMap r0 = com.google.appinventor.components.runtime.collect.Maps.newHashMap()
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = r0
            java.util.Locale[] r0 = java.util.Locale.getAvailableLocales()
            int r1 = r0.length
            r2 = 0
        L_0x0012:
            if (r2 >= r1) goto L_0x0037
            r3 = r0[r2]
            java.lang.String r4 = r3.getISO3Country()     // Catch:{ MissingResourceException -> 0x0025 }
            int r5 = r4.length()     // Catch:{ MissingResourceException -> 0x0025 }
            if (r5 <= 0) goto L_0x0025
            java.util.Map<java.lang.String, java.util.Locale> r5 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho     // Catch:{ MissingResourceException -> 0x0025 }
            r5.put(r4, r3)     // Catch:{ MissingResourceException -> 0x0025 }
        L_0x0025:
            java.lang.String r4 = r3.getISO3Language()     // Catch:{ MissingResourceException -> 0x0034 }
            int r5 = r4.length()     // Catch:{ MissingResourceException -> 0x0034 }
            if (r5 <= 0) goto L_0x0034
            java.util.Map<java.lang.String, java.util.Locale> r5 = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH     // Catch:{ MissingResourceException -> 0x0034 }
            r5.put(r4, r3)     // Catch:{ MissingResourceException -> 0x0034 }
        L_0x0034:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.TextToSpeech.<clinit>():void");
    }

    public TextToSpeech(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Language("");
        Country("");
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new InternalTextToSpeech(componentContainer.$context(), new ITextToSpeech.TextToSpeechCallback() {
            public final void onSuccess() {
                boolean unused = TextToSpeech.this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = true;
                TextToSpeech.this.AfterSpeaking(true);
            }

            public final void onFailure() {
                boolean unused = TextToSpeech.this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = false;
                TextToSpeech.this.AfterSpeaking(false);
            }
        });
        this.form.registerForOnStop(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnDestroy(this);
        this.form.setVolumeControlStream(3);
        this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = false;
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new ArrayList<>();
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = new ArrayList<>();
        this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = YailList.makeList((List) this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = YailList.makeList((List) this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Result() {
        return this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK;
    }

    @DesignerProperty(defaultValue = "", editorType = "languages")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the language for TextToSpeech. This changes the way that words are pronounced, not the actual language that is spoken.  For example setting the language to and speaking English text with sound like someone speaking English with a French accent.")
    public void Language(String str) {
        Locale locale;
        int length = str.length();
        if (length == 2) {
            Locale locale2 = new Locale(str);
            this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = locale2.getLanguage();
            locale = locale2;
        } else if (length != 3) {
            locale = Locale.getDefault();
            this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = locale.getLanguage();
        } else {
            Map<String, Locale> map = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
            Locale locale3 = map.get(str);
            if (locale3 == null) {
                locale3 = map.get(str.toLowerCase(Locale.ENGLISH));
            }
            if (locale3 == null) {
                locale = Locale.getDefault();
            } else {
                locale = locale3;
            }
            this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = locale.getISO3Language();
        }
        this.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL = locale.getLanguage();
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the Pitch for TextToSpeech The values should be between 0 and 2 where lower values lower the tone of synthesized voice and greater values raise it.")
    public void Pitch(float f) {
        if (f < 0.0f || f > 2.0f) {
            Log.i("TextToSpeech", "Pitch value should be between 0 and 2, but user specified: ".concat(String.valueOf(f)));
            return;
        }
        this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = f;
        ITextToSpeech iTextToSpeech = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (f == 0.0f) {
            f = 0.1f;
        }
        iTextToSpeech.setPitch(f);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of Pitch")
    public float Pitch() {
        return this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL;
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the SpeechRate for TextToSpeech. The values should be between 0 and 2 where lower values slow down the pitch and greater values accelerate it.")
    public void SpeechRate(float f) {
        if (f < 0.0f || f > 2.0f) {
            Log.i("TextToSpeech", "speechRate value should be between 0 and 2, but user specified: ".concat(String.valueOf(f)));
            return;
        }
        this.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = f;
        ITextToSpeech iTextToSpeech = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (f == 0.0f) {
            f = 0.1f;
        }
        iTextToSpeech.setSpeechRate(f);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of SpeechRate")
    public float SpeechRate() {
        return this.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm;
    }

    @SimpleProperty
    public String Language() {
        return this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ;
    }

    @DesignerProperty(defaultValue = "", editorType = "countries")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Country code to use for speech generation.  This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.")
    public void Country(String str) {
        Locale locale;
        int length = str.length();
        if (length == 2) {
            Locale locale2 = new Locale(str);
            this.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7 = locale2.getCountry();
            locale = locale2;
        } else if (length != 3) {
            locale = Locale.getDefault();
            this.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7 = locale.getCountry();
        } else {
            Map<String, Locale> map = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
            Locale locale3 = map.get(str);
            if (locale3 == null) {
                locale3 = map.get(str.toUpperCase(Locale.ENGLISH));
            }
            if (locale3 == null) {
                locale = Locale.getDefault();
            } else {
                locale = locale3;
            }
            this.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7 = locale.getISO3Country();
        }
        this.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw = locale.getCountry();
    }

    @SimpleProperty
    public String Country() {
        return this.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7;
    }

    @SimpleProperty(description = "List of the languages available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
    public YailList AvailableLanguages() {
        prepareLanguageAndCountryProperties();
        return this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    }

    @SimpleProperty(description = "List of the country codes available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
    public YailList AvailableCountries() {
        prepareLanguageAndCountryProperties();
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    public void prepareLanguageAndCountryProperties() {
        if (!this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4) {
            if (!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isInitialized()) {
                this.form.dispatchErrorOccurredEvent(this, "TextToSpeech", ErrorMessages.ERROR_TTS_NOT_READY, new Object[0]);
                Speak("");
                return;
            }
            for (Locale locale : Locale.getAvailableLocales()) {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLanguageAvailable(locale) != -2) {
                    String language = locale.getLanguage();
                    String iSO3Country = locale.getISO3Country();
                    if (!language.equals("") && !this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.contains(language)) {
                        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.add(language);
                    }
                    if (!iSO3Country.equals("") && !this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.contains(iSO3Country)) {
                        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(iSO3Country);
                    }
                }
            }
            Collections.sort(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
            Collections.sort(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = YailList.makeList((List) this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
            this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = YailList.makeList((List) this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
            this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = true;
        }
    }

    @SimpleFunction
    public void Speak(String str) {
        BeforeSpeaking();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.speak(str, new Locale(this.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL, this.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw));
    }

    @SimpleEvent
    public void BeforeSpeaking() {
        EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
    }

    @SimpleEvent
    public void AfterSpeaking(boolean z) {
        EventDispatcher.dispatchEvent(this, "AfterSpeaking", Boolean.valueOf(z));
    }

    public void onStop() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onStop();
    }

    public void onResume() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onResume();
    }

    public void onDestroy() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onDestroy();
    }
}

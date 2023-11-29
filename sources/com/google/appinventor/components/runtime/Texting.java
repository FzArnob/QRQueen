package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesBroadcastReceivers;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.annotations.androidmanifest.ReceiverElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.ReceivingState;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SmsBroadcastReceiver;
import com.microsoft.appcenter.Constants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A component that will, when the <code>SendMessage</code> method is called, send the text message specified in the <code>Message</code> property to the phone number specified in the <code>PhoneNumber</code> property.</p> <p>If the <code>ReceivingEnabled</code> property is set to 1 messages will <b>not</b> be received. If <code>ReceivingEnabled</code> is set to 2 messages will be received only when the application is running. Finally if <code>ReceivingEnabled</code> is set to 3, messages will be received when the application is running <b>and</b> when the application is not running they will be queued and a notification displayed to the user.</p> <p>When a message arrives, the <code>MessageReceived</code> event is raised and provides the sending number and message.</p> <p> An app that includes this component will receive messages even when it is in the background (i.e. when it's not visible on the screen) and, moreso, even if the app is not running, so long as it's installed on the phone. If the phone receives a text message when the app is not in the foreground, the phone will show a notification in the notification bar.  Selecting the notification will bring up the app.  As an app developer, you'll probably want to give your users the ability to control ReceivingEnabled so that they can make the phone ignore text messages.</p> <p>If the GoogleVoiceEnabled property is true, messages can be sent over Wifi using Google Voice. This option requires that the user have a Google Voice account and that the mobile Voice app is installed on the phone. The Google Voice option works only on phones that support Android 2.0 (Eclair) or higher.</p> <p>To specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces may not be included.</p> <p>Another way for an app to specify a phone number would be to include a <code>PhoneNumberPicker</code> component, which lets the users select a phone numbers from the ones stored in the the phone's contacts.</p>", iconName = "images/texting.png", nonVisible = true, version = 5)
@UsesLibraries({"google-api-client-beta.jar", "google-api-client-android2-beta.jar", "google-http-client-beta.jar", "google-http-client-android2-beta.jar", "google-http-client-android3-beta.jar", "google-oauth-client-beta.jar", "guava.jar"})
@SimpleObject
@UsesPermissions({"com.google.android.apps.googlevoice.permission.RECEIVE_SMS", "com.google.android.apps.googlevoice.permission.SEND_SMS", "android.permission.ACCOUNT_MANAGER", "android.permission.MANAGE_ACCOUNTS", "android.permission.GET_ACCOUNTS", "android.permission.USE_CREDENTIALS"})
public class Texting extends AndroidNonvisibleComponent implements ActivityResultListener, Component, Deleteable, OnPauseListener, OnResumeListener, OnStopListener, OnInitializeListener {
    private static final String CACHE_FILE = "textingmsgcache";
    public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
    private static final String GV_SERVICE = "grandcentral";
    public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
    public static final String GV_URL = "https://www.google.com/voice/b/0/redirection/voice";
    private static final String MESSAGE_DELIMITER = "\u0001";
    public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
    public static final String META_DATA_SMS_KEY = "sms_handler_component";
    public static final String META_DATA_SMS_VALUE = "Texting";
    public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
    private static final String PREF_FILE = "TextingState";
    private static final String PREF_GVENABLED = "gvenabled";
    private static final String PREF_RCVENABLED = "receiving2";
    private static final String PREF_RCVENABLED_LEGACY = "receiving";
    private static final String SENT = "SMS_SENT";
    private static final int SERVER_TIMEOUT_MS = 30000;
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG = "Texting Component";
    public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
    public static final int TEXTING_REQUEST_CODE = 1413830740;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
    private static final String UTF8 = "UTF-8";
    /* access modifiers changed from: private */
    public static Activity activity;
    private static Object cacheLock = new Object();
    private static Component component;
    private static boolean isRunning;
    private static int messagesCached;
    private static ReceivingState receivingState = ReceivingState.Foreground;
    /* access modifiers changed from: private */
    public String authToken;
    private ComponentContainer container;
    private boolean googleVoiceEnabled;
    /* access modifiers changed from: private */
    public c gvHelper;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    /* access modifiers changed from: private */
    public boolean haveReceivePermission = false;
    private boolean isInitialized;
    /* access modifiers changed from: private */
    public String message;
    private Queue<String> pendingQueue = new ConcurrentLinkedQueue();
    private String phoneNumber;
    private SmsManager smsManager;

    public Texting(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Log.d(TAG, "Texting constructor");
        this.container = componentContainer;
        component = this;
        Activity $context = componentContainer.$context();
        activity = $context;
        SharedPreferences sharedPreferences = $context.getSharedPreferences(PREF_FILE, 0);
        if (sharedPreferences != null) {
            int i = sharedPreferences.getInt(PREF_RCVENABLED, -1);
            if (i != -1) {
                receivingState = ReceivingState.fromUnderlyingValue(Integer.valueOf(i));
            } else if (sharedPreferences.getBoolean(PREF_RCVENABLED_LEGACY, true)) {
                receivingState = ReceivingState.Foreground;
            } else {
                receivingState = ReceivingState.Off;
            }
            this.googleVoiceEnabled = sharedPreferences.getBoolean(PREF_GVENABLED, false);
            Log.i(TAG, "Starting with receiving Enabled=" + receivingState.toUnderlyingValue() + " GV enabled=" + this.googleVoiceEnabled);
        } else {
            receivingState = ReceivingState.Off;
            this.googleVoiceEnabled = false;
        }
        if (this.googleVoiceEnabled) {
            new a().execute(new Void[0]);
        }
        this.smsManager = SmsManager.getDefault();
        PhoneNumber("");
        this.isInitialized = false;
        isRunning = false;
        componentContainer.$form().registerForOnInitialize(this);
        componentContainer.$form().registerForOnResume(this);
        componentContainer.$form().registerForOnPause(this);
        componentContainer.$form().registerForOnStop(this);
    }

    public void onInitialize() {
        Log.i(TAG, "onInitialize()");
        this.isInitialized = true;
        isRunning = true;
        processCachedMessages();
        ((NotificationManager) activity.getSystemService("notification")).cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
    }

    public void Initialize() {
        if (receivingState != ReceivingState.Off && !this.haveReceivePermission) {
            requestReceiveSmsPermission("Initialize");
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void PhoneNumber(String str) {
        Log.i(TAG, "PhoneNumber set: ".concat(String.valueOf(str)));
        this.phoneNumber = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number that the message will be sent to when the SendMessage method is called. The number is a text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces should not be included.")
    public String PhoneNumber() {
        return this.phoneNumber;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The message that will be sent when the SendMessage method is called.")
    public void Message(String str) {
        Log.i(TAG, "Message set: ".concat(String.valueOf(str)));
        this.message = str;
    }

    @SimpleProperty
    public String Message() {
        return this.message;
    }

    @SimpleFunction
    public void SendMessage() {
        String str = this.phoneNumber;
        String str2 = this.message;
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:".concat(String.valueOf(str))));
        intent.putExtra("sms_body", str2);
        if (intent.resolveActivity(this.form.getPackageManager()) != null) {
            this.form.registerForActivityResult(this, TEXTING_REQUEST_CODE);
            this.form.startActivityForResult(intent, TEXTING_REQUEST_CODE);
        }
    }

    @UsesPermissions({"android.permission.SEND_SMS", "android.permission.READ_PHONE_STATE"})
    @SimpleFunction
    public void SendMessageDirect() {
        Log.i(TAG, "Sending message " + this.message + " to " + this.phoneNumber);
        String str = this.phoneNumber;
        String str2 = this.message;
        if (!this.googleVoiceEnabled) {
            Log.i(TAG, "Sending via SMS");
            sendViaSms("SendMessage");
        } else if (this.authToken == null) {
            Log.i(TAG, "Need to get an authToken -- enqueing " + str + " " + str2);
            Queue<String> queue = this.pendingQueue;
            if (!queue.offer(str + ":::" + str2)) {
                Toast.makeText(activity, "Pending message queue full. Can't send message", 0).show();
            } else if (this.pendingQueue.size() == 1) {
                new a().execute(new Void[0]);
            }
        } else {
            Log.i(TAG, "Creating AsyncSendMessage");
            new b().execute(new String[]{str, str2});
        }
    }

    /* access modifiers changed from: private */
    public void processPendingQueue() {
        while (this.pendingQueue.size() != 0) {
            String remove = this.pendingQueue.remove();
            String substring = remove.substring(0, remove.indexOf(":::"));
            String substring2 = remove.substring(remove.indexOf(":::") + 3);
            Log.i(TAG, "Sending queued message " + substring + " " + substring2);
            new b().execute(new String[]{substring, substring2});
        }
    }

    @SimpleEvent
    public static void MessageReceived(String str, String str2) {
        if (receivingState != ReceivingState.Off) {
            Log.i(TAG, "MessageReceived from " + str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str2);
            if (EventDispatcher.dispatchEvent(component, "MessageReceived", str, str2)) {
                Log.i(TAG, "Dispatch successful");
                return;
            }
            Log.i(TAG, "Dispatch failed, caching");
            synchronized (cacheLock) {
                addMessageToCache(activity, str, str2);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then SendMessage will attempt to send messages over Wifi using Google Voice.  This requires that the Google Voice app must be installed and set up on the phone or tablet, with a Google Voice account.  If GoogleVoiceEnabled is false, the device must have phone and texting service in order to send or receive messages with this component.")
    public boolean GoogleVoiceEnabled() {
        return this.googleVoiceEnabled;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @UsesBroadcastReceivers(receivers = {@ReceiverElement(intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "com.google.android.apps.googlevoice.SMS_RECEIVED")})}, name = "com.google.appinventor.components.runtime.util.SmsBroadcastReceiver")})
    @SimpleProperty
    public void GoogleVoiceEnabled(boolean z) {
        this.googleVoiceEnabled = z;
        SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
        edit.putBoolean(PREF_GVENABLED, z);
        edit.commit();
    }

    @Options(ReceivingState.class)
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to 1 (OFF) no messages will be received.  If set to 2 (FOREGROUND) or3 (ALWAYS) the component will respond to messages if it is running. If the app is not running then the message will be discarded if set to 2 (FOREGROUND). If set to 3 (ALWAYS) and the app is not running the phone will show a notification.  Selecting the notification will bring up the app and signal the MessageReceived event.  Messages received when the app is dormant will be queued, and so several MessageReceived events might appear when the app awakens.  As an app developer, it would be a good idea to give your users control over this property, so they can make their phones ignore text messages when your app is installed.")
    public int ReceivingEnabled() {
        return ReceivingEnabledAbstract().toUnderlyingValue().intValue();
    }

    public ReceivingState ReceivingEnabledAbstract() {
        return receivingState;
    }

    public void ReceivingEnabledAbstract(ReceivingState receivingState2) {
        receivingState = receivingState2;
        SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
        edit.putInt(PREF_RCVENABLED, receivingState2.toUnderlyingValue().intValue());
        edit.remove(PREF_RCVENABLED_LEGACY);
        edit.commit();
        if (receivingState2 != ReceivingState.Off && !this.haveReceivePermission) {
            requestReceiveSmsPermission("ReceivingEnabled");
        }
    }

    @DesignerProperty(alwaysSend = true, defaultValue = "1", editorType = "text_receiving")
    @SimpleProperty
    @UsesBroadcastReceivers(receivers = {@ReceiverElement(intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.provider.Telephony.SMS_RECEIVED")})}, name = "com.google.appinventor.components.runtime.util.SmsBroadcastReceiver")})
    @UsesPermissions({"android.permission.RECEIVE_SMS"})
    public void ReceivingEnabled(@Options(ReceivingState.class) int i) {
        ReceivingState fromUnderlyingValue = ReceivingState.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.container.$form().dispatchErrorOccurredEvent(this, META_DATA_SMS_VALUE, ErrorMessages.ERROR_BAD_VALUE_FOR_TEXT_RECEIVING, Integer.valueOf(i));
            return;
        }
        ReceivingEnabledAbstract(fromUnderlyingValue);
    }

    public static int isReceivingEnabled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        int i = sharedPreferences.getInt(PREF_RCVENABLED, -1);
        if (i != -1) {
            return i;
        }
        if (sharedPreferences.getBoolean(PREF_RCVENABLED_LEGACY, true)) {
            return 2;
        }
        return 1;
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
        int length = objArr.length;
        byte[][] bArr = new byte[length][];
        for (int i = 0; i < objArr.length; i++) {
            bArr[i] = (byte[]) objArr[i];
        }
        byte[][] bArr2 = new byte[length][];
        SmsMessage[] smsMessageArr = new SmsMessage[length];
        for (int i2 = 0; i2 < length; i2++) {
            byte[] bArr3 = bArr[i2];
            bArr2[i2] = bArr3;
            smsMessageArr[i2] = SmsMessage.createFromPdu(bArr3);
        }
        return smsMessageArr;
    }

    private void processCachedMessages() {
        String[] retrieveCachedMessages;
        synchronized (cacheLock) {
            retrieveCachedMessages = retrieveCachedMessages();
        }
        if (retrieveCachedMessages != null) {
            Log.i(TAG, "processing " + retrieveCachedMessages.length + " cached messages ");
            for (int i = 0; i < retrieveCachedMessages.length; i++) {
                String str = retrieveCachedMessages[i];
                Log.i(TAG, "Message + " + i + " " + str);
                int indexOf = str.indexOf(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                if (!(receivingState == ReceivingState.Off || indexOf == -1)) {
                    MessageReceived(str.substring(0, indexOf), str.substring(indexOf + 1));
                }
            }
        }
    }

    private String[] retrieveCachedMessages() {
        Log.i(TAG, "Retrieving cached messages");
        try {
            String str = new String(FileUtil.readFile(this.form, CACHE_FILE));
            activity.deleteFile(CACHE_FILE);
            messagesCached = 0;
            Log.i(TAG, "Retrieved cache ".concat(str));
            return str.split(MESSAGE_DELIMITER);
        } catch (FileNotFoundException unused) {
            Log.e(TAG, "No Cache file found -- this is not (usually) an error");
            return null;
        } catch (Exception unused2) {
            Log.e(TAG, "I/O Error reading from cache file");
            return null;
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static int getCachedMsgCount() {
        return messagesCached;
    }

    public void onResume() {
        Log.i(TAG, "onResume()");
        isRunning = true;
        if (this.isInitialized) {
            processCachedMessages();
            ((NotificationManager) activity.getSystemService("notification")).cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
        }
    }

    public void onPause() {
        Log.i(TAG, "onPause()");
        isRunning = false;
    }

    public static void handledReceivedMessage(Context context, String str, String str2) {
        if (isRunning) {
            MessageReceived(str, str2);
            return;
        }
        synchronized (cacheLock) {
            addMessageToCache(context, str, str2);
        }
    }

    private static void addMessageToCache(Context context, String str, String str2) {
        try {
            String str3 = str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str2 + MESSAGE_DELIMITER;
            Log.i(TAG, "Caching ".concat(String.valueOf(str3)));
            FileOutputStream openFileOutput = context.openFileOutput(CACHE_FILE, 32768);
            openFileOutput.write(str3.getBytes());
            openFileOutput.close();
            messagesCached++;
            Log.i(TAG, "Cached ".concat(String.valueOf(str3)));
        } catch (FileNotFoundException unused) {
            Log.e(TAG, "File not found error writing to cache file");
        } catch (Exception unused2) {
            Log.e(TAG, "I/O Error writing to cache file");
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        String str;
        if (i == 1413830740) {
            Form form = this.form;
            if (intent == null) {
                str = "";
            } else {
                str = intent.getStringExtra("sms_body");
            }
            handleSentMessage(form, (BroadcastReceiver) null, i2, str);
            this.form.unregisterForActivityResult(this);
        }
    }

    class c {
        private String LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA;
        private String authToken;
        private int fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM;
        private final int hZhaRgwkBZwejuAnnYABoWeuzwSnVTS6FhaE0jegMWisoVYsWwdasjmLDosamQJe = 5;
        private CookieManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new CookieManager();
        boolean isInitialized;
        private String kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q;

        public c(String str) {
            Log.i(Texting.TAG, "Creating GV Util");
            this.authToken = str;
            try {
                Log.i(Texting.TAG, "getGeneral()");
                this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(Texting.GV_URL);
                Log.i(Texting.TAG, "general = " + this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA);
                Log.i(Texting.TAG, "setRNRSEE()");
                String str2 = this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA;
                if (str2 == null) {
                    Log.i(Texting.TAG, "setRNRSEE(): Answer was null!");
                    throw new IOException("setRNRSEE(): Answer was null!");
                } else if (str2.contains("'_rnr_se': '")) {
                    this.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q = this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA.split("'_rnr_se': '", 2)[1].split("',", 2)[0];
                    Log.i(Texting.TAG, "Successfully Received rnr_se.");
                    this.isInitialized = true;
                } else {
                    Log.i(Texting.TAG, "Answer did not contain rnr_se! " + this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA);
                    throw new IOException("Answer did not contain rnr_se! " + this.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA);
                }
            } catch (Exception e) {
                Log.e(Texting.TAG, e.getMessage());
            }
        }

        /* access modifiers changed from: package-private */
        public final String vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(String str) {
            Log.i(Texting.TAG, "sendGvSms()");
            StringBuilder sb = new StringBuilder();
            try {
                String str2 = str + "&" + URLEncoder.encode("_rnr_se", "UTF-8") + "=" + URLEncoder.encode(this.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q, "UTF-8");
                Log.i(Texting.TAG, "smsData = ".concat(String.valueOf(str2)));
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Texting.GV_SMS_SEND_URL).openConnection();
                httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, "GoogleLogin auth=" + this.authToken);
                httpURLConnection.setRequestProperty("User-agent", Texting.USER_AGENT);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(httpURLConnection);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setConnectTimeout(Texting.SERVER_TIMEOUT_MS);
                Log.i(Texting.TAG, "sms request = ".concat(String.valueOf(httpURLConnection)));
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(str2);
                outputStreamWriter.flush();
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(httpURLConnection);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                }
                Log.i(Texting.TAG, "sendGvSms:  Sent SMS, response = ".concat(String.valueOf(sb)));
                outputStreamWriter.close();
                bufferedReader.close();
                if (sb.length() != 0) {
                    return sb.toString();
                }
                throw new IOException("No Response Data Received.");
            } catch (Exception e) {
                Log.i(Texting.TAG, "IO Error on Send " + e.getMessage(), e);
                return "IO Error Message not sent";
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HttpURLConnection httpURLConnection) {
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().getCookies().size() > 0) {
                httpURLConnection.setRequestProperty("Cookie", TextUtils.join(";", this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().getCookies()));
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(HttpURLConnection httpURLConnection) {
            List<String> list = (List) httpURLConnection.getHeaderFields().get("Set-Cookie");
            if (list != null) {
                for (String parse : list) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().add((URI) null, HttpCookie.parse(parse).get(0));
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0165, code lost:
            throw new java.io.IOException(r10 + " : " + r3.getResponseMessage() + "(" + r5 + ") : Received moved answer but no Location. exiting.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(java.lang.String r10) throws java.io.IOException {
            /*
                r9 = this;
                java.lang.String r0 = " - "
                java.lang.String r1 = " : "
                java.lang.String r2 = "("
            L_0x0006:
                java.net.URL r3 = new java.net.URL
                r3.<init>(r10)
                java.net.URLConnection r3 = r3.openConnection()
                java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
                r4 = 0
                java.lang.String r5 = "Authorization"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190 }
                java.lang.String r7 = "GoogleLogin auth="
                r6.<init>(r7)     // Catch:{ Exception -> 0x0190 }
                java.lang.String r7 = r9.authToken     // Catch:{ Exception -> 0x0190 }
                r6.append(r7)     // Catch:{ Exception -> 0x0190 }
                java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0190 }
                r3.setRequestProperty(r5, r6)     // Catch:{ Exception -> 0x0190 }
                java.lang.String r5 = "User-agent"
                java.lang.String r6 = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13"
                r3.setRequestProperty(r5, r6)     // Catch:{ Exception -> 0x0190 }
                r3.setInstanceFollowRedirects(r4)     // Catch:{ Exception -> 0x0190 }
                r9.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r3)     // Catch:{ Exception -> 0x0190 }
                r3.connect()     // Catch:{ Exception -> 0x0190 }
                int r5 = r3.getResponseCode()     // Catch:{ Exception -> 0x0190 }
                java.lang.String r6 = "Texting Component"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x018f }
                r7.<init>()     // Catch:{ Exception -> 0x018f }
                r7.append(r10)     // Catch:{ Exception -> 0x018f }
                r7.append(r0)     // Catch:{ Exception -> 0x018f }
                java.lang.String r8 = r3.getResponseMessage()     // Catch:{ Exception -> 0x018f }
                r7.append(r8)     // Catch:{ Exception -> 0x018f }
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x018f }
                android.util.Log.i(r6, r7)     // Catch:{ Exception -> 0x018f }
                r9.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r3)
                r6 = 200(0xc8, float:2.8E-43)
                if (r5 != r6) goto L_0x0062
                java.io.InputStream r6 = r3.getInputStream()
                goto L_0x0078
            L_0x0062:
                r6 = 301(0x12d, float:4.22E-43)
                if (r5 == r6) goto L_0x0104
                r6 = 302(0x12e, float:4.23E-43)
                if (r5 == r6) goto L_0x0104
                r6 = 303(0x12f, float:4.25E-43)
                if (r5 == r6) goto L_0x0104
                r6 = 307(0x133, float:4.3E-43)
                if (r5 != r6) goto L_0x0074
                goto L_0x0104
            L_0x0074:
                java.io.InputStream r6 = r3.getErrorStream()
            L_0x0078:
                r9.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM = r4
                if (r6 == 0) goto L_0x00dd
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00ae }
                java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00ae }
                r4.<init>(r6)     // Catch:{ Exception -> 0x00ae }
                r1.<init>(r4)     // Catch:{ Exception -> 0x00ae }
                java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00ae }
                r4.<init>()     // Catch:{ Exception -> 0x00ae }
            L_0x008b:
                java.lang.String r6 = r1.readLine()     // Catch:{ Exception -> 0x00ae }
                if (r6 == 0) goto L_0x00a6
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ae }
                r7.<init>()     // Catch:{ Exception -> 0x00ae }
                r7.append(r6)     // Catch:{ Exception -> 0x00ae }
                java.lang.String r6 = "\n\r"
                r7.append(r6)     // Catch:{ Exception -> 0x00ae }
                java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x00ae }
                r4.append(r6)     // Catch:{ Exception -> 0x00ae }
                goto L_0x008b
            L_0x00a6:
                r1.close()     // Catch:{ Exception -> 0x00ae }
                java.lang.String r10 = r4.toString()     // Catch:{ Exception -> 0x00ae }
                return r10
            L_0x00ae:
                r1 = move-exception
                java.io.IOException r4 = new java.io.IOException
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                r6.append(r10)
                r6.append(r0)
                java.lang.String r10 = r3.getResponseMessage()
                r6.append(r10)
                r6.append(r2)
                r6.append(r5)
                java.lang.String r10 = ") - "
                r6.append(r10)
                java.lang.String r10 = r1.getMessage()
                r6.append(r10)
                java.lang.String r10 = r6.toString()
                r4.<init>(r10)
                throw r4
            L_0x00dd:
                java.io.IOException r0 = new java.io.IOException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r10)
                r4.append(r1)
                java.lang.String r10 = r3.getResponseMessage()
                r4.append(r10)
                r4.append(r2)
                r4.append(r5)
                java.lang.String r10 = ") : InputStream was null : exiting."
                r4.append(r10)
                java.lang.String r10 = r4.toString()
                r0.<init>(r10)
                throw r0
            L_0x0104:
                int r6 = r9.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM
                int r6 = r6 + 1
                r9.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM = r6
                r7 = 5
                if (r6 > r7) goto L_0x0166
                java.lang.String r4 = "Location"
                java.lang.String r4 = r3.getHeaderField(r4)
                if (r4 == 0) goto L_0x013f
                java.lang.String r6 = ""
                boolean r6 = r4.equals(r6)
                if (r6 != 0) goto L_0x013f
                java.io.PrintStream r3 = java.lang.System.out
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                r6.append(r10)
                r6.append(r0)
                r6.append(r5)
                java.lang.String r10 = " - new URL: "
                r6.append(r10)
                r6.append(r4)
                java.lang.String r10 = r6.toString()
                r3.println(r10)
                r10 = r4
                goto L_0x0006
            L_0x013f:
                java.io.IOException r0 = new java.io.IOException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r10)
                r4.append(r1)
                java.lang.String r10 = r3.getResponseMessage()
                r4.append(r10)
                r4.append(r2)
                r4.append(r5)
                java.lang.String r10 = ") : Received moved answer but no Location. exiting."
                r4.append(r10)
                java.lang.String r10 = r4.toString()
                r0.<init>(r10)
                throw r0
            L_0x0166:
                r9.fAlnFrzRyM2sCUVmNXgAumx383He1buRJXamXMT9T0hNFh1WxfF7xQU1M3W9TIGM = r4
                java.io.IOException r0 = new java.io.IOException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r10)
                r4.append(r1)
                java.lang.String r10 = r3.getResponseMessage()
                r4.append(r10)
                r4.append(r2)
                r4.append(r5)
                java.lang.String r10 = ") : Too many redirects. exiting."
                r4.append(r10)
                java.lang.String r10 = r4.toString()
                r0.<init>(r10)
                throw r0
            L_0x018f:
                r4 = r5
            L_0x0190:
                java.io.IOException r0 = new java.io.IOException
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                r5.append(r10)
                r5.append(r1)
                java.lang.String r10 = r3.getResponseMessage()
                r5.append(r10)
                r5.append(r2)
                r5.append(r4)
                java.lang.String r10 = ") : IO Error."
                r5.append(r10)
                java.lang.String r10 = r5.toString()
                r0.<init>(r10)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.c.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(java.lang.String):java.lang.String");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void handleSentMessage(android.content.Context r2, android.content.BroadcastReceiver r3, int r4, java.lang.String r5) {
        /*
            r1 = this;
            monitor-enter(r1)
            r2 = -1
            r3 = 0
            if (r4 == r2) goto L_0x0082
            r2 = 1
            if (r4 == r2) goto L_0x0066
            r0 = 2
            if (r4 == r0) goto L_0x004a
            r2 = 3
            if (r4 == r2) goto L_0x002e
            r2 = 4
            if (r4 == r2) goto L_0x0012
            goto L_0x0064
        L_0x0012:
            java.lang.String r2 = "Texting Component"
            java.lang.String r4 = "Received no service error, msg:"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x009e }
            android.util.Log.e(r2, r4)     // Catch:{ all -> 0x009e }
            android.app.Activity r2 = activity     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "No Sms service available. Message not sent."
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r3)     // Catch:{ all -> 0x009e }
            r2.show()     // Catch:{ all -> 0x009e }
            monitor-exit(r1)
            return
        L_0x002e:
            java.lang.String r2 = "Texting Component"
            java.lang.String r4 = "Received null PDU error, msg:"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x009e }
            android.util.Log.e(r2, r4)     // Catch:{ all -> 0x009e }
            android.app.Activity r2 = activity     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "Received null PDU error. Message not sent."
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r3)     // Catch:{ all -> 0x009e }
            r2.show()     // Catch:{ all -> 0x009e }
            monitor-exit(r1)
            return
        L_0x004a:
            java.lang.String r3 = "Texting Component"
            java.lang.String r4 = "Received radio off error, msg:"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x009e }
            android.util.Log.e(r3, r4)     // Catch:{ all -> 0x009e }
            android.app.Activity r3 = activity     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "Could not send SMS message: radio off."
            android.widget.Toast r2 = android.widget.Toast.makeText(r3, r4, r2)     // Catch:{ all -> 0x009e }
            r2.show()     // Catch:{ all -> 0x009e }
        L_0x0064:
            monitor-exit(r1)
            return
        L_0x0066:
            java.lang.String r2 = "Texting Component"
            java.lang.String r4 = "Received generic failure, msg:"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x009e }
            android.util.Log.e(r2, r4)     // Catch:{ all -> 0x009e }
            android.app.Activity r2 = activity     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "Generic failure: message not sent"
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r3)     // Catch:{ all -> 0x009e }
            r2.show()     // Catch:{ all -> 0x009e }
            monitor-exit(r1)
            return
        L_0x0082:
            java.lang.String r2 = "Texting Component"
            java.lang.String r4 = "Received OK, msg:"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x009e }
            android.util.Log.i(r2, r4)     // Catch:{ all -> 0x009e }
            android.app.Activity r2 = activity     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "Message sent"
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r4, r3)     // Catch:{ all -> 0x009e }
            r2.show()     // Catch:{ all -> 0x009e }
            monitor-exit(r1)
            return
        L_0x009e:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.handleSentMessage(android.content.Context, android.content.BroadcastReceiver, int, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void sendViaSms(final String str) {
        Log.i(TAG, "Sending via built-in Sms");
        if (!this.havePermission) {
            final Form $form = this.container.$form();
            $form.runOnUiThread(new Runnable() {
                public final void run() {
                    $form.askPermission("android.permission.SEND_SMS", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = this.havePermission = true;
                                this.sendViaSms(str);
                                return;
                            }
                            $form.dispatchPermissionDeniedEvent((Component) this, str, "android.permission.SEND_SMS");
                        }
                    });
                }
            });
            return;
        }
        ArrayList<String> divideMessage = this.smsManager.divideMessage(this.message);
        int size = divideMessage.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(PendingIntent.getBroadcast(activity, 0, new Intent(SENT), 0));
        }
        activity.registerReceiver(new BroadcastReceiver() {
            public final synchronized void onReceive(Context context, Intent intent) {
                try {
                    Texting.this.handleSentMessage(context, (BroadcastReceiver) null, getResultCode(), Texting.this.message);
                    Texting.activity.unregisterReceiver(this);
                } catch (Exception e) {
                    Log.e("BroadcastReceiver", "Error in onReceive for msgId " + intent.getAction());
                    Log.e("BroadcastReceiver", e.getMessage());
                }
            }
        }, new IntentFilter(SENT));
        this.smsManager.sendMultipartTextMessage(this.phoneNumber, (String) null, divideMessage, arrayList, (ArrayList) null);
    }

    private void requestReceiveSmsPermission(final String str) {
        this.form.runOnUiThread(new Runnable() {
            public final void run() {
                Texting.this.form.askPermission("android.permission.RECEIVE_SMS", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            boolean unused = Texting.this.haveReceivePermission = true;
                        } else {
                            Texting.this.form.dispatchPermissionDeniedEvent((Component) Texting.this, str, "android.permission.RECEIVE_SMS");
                        }
                    }
                });
            }
        });
    }

    class a extends AsyncTask<Void, Void, String> {
        a() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            Log.i(Texting.TAG, "authToken = ".concat(String.valueOf(str)));
            String unused = Texting.this.authToken = str;
            Toast.makeText(Texting.activity, "Finished authentication", 0).show();
            Texting.this.processPendingQueue();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Log.i(Texting.TAG, "Authenticating");
            return new OAuth2Helper().getRefreshedAuthToken(Texting.activity, Texting.GV_SERVICE);
        }
    }

    class b extends AsyncTask<String, Void, String> {
        b() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x003a  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void onPostExecute(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.String r5 = (java.lang.String) r5
                super.onPostExecute(r5)
                r0 = 0
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0020 }
                r1.<init>(r5)     // Catch:{ JSONException -> 0x0020 }
                java.lang.String r2 = "ok"
                boolean r2 = r1.getBoolean(r2)     // Catch:{ JSONException -> 0x0020 }
                java.lang.String r3 = "data"
                org.json.JSONObject r1 = r1.getJSONObject(r3)     // Catch:{ JSONException -> 0x001e }
                java.lang.String r3 = "code"
                int r1 = r1.getInt(r3)     // Catch:{ JSONException -> 0x001e }
                goto L_0x0038
            L_0x001e:
                r1 = move-exception
                goto L_0x0022
            L_0x0020:
                r1 = move-exception
                r2 = 0
            L_0x0022:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r1 = r1.getMessage()
                r3.append(r1)
                java.lang.String r1 = r3.toString()
                java.lang.String r3 = "Texting Component"
                android.util.Log.e(r3, r1)
                r1 = 0
            L_0x0038:
                if (r2 == 0) goto L_0x0048
                android.app.Activity r5 = com.google.appinventor.components.runtime.Texting.activity
                java.lang.String r1 = "Message sent"
                android.widget.Toast r5 = android.widget.Toast.makeText(r5, r1, r0)
                r5.show()
                return
            L_0x0048:
                r2 = 58
                if (r1 != r2) goto L_0x005a
                android.app.Activity r5 = com.google.appinventor.components.runtime.Texting.activity
                java.lang.String r1 = "Errcode 58: SMS limit reached"
                android.widget.Toast r5 = android.widget.Toast.makeText(r5, r1, r0)
                r5.show()
                return
            L_0x005a:
                java.lang.String r1 = "IO Error"
                boolean r1 = r5.contains(r1)
                if (r1 == 0) goto L_0x006d
                android.app.Activity r1 = com.google.appinventor.components.runtime.Texting.activity
                android.widget.Toast r5 = android.widget.Toast.makeText(r1, r5, r0)
                r5.show()
            L_0x006d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.b.onPostExecute(java.lang.Object):void");
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            String str = strArr[0];
            String str2 = strArr[1];
            Log.i(Texting.TAG, "Async sending phoneNumber = " + str + " message = " + str2);
            try {
                String str3 = URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8") + "&" + URLEncoder.encode(PropertyTypeConstants.PROPERTY_TYPE_TEXT, "UTF-8") + "=" + URLEncoder.encode(str2, "UTF-8");
                if (Texting.this.gvHelper == null) {
                    Texting texting = Texting.this;
                    c unused = texting.gvHelper = new c(texting.authToken);
                }
                if (!Texting.this.gvHelper.isInitialized) {
                    return "IO Error: unable to create GvHelper";
                }
                String vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = Texting.this.gvHelper.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(str3);
                Log.i(Texting.TAG, "Sent SMS, response = ".concat(String.valueOf(vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R)));
                return vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
            } catch (Exception e) {
                Log.e(Texting.TAG, e.getMessage());
                return "";
            }
        }
    }

    public void onStop() {
        SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
        edit.putInt(PREF_RCVENABLED, receivingState.toUnderlyingValue().intValue());
        edit.putBoolean(PREF_GVENABLED, this.googleVoiceEnabled);
        edit.commit();
    }

    public void onDelete() {
        this.form.unregisterForActivityResult(this);
    }
}

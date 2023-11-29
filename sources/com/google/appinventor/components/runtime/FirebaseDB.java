package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
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
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Non-visible component that communicates with Firebase to store and retrieve information.", designerHelpDescription = "Non-visible component that communicates with a Firebase to store and retrieve information.", helpUrl = "https://docs.kodular.io/components/google/firebase-database/", iconName = "images/firebaseDB.png", nonVisible = true, version = 4)
@UsesLibraries({"firebase.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class FirebaseDB extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Firebase";
    private static boolean isInitialized = false;
    private static boolean persist = false;
    private final Activity activity;
    /* access modifiers changed from: private */
    public Handler androidUIHandler = new Handler();
    private Firebase.AuthStateListener authListener;
    private ChildEventListener childListener;
    private String defaultURL = null;
    private String developerBucket;
    /* access modifiers changed from: private */
    public String firebaseToken;
    private String firebaseURL = null;
    /* access modifiers changed from: private */
    public Firebase myFirebase;
    private String projectBucket;
    private boolean useDefault = true;

    static class a {
        String iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm;
        Object vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    static abstract class Transactional {
        final Object arg1;
        final Object arg2;
        final a retv;

        /* access modifiers changed from: package-private */
        public abstract Transaction.Result run(MutableData mutableData);

        Transactional(Object obj, Object obj2, a aVar) {
            this.arg1 = obj;
            this.arg2 = obj2;
            this.retv = aVar;
        }

        /* access modifiers changed from: package-private */
        public a getResult() {
            return this.retv;
        }
    }

    public FirebaseDB(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Activity $context = componentContainer.$context();
        this.activity = $context;
        Firebase.setAndroidContext($context);
        this.developerBucket = "";
        this.projectBucket = "";
        this.firebaseToken = "";
        this.childListener = new ChildEventListener() {
            public final void onChildMoved(DataSnapshot dataSnapshot, String str) {
            }

            public final void onChildAdded(final DataSnapshot dataSnapshot, String str) {
                FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        FirebaseDB.this.DataChanged(dataSnapshot.getKey(), dataSnapshot.getValue());
                    }
                });
            }

            public final void onCancelled(final FirebaseError firebaseError) {
                FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        FirebaseDB.this.FirebaseError(firebaseError.getMessage());
                    }
                });
            }

            public final void onChildChanged(final DataSnapshot dataSnapshot, String str) {
                FirebaseDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        FirebaseDB.this.DataChanged(dataSnapshot.getKey(), dataSnapshot.getValue());
                    }
                });
            }

            public final void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(FirebaseDB.LOG_TAG, "onChildRemoved: " + dataSnapshot.getKey() + " removed.");
            }
        };
        this.authListener = new Firebase.AuthStateListener() {
            public final void onAuthStateChanged(AuthData authData) {
                Log.i(FirebaseDB.LOG_TAG, "onAuthStateChanged: data = ".concat(String.valueOf(authData)));
                if (authData == null && FirebaseDB.this.myFirebase != null && FirebaseDB.this.firebaseToken != null) {
                    FirebaseDB.this.myFirebase.authWithCustomToken(FirebaseDB.this.firebaseToken, new Firebase.AuthResultHandler() {
                        public final void onAuthenticated(AuthData authData) {
                            Log.i(FirebaseDB.LOG_TAG, "Auth Successful.");
                        }

                        public final void onAuthenticationError(FirebaseError firebaseError) {
                            Log.e(FirebaseDB.LOG_TAG, "Auth Failed: " + firebaseError.getMessage());
                        }
                    });
                }
            }
        };
    }

    public void Initialize() {
        Log.i(LOG_TAG, "Initalize called!");
        isInitialized = true;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the URL for this FirebaseDB.")
    public String FirebaseURL() {
        if (this.useDefault) {
            return "DEFAULT";
        }
        return this.firebaseURL;
    }

    @DesignerProperty(defaultValue = "DEFAULT", editorType = "FirbaseURL")
    @SimpleProperty(description = "Sets the URL for this FirebaseDB.")
    public void FirebaseURL(String str) {
        if (!str.equals("DEFAULT")) {
            this.useDefault = false;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = InternalZipConstants.ZIP_FILE_SEPARATOR;
            if (str.endsWith(str2)) {
                str2 = "";
            }
            sb.append(str2);
            String sb2 = sb.toString();
            if (!this.firebaseURL.equals(sb2)) {
                this.firebaseURL = sb2;
                this.useDefault = false;
                resetListener();
                return;
            }
            return;
        }
        if (!this.useDefault) {
            this.useDefault = true;
            if (this.defaultURL == null) {
                Log.d(LOG_TAG, "FirebaseURL called before DefaultURL (should not happen!)");
                return;
            }
        }
        this.firebaseURL = this.defaultURL;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String DeveloperBucket() {
        return this.developerBucket;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void DeveloperBucket(String str) {
        this.developerBucket = str;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectBucket for this FirebaseDB.")
    public String ProjectBucket() {
        return this.projectBucket;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Sets the ProjectBucket for this FirebaseDB.")
    public void ProjectBucket(String str) {
        if (!this.projectBucket.equals(str)) {
            this.projectBucket = str;
            resetListener();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the FirebaseToken from this FirebaseDB.")
    public String FirebaseToken() {
        return this.firebaseToken;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Sets the FirebaseToken for this FirebaseDB.")
    public void FirebaseToken(String str) {
        this.firebaseToken = str;
        resetListener();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If true, variables will retain their values when off-line and the App exits. Values will be uploaded to Firebase the next time the App is run while connected to the network. This is useful for applications which will gather data while not connected to the network. Note: AppendValue and RemoveFirst will not work correctly when off-line, they require a network connection.<br/><br/> <i>Note</i>: If you set Persist on any Firebase component, on any screen, it makes all Firebase components on all screens persistent. This is a limitation of the low level Firebase library. Also be aware that if you want to set persist to true, you should do so before connecting the Companion for incremental development.", userVisible = false)
    public void Persist(boolean z) {
        Log.i(LOG_TAG, "Persist Called: Value = ".concat(String.valueOf(z)));
        if (persist == z) {
            return;
        }
        if (!isInitialized) {
            Config defaultConfig = Firebase.getDefaultConfig();
            defaultConfig.setPersistenceEnabled(z);
            Firebase.setDefaultConfig(defaultConfig);
            persist = z;
            resetListener();
            return;
        }
        throw new RuntimeException("You cannot change the Persist value of Firebase after Application Initialization, this includes the Companion");
    }

    private void resetListener() {
        if (isInitialized) {
            Firebase firebase = this.myFirebase;
            if (firebase != null) {
                firebase.removeEventListener(this.childListener);
                this.myFirebase.removeAuthStateListener(this.authListener);
            }
            this.myFirebase = null;
            connectFirebase();
        }
    }

    @SimpleFunction(description = "Remove the tag from Firebase")
    public void ClearTag(String str) {
        Firebase firebase = this.myFirebase;
        if (firebase != null) {
            firebase.child(str).removeValue();
        }
    }

    @SimpleFunction
    public void StoreValue(String str, Object obj) {
        if (obj != null) {
            try {
                obj = JsonUtil.getJsonRepresentation(obj);
            } catch (JSONException e) {
                Log.e(LOG_TAG, String.valueOf(e));
                obj = "Value failed to convert from JSON. JSON Creation Error.";
            }
        }
        Firebase firebase = this.myFirebase;
        if (firebase != null) {
            firebase.child(str).setValue(obj);
        }
    }

    @SimpleFunction
    public void GetValue(final String str, final Object obj) {
        Firebase firebase = this.myFirebase;
        if (firebase != null) {
            firebase.child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                public final void onDataChange(DataSnapshot dataSnapshot) {
                    final AtomicReference atomicReference = new AtomicReference();
                    try {
                        if (dataSnapshot.exists()) {
                            atomicReference.set(dataSnapshot.getValue());
                        } else {
                            atomicReference.set(JsonUtil.getJsonRepresentation(obj));
                        }
                        FirebaseDB.this.androidUIHandler.post(new Runnable() {
                            public final void run() {
                                FirebaseDB.this.GotValue(str, atomicReference.get());
                            }
                        });
                    } catch (JSONException e) {
                        Log.e(FirebaseDB.LOG_TAG, String.valueOf(e));
                        FirebaseDB firebaseDB = FirebaseDB.this;
                        firebaseDB.FirebaseError(e.getMessage());
                    }
                }

                public final void onCancelled(final FirebaseError firebaseError) {
                    FirebaseDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            FirebaseDB.this.FirebaseError(firebaseError.getMessage());
                        }
                    });
                }
            });
        } else {
            FirebaseError("Can not call 'Get Value' if the firebase object is NULL.");
        }
    }

    @SimpleEvent
    public void GotValue(String str, Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    obj = JsonUtil.getObjectFromJson((String) obj, true);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, String.valueOf(e));
                obj = "Value failed to convert from JSON. JSON Retrieval Error.";
            }
        }
        try {
            EventDispatcher.dispatchEvent(this, "GotValue", str, obj);
        } catch (Exception e2) {
            Log.e(LOG_TAG, String.valueOf(e2));
            FirebaseError(e2.getMessage());
        }
    }

    @SimpleEvent
    public void DataChanged(String str, Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    obj = JsonUtil.getObjectFromJson((String) obj, true);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, String.valueOf(e));
                obj = "Value failed to convert from JSON. JSON Retrieval Error.";
            }
        }
        EventDispatcher.dispatchEvent(this, "DataChanged", str, obj);
    }

    @SimpleEvent
    public void FirebaseError(String str) {
        Log.e(LOG_TAG, str);
        if (!EventDispatcher.dispatchEvent(this, "FirebaseError", str)) {
            try {
                Notifier.oneButtonAlert(this.form, str, "FirebaseError", "Continue");
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    private void connectFirebase() {
        if (this.useDefault) {
            try {
                this.myFirebase = new Firebase(this.firebaseURL + "developers/" + this.developerBucket + this.projectBucket);
            } catch (Exception e) {
                FirebaseError(e.getMessage());
                return;
            }
        } else {
            try {
                this.myFirebase = new Firebase(this.firebaseURL + this.projectBucket);
            } catch (Exception e2) {
                FirebaseError(e2.getMessage());
                return;
            }
        }
        try {
            Firebase firebase = this.myFirebase;
            if (firebase != null) {
                firebase.addChildEventListener(this.childListener);
            }
            try {
                Firebase firebase2 = this.myFirebase;
                if (firebase2 != null) {
                    firebase2.addAuthStateListener(this.authListener);
                }
            } catch (Exception e3) {
                FirebaseError(e3.getMessage());
            }
        } catch (Exception e4) {
            FirebaseError(e4.getMessage());
        }
    }

    @SimpleFunction(description = "If you are having difficulty with the Companion and you are switching between different Firebase accounts, you may need to use this function to clear internal Firebase caches. You can just use the \"Do It\" function on this block in the blocks editor. Note: You should not normally need to use this block as part of an application.")
    public void Unauthenticate() {
        if (this.myFirebase == null) {
            connectFirebase();
        }
        this.myFirebase.unauth();
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void DefaultURL(String str) {
        this.defaultURL = str;
        if (this.useDefault) {
            this.firebaseURL = str;
            resetListener();
        }
    }

    @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
    public void RemoveFirst(String str) {
        final a aVar = new a((byte) 0);
        firebaseTransaction(new Transactional(aVar) {
            /* access modifiers changed from: package-private */
            public final Transaction.Result run(MutableData mutableData) {
                Object value = mutableData.getValue();
                if (value == null) {
                    aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Previous value was empty.";
                    return Transaction.abort();
                }
                try {
                    if (value instanceof String) {
                        Object objectFromJson = JsonUtil.getObjectFromJson((String) value, true);
                        if (objectFromJson instanceof List) {
                            List list = (List) objectFromJson;
                            if (list.isEmpty()) {
                                aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "The list was empty";
                                return Transaction.abort();
                            }
                            aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = list.remove(0);
                            try {
                                mutableData.setValue(JsonUtil.getJsonRepresentation(YailList.makeList((List) objectFromJson)));
                                return Transaction.success(mutableData);
                            } catch (JSONException unused) {
                                aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Could not convert value to JSON.";
                                return Transaction.abort();
                            }
                        } else {
                            aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "You can only remove elements from a list.";
                            return Transaction.abort();
                        }
                    } else {
                        aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Invalid JSON object in database (shouldn't happen!)";
                        return Transaction.abort();
                    }
                } catch (JSONException unused2) {
                    aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Invalid JSON object in database (shouldn't happen!)";
                    return Transaction.abort();
                }
            }
        }, this.myFirebase.child(str), new Runnable() {
            public final void run() {
                FirebaseDB.this.FirstRemoved(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
            }
        });
    }

    @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
    public void GetTagList() {
        this.myFirebase.child("").addListenerForSingleValueEvent(new ValueEventListener() {
            public final void onCancelled(FirebaseError firebaseError) {
            }

            public final void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                if (value instanceof HashMap) {
                    final List arrayList = new ArrayList(((HashMap) value).keySet());
                    FirebaseDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            FirebaseDB.this.TagList(arrayList);
                        }
                    });
                }
            }
        });
    }

    @SimpleFunction(description = "Take the database online")
    public void GoOnline() {
        if (this.myFirebase != null) {
            Firebase.goOnline();
        } else {
            connectFirebase();
        }
    }

    @SimpleFunction(description = "Take the database offline")
    public void GoOffline() {
        if (this.myFirebase != null) {
            Firebase.goOffline();
        }
    }

    @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
    public void TagList(List list) {
        EventDispatcher.dispatchEvent(this, "TagList", list);
    }

    @SimpleEvent(description = "Event triggered by the \"RemoveFirst\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
    public void FirstRemoved(Object obj) {
        EventDispatcher.dispatchEvent(this, "FirstRemoved", obj);
    }

    @SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
    public void AppendValue(String str, final Object obj) {
        final a aVar = new a((byte) 0);
        firebaseTransaction(new Transactional(aVar) {
            /* access modifiers changed from: package-private */
            public final Transaction.Result run(MutableData mutableData) {
                Object value = mutableData.getValue();
                if (value == null) {
                    aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Previous value was empty.";
                    return Transaction.abort();
                }
                try {
                    if (value instanceof String) {
                        Object objectFromJson = JsonUtil.getObjectFromJson((String) value, true);
                        if (objectFromJson instanceof List) {
                            ((List) objectFromJson).add(obj);
                            try {
                                mutableData.setValue(JsonUtil.getJsonRepresentation((List) objectFromJson));
                                return Transaction.success(mutableData);
                            } catch (JSONException unused) {
                                aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Could not convert value to JSON.";
                                return Transaction.abort();
                            }
                        } else {
                            aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "You can only append to a list.";
                            return Transaction.abort();
                        }
                    } else {
                        aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Invalid JSON object in database (shouldn't happen!)";
                        return Transaction.abort();
                    }
                } catch (JSONException unused2) {
                    aVar.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = "Invalid JSON object in database (shouldn't happen!)";
                    return Transaction.abort();
                }
            }
        }, this.myFirebase.child(str), (Runnable) null);
    }

    private void firebaseTransaction(final Transactional transactional, Firebase firebase, final Runnable runnable) {
        final a result = transactional.getResult();
        firebase.runTransaction(new Transaction.Handler() {
            public final Transaction.Result doTransaction(MutableData mutableData) {
                return transactional.run(mutableData);
            }

            public final void onComplete(final FirebaseError firebaseError, boolean z, DataSnapshot dataSnapshot) {
                if (firebaseError != null) {
                    FirebaseDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            Log.i(FirebaseDB.LOG_TAG, "AppendValue(onComplete): firebase: " + firebaseError.getMessage());
                            Log.i(FirebaseDB.LOG_TAG, "AppendValue(onComplete): result.err: " + result.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm);
                            FirebaseDB.this.FirebaseError(firebaseError.getMessage());
                        }
                    });
                } else if (!z) {
                    FirebaseDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            Log.i(FirebaseDB.LOG_TAG, "AppendValue(!committed): result.err: " + result.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm);
                            FirebaseDB.this.FirebaseError(result.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm);
                        }
                    });
                } else if (runnable != null) {
                    FirebaseDB.this.androidUIHandler.post(runnable);
                }
            }
        });
    }
}

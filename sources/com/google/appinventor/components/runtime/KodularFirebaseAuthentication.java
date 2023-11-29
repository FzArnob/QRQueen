package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.TimeUnit;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Authentication", iconName = "images/firebaseDB.png", nonVisible = true, version = 3)
@UsesLibraries({"firebase-annotations.jar", "firebase-auth.jar", "firebase-auth.aar", "firebase-auth-interop.jar", "firebase-auth-interop.aar", "firebase-common.jar", "firebase-common.aar", "firebase-components.jar", "firebase-components.aar", "play-services-auth.jar", "play-services-auth.aar", "play-services-auth-api-phone.jar", "play-services-auth-api-phone.aar", "play-services-auth-base.jar", "play-services-auth-base.aar", "play-services-base.jar", "play-services-base.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-tasks.jar", "play-services-tasks.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK", "com.google.android.c2dm.permission.RECEIVE"})
public class KodularFirebaseAuthentication extends AndroidNonvisibleComponent implements ActivityResultListener {
    private static final String LOG_TAG = "KodularFirebaseAuth";
    private static final String PROVIDER_EMAIL_PASSWORD = "EmailPassword";
    private static final String PROVIDER_GOOGLE = "Google";
    private static final String PROVIDER_PHONE_NUMBER = "PhoneNumber";
    private static final String PROVIDER_PLAY_GAMES = "Play Games";
    private static final String STORAGE_TAG = "KodularFirebaseAuth";
    private Activity activity;
    private Context context;
    private int googleRequestCode = 0;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient = null;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mPhoneAuthCallbacks;
    private GoogleSignInClient mPlayGamesSignInClient = null;
    /* access modifiers changed from: private */
    public String mVerificationId = null;
    private boolean phoneNewUser = true;
    private int playGamesRequestCode = 0;
    /* access modifiers changed from: private */
    public SharedPreferences preferences;

    public KodularFirebaseAuthentication(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
        if (componentContainer.$form() instanceof ReplForm) {
            this.isCompanion = true;
        }
        this.preferences = this.context.getSharedPreferences("KodularFirebaseAuth", 0);
        this.mAuth = FirebaseAuth.getInstance();
        this.mPhoneAuthCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                KodularFirebaseAuthentication.this.firebaseAuthWithPhoneNumber(phoneAuthCredential);
                KodularFirebaseAuthentication.this.preferences.edit().remove("VerificationId").apply();
            }

            public final void onVerificationFailed(FirebaseException firebaseException) {
                KodularFirebaseAuthentication.this.LoginFailed(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
            }

            public final void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                String unused = KodularFirebaseAuthentication.this.mVerificationId = str;
                KodularFirebaseAuthentication.this.preferences.edit().putString("VerificationId", str).commit();
            }
        };
    }

    @SimpleFunction(description = "Check if the user is signed in")
    public boolean IsSignedIn() {
        return this.mAuth.getCurrentUser() != null;
    }

    @SimpleFunction(description = "Try to get the user that is signed in")
    public void GetCurrentUser() {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            CurrentUserSuccess(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
        } else {
            CurrentUserFailed();
        }
    }

    @SimpleFunction(description = "Try to let the user sign in with Email and Password")
    public void EmailPasswordLogin(String str, String str2) {
        CompanionToast();
        this.mAuth.signInWithEmailAndPassword(str, str2).addOnCompleteListener(this.activity, new OnCompleteListener<AuthResult>() {
            public final void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = KodularFirebaseAuthentication.this.mAuth.getCurrentUser();
                    KodularFirebaseAuthentication.this.LoginSuccess(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                KodularFirebaseAuthentication.this.LoginFailed(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD);
            }
        });
    }

    @SimpleFunction(description = "Create a new user by Email and Password")
    public void EmailPasswordSignUp(String str, String str2) {
        CompanionToast();
        this.mAuth.createUserWithEmailAndPassword(str, str2).addOnCompleteListener(this.activity, new OnCompleteListener<AuthResult>() {
            public final void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = KodularFirebaseAuthentication.this.mAuth.getCurrentUser();
                    KodularFirebaseAuthentication.this.SignUpSuccess(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                KodularFirebaseAuthentication kodularFirebaseAuthentication = KodularFirebaseAuthentication.this;
                kodularFirebaseAuthentication.SignUpFailed(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, task.getException().getMessage());
            }
        });
    }

    @SimpleFunction(description = "Try to let the user sign in with Google")
    public void GoogleSignIn() {
        CompanionToast();
        if (this.googleRequestCode == 0) {
            this.googleRequestCode = this.form.registerForActivityResult(this);
        }
        Log.i("KodularFirebaseAuth", "Activity Started: " + this.googleRequestCode);
        this.activity.startActivityForResult(getGoogleSignInClient().getSignInIntent(), this.googleRequestCode);
    }

    public void PlayGamesSignIn() {
        CompanionToast();
        if (this.playGamesRequestCode == 0) {
            this.playGamesRequestCode = this.form.registerForActivityResult(this);
        }
        Log.i("KodularFirebaseAuth", "Activity Started: " + this.playGamesRequestCode);
        this.activity.startActivityForResult(getPlayGamesSignInClient().getSignInIntent(), this.playGamesRequestCode);
    }

    @SimpleFunction(description = "Try to let the user sign in with a Phone Number")
    public void PhoneNumberSignIn(String str) {
        CompanionToast();
        this.phoneNewUser = true;
        PhoneAuthProvider.getInstance(this.mAuth).verifyPhoneNumber(str, 60, TimeUnit.SECONDS, this.activity, this.mPhoneAuthCallbacks);
    }

    @SimpleFunction(description = "Verify the SMS code")
    public void VerifyPhoneCode(String str) {
        if (this.mVerificationId == null) {
            this.mVerificationId = this.preferences.getString("VerificationId", (String) null);
        }
        String str2 = this.mVerificationId;
        if (str2 != null) {
            firebaseAuthWithPhoneNumber(PhoneAuthProvider.getCredential(str2, str));
        }
        this.preferences.edit().remove("VerificationId").apply();
    }

    @SimpleFunction(description = "Verify the Email")
    public void VerifyEmail() {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.sendEmailVerification(ActionCodeSettings.newBuilder().setAndroidPackageName(this.form.getPackageName(), true, "16").build());
        }
    }

    @SimpleFunction(description = "Log the current user out")
    public void Logout() {
        this.mAuth.signOut();
        getGoogleSignInClient().signOut();
        getPlayGamesSignInClient().signOut();
    }

    @SimpleFunction(description = "Try to update the profile of the current user (The user must have been recently signed in)")
    public void UpdateProfile(String str, String str2) {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(str).setPhotoUri(Uri.parse(str2)).build()).addOnCompleteListener(this.activity, new OnCompleteListener<Void>() {
                public final void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        KodularFirebaseAuthentication.this.UserUpdateSuccess("Profile");
                    } else {
                        KodularFirebaseAuthentication.this.UserUpdateFailed("Profile");
                    }
                }
            });
        } else {
            UserUpdateFailed("Profile");
        }
    }

    @SimpleFunction(description = "Try to update the email of the current user (The user must have been recently signed in)")
    public void UpdateEmail(String str) {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.updateEmail(str).addOnCompleteListener(this.activity, new OnCompleteListener<Void>() {
                public final void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        KodularFirebaseAuthentication.this.UserUpdateSuccess("Email");
                    } else {
                        KodularFirebaseAuthentication.this.UserUpdateFailed("Email");
                    }
                }
            });
        } else {
            UserUpdateFailed("Email");
        }
    }

    @SimpleFunction(description = "Try to update the phone number of the current user (The user must have been recently signed in)")
    public void UpdatePhoneNumber(String str) {
        if (this.mAuth.getCurrentUser() != null) {
            this.phoneNewUser = false;
            PhoneAuthProvider.getInstance(this.mAuth).verifyPhoneNumber(str, 60, TimeUnit.SECONDS, this.activity, this.mPhoneAuthCallbacks);
            return;
        }
        UserUpdateFailed(PROVIDER_PHONE_NUMBER);
    }

    @SimpleFunction(description = "Get the id token from the current user.")
    public void GetIdToken() {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    String token = ((GetTokenResult) obj).getToken();
                    if (token != null) {
                        KodularFirebaseAuthentication.this.GotIdToken(token);
                    } else {
                        KodularFirebaseAuthentication.this.GotIdToken("");
                    }
                }
            });
        } else {
            GotIdToken("");
        }
    }

    @SimpleFunction(description = "Get the id token from the current user.")
    public void SendResetPasswordEmail(String str) {
        this.mAuth.sendPasswordResetEmail(str).addOnCompleteListener(new OnCompleteListener<Void>() {
            public final void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    KodularFirebaseAuthentication.this.ResetPasswordEmailDone(true);
                } else {
                    KodularFirebaseAuthentication.this.ResetPasswordEmailDone(false);
                }
            }
        });
    }

    @SimpleEvent(description = "Triggers when the 'Get Id Token' got a result. If there was a error it returns a empty text.")
    public void GotIdToken(String str) {
        EventDispatcher.dispatchEvent(this, "GotIdToken", str);
    }

    @SimpleEvent(description = "Triggers when the 'Send Reset Password Email' got a result. Returns true if the reset email was send.")
    public void ResetPasswordEmailDone(boolean z) {
        EventDispatcher.dispatchEvent(this, "ResetPasswordEmailDone", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Triggers when the Firebase Login was successful")
    public void LoginSuccess(String str, String str2, String str3, String str4, String str5, String str6) {
        Object[] objArr = new Object[6];
        objArr[0] = str;
        if (str2 == null) {
            str2 = "";
        }
        objArr[1] = str2;
        if (str3 == null) {
            str3 = "";
        }
        objArr[2] = str3;
        if (str4 == null) {
            str4 = "";
        }
        objArr[3] = str4;
        if (str5 == null) {
            str5 = "";
        }
        objArr[4] = str5;
        if (str6 == null) {
            str6 = "";
        }
        objArr[5] = str6;
        EventDispatcher.dispatchEvent(this, "LoginSuccess", objArr);
    }

    @SimpleEvent(description = "Triggers when the Firebase Login failed")
    public void LoginFailed(String str) {
        EventDispatcher.dispatchEvent(this, "LoginFailed", str);
    }

    @SimpleEvent(description = "Triggers when the Firebase Sign Up failed")
    public void SignUpFailed(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "SignUpFailed", str, str2);
    }

    @SimpleEvent(description = "Triggers when the Firebase Sign Up failed")
    public void SignUpSuccess(String str, String str2, String str3, String str4, String str5, String str6) {
        Object[] objArr = new Object[6];
        objArr[0] = str;
        if (str2 == null) {
            str2 = "";
        }
        objArr[1] = str2;
        if (str3 == null) {
            str3 = "";
        }
        objArr[2] = str3;
        if (str4 == null) {
            str4 = "";
        }
        objArr[3] = str4;
        if (str5 == null) {
            str5 = "";
        }
        objArr[4] = str5;
        if (str6 == null) {
            str6 = "";
        }
        objArr[5] = str6;
        EventDispatcher.dispatchEvent(this, "SignUpSuccess", objArr);
    }

    @SimpleEvent(description = "Triggers when updating of the user was successful")
    public void UserUpdateSuccess(String str) {
        EventDispatcher.dispatchEvent(this, "UserUpdateSuccess", str);
    }

    @SimpleEvent(description = "Triggers when updating of the user failed")
    public void UserUpdateFailed(String str) {
        EventDispatcher.dispatchEvent(this, "UserUpdateFailed", str);
    }

    @SimpleEvent(description = "Triggers when the current Firebase User was successful loaded")
    public void CurrentUserSuccess(String str, String str2, String str3, String str4, String str5) {
        Object[] objArr = new Object[5];
        if (str == null) {
            str = "";
        }
        objArr[0] = str;
        if (str2 == null) {
            str2 = "";
        }
        objArr[1] = str2;
        if (str3 == null) {
            str3 = "";
        }
        objArr[2] = str3;
        if (str4 == null) {
            str4 = "";
        }
        objArr[3] = str4;
        if (str5 == null) {
            str5 = "";
        }
        objArr[4] = str5;
        EventDispatcher.dispatchEvent(this, "CurrentUserSuccess", objArr);
    }

    @SimpleEvent(description = "Triggers when the current Firebase User failed to load")
    public void CurrentUserFailed() {
        EventDispatcher.dispatchEvent(this, "CurrentUserFailed", new Object[0]);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        Log.d("KodularFirebaseAuth", "firebaseAuthWithGoogle:" + googleSignInAccount.getId());
        firebaseAuthWithCredential(GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), (String) null), PROVIDER_GOOGLE);
    }

    private void firebaseAuthWithPlayGames(GoogleSignInAccount googleSignInAccount) {
        Log.d("KodularFirebaseAuth", "firebaseAuthWithPlayGames:" + googleSignInAccount.getId());
        String serverAuthCode = googleSignInAccount.getServerAuthCode();
        if (serverAuthCode != null) {
            firebaseAuthWithCredential(PlayGamesAuthProvider.getCredential(serverAuthCode), PROVIDER_PLAY_GAMES);
        } else {
            LoginFailed(PROVIDER_PLAY_GAMES);
        }
    }

    private void firebaseAuthWithCredential(AuthCredential authCredential, final String str) {
        this.mAuth.signInWithCredential(authCredential).addOnCompleteListener(this.activity, new OnCompleteListener<AuthResult>() {
            public final void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("KodularFirebaseAuth", "signInWithCredential:success");
                    FirebaseUser currentUser = KodularFirebaseAuthentication.this.mAuth.getCurrentUser();
                    KodularFirebaseAuthentication.this.LoginSuccess(str, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                Log.w("KodularFirebaseAuth", "signInWithCredential:failure", task.getException());
                KodularFirebaseAuthentication.this.LoginFailed(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void firebaseAuthWithPhoneNumber(PhoneAuthCredential phoneAuthCredential) {
        if (this.phoneNewUser) {
            this.mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this.activity, new OnCompleteListener<AuthResult>() {
                public final void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d("KodularFirebaseAuth", "signInWithCredential:success");
                        FirebaseUser currentUser = KodularFirebaseAuthentication.this.mAuth.getCurrentUser();
                        KodularFirebaseAuthentication.this.LoginSuccess(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                        return;
                    }
                    Log.w("KodularFirebaseAuth", "signInWithCredential:failure", task.getException());
                    task.getException();
                    KodularFirebaseAuthentication.this.LoginFailed(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                }
            });
            return;
        }
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.updatePhoneNumber(phoneAuthCredential).addOnCompleteListener(this.activity, new OnCompleteListener<Void>() {
                public final void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        KodularFirebaseAuthentication.this.UserUpdateSuccess(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                    } else {
                        KodularFirebaseAuthentication.this.UserUpdateFailed(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                    }
                }
            });
        }
    }

    private GoogleSignInClient getGoogleSignInClient() {
        if (this.mGoogleSignInClient == null) {
            this.mGoogleSignInClient = GoogleSignIn.getClient(this.context, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(KodularResourcesUtil.getString(this.context, "default_web_client_id")).requestEmail().build());
        }
        return this.mGoogleSignInClient;
    }

    private GoogleSignInClient getPlayGamesSignInClient() {
        if (this.mPlayGamesSignInClient == null) {
            this.mPlayGamesSignInClient = GoogleSignIn.getClient(this.context, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        }
        return this.mPlayGamesSignInClient;
    }

    private void CompanionToast() {
        if (this.isCompanion) {
            Toast.makeText(this.context, "Firebase Auth doesn't work in the Companion", 1).show();
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        Log.i("KodularFirebaseAuth", "Result Returned: ".concat(String.valueOf(i)));
        if (i == this.googleRequestCode) {
            Log.i("KodularFirebaseAuth", "Google Sign in Result Returned");
            try {
                firebaseAuthWithGoogle((GoogleSignInAccount) GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException.class));
            } catch (ApiException e) {
                Log.w("KodularFirebaseAuth", "Google sign in failed: " + e.getMessage(), e);
                LoginFailed(PROVIDER_GOOGLE);
            }
        } else if (i == this.playGamesRequestCode) {
            Log.i("KodularFirebaseAuth", "Play Games Sign in Result Returned");
            try {
                firebaseAuthWithPlayGames((GoogleSignInAccount) GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException.class));
            } catch (ApiException e2) {
                Log.w("KodularFirebaseAuth", "Play Games sign in failed: " + e2.getMessage(), e2);
                LoginFailed(PROVIDER_PLAY_GAMES);
            }
        }
    }
}

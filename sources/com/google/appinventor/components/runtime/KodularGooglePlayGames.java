package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Google Play Games component to connect to the Google Play Games services", iconName = "images/gameClient.png", nonVisible = true, version = 1)
@UsesLibraries({"core-runtime.jar", "core-runtime.aar", "play-services-games.jar", "play-services-games.aar", "play-services-base.jar", "play-services-base.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-tasks.jar", "play-services-tasks.aar", "play-services-auth-base.jar", "play-services-auth-base.aar", "play-services-auth.jar", "play-services-auth.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class KodularGooglePlayGames extends AndroidNonvisibleComponent implements ActivityResultListener, OnResumeListener {
    private static final String LOG_TAG = "KodularGooglePlayGames";
    /* access modifiers changed from: private */
    public int achievementCode = 0;
    private AchievementsClient achievementsClient;
    private ComponentContainer componentContainer;
    private Context context;
    private GamesClient gamesClient;
    private GoogleSignInClient googleSignInClient;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public int leaderboardCode = 0;
    private LeaderboardsClient leaderboardsClient;
    private PlayersClient playersClient;
    private int requestCode = 0;
    private GoogleSignInOptions signInOptions;

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void GooglePlayGamesAppId(String str) {
    }

    public KodularGooglePlayGames(ComponentContainer componentContainer2) {
        super(componentContainer2.$form());
        this.context = componentContainer2.$context();
        this.componentContainer = componentContainer2;
        this.form.registerForOnResume(this);
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
        this.signInOptions = googleSignInOptions;
        this.googleSignInClient = GoogleSignIn.getClient(this.context, googleSignInOptions);
        if (this.form instanceof ReplForm) {
            this.isCompanion = true;
        }
        Log.d(LOG_TAG, "Google Play Games component initialized");
    }

    @SimpleFunction(description = "Unlock an achievement.")
    public void UnlockAchievement(String str) {
        GoogleSignInAccount lastSignedInAccount;
        if (!isCompanionMode() && (lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.context)) != null) {
            Games.getAchievementsClient(this.context, lastSignedInAccount).unlock(str);
        }
    }

    @SimpleFunction(description = "Increment an achievement.")
    public void IncrementAchievement(String str, int i) {
        AchievementsClient achievementsClient2;
        if (!isCompanionMode() && (achievementsClient2 = this.achievementsClient) != null) {
            achievementsClient2.increment(str, i);
        }
    }

    @SimpleFunction(description = "Shows Achievement.")
    public void ShowAchievements() {
        if (!isCompanionMode() && this.achievementsClient != null) {
            if (this.achievementCode == 0) {
                this.achievementCode = this.form.registerForActivityResult(this);
            }
            this.achievementsClient.getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    KodularGooglePlayGames.this.form.startActivityForResult((Intent) obj, KodularGooglePlayGames.this.achievementCode);
                }
            });
        }
    }

    @SimpleFunction(description = "Submits user score to leaderboard.")
    public void SubmitScore(String str, int i) {
        LeaderboardsClient leaderboardsClient2;
        if (!isCompanionMode() && (leaderboardsClient2 = this.leaderboardsClient) != null) {
            leaderboardsClient2.submitScore(str, (long) i);
        }
    }

    @SimpleFunction(description = "Shows Leaderboard.")
    public void ShowLeaderboard(String str) {
        if (!isCompanionMode() && this.leaderboardsClient != null) {
            if (this.leaderboardCode == 0) {
                this.leaderboardCode = this.form.registerForActivityResult(this);
            }
            this.leaderboardsClient.getLeaderboardIntent(str).addOnSuccessListener(new OnSuccessListener<Intent>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    KodularGooglePlayGames.this.form.startActivityForResult((Intent) obj, KodularGooglePlayGames.this.leaderboardCode);
                }
            });
        }
    }

    @SimpleFunction(description = "Sign in the user. useLastAccount, true: use the last signed in account if possible, false: open a screen to choose for an account")
    public void SignIn(boolean z) {
        if (!isCompanionMode()) {
            if (z) {
                signInSilently(true);
            } else {
                startSignInIntent();
            }
        }
    }

    @SimpleFunction(description = "Sign out the user.")
    public void SignOut() {
        if (!isCompanionMode()) {
            signOut();
        }
    }

    @SimpleFunction(description = "Is user signed in?")
    public boolean IsSignedIn() {
        return isSignedIn();
    }

    @SimpleFunction(description = "Get the current player")
    public void GetPlayer() {
        PlayersClient playersClient2;
        if (!isCompanionMode() && (playersClient2 = this.playersClient) != null) {
            playersClient2.getCurrentPlayer().addOnCompleteListener(this.form, new OnCompleteListener<Player>() {
                public final void onComplete(Task<Player> task) {
                    int i;
                    if (task.isSuccessful()) {
                        Player player = (Player) task.getResult();
                        long j = 0;
                        try {
                            i = player.getLevelInfo().getCurrentLevel().getLevelNumber();
                        } catch (NullPointerException unused) {
                            i = 0;
                        }
                        try {
                            j = player.getLevelInfo().getCurrentXpTotal();
                        } catch (NullPointerException unused2) {
                        }
                        player.getLevelInfo();
                        KodularGooglePlayGames.this.GotPlayer(player.getDisplayName(), player.getPlayerId(), i, j);
                    }
                }
            });
        }
    }

    @SimpleEvent(description = "User signed in.")
    public void SignedIn(boolean z) {
        EventDispatcher.dispatchEvent(this, "SignedIn", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "User signed out.")
    public void SignedOut() {
        EventDispatcher.dispatchEvent(this, "SignedOut", new Object[0]);
    }

    @SimpleEvent(description = "Got player info")
    public void GotPlayer(String str, String str2, int i, long j) {
        EventDispatcher.dispatchEvent(this, "GotPlayer", str, str2, Integer.valueOf(i), Long.valueOf(j));
    }

    private boolean isCompanionMode() {
        if (this.isCompanion) {
            try {
                new Notifier(this.componentContainer).ShowMessageDialog("Please export your project as an apk and test google play games on a real device.", "Companion error", "OK");
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        return this.isCompanion;
    }

    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this.context) != null;
    }

    private void signInSilently(final boolean z) {
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.context);
        if (GoogleSignIn.hasPermissions(lastSignedInAccount, this.signInOptions.getScopeArray())) {
            onConnected(lastSignedInAccount);
        } else {
            this.googleSignInClient.silentSignIn().addOnCompleteListener(this.form, new OnCompleteListener<GoogleSignInAccount>() {
                public final void onComplete(Task<GoogleSignInAccount> task) {
                    if (task.isSuccessful()) {
                        KodularGooglePlayGames.this.onConnected((GoogleSignInAccount) task.getResult());
                        KodularGooglePlayGames.this.SignedIn(true);
                    } else if (z) {
                        KodularGooglePlayGames.this.startSignInIntent();
                    } else {
                        KodularGooglePlayGames.this.SignedIn(false);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void startSignInIntent() {
        this.requestCode = this.form.registerForActivityResult(this);
        Log.i(LOG_TAG, "Activity Started: " + this.requestCode);
        this.form.startActivityForResult(this.googleSignInClient.getSignInIntent(), this.requestCode);
    }

    private void signOut() {
        this.googleSignInClient.signOut().addOnCompleteListener(this.form, new OnCompleteListener<Void>() {
            public final void onComplete(Task<Void> task) {
                KodularGooglePlayGames.this.SignedOut();
            }
        });
        onDisconnected();
    }

    /* access modifiers changed from: private */
    public void onConnected(GoogleSignInAccount googleSignInAccount) {
        Log.d(LOG_TAG, "Connected to Google APIs");
        this.achievementsClient = Games.getAchievementsClient(this.context, googleSignInAccount);
        this.leaderboardsClient = Games.getLeaderboardsClient(this.context, googleSignInAccount);
        this.gamesClient = Games.getGamesClient(this.context, googleSignInAccount);
        this.playersClient = Games.getPlayersClient(this.context, googleSignInAccount);
        this.gamesClient.setViewForPopups(this.form.coordinatorLayout);
        this.gamesClient.setGravityForPopups(49);
    }

    private void onDisconnected() {
        Log.d(LOG_TAG, "onDisconnected()");
        this.achievementsClient = null;
        this.leaderboardsClient = null;
        this.gamesClient = null;
    }

    public void onResume() {
        signInSilently(false);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode) {
            GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            if (signInResultFromIntent.isSuccess()) {
                onConnected(signInResultFromIntent.getSignInAccount());
                return;
            }
            Log.w(LOG_TAG, "signInResult:failed code=" + signInResultFromIntent.getStatus().getStatusCode());
            onDisconnected();
        }
    }
}

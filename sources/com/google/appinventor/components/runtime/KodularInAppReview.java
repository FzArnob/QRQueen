package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
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

@DesignerComponent(androidMinSdk = 21, category = ComponentCategory.EXPERIMENTAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/in-app-review/", iconName = "images/inappReview.png", nonVisible = true, version = 1)
@UsesLibraries({"play-core.aar", "play-core.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.FOREGROUND_SERVICE"})
public class KodularInAppReview extends AndroidNonvisibleComponent {
    /* access modifiers changed from: private */
    public boolean N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = false;
    /* access modifiers changed from: private */
    public ReviewInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final ReviewManager f185hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean testMode = false;

    public KodularInAppReview(Form form) {
        super(form);
        ReviewManager create = ReviewManagerFactory.create(form);
        this.f185hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = create;
        create.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            public final void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    ReviewInfo unused = KodularInAppReview.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (ReviewInfo) task.getResult();
                    boolean unused2 = KodularInAppReview.this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = true;
                    KodularInAppReview.this.Initialized();
                    return;
                }
                Log.e("InApp Review", "Failed to initialize: " + task.getException().getMessage());
            }
        });
        Log.d("InApp Review", "Kodular InApp Review created");
    }

    @SimpleProperty(description = "Check if the InApp Review component is ready to request a review")
    public boolean IsReady() {
        return this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT;
    }

    @SimpleEvent(description = "Use this event to check if the app is ready to request the user to review")
    public void Initialized() {
        EventDispatcher.dispatchEvent(this, "Initialized", new Object[0]);
    }

    @SimpleEvent(description = "Use this event to know when the review popup was closed (this triggers always, even if the user did not review)")
    public void ReviewClosed() {
        EventDispatcher.dispatchEvent(this, "ReviewClosed", new Object[0]);
    }

    @SimpleFunction(description = "Launch the flow for a user to review the app")
    public void RequestReview() {
        if (this.testMode) {
            final FakeReviewManager fakeReviewManager = new FakeReviewManager(this.form);
            fakeReviewManager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                public final void onComplete(Task<ReviewInfo> task) {
                    if (task.isSuccessful()) {
                        fakeReviewManager.launchReviewFlow(KodularInAppReview.this.form, (ReviewInfo) task.getResult()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            public final void onComplete(Task<Void> task) {
                                KodularInAppReview.this.ReviewClosed();
                            }
                        });
                    } else {
                        KodularInAppReview.this.ReviewClosed();
                    }
                }
            });
            return;
        }
        this.f185hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.launchReviewFlow(this.form, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).addOnCompleteListener(new OnCompleteListener<Void>() {
            public final void onComplete(Task<Void> task) {
                KodularInAppReview.this.ReviewClosed();
            }
        });
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void TestMode(boolean z) {
        this.testMode = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether it is testing mode enabled or not.")
    public boolean TestMode() {
        return this.testMode;
    }
}

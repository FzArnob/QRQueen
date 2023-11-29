package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
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
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.listener.OnSwipeTouchListener;
import de.hdodenhof.circleimageview.CircleImageView;

@DesignerComponent(category = ComponentCategory.VIEWS, description = "write in ode", iconName = "images/chatView.png", version = 3)
@UsesLibraries({"glide.jar", "circleimageview.aar", "circleimageview.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class KodularChatView extends AndroidViewComponent implements Component {
    private static final String LOG_TAG = "Kodular Chat View";
    private final Activity activity;
    private boolean automaticScrollDown;
    private int backgroundColor;
    private Drawable backgroundImageDrawable;
    /* access modifiers changed from: private */
    public ScrollView chatView;
    private LinearLayout chatViewLinearLayout;
    /* access modifiers changed from: private */
    public boolean clickable;
    private Context context;
    private int countDateTimestamp = 0;
    private int countMessages = 0;
    private Drawable defaultDrawable;
    /* access modifiers changed from: private */
    public boolean doubleTap;
    private Form form;
    private String imagePath = "";
    private boolean isCompanion = false;
    private int lastId = 0;
    private LinearLayout linearLayout;
    private float messageFontSize = 14.0f;
    private float messagesCornerRadius = 5.0f;
    private int receiversBackgroundColor;
    private int receiversMessageColor;
    private int receiversTitleColor;
    private int receiversTypefaceMessage = 0;
    private String receiversTypefaceMessageCustom = "";
    private int receiversTypefaceTitle = 0;
    private String receiversTypefaceTitleCustom = "";
    private boolean scrollbar = true;
    private int sendersBackgroundColor;
    private int sendersMessageColor;
    private int sendersTitleColor;
    private int sendersTypefaceMessage = 0;
    private String sendersTypefaceMessageCustom = "";
    private int sendersTypefaceTitle = 0;
    private String sendersTypefaceTitleCustom = "";
    private int size;
    /* access modifiers changed from: private */
    public boolean swipeable;
    private float timestampCornerRadius = 5.0f;
    private int timestampTextColor;
    private float timestapeFontSize = 14.0f;
    private float titleFontSize = 14.0f;

    public KodularChatView(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        TextViewUtil.setContext(this.context);
        CreateLayout();
        this.defaultDrawable = getView().getBackground();
        if (this.form instanceof ReplForm) {
            this.isCompanion = true;
        }
        BackgroundColor(0);
        AutomaticScrollDown(true);
        Scrollbar(true);
        Clickable(false);
        Swipeable(false);
        DoubleTap(false);
        SendersBackgroundColor(Component.COLOR_LIGHT_GREEN);
        ReceiversBackgroundColor(Component.COLOR_LIGHT_GRAY);
        SendersTitleColor(-16777216);
        ReceiversTitleColor(-16777216);
        SendersMessageColor(-16777216);
        ReceiversMessageColor(-16777216);
        TimestampTextColor(-16777216);
        SendersTypefaceTitle(0);
        SendersTypefaceMessage(0);
        SendersTypefaceTitleImport("");
        SendersTypefaceMessageImport("");
        ReceiversTypefaceTitle(0);
        ReceiversTypefaceMessage(0);
        ReceiversTypefaceTitleImport("");
        ReceiversTypefaceMessageImport("");
        TimestampFontSize(14.0f);
        TitleFontSize(14.0f);
        MessagesFontSize(14.0f);
        MessagesCornerRadius(5.0f);
        TimestampCornerRadius(5.0f);
        componentContainer.$add(this);
        Width(-2);
        Height(-2);
        Log.d(LOG_TAG, "Kodular ChatView Created");
    }

    public View getView() {
        return this.linearLayout;
    }

    @SimpleProperty
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleProperty
    public void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    private void CreateLayout() {
        this.size = KodularUnitUtil.DpToPixels(this.context, 8);
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        this.linearLayout = linearLayout2;
        linearLayout2.setOrientation(1);
        ScrollView scrollView = new ScrollView(this.activity);
        this.chatView = scrollView;
        scrollView.setSmoothScrollingEnabled(true);
        this.chatView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout3 = new LinearLayout(this.activity);
        this.chatViewLinearLayout = linearLayout3;
        linearLayout3.setOrientation(1);
        this.chatViewLinearLayout.setLayoutParams(getLayoutParams(true));
        this.chatView.addView(this.chatViewLinearLayout);
        this.linearLayout.addView(this.chatView);
    }

    private void removeMessage(int i) {
        try {
            LinearLayout view = getView(i);
            if (view == null) {
                Log.w(LOG_TAG, "Can not perfom 'remove Message'. The object is null.");
                return;
            }
            view.setVisibility(8);
            if (view.getTag() == null) {
                return;
            }
            if (view.getTag().toString().equals("addMessageLayout")) {
                this.countMessages--;
            } else {
                this.countDateTimestamp--;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private LinearLayout getView(int i) {
        try {
            return (LinearLayout) this.form.findViewById(i);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return null;
        }
    }

    private void updateMessage(String str, int i, String str2, int i2) {
        try {
            LinearLayout view = getView(i);
            if (view == null) {
                Log.w(LOG_TAG, "Can not perfom 'update Message'. The object is null.");
                return;
            }
            for (int i3 = 0; i3 < view.getChildCount(); i3++) {
                View childAt = view.getChildAt(i3);
                if ((childAt instanceof TextView) && childAt.getTag() != null && childAt.getTag().toString().equals(str)) {
                    TextView textView = (TextView) childAt;
                    textView.setTextColor(i2);
                    TextViewUtil.setTextHTML(textView, str2);
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void updateUserImage(int i, String str) {
        try {
            LinearLayout view = getView(i);
            if (view == null) {
                Log.w(LOG_TAG, "Can not perfom 'update User Image'. The object is null.");
            } else if (view.getParent() != null) {
                LinearLayout linearLayout2 = (LinearLayout) view.getParent();
                for (int i2 = 0; i2 < linearLayout2.getChildCount(); i2++) {
                    CircleImageView childAt = linearLayout2.getChildAt(i2);
                    if (childAt instanceof CircleImageView) {
                        KodularUtil.LoadPicture(this.context, str == null ? "" : str, childAt, this.isCompanion);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void addDateTimestamp(String str, int i, int i2, int i3) {
        if (str.isEmpty()) {
            Log.i(LOG_TAG, "Case 1 - Date Timestamp - Date string is empty but needed.");
            return;
        }
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setId(i3);
        this.lastId = i3;
        this.countDateTimestamp++;
        linearLayout2.setTag("timestampLayout");
        linearLayout2.setOrientation(1);
        TextView newTextView = getNewTextView("timestamp", str);
        TextViewUtil.setAlignment(newTextView, 1, true);
        LinearLayout.LayoutParams layoutParams = getLayoutParams(false);
        layoutParams.gravity = 17;
        newTextView.setLayoutParams(layoutParams);
        TextViewUtil.setFontSize(newTextView, this.timestapeFontSize);
        LinearLayout.LayoutParams layoutParams2 = getLayoutParams(false);
        int i4 = this.size;
        layoutParams2.setMargins(0, i4 / 2, 0, i4 / 2);
        layoutParams2.gravity = 17;
        setShape(linearLayout2, i2, i2 == 16777215 ? -1 : i2, this.timestampCornerRadius);
        linearLayout2.setLayoutParams(layoutParams2);
        newTextView.setTextColor(i);
        linearLayout2.addView(newTextView);
        this.chatViewLinearLayout.addView(linearLayout2);
        scrollDownIfNeeded();
    }

    private CircleImageView addCircleImage(String str, int i) {
        try {
            CircleImageView circleImageView = new CircleImageView(this.activity);
            KodularUtil.LoadPicture(this.context, str == null ? "" : str, circleImageView, this.isCompanion);
            addCircleClickListener(circleImageView, str, i);
            return circleImageView;
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return null;
        }
    }

    private void addCircleClickListener(CircleImageView circleImageView, final String str, final int i) {
        circleImageView.setClickable(true);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                KodularChatView.this.UserImageClick(i, str);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00fe A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x017f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0210  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addMessage(java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26, boolean r27, java.lang.String r28, boolean r29, com.google.appinventor.components.runtime.AndroidViewComponent r30, boolean r31, java.lang.String r32, java.lang.String r33, boolean r34) {
        /*
            r21 = this;
            r15 = r21
            r14 = r22
            r8 = r25
            r13 = r26
            boolean r0 = r28.isEmpty()
            java.lang.String r12 = "Kodular Chat View"
            if (r0 == 0) goto L_0x0018
            if (r29 == 0) goto L_0x0018
            java.lang.String r0 = "Case 1 - Imagepath is empty but needed."
            android.util.Log.i(r12, r0)
            return
        L_0x0018:
            boolean r0 = r24.isEmpty()
            if (r0 == 0) goto L_0x0026
            if (r29 != 0) goto L_0x0026
            java.lang.String r0 = "Case 2 - Message is empty but needed."
            android.util.Log.i(r12, r0)
            return
        L_0x0026:
            boolean r0 = r23.isEmpty()
            if (r0 == 0) goto L_0x0040
            boolean r0 = r24.isEmpty()
            if (r0 == 0) goto L_0x0040
            boolean r0 = r25.isEmpty()
            if (r0 == 0) goto L_0x0040
            if (r29 != 0) goto L_0x0040
            java.lang.String r0 = "Case 3 - Title, Message and Timestamp are empty but needed."
            android.util.Log.i(r12, r0)
            return
        L_0x0040:
            boolean r0 = r32.isEmpty()
            if (r0 == 0) goto L_0x0054
            boolean r0 = r33.isEmpty()
            if (r0 == 0) goto L_0x0054
            if (r34 == 0) goto L_0x0054
            java.lang.String r0 = "Case 4 - File is empty but needed."
            android.util.Log.i(r12, r0)
            return
        L_0x0054:
            android.widget.LinearLayout r11 = new android.widget.LinearLayout
            android.app.Activity r0 = r15.activity
            r11.<init>(r0)
            r11.setId(r13)
            r15.lastId = r13
            int r0 = r15.countMessages
            r1 = 1
            int r0 = r0 + r1
            r15.countMessages = r0
            java.lang.String r0 = "addMessageLayout"
            r11.setTag(r0)
            r11.setOrientation(r1)
            boolean r0 = r23.isEmpty()
            r10 = 0
            if (r0 != 0) goto L_0x009f
            java.lang.String r0 = "title"
            r7 = r23
            android.widget.TextView r0 = r15.getNewTextView(r0, r7)
            android.widget.LinearLayout$LayoutParams r1 = r15.getLayoutParams(r10)
            r0.setLayoutParams(r1)
            float r1 = r15.titleFontSize
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontSize(r0, r1)
            r3 = 0
            java.lang.String r4 = r15.sendersTypefaceTitleCustom
            java.lang.String r5 = r15.receiversTypefaceTitleCustom
            int r6 = r15.sendersTypefaceTitle
            int r2 = r15.receiversTypefaceTitle
            r1 = r21
            r16 = r2
            r2 = r27
            r7 = r16
            r1.fontHelper(r2, r3, r4, r5, r6, r7)
            r7 = r0
            goto L_0x00a0
        L_0x009f:
            r7 = 0
        L_0x00a0:
            if (r29 == 0) goto L_0x00c8
            boolean r0 = r28.isEmpty()
            if (r0 != 0) goto L_0x00c8
            r6 = r28
            androidx.appcompat.widget.AppCompatImageView r0 = r15.getNewImageView(r6)     // Catch:{ Exception -> 0x00b0 }
            r5 = r0
            goto L_0x00cb
        L_0x00b0:
            r0 = move-exception
            r1 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Unable to load image. Reason: "
            r0.<init>(r2)
            java.lang.String r1 = r1.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r12, r0)
            goto L_0x00ca
        L_0x00c8:
            r6 = r28
        L_0x00ca:
            r5 = 0
        L_0x00cb:
            if (r34 == 0) goto L_0x00f9
            boolean r0 = r32.isEmpty()
            if (r0 != 0) goto L_0x00f9
            boolean r0 = r33.isEmpty()
            if (r0 != 0) goto L_0x00f9
            r4 = r33
            androidx.appcompat.widget.AppCompatImageView r0 = r15.getNewImageView(r4)     // Catch:{ Exception -> 0x00e1 }
            r3 = r0
            goto L_0x00fc
        L_0x00e1:
            r0 = move-exception
            r1 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Unable to load video image thumbnail. Reason: "
            r0.<init>(r2)
            java.lang.String r1 = r1.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r12, r0)
            goto L_0x00fb
        L_0x00f9:
            r4 = r33
        L_0x00fb:
            r3 = 0
        L_0x00fc:
            if (r31 == 0) goto L_0x011b
            if (r30 == 0) goto L_0x011b
            android.view.View r1 = r30.getView()     // Catch:{ Exception -> 0x0110 }
            android.view.ViewParent r0 = r1.getParent()     // Catch:{ Exception -> 0x010e }
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch:{ Exception -> 0x010e }
            r0.removeView(r1)     // Catch:{ Exception -> 0x010e }
            goto L_0x0119
        L_0x010e:
            r0 = move-exception
            goto L_0x0112
        L_0x0110:
            r0 = move-exception
            r1 = 0
        L_0x0112:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            android.util.Log.e(r12, r0)
        L_0x0119:
            r0 = r1
            goto L_0x011c
        L_0x011b:
            r0 = 0
        L_0x011c:
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x0154
            java.lang.String r1 = "message"
            r2 = r24
            android.widget.TextView r1 = r15.getNewTextView(r1, r2)
            android.widget.LinearLayout$LayoutParams r9 = r15.getLayoutParams(r10)
            r1.setLayoutParams(r9)
            float r9 = r15.messageFontSize
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontSize(r1, r9)
            java.lang.String r9 = r15.sendersTypefaceMessageCustom
            java.lang.String r10 = r15.receiversTypefaceMessageCustom
            int r6 = r15.sendersTypefaceMessage
            r18 = r7
            int r7 = r15.receiversTypefaceMessage
            r19 = r1
            r1 = r21
            r2 = r27
            r13 = r3
            r3 = r19
            r4 = r9
            r9 = r5
            r5 = r10
            r10 = r18
            r1.fontHelper(r2, r3, r4, r5, r6, r7)
            r7 = r19
            goto L_0x0158
        L_0x0154:
            r13 = r3
            r9 = r5
            r10 = r7
            r7 = 0
        L_0x0158:
            r6 = 8388613(0x800005, float:1.175495E-38)
            if (r8 == 0) goto L_0x017a
            boolean r1 = r25.isEmpty()
            if (r1 != 0) goto L_0x017a
            java.lang.String r1 = "timestamp"
            android.widget.TextView r1 = r15.getNewTextView(r1, r8)
            r5 = 0
            android.widget.LinearLayout$LayoutParams r2 = r15.getLayoutParams(r5)
            r2.gravity = r6
            r1.setLayoutParams(r2)
            float r2 = r15.timestapeFontSize
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontSize(r1, r2)
            r4 = r1
            goto L_0x017c
        L_0x017a:
            r5 = 0
            r4 = 0
        L_0x017c:
            r1 = -2
            if (r9 != 0) goto L_0x0186
            if (r0 != 0) goto L_0x0186
            if (r13 == 0) goto L_0x0184
            goto L_0x0186
        L_0x0184:
            r2 = -2
            goto L_0x0191
        L_0x0186:
            boolean r2 = r21.isScreenOrientationPortrait()
            if (r2 == 0) goto L_0x018f
            r2 = 50
            goto L_0x0191
        L_0x018f:
            r2 = 35
        L_0x0191:
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            if (r2 != r1) goto L_0x0197
            r2 = -2
            goto L_0x01a1
        L_0x0197:
            int r16 = r21.displayWidth()
            int r16 = r16 / 100
            int r16 = r16 * r2
            r2 = r16
        L_0x01a1:
            r3.<init>(r2, r1)
            boolean r1 = r22.isEmpty()
            if (r1 == 0) goto L_0x01b3
            int r1 = r15.size
            int r2 = r1 / 2
            int r5 = r1 / 2
            r3.setMargins(r1, r2, r1, r5)
        L_0x01b3:
            r1 = 1056964608(0x3f000000, float:0.5)
            r3.weight = r1
            java.lang.String r1 = java.lang.String.valueOf(r27)
            java.lang.String r2 = "Send now as sender: "
            java.lang.String r1 = r2.concat(r1)
            android.util.Log.i(r12, r1)
            r5 = 8388611(0x800003, float:1.1754948E-38)
            if (r27 == 0) goto L_0x01f0
            r3.gravity = r6
            int r2 = r15.sendersBackgroundColor
            int r1 = r15.sendersTitleColor
            int r8 = r15.sendersMessageColor
            r16 = r1
            r1 = r21
            r18 = r2
            r2 = r11
            r19 = r12
            r12 = r3
            r3 = r10
            r14 = r4
            r4 = r7
            r30 = r13
            r13 = 8388611(0x800003, float:1.1754948E-38)
            r17 = 0
            r5 = r18
            r6 = r16
            r31 = r7
            r7 = r8
            r1.messageHelper(r2, r3, r4, r5, r6, r7)
            goto L_0x020e
        L_0x01f0:
            r14 = r4
            r31 = r7
            r19 = r12
            r30 = r13
            r13 = 8388611(0x800003, float:1.1754948E-38)
            r17 = 0
            r12 = r3
            r12.gravity = r13
            int r5 = r15.receiversBackgroundColor
            int r6 = r15.receiversTitleColor
            int r7 = r15.receiversMessageColor
            r1 = r21
            r2 = r11
            r3 = r10
            r4 = r31
            r1.messageHelper(r2, r3, r4, r5, r6, r7)
        L_0x020e:
            if (r14 == 0) goto L_0x0215
            int r1 = r15.timestampTextColor
            r14.setTextColor(r1)
        L_0x0215:
            r11.setLayoutParams(r12)
            if (r10 == 0) goto L_0x021d
            r11.addView(r10)
        L_0x021d:
            if (r9 == 0) goto L_0x0222
            r11.addView(r9)
        L_0x0222:
            if (r0 == 0) goto L_0x0227
            r11.addView(r0)
        L_0x0227:
            if (r30 == 0) goto L_0x022e
            r9 = r30
            r11.addView(r9)
        L_0x022e:
            r0 = r31
            if (r0 == 0) goto L_0x0235
            r11.addView(r0)
        L_0x0235:
            if (r14 == 0) goto L_0x023a
            r11.addView(r14)
        L_0x023a:
            r1 = r21
            r2 = r11
            r3 = r0
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r8 = r27
            r9 = r28
            r14 = 0
            r10 = r29
            r12 = r11
            r11 = r32
            r20 = r12
            r15 = r19
            r12 = r33
            r0 = 8388611(0x800003, float:1.1754948E-38)
            r13 = r34
            r15 = r22
            r14 = r22
            r1.addClickListener(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            if (r15 == 0) goto L_0x02e0
            boolean r1 = r22.isEmpty()
            if (r1 != 0) goto L_0x02e0
            java.lang.String r1 = "The user wants a user image near the message."
            r2 = r19
            android.util.Log.i(r2, r1)
            r2 = 0
            r1 = r21
            android.widget.LinearLayout$LayoutParams r3 = r1.getLayoutParams(r2)
            android.widget.LinearLayout r4 = new android.widget.LinearLayout
            android.app.Activity r5 = r1.activity
            r4.<init>(r5)
            r4.setOrientation(r2)
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams
            android.app.Activity r6 = r1.activity
            r7 = 45
            int r6 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r6, (int) r7)
            android.app.Activity r8 = r1.activity
            int r7 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r8, (int) r7)
            r5.<init>(r6, r7)
            int r6 = r1.size
            int r7 = r6 / 2
            r5.setMargins(r6, r7, r6, r2)
            if (r27 == 0) goto L_0x02be
            r6 = 8388613(0x800005, float:1.175495E-38)
            r3.gravity = r6
            int r0 = r1.size
            int r6 = r0 / 2
            int r7 = r0 / 2
            r3.setMargins(r0, r6, r2, r7)
            r4.setLayoutParams(r3)
            r6 = r20
            r4.addView(r6)
            r7 = r26
            de.hdodenhof.circleimageview.CircleImageView r0 = r1.addCircleImage(r15, r7)
            r4.addView(r0, r5)
            goto L_0x02da
        L_0x02be:
            r7 = r26
            r6 = r20
            r3.gravity = r0
            int r0 = r1.size
            int r8 = r0 / 2
            int r9 = r0 / 2
            r3.setMargins(r2, r8, r0, r9)
            r4.setLayoutParams(r3)
            de.hdodenhof.circleimageview.CircleImageView r0 = r1.addCircleImage(r15, r7)
            r4.addView(r0, r5)
            r4.addView(r6)
        L_0x02da:
            android.widget.LinearLayout r0 = r1.chatViewLinearLayout
            r0.addView(r4)
            goto L_0x02e9
        L_0x02e0:
            r1 = r21
            r6 = r20
            android.widget.LinearLayout r0 = r1.chatViewLinearLayout
            r0.addView(r6)
        L_0x02e9:
            r21.scrollDownIfNeeded()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularChatView.addMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, boolean, java.lang.String, boolean, com.google.appinventor.components.runtime.AndroidViewComponent, boolean, java.lang.String, java.lang.String, boolean):void");
    }

    private void scrollDownIfNeeded() {
        if (this.automaticScrollDown) {
            this.chatView.post(new Runnable() {
                public final void run() {
                    KodularChatView.this.chatView.fullScroll(130);
                }
            });
        }
    }

    private TextView getNewTextView(String str, String str2) {
        TextView textView = new TextView(this.activity);
        textView.setTag(str);
        TextViewUtil.setTextHTML(textView, str2);
        int i = this.size;
        textView.setPadding(i, i / 5, i, i / 5);
        return textView;
    }

    private AppCompatImageView getNewImageView(String str) {
        AppCompatImageView appCompatImageView = new AppCompatImageView(this.activity);
        Context context2 = this.context;
        if (str == null) {
            str = "";
        }
        KodularUtil.LoadPicture(context2, str, appCompatImageView, this.isCompanion);
        appCompatImageView.setLayoutParams(getLayoutParams(true));
        int i = this.size;
        appCompatImageView.setPadding(i, i / 5, i, i / 5);
        return appCompatImageView;
    }

    private LinearLayout.LayoutParams getLayoutParams(boolean z) {
        if (z) {
            return new LinearLayout.LayoutParams(-1, -1);
        }
        return new LinearLayout.LayoutParams(-2, -2);
    }

    private void messageHelper(LinearLayout linearLayout2, TextView textView, TextView textView2, int i, int i2, int i3) {
        setShape(linearLayout2, i, i == 16777215 ? -1 : i, this.messagesCornerRadius);
        if (textView != null) {
            textView.setTextColor(i2);
        }
        if (textView2 != null) {
            textView2.setTextColor(i3);
        }
    }

    private void scrollDown(int i) {
        try {
            LinearLayout view = getView(i);
            if (view == null) {
                Log.w(LOG_TAG, "Can not perfom 'scroll Down'. The object is null.");
            } else {
                this.chatView.smoothScrollTo(0, view.getBottom() + (this.size / 2));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private void updateAppearance() {
        Drawable drawable = this.backgroundImageDrawable;
        if (drawable != null) {
            ViewUtil.setBackgroundImage(this.linearLayout, drawable);
        } else if (this.backgroundColor == 0) {
            ViewUtil.setBackgroundDrawable(this.linearLayout, this.defaultDrawable);
        } else {
            ViewUtil.setBackgroundDrawable(this.linearLayout, (Drawable) null);
            this.linearLayout.setBackgroundColor(this.backgroundColor);
        }
    }

    private int displayWidth() {
        return this.context.getResources().getDisplayMetrics().widthPixels;
    }

    private void setShape(View view, int i, int i2, float f) {
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(KodularUnitUtil.DpToPixels((Context) this.activity, f));
            gradientDrawable.setColor(i);
            gradientDrawable.setStroke(2, i2);
            view.setBackground(gradientDrawable);
            view.invalidate();
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    private boolean isScreenOrientationPortrait() {
        return this.context.getResources().getConfiguration().orientation == 1;
    }

    private void fontHelper(boolean z, TextView textView, String str, String str2, int i, int i2) {
        if (z) {
            if (str == null || str.isEmpty()) {
                TextViewUtil.setFontTypeface(textView, i, false, false);
            } else {
                TextViewUtil.setCustomFontTypeface(this.form, textView, str, false, false);
            }
        } else if (str2 == null || str2.isEmpty()) {
            TextViewUtil.setFontTypeface(textView, i2, false, false);
        } else {
            TextViewUtil.setCustomFontTypeface(this.form, textView, str2, false, false);
        }
    }

    private void addClickListener(LinearLayout linearLayout2, TextView textView, String str, String str2, String str3, int i, boolean z, String str4, boolean z2, String str5, String str6, boolean z3, String str7) {
        final String str8 = str7;
        final String str9 = str;
        final String str10 = str2;
        final String str11 = str3;
        final int i2 = i;
        final boolean z4 = z;
        final String str12 = str4;
        final boolean z5 = z2;
        final String str13 = str5;
        final String str14 = str6;
        final boolean z6 = z3;
        LinearLayout linearLayout3 = linearLayout2;
        linearLayout3.setOnTouchListener(new OnSwipeTouchListener(this.activity) {
            public final void onSwipeTop() {
                if (KodularChatView.this.swipeable) {
                    KodularChatView.this.Swipe(3, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeRight() {
                if (KodularChatView.this.swipeable) {
                    KodularChatView.this.Swipe(2, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeLeft() {
                if (KodularChatView.this.swipeable) {
                    KodularChatView.this.Swipe(1, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onSwipeBottom() {
                if (KodularChatView.this.swipeable) {
                    KodularChatView.this.Swipe(4, str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onClick() {
                if (KodularChatView.this.clickable) {
                    KodularChatView.this.Click(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onLongClick() {
                if (KodularChatView.this.clickable) {
                    KodularChatView.this.LongClick(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }

            public final void onDoubleClick() {
                if (KodularChatView.this.doubleTap) {
                    KodularChatView.this.DoubleTapClick(str8, str9, str10, str11, i2, z4, str12, z5, str13, str14, z6);
                }
            }
        });
    }

    @SimpleFunction(description = "Scroll to a specific message in the chat view with the given id.")
    public void ScrollTo(int i) {
        scrollDown(i);
    }

    @SimpleFunction(description = "Returns the last used id.")
    public int GetLastUsedId() {
        return this.lastId;
    }

    @SimpleFunction(description = "Returns the number of all messages. 'Date Timestamp' messages are not included.")
    public int CountMessages() {
        return this.countMessages;
    }

    @SimpleFunction(description = "Returns the number of all date timestamps. Normal messages are not included.")
    public int CountDateTimestamp() {
        return this.countDateTimestamp;
    }

    @SimpleFunction(description = "Add a new simple message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddMessage(String str, String str2, String str3, String str4, int i, boolean z) {
        addMessage(str, str2, str3, str4, i, z, "", false, (AndroidViewComponent) null, false, "", "", false);
    }

    @SimpleFunction(description = "Add a new simple image message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddImageMessage(String str, String str2, String str3, String str4, String str5, int i, boolean z) {
        addMessage(str, str2, str3, str5, i, z, str4, true, (AndroidViewComponent) null, false, "", "", false);
    }

    @SimpleFunction(description = "Add a new simple file message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty.")
    public void AddFileMessage(String str, String str2, String str3, String str4, String str5, String str6, int i, boolean z) {
        addMessage(str, str2, str3, str6, i, z, "", false, (AndroidViewComponent) null, false, str4, str5, true);
    }

    @SimpleFunction(description = "Add a new simple date timestamp into the chat view. You NEED to write a date, else this block will do nothing. This means the field 'date' can NOT be empty. Timestamp messages are not clickable.")
    public void AddDateTimestamp(String str, int i, int i2, int i3) {
        addDateTimestamp(str, i, i2, i3);
    }

    @SimpleFunction(description = "Add a new simple component message into the chat view. If you do not want a user image or title or message or timestamp, then let the field empty. Make sure that the component is VISIBLE on the screen when you try to add it here. It will be then removed automatic from the screen and only visible again in the chat view.")
    public void AddComponentMessage(String str, String str2, String str3, String str4, AndroidViewComponent androidViewComponent, int i, boolean z) {
        addMessage(str, str2, str3, str4, i, z, "", false, androidViewComponent, true, "", "", false);
    }

    @SimpleFunction(description = "Update the title content of a chat view message.")
    public void UpdateTitleContent(int i, String str, int i2) {
        updateMessage("title", i, str, i2);
    }

    @SimpleFunction(description = "Update the message content of a chat view message.")
    public void UpdateMessageContent(int i, String str, int i2) {
        updateMessage("message", i, str, i2);
    }

    @SimpleFunction(description = "Update the timestamp content of a chat view message.")
    public void UpdateTimestampContent(int i, String str, int i2) {
        updateMessage("timestamp", i, str, i2);
    }

    @SimpleFunction(description = "Update the user image of a chat view message. The image can only be updated if there was before a old image.")
    public void UpdateUserImage(int i, String str) {
        updateUserImage(i, str);
    }

    @SimpleFunction(description = "Update the background color of a chat view message.")
    public void UpdateMessageBackgroundColor(int i, int i2) {
        try {
            LinearLayout view = getView(i);
            if (view == null) {
                Log.w(LOG_TAG, "Can not perfom 'Update Message Background Color'. The object is null.");
                return;
            }
            if (i2 == 0) {
                ViewUtil.setBackgroundDrawable(view, this.defaultDrawable);
            } else {
                ViewUtil.setBackgroundDrawable(view, (Drawable) null);
                view.setBackgroundColor(i2);
            }
            float f = 12.0f;
            if (view.getTag() != null && view.getTag().toString().equals("timestampLayout")) {
                f = 10.0f;
            }
            setShape(view, i2, i2 == 16777215 ? -1 : i2, f);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a message or timestamp from the chat view.")
    public void RemoveMessage(int i) {
        removeMessage(i);
    }

    @SimpleFunction(description = "Removes all messages and timestamps from the chat view.")
    public void ClearChatView() {
        try {
            if (this.chatViewLinearLayout.getChildCount() > 0) {
                this.chatViewLinearLayout.removeAllViews();
                this.countMessages = 0;
                this.countDateTimestamp = 0;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @DesignerProperty(defaultValue = "&HFF8BC24A", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for sender's messages.")
    public void SendersBackgroundColor(int i) {
        this.sendersBackgroundColor = i;
    }

    @SimpleProperty
    public int SendersBackgroundColor() {
        return this.sendersBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for sender's title messages.")
    public void SendersTitleColor(int i) {
        this.sendersTitleColor = i;
    }

    @SimpleProperty
    public int SendersTitleColor() {
        return this.sendersTitleColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for sender's messages.")
    public void SendersMessageColor(int i) {
        this.sendersMessageColor = i;
    }

    @SimpleProperty
    public int SendersMessageColor() {
        return this.sendersMessageColor;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for receiver's messages.")
    public void ReceiversBackgroundColor(int i) {
        this.receiversBackgroundColor = i;
    }

    @SimpleProperty
    public int ReceiversBackgroundColor() {
        return this.receiversBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for receivers's title messages.")
    public void ReceiversTitleColor(int i) {
        this.receiversTitleColor = i;
    }

    @SimpleProperty
    public int ReceiversTitleColor() {
        return this.receiversTitleColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for receivers's messages.")
    public void ReceiversMessageColor(int i) {
        this.receiversMessageColor = i;
    }

    @SimpleProperty
    public int ReceiversMessageColor() {
        return this.receiversMessageColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Specifies the color for the timestamp.")
    public void TimestampTextColor(int i) {
        this.timestampTextColor = i;
    }

    @SimpleProperty
    public int TimestampTextColor() {
        return this.timestampTextColor;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar.")
    public void Scrollbar(boolean z) {
        this.scrollbar = z;
        this.chatView.setVerticalScrollBarEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Scrollbar() {
        return this.scrollbar;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true the chat view will scroll down when a new message was send.")
    public void AutomaticScrollDown(boolean z) {
        this.automaticScrollDown = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean AutomaticScrollDown() {
        return this.automaticScrollDown;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the component's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the component's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the component's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(String str) {
        if (!str.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            if (str == null) {
                str = "";
            }
            this.imagePath = str;
            this.backgroundImageDrawable = null;
            if (str.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception e) {
                    Log.e(LOG_TAG, String.valueOf(e));
                }
            }
            updateAppearance();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Image() {
        return this.imagePath;
    }

    @SimpleEvent(description = "Click listener event for the user image.")
    public void UserImageClick(int i, String str) {
        EventDispatcher.dispatchEvent(this, "UserImageClick", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Click listener event.")
    public void Click(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        EventDispatcher.dispatchEvent(this, "Click", str, str2, str3, str4, Integer.valueOf(i), Boolean.valueOf(z), str5, Boolean.valueOf(z2), str6, str7, Boolean.valueOf(z3));
    }

    @SimpleEvent(description = "Long click listener event.")
    public void LongClick(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        EventDispatcher.dispatchEvent(this, "LongClick", str, str2, str3, str4, Integer.valueOf(i), Boolean.valueOf(z), str5, Boolean.valueOf(z2), str6, str7, Boolean.valueOf(z3));
    }

    @SimpleEvent(description = "Double tap click listener event.")
    public void DoubleTapClick(String str, String str2, String str3, String str4, int i, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        EventDispatcher.dispatchEvent(this, "DoubleTapClick", str, str2, str3, str4, Integer.valueOf(i), Boolean.valueOf(z), str5, Boolean.valueOf(z2), str6, str7, Boolean.valueOf(z3));
    }

    @SimpleEvent(description = "Swipe listener event. The direction value returns '1' for right-to-left swipes, '2' for left-to-right swipes '3' for bottom-to-top swipes and '4' for top-to-bottom swipes")
    public void Swipe(int i, String str, String str2, String str3, String str4, int i2, boolean z, String str5, boolean z2, String str6, String str7, boolean z3) {
        EventDispatcher.dispatchEvent(this, "Swipe", Integer.valueOf(i), str, str2, str3, str4, Integer.valueOf(i2), Boolean.valueOf(z), str5, Boolean.valueOf(z2), str6, str7, Boolean.valueOf(z3));
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public void Clickable(boolean z) {
        this.clickable = z;
    }

    @SimpleProperty
    public boolean Clickable() {
        return this.clickable;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Set the component enabled for double taps or not.")
    public void DoubleTap(boolean z) {
        this.doubleTap = z;
    }

    @SimpleProperty
    public boolean DoubleTap() {
        return this.doubleTap;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true you can swipe with your fingers through views.")
    public void Swipeable(boolean z) {
        this.swipeable = z;
    }

    @SimpleProperty
    public boolean Swipeable() {
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceTitle(int i) {
        this.sendersTypefaceTitle = i;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceMessage(int i) {
        this.sendersTypefaceMessage = i;
    }

    @DesignerProperty(editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceTitleImport(String str) {
        if (str != null) {
            this.sendersTypefaceTitleCustom = str;
        }
    }

    @DesignerProperty(editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SendersTypefaceMessageImport(String str) {
        if (str != null) {
            this.sendersTypefaceMessageCustom = str;
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceTitle(int i) {
        this.receiversTypefaceTitle = i;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceMessage(int i) {
        this.receiversTypefaceMessage = i;
    }

    @DesignerProperty(editorType = "asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceTitleImport(String str) {
        if (str != null) {
            this.receiversTypefaceTitleCustom = str;
        }
    }

    @DesignerProperty(editorType = "asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void ReceiversTypefaceMessageImport(String str) {
        if (str != null) {
            this.receiversTypefaceMessageCustom = str;
        }
    }

    @DesignerProperty(defaultValue = "5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MessagesCornerRadius(float f) {
        this.messagesCornerRadius = f;
    }

    @DesignerProperty(defaultValue = "5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TimestampCornerRadius(float f) {
        this.timestampCornerRadius = f;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TimestampFontSize(float f) {
        this.timestapeFontSize = f;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void TitleFontSize(float f) {
        this.titleFontSize = f;
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MessagesFontSize(float f) {
        this.messageFontSize = f;
    }
}

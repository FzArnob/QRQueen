package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.KodularRippleDrawable;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "write in ode", version = 4)
@UsesLibraries(libraries = "cardview.jar, cardview.aar")
@SimpleObject
public class MakeroidCardView extends AndroidViewComponent implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, ComponentContainer {
    private int KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_LIGHT_GRAY;
    private float PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = 0.0f;
    private int SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = 8;
    private int SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = Component.COLOR_LIGHT_GRAY;
    private int XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = 8;
    private final Activity activity;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler = new Handler();
    private int backgroundColor = -1;
    private ComponentContainer container;
    private Context context;
    private int eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = 8;
    private int horizontalAlignment = 1;
    private final CardViewHelper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private final ViewGroup mainLayout;
    private int muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = 8;
    private float oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = 2.0f;
    private int verticalAlignment = 1;
    private final LinearLayout viewLayout;
    private boolean xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = false;
    private float yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = 2.0f;

    public MakeroidCardView(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        CardViewHelper cardViewHelper = new CardViewHelper(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = cardViewHelper;
        cardViewHelper.setClickable(true);
        cardViewHelper.setLongClickable(true);
        cardViewHelper.setFocusable(true);
        cardViewHelper.setOnClickListener(this);
        cardViewHelper.setOnLongClickListener(this);
        cardViewHelper.setOnTouchListener(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int DpToPixels = KodularUnitUtil.DpToPixels(this.context, 8);
        int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, 4);
        layoutParams.setMargins(DpToPixels, DpToPixels2, DpToPixels, DpToPixels2);
        cardViewHelper.setLayoutParams(layoutParams);
        cardViewHelper.setUseCompatPadding(true);
        cardViewHelper.setPreventCornerOverlap(false);
        LinearLayout linearLayout = new LinearLayout(this.context, 1, 100, 100);
        this.viewLayout = linearLayout;
        linearLayout.setBaselineAligned(false);
        AlignmentUtil alignmentUtil = new AlignmentUtil(linearLayout);
        this.alignmentSetter = alignmentUtil;
        alignmentUtil.setHorizontalAlignment(this.horizontalAlignment);
        this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
        cardViewHelper.addView(linearLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(this.context);
        this.mainLayout = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        frameLayout.addView(cardViewHelper);
        componentContainer.$add(this);
        BackgroundColor(this.backgroundColor);
        UpdateCard();
        CornerRadius(this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT);
        Elevation(this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        FullClickable(this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt);
        StrokeWidth(this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC);
        StrokeColor(this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3);
        TouchColor(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
        Log.d("Card View", "Card View Created");
    }

    public View getView() {
        return this.mainLayout;
    }

    public Activity $context() {
        return this.activity;
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        setChildWidth(androidViewComponent, i, 0);
    }

    public LinearLayout getViewLayout() {
        return this.viewLayout;
    }

    public CardViewHelper getViewHelper() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void setChildWidth(final AndroidViewComponent androidViewComponent, final int i, final int i2) {
        int Width = this.container.$form().Width();
        if (Width == 0 && i2 < 2) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    MakeroidCardView.this.setChildWidth(androidViewComponent, i, i2 + 1);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Width * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastWidth(i);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent.getView(), i);
    }

    public void setChildHeight(final AndroidViewComponent androidViewComponent, final int i) {
        int Height = this.container.$form().Height();
        if (Height == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    MakeroidCardView.this.setChildHeight(androidViewComponent, i);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Height * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastHeight(i);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent.getView(), i);
    }

    public void onClick(View view) {
        Click();
    }

    public boolean onLongClick(View view) {
        LongClick();
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            TouchDown();
            return false;
        } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return false;
        } else {
            TouchUp();
            return false;
        }
    }

    public void UpdateCard() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentPadding(KodularUnitUtil.DpToPixels(this.context, this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN), KodularUnitUtil.DpToPixels(this.context, this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS), KodularUnitUtil.DpToPixels(this.context, this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08), KodularUnitUtil.DpToPixels(this.context, this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Click\".")
    public void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Long click\".")
    public void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch up on the card.")
    public void TouchUp() {
        EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch down on the card.")
    public void TouchDown() {
        EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the card view background color.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCardBackgroundColor(i);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The corner radius from the card view.")
    public void CornerRadius(float f) {
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = f;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRadius(KodularUnitUtil.DpToPixels(this.context, f));
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The card view elevation value.")
    public void Elevation(float f) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = f;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCardElevation(KodularUnitUtil.DpToPixels(this.context, f));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, f));
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the left padding between the card view edges and the children of card view.")
    public void ContentPaddingLeft(int i) {
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the top padding between the card view edges and the children of card view.")
    public void ContentPaddingTop(int i) {
        this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the right padding between the card view edges and the children of card view.")
    public void ContentPaddingRight(int i) {
        this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the bottom padding between the card view edges and the children of CardView.")
    public void ContentPaddingBottom(int i) {
        this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public void FullClickable(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fullClick = z;
        this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = z;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the touch color also known as ripple color to the card view component.", userVisible = false)
    public void TouchColor(int i) {
        if (i != 0) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = i;
        } else {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_LIGHT_GRAY;
        }
        KodularRippleDrawable.setRippleDrawable(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.backgroundColor, i);
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "The stroke width for the card view.")
    public void StrokeWidth(float f) {
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = f;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, f));
    }

    @SimpleProperty(description = "Returns the stroke width from the card view.")
    public float StrokeWidth() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "The stroke color for the card view.")
    public void StrokeColor(int i) {
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeColor(i);
    }

    @SimpleProperty(description = "Returns the stroke color from the card view.")
    public int StrokeColor() {
        return this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;
    }

    @SimpleProperty
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty
    public float CornerRadius() {
        return this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;
    }

    @SimpleProperty
    public float Elevation() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @SimpleProperty
    public int ContentPaddingLeft() {
        return this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN;
    }

    @SimpleProperty
    public int ContentPaddingTop() {
        return this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS;
    }

    @SimpleProperty
    public int ContentPaddingRight() {
        return this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08;
    }

    @SimpleProperty
    public int ContentPaddingBottom() {
        return this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk;
    }

    @SimpleProperty
    public boolean FullClickable() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fullClick;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(int i) {
        try {
            this.alignmentSetter.setHorizontalAlignment(i);
            this.horizontalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(int i) {
        try {
            this.alignmentSetter.setVerticalAlignment(i);
            this.verticalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    public static class CardViewHelper extends MaterialCardView {
        public boolean fullClick = false;

        public CardViewHelper(Context context) {
            super(context);
        }

        public CardViewHelper(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public CardViewHelper(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return this.fullClick;
        }
    }
}

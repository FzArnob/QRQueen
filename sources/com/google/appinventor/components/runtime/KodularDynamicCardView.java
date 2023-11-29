package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularRippleDrawable;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic cardviews in Arrangements.", iconName = "images/cardview.png", nonVisible = true, version = 3)
public final class KodularDynamicCardView extends AndroidNonvisibleComponent {
    private int KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_LIGHT_GRAY;
    private final String LOG_TAG = "KodularDynamicCardView";
    private float PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = 1.0f;
    private int SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = 8;
    private int SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = Component.COLOR_LIGHT_GRAY;
    private int XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = 8;
    private int backgroundColor = -1;
    private Context context;
    private int eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = 8;
    private Form form;
    private boolean fullClick = false;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();
    private int muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = 8;
    private float oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = 2.0f;
    private float yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = 2.0f;

    public KodularDynamicCardView(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
    }

    @SimpleFunction(description = "Create a new card view component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateCardView(final int i, AndroidViewComponent androidViewComponent, int i2, int i3) {
        try {
            KodularCardView kodularCardView = new KodularCardView(this.context);
            int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i2);
            int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i3);
            if (i2 == -1) {
                DpToPixels = -2;
            } else if (i2 == -2) {
                DpToPixels = -1;
            }
            if (i3 == -1) {
                DpToPixels2 = -2;
            } else if (i3 == -2) {
                DpToPixels2 = -1;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DpToPixels, DpToPixels2);
            int DpToPixels3 = KodularUnitUtil.DpToPixels(this.context, 8);
            int DpToPixels4 = KodularUnitUtil.DpToPixels(this.context, 4);
            layoutParams.setMargins(DpToPixels3, DpToPixels4, DpToPixels3, DpToPixels4);
            kodularCardView.setLayoutParams(layoutParams);
            LinearLayout linearLayout = new LinearLayout(this.context);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.kodularDynamicModelList.add(new KodularDynamicModel(i, kodularCardView, androidViewComponent, linearLayout));
            kodularCardView.addView(linearLayout);
            ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(kodularCardView);
            kodularCardView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    KodularDynamicCardView.this.Click(i);
                }
            });
            kodularCardView.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    KodularDynamicCardView.this.LongClick(i);
                    return true;
                }
            });
            kodularCardView.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        KodularDynamicCardView.this.TouchDown(i);
                        return false;
                    } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                        return false;
                    } else {
                        KodularDynamicCardView.this.TouchUp(i);
                        return false;
                    }
                }
            });
            kodularCardView.setClickable(true);
            kodularCardView.setUseCompatPadding(true);
            kodularCardView.setPreventCornerOverlap(false);
            kodularCardView.setCardBackgroundColor(this.backgroundColor);
            kodularCardView.setRadius(KodularUnitUtil.DpToPixels(this.context, this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT));
            kodularCardView.setCardElevation(KodularUnitUtil.DpToPixels(this.context, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS));
            kodularCardView.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS));
            kodularCardView.fullClick = this.fullClick;
            kodularCardView.setContentPadding(KodularUnitUtil.DpToPixels(this.context, this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN), KodularUnitUtil.DpToPixels(this.context, this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS), KodularUnitUtil.DpToPixels(this.context, this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08), KodularUnitUtil.DpToPixels(this.context, this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk));
            kodularCardView.setStrokeColor(this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3);
            kodularCardView.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC));
            KodularRippleDrawable.setRippleDrawable(kodularCardView, this.backgroundColor, this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
            kodularCardView.invalidate();
        } catch (Exception e) {
            Log.e("KodularDynamicCardView", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Add a component into the card view component with the given id.")
    public final void AddComponentToCardView(int i, AndroidViewComponent androidViewComponent) {
        try {
            KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            LinearLayout linearLayout = (LinearLayout) KodularDynamicUtil.getChildViewHolderById(i, this.kodularDynamicModelList);
            try {
                View view = androidViewComponent.getView();
                ((ViewGroup) view.getParent()).removeView(view);
                linearLayout.addView(view);
                kodularCardView.invalidate();
            } catch (Exception e) {
                Log.e("KodularDynamicCardView", String.valueOf(e));
                try {
                    ViewGroup viewGroup = (ViewGroup) androidViewComponent.getView();
                    ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
                    linearLayout.addView(viewGroup);
                    kodularCardView.invalidate();
                } catch (Exception e2) {
                    Log.e("KodularDynamicCardView", String.valueOf(e2));
                }
            }
        } catch (Exception e3) {
            Log.e("KodularDynamicCardView", String.valueOf(e3));
        }
    }

    @SimpleFunction(description = "Remove a card view component with the given id.")
    public final void DeleteCardView(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("KodularDynamicCardView", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the card view referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetCardViewById(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(kodularCardView);
        }
        return null;
    }

    @SimpleFunction(description = "Set the background color of a card view component.")
    public final void SetBackgroundColor(int i, int i2) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setCardBackgroundColor(i2);
            kodularCardView.invalidate();
        }
    }

    @SimpleFunction(description = "Get the background color of a card view component.")
    public final int GetBackgroundColor(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return kodularCardView.getCardBackgroundColor().getDefaultColor();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the corner radius of a card view component.")
    public final void SetCornerRadius(int i, float f) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setRadius(KodularUnitUtil.DpToPixels(this.context, f));
        }
    }

    @SimpleFunction(description = "Get the corner radius of a card view component.")
    public final int GetCornerRadius(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return (int) kodularCardView.getRadius();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the elevation of a card view component.")
    public final void SetElevation(int i, float f) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setCardElevation(KodularUnitUtil.DpToPixels(this.context, f));
            kodularCardView.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, f));
        }
    }

    @SimpleFunction(description = "Get the elevation of a card view component.")
    public final int GetElevation(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return (int) kodularCardView.getCardElevation();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the content padding of a card view component.")
    public final void ContentPadding(int i, int i2, int i3, int i4, int i5) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setContentPadding(KodularUnitUtil.DpToPixels(this.context, i2), KodularUnitUtil.DpToPixels(this.context, i3), KodularUnitUtil.DpToPixels(this.context, i4), KodularUnitUtil.DpToPixels(this.context, i5));
        }
    }

    @SimpleFunction(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public final void FullClickable(int i, boolean z) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.fullClick = z;
        }
    }

    @SimpleFunction(description = "Set the width of a card view component.")
    public final void SetWidth(int i, int i2) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) kodularCardView.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i2);
            kodularCardView.setLayoutParams(layoutParams);
            kodularCardView.invalidate();
        }
    }

    @SimpleFunction(description = "Get the width of a card view component.")
    public final int GetWidth(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return kodularCardView.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the height of a card view component.")
    public final void SetHeight(int i, int i2) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) kodularCardView.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i2);
            kodularCardView.setLayoutParams(layoutParams);
            kodularCardView.invalidate();
        }
    }

    @SimpleFunction(description = "Get the height of a card view component.")
    public final int GetHeight(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            return kodularCardView.getHeight();
        }
        return 0;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the cards's background color.")
    public final void BackgroundColor(int i) {
        this.backgroundColor = i;
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The corner radius from the card view.")
    public final void CornerRadius(float f) {
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = f;
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The card view elevation value.")
    public final void Elevation(float f) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = f;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public final void FullClickable(boolean z) {
        this.fullClick = z;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the left padding between the card's edges and the children of card view.")
    public final void ContentPaddingLeft(int i) {
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = i;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the top padding between the card's edges and the children of card view.")
    public final void ContentPaddingTop(int i) {
        this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = i;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the right padding between the card's edges and the children of card view.")
    public final void ContentPaddingRight(int i) {
        this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = i;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the bottom padding between the card's edges and the children of card view.")
    public final void ContentPaddingBottom(int i) {
        this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = i;
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple click on a card view component with the specific id.")
    public final void Click(int i) {
        EventDispatcher.dispatchEvent(this, "Click", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple long click on a card view component with the specific id.")
    public final void LongClick(int i) {
        EventDispatcher.dispatchEvent(this, "LongClick", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch up on a card view component with the specific id.")
    public final void TouchUp(int i) {
        EventDispatcher.dispatchEvent(this, "TouchUp", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch down on a card view component with the specific id.")
    public final void TouchDown(int i) {
        EventDispatcher.dispatchEvent(this, "TouchDown", Integer.valueOf(i));
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the touch color also known as ripple color to a card view component.")
    public final void TouchColor(int i) {
        if (i != 0) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = i;
        } else {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_LIGHT_GRAY;
        }
    }

    @DesignerProperty(defaultValue = "1", editorType = "non_negative_float")
    @SimpleProperty(description = "The stroke width for the card view.")
    public final void StrokeWidth(float f) {
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = f;
    }

    @SimpleFunction(description = "Set the stroke width to a card view component with the specific id.")
    public final void StrokeWidth(int i, float f) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, f));
        }
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "The stroke width for the card view.")
    public final void StrokeColor(int i) {
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = i;
    }

    @SimpleFunction(description = "Set the stroke color to a card view component with the specific id.")
    public final void StrokeColor(int i, int i2) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (kodularCardView != null) {
            kodularCardView.setStrokeColor(i2);
        }
    }

    public class KodularCardView extends MaterialCardView {
        public boolean fullClick = false;

        public KodularCardView(Context context) {
            super(context);
        }

        public KodularCardView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public KodularCardView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return this.fullClick;
        }
    }
}

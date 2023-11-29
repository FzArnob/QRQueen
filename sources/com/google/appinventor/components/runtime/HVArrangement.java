package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.HorizontalAlignment;
import com.google.appinventor.components.common.VerticalAlignment;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
public class HVArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    /* access modifiers changed from: private */
    public AlphaAnimation B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private final int JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
    private boolean Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY = false;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler = new Handler();
    private int backgroundColor;
    private Drawable backgroundImageDrawable;
    private boolean clickable = false;
    private boolean eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC = false;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.Left;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f159hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ViewGroup f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AlphaAnimation f161hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String imagePath = "";
    private boolean scrollable = false;
    private boolean scrollbar = true;
    private int size = 8;
    private VerticalAlignment verticalAlignment = VerticalAlignment.Top;
    public final LinearLayout viewLayout;

    @Deprecated
    @SimpleProperty(description = "", userVisible = false)
    public void FullClickable(boolean z) {
    }

    public HVArrangement(ComponentContainer componentContainer, int i, boolean z) {
        super(componentContainer);
        Activity $context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context;
        this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = i;
        this.scrollable = z;
        LinearLayout linearLayout = new LinearLayout($context, i, 100, 100);
        this.viewLayout = linearLayout;
        linearLayout.setBaselineAligned(false);
        AlignmentUtil alignmentUtil = new AlignmentUtil(linearLayout);
        this.alignmentSetter = alignmentUtil;
        alignmentUtil.setHorizontalAlignment(this.horizontalAlignment);
        this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
        if (!z) {
            Log.d("HVArrangement", "Setting up frameContainer = FrameLayout()");
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new FrameLayout($context);
        } else if (i == 0) {
            Log.d("HVArrangement", "Setting up frameContainer = HorizontalScrollView()");
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HorizontalScrollView($context);
        } else if (i == 1) {
            Log.d("HVArrangement", "Setting up frameContainer = ScrollView()");
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ScrollView($context);
        }
        this.size = KodularUnitUtil.DpToPixels((Context) $context, this.size);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(linearLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
        this.f159hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getView().getBackground();
        componentContainer.$add(this);
        BackgroundColor(0);
        setAnimation();
        UseRoundCard(false);
        isCard(false);
        Clickable(false);
    }

    public Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
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

    public void setChildWidth(final AndroidViewComponent androidViewComponent, final int i, final int i2) {
        int Width = this.container.$form().Width();
        if (Width == 0 && i2 < 2) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    Log.d("HVArrangement", "(HVArrangement)Width not stable yet... trying again");
                    HVArrangement.this.setChildWidth(androidViewComponent, i, i2 + 1);
                }
            }, 100);
        }
        if (i <= -1000) {
            Log.d("HVArrangement", "HVArrangement.setChildWidth(): width = " + i + " parent Width = " + Width + " child = " + androidViewComponent);
            i = (Width * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastWidth(i);
        if (this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd == 0) {
            ViewUtil.setChildWidthForHorizontalLayout(androidViewComponent.getView(), i);
        } else {
            ViewUtil.setChildWidthForVerticalLayout(androidViewComponent.getView(), i);
        }
    }

    public void setChildHeight(final AndroidViewComponent androidViewComponent, final int i) {
        int Height = this.container.$form().Height();
        if (Height == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    Log.d("HVArrangement", "(HVArrangement)Height not stable yet... trying again");
                    HVArrangement.this.setChildHeight(androidViewComponent, i);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Height * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastHeight(i);
        if (this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd == 0) {
            ViewUtil.setChildHeightForHorizontalLayout(androidViewComponent.getView(), i);
        } else {
            ViewUtil.setChildHeightForVerticalLayout(androidViewComponent.getView(), i);
        }
    }

    @SimpleProperty
    public void Width(int i) {
        super.Width(i);
        if (i == -2) {
            ViewGroup viewGroup = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup instanceof ScrollView) {
                ((ScrollView) viewGroup).setFillViewport(true);
            }
            ViewGroup viewGroup2 = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup2 instanceof HorizontalScrollView) {
                ((HorizontalScrollView) viewGroup2).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void WidthPercent(int i) {
        super.WidthPercent(i);
        if (i == 100) {
            ViewGroup viewGroup = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup instanceof ScrollView) {
                ((ScrollView) viewGroup).setFillViewport(true);
            }
            ViewGroup viewGroup2 = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup2 instanceof HorizontalScrollView) {
                ((HorizontalScrollView) viewGroup2).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void Height(int i) {
        super.Height(i);
        if (i == -2) {
            ViewGroup viewGroup = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup instanceof ScrollView) {
                ((ScrollView) viewGroup).setFillViewport(true);
            }
            ViewGroup viewGroup2 = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup2 instanceof HorizontalScrollView) {
                ((HorizontalScrollView) viewGroup2).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void HeightPercent(int i) {
        super.HeightPercent(i);
        if (i == 100) {
            ViewGroup viewGroup = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup instanceof ScrollView) {
                ((ScrollView) viewGroup).setFillViewport(true);
            }
            ViewGroup viewGroup2 = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (viewGroup2 instanceof HorizontalScrollView) {
                ((HorizontalScrollView) viewGroup2).setFillViewport(true);
            }
        }
    }

    public View getView() {
        return this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @Options(HorizontalAlignment.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public int AlignHorizontal() {
        return AlignHorizontalAbstract().toUnderlyingValue().intValue();
    }

    public HorizontalAlignment AlignHorizontalAbstract() {
        return this.horizontalAlignment;
    }

    public void AlignHorizontalAbstract(HorizontalAlignment horizontalAlignment2) {
        this.alignmentSetter.setHorizontalAlignment(horizontalAlignment2);
        this.horizontalAlignment = horizontalAlignment2;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(@Options(HorizontalAlignment.class) int i) {
        HorizontalAlignment fromUnderlyingValue = HorizontalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i));
            return;
        }
        AlignHorizontalAbstract(fromUnderlyingValue);
    }

    @Options(VerticalAlignment.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.")
    public int AlignVertical() {
        return AlignVerticalAbstract().toUnderlyingValue().intValue();
    }

    public VerticalAlignment AlignVerticalAbstract() {
        return this.verticalAlignment;
    }

    public void AlignVerticalAbstract(VerticalAlignment verticalAlignment2) {
        this.alignmentSetter.setVerticalAlignment(verticalAlignment2);
        this.verticalAlignment = verticalAlignment2;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(@Options(VerticalAlignment.class) int i) {
        VerticalAlignment fromUnderlyingValue = VerticalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i));
            return;
        }
        AlignVerticalAbstract(fromUnderlyingValue);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the component's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the component's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    public void setAnimation() {
        this.f161hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AlphaAnimation(1.0f, 0.9f);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new AlphaAnimation(0.9f, 1.0f);
        this.f161hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDuration(450);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setDuration(450);
        this.f161hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFillAfter(true);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setFillAfter(true);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled the card layout will have then round corners.")
    public void UseRoundCard(boolean z) {
        this.eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC = z;
    }

    @SimpleProperty
    public boolean UseRoundCard() {
        return this.eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled the arrangement will be convert to a card view. You can detect clicks on it with the \"Click\" event.")
    public void isCard(boolean z) {
        this.Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY = z;
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(this.clickable);
        if (z) {
            int i = -1;
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(-1);
            ViewCompat.setElevation(this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 10.0f);
            int i2 = this.backgroundColor;
            if (i2 != 16777215) {
                i = i2;
            }
            KodularHelper.setShape(this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i2, i, this.eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutParams();
            marginLayoutParams.setMargins(this.size, KodularUnitUtil.DpToPixels((Context) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 4), this.size, KodularUnitUtil.DpToPixels((Context) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 4));
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(marginLayoutParams);
            ViewGroup viewGroup = this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            int i3 = this.size;
            viewGroup.setPadding(i3, i3, i3, i3);
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        if (HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this)) {
                            HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this).startAnimation(HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this));
                            HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this).invalidate();
                        }
                    } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this)) {
                        HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this).startAnimation(HVArrangement.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
                        HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HVArrangement.this).invalidate();
                        HVArrangement.this.Click();
                    }
                    return true;
                }
            });
            return;
        }
        ViewCompat.setElevation(this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(this.backgroundColor);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPadding(0, 0, 0, 0);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutParams();
        marginLayoutParams2.setMargins(0, 0, 0, 0);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(marginLayoutParams2);
    }

    @SimpleProperty
    public boolean isCard() {
        return this.Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY;
    }

    @SimpleEvent(description = "Click listener event.")
    public void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Long click listener event.")
    public void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public void Clickable(boolean z) {
        this.clickable = z;
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(z);
        if (this.clickable) {
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(true);
            ((FrameLayout) this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setForeground(getSelectedItemDrawable());
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    HVArrangement.this.Click();
                }
            });
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    HVArrangement.this.LongClick();
                    return true;
                }
            });
            return;
        }
        ((FrameLayout) this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setForeground((Drawable) null);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener((View.OnClickListener) null);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener((View.OnLongClickListener) null);
        this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(false);
    }

    @SimpleProperty
    public boolean Clickable() {
        return this.clickable;
    }

    public Drawable getSelectedItemDrawable() {
        TypedArray obtainStyledAttributes = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.obtainStyledAttributes(new int[]{16843534});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar")
    public void Scrollbar(boolean z) {
        int i = this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
        if (i == 1) {
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVerticalScrollBarEnabled(z);
        } else if (i == 0) {
            this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setHorizontalScrollBarEnabled(z);
        }
        this.scrollbar = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Scrollbar() {
        return this.scrollbar;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Image() {
        return this.imagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the component's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(@Asset String str) {
        if (!str.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            if (str == null) {
                str = "";
            }
            this.imagePath = str;
            this.backgroundImageDrawable = null;
            if (str.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception unused) {
                }
            }
            updateAppearance();
        }
    }

    private void updateAppearance() {
        if (this.backgroundImageDrawable == null) {
            if (this.backgroundColor == 0) {
                ViewUtil.setBackgroundDrawable(this.viewLayout.getLayoutManager(), this.f159hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            } else {
                ViewUtil.setBackgroundDrawable(this.viewLayout.getLayoutManager(), (Drawable) null);
                this.viewLayout.getLayoutManager().setBackgroundColor(this.backgroundColor);
            }
            if (this.Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY) {
                int i = this.backgroundColor;
                KodularHelper.setShape(this.f160hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i, i == 16777215 ? -1 : i, this.eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC);
                return;
            }
            return;
        }
        ViewUtil.setBackgroundImage(this.viewLayout.getLayoutManager(), this.backgroundImageDrawable);
    }
}

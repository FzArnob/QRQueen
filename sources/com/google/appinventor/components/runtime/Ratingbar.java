package com.google.appinventor.components.runtime;

import android.graphics.drawable.LayerDrawable;
import android.widget.RatingBar;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", helpUrl = "https://docs.kodular.io/components/user-interface/rating-bar/", iconName = "images/ratingbar.png", version = 1)
public final class Ratingbar extends AndroidViewComponent implements RatingBar.OnRatingBarChangeListener {
    private LayerDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final RatingBar f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = Component.COLOR_GRAY;
    private int vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = Component.COLOR_RED;

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    public Ratingbar(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        RatingBar ratingBar = new RatingBar(componentContainer.$context());
        this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ratingBar;
        ratingBar.setOnRatingBarChangeListener(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (LayerDrawable) ratingBar.getProgressDrawable();
        componentContainer.$add(this);
        SetNumberOfStars(5);
        IsIndicator(false);
        SetStepSize(0.5f);
        StarColor(Component.COLOR_RED);
        BackgroundColor(Component.COLOR_GRAY);
    }

    public final RatingBar getView() {
        return this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        Changed(f);
    }

    @SimpleEvent(description = "Event invoked when the rating has been changed.")
    public final void Changed(float f) {
        EventDispatcher.dispatchEvent(this, "Changed", Float.valueOf(f));
    }

    @DesignerProperty(defaultValue = "5", editorType = "string")
    @SimpleProperty(description = "Sets the number of stars to show.")
    public final void SetNumberOfStars(int i) {
        this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumStars(i);
    }

    @SimpleProperty(description = "Returns the number of stars shown.")
    public final int GetNumberOfStars() {
        return this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumStars();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether this rating bar should only be an indicator (thus non-changeable by the user).")
    public final void IsIndicator(boolean z) {
        this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setIsIndicator(z);
    }

    @SimpleProperty(description = "Sets the rating (the number of stars filled).")
    public final void SetRating(float f) {
        this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRating(f);
    }

    @SimpleProperty(description = "Gets the current rating (number of stars filled).")
    public final float GetRating() {
        return this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRating();
    }

    @DesignerProperty(defaultValue = "&HFFF34336", editorType = "color")
    @SimpleProperty(description = "Change the color of the star.")
    public final void StarColor(int i) {
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = i;
        DrawableCompat.setTint(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDrawable(2), i);
    }

    @SimpleProperty(description = "Return the background color of the star.")
    public final int StarColor() {
        return this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ;
    }

    @DesignerProperty(defaultValue = "&HFF9E9E9E", editorType = "color")
    @SimpleProperty(description = "Change the background color of the star.")
    public final void BackgroundColor(int i) {
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = i;
        DrawableCompat.setTint(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDrawable(0), i);
    }

    @SimpleProperty(description = "Return the background color of the star.")
    public final int BackgroundColor() {
        return this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA;
    }

    @DesignerProperty(defaultValue = ".5", editorType = "float")
    @SimpleProperty(description = "Sets the step size (granularity) of this rating bar.")
    public final void SetStepSize(float f) {
        this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStepSize(f);
    }

    @SimpleProperty(description = "Gets the step size (granularity) of this rating bar.")
    public final float GetStepSize() {
        return this.f245hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStepSize();
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final int Width() {
        return getView().getWidth();
    }

    public final void Width(int i) {
        this.container.setChildWidth(this, i);
    }
}

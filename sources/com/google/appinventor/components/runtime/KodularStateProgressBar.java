package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateErrorListener;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "in ode", iconName = "images/stateProgressBar.png", nonVisible = false, version = 1)
@UsesLibraries({"stateProgressBar.aar", "stateProgressBar.jar"})
public final class KodularStateProgressBar extends AndroidViewComponent {
    private Context context;
    private final StateProgressBar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public KodularStateProgressBar(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        StateProgressBar stateProgressBar = new StateProgressBar(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = stateProgressBar;
        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            public final void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int i, boolean z) {
                KodularStateProgressBar.this.StateItemClick(i, z);
            }
        });
        stateProgressBar.setOnErrorListener(new OnStateErrorListener() {
            public final void onStateError(StateProgressBar stateProgressBar, String str) {
                KodularStateProgressBar.this.ErrorOccurred(str);
            }
        });
        TextViewUtil.setContext(this.context);
        componentContainer.$add(this);
        ElementsFromString("Item 1, Item 2, Item 3, Item 4, Item 5");
        StateDescriptionTypeface(0);
        StateNumberTypeface(0);
        StateDescriptionTypefaceImport("");
        StateNumberTypefaceImport("");
        StateNumberIsDescending(false);
        JustifyMultilineDescription(true);
        AnimationToCurrentState(true);
        MaxDescriptionLine(2);
        MaxStateNumber(5);
        CurrentStateNumber(1);
        AnimationDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        StateProgressBarHeight(40.0f);
        StateNumberTextSize(20.0f);
        StateLineThickness(10.0f);
        DescriptionTopSpaceIncrementer(10.0f);
        StateDescriptionSize(18.0f);
        DescriptionLinesSpacing(5.0f);
        ForegroundColor(Component.COLOR_GREEN);
        BackgroundColor(Component.COLOR_DARK_GRAY);
        StateNumberForegroundColor(-1);
        StateNumberBackgroundColor(-16777216);
        CurrentStateDescriptionColor(Component.COLOR_GREEN);
        StateDescriptionColor(Component.COLOR_DARK_GRAY);
        Log.d("Kodular StateProgress", "Kodular StateProgressBar Created");
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Event invoked when a state item was clicked.")
    public final void StateItemClick(int i, boolean z) {
        EventDispatcher.dispatchEvent(this, "StateItemClick", Integer.valueOf(i), Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Event invoked when a error occurred.")
    public final void ErrorOccurred(String str) {
        EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleProperty(description = "If set to true all states are completed.")
    public final void AllStatesCompleted(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAllStatesCompleted(z);
    }

    @SimpleProperty(description = "Returns true if 'All States Completed' is enabled.")
    public final boolean AllStatesCompleted() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAllStatesCompletedEnabled();
    }

    @DesignerProperty(defaultValue = "Item 1, Item 2, Item 3, Item 4, Item 5", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The State Progress Bar elements specified as a string with the items separated by commas such as: Item 1, Item 2, Item 3, Item 4, Item 5. Each word before the comma will be an element in the list. You can add maximum 5 items.")
    public final void ElementsFromString(String str) {
        if (str != null && !str.isEmpty()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionData(str.split(","));
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true the state number is in descending order.")
    public final void StateNumberIsDescending(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberIsDescending(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean StateNumberIsDescending() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberIsDescending();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true justify multiline description is enabled.")
    public final void JustifyMultilineDescription(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setJustifyMultilineDescription(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean JustifyMultilineDescription() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isDescriptionMultiline();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true the states will use a animation when they changed.")
    public final void AnimationToCurrentState(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableAnimationToCurrentState(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean AnimationToCurrentState() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnableAnimationToCurrentState();
    }

    @DesignerProperty(defaultValue = "250", editorType = "integer")
    @SimpleProperty(description = "Set the animation duration in milliseconds. Example:  use 1000 for 1 second.")
    public final void AnimationDuration(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimationDuration(i);
    }

    @SimpleProperty(description = "Returns the animation duration in milliseconds.")
    public final int AnimationDuration() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAnimationDuration();
    }

    @DesignerProperty(defaultValue = "2", editorType = "integer")
    @SimpleProperty(description = "Set the maximum description line.")
    public final void MaxDescriptionLine(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxDescriptionLine(i);
    }

    @SimpleProperty(description = "Returns the maximum description line.")
    public final int MaxDescriptionLine() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxDescriptionLine();
    }

    @DesignerProperty(defaultValue = "5", editorType = "state_number")
    @SimpleProperty(description = "Set the maximum state number.")
    public final void MaxStateNumber(int i) {
        if (i == 1) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.ONE);
        } else if (i == 2) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
        } else if (i == 3) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.THREE);
        } else if (i == 4) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.FIVE);
        }
    }

    @SimpleProperty(description = "Returns the maximum state number.")
    public final int MaxStateNumber() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxStateNumber();
    }

    @DesignerProperty(defaultValue = "1", editorType = "state_number")
    @SimpleProperty(description = "Set the current state number.")
    public final void CurrentStateNumber(int i) {
        int maxStateNumber = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxStateNumber();
        if (i != 1 || i > maxStateNumber) {
            if (i == 2 && i <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                return;
            } else if (i == 3 && i <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                return;
            } else if (i == 4 && i <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                return;
            } else if (i == 5 && i <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
                return;
            }
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
    }

    @SimpleProperty(description = "Returns the current state number.")
    public final int CurrentStateNumber() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentStateNumber();
    }

    @DesignerProperty(defaultValue = "40.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state progress bar height.")
    public final void StateProgressBarHeight(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateSize(f);
    }

    @SimpleProperty(description = "Returns the state progress bar height.")
    public final float StateProgressBarHeight() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateSize();
    }

    @DesignerProperty(defaultValue = "20.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state number text size.")
    public final void StateNumberTextSize(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTextSize(f);
    }

    @SimpleProperty(description = "Returns the state number text size.")
    public final float StateNumberTextSize() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberTextSize();
    }

    @DesignerProperty(defaultValue = "10.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state line thickness.")
    public final void StateLineThickness(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateLineThickness(f);
    }

    @SimpleProperty(description = "Returns the state line thickness.")
    public final float StateLineThickness() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateLineThickness();
    }

    @DesignerProperty(defaultValue = "10.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the description top space incrementer.")
    public final void DescriptionTopSpaceIncrementer(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDescriptionTopSpaceIncrementer(f);
    }

    @SimpleProperty(description = "Returns the description top space incrementer.")
    public final float DescriptionTopSpaceIncrementer() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDescriptionTopSpaceIncrementer();
    }

    @DesignerProperty(defaultValue = "18.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state description size.")
    public final void StateDescriptionSize(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionSize(f);
    }

    @SimpleProperty(description = "Returns the state description size.")
    public final float StateDescriptionSize() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateDescriptionSize();
    }

    @DesignerProperty(defaultValue = "5.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the description lines spacing.")
    public final void DescriptionLinesSpacing(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDescriptionLinesSpacing(f);
    }

    @SimpleProperty(description = "Returns the description lines spacing.")
    public final float DescriptionLinesSpacing() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDescriptionLinesSpacing();
    }

    @DesignerProperty(defaultValue = "&HFF4CAF50", editorType = "color")
    @SimpleProperty(description = "Change the foreground color.")
    public final void ForegroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setForegroundColor(i);
    }

    @SimpleProperty(description = "Returns the foreground color.")
    public final int ForegroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getForegroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Change the background color.")
    public final void BackgroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(description = "Returns the background color.")
    public final int BackgroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackgroundColor();
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Change the state number foreground color.")
    public final void StateNumberForegroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberForegroundColor(i);
    }

    @SimpleProperty(description = "Returns the state number foreground color.")
    public final int StateNumberForegroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberForegroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Change the state number background color.")
    public final void StateNumberBackgroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberBackgroundColor(i);
    }

    @SimpleProperty(description = "Returns the state number background color.")
    public final int StateNumberBackgroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberBackgroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF4CAF50", editorType = "color")
    @SimpleProperty(description = "Change the current state description color.")
    public final void CurrentStateDescriptionColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateDescriptionColor(i);
    }

    @SimpleProperty(description = "Returns the current state description color.")
    public final int CurrentStateDescriptionColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentStateDescriptionColor();
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Change the state description color.")
    public final void StateDescriptionColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionColor(i);
    }

    @SimpleProperty(description = "Returns the state description color.")
    public final int StateDescriptionColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateDescriptionColor();
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void StateDescriptionTypeface(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionTypefaceFile(TextViewUtil.getTitleBarTypeFace(i));
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font for state description typeface.")
    public final void StateDescriptionTypefaceImport(String str) {
        if (str != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionTypefaceFile(TextViewUtil.getTitleBarCustomTypeFace(this.container.$form(), str));
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void StateNumberTypeface(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTypefaceFile(TextViewUtil.getTitleBarTypeFace(i));
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font for state number typeface.")
    public final void StateNumberTypefaceImport(String str) {
        if (str != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTypefaceFile(TextViewUtil.getTitleBarCustomTypeFace(this.container.$form(), str));
        }
    }
}

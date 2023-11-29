package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@SimpleObject
public abstract class AndroidViewComponent extends VisibleComponent {
    private int column = -1;
    protected final ComponentContainer container;
    private int lastSetHeight = -3;
    private int lastSetWidth = -3;
    private int percentHeightHolder = -3;
    private int percentWidthHolder = -3;
    private int row = -1;

    public abstract View getView();

    protected AndroidViewComponent(ComponentContainer componentContainer) {
        this.container = componentContainer;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getView().getVisibility() == 0;
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        getView().setVisibility(z ? 0 : 8);
    }

    @SimpleProperty
    public int Width() {
        return (int) (((float) getView().getWidth()) / this.container.$form().deviceDensity());
    }

    @SimpleProperty
    public void Width(int i) {
        this.container.setChildWidth(this, i);
        this.lastSetWidth = i;
        if (i <= -1000) {
            this.container.$form().registerPercentLength(this, i, Form.PercentStorageRecord.Dim.WIDTH);
        }
    }

    @SimpleProperty
    public void WidthPercent(int i) {
        if (i < 0 || i > 100) {
            this.container.$form().dispatchErrorOccurredEvent(this, "WidthPercent", ErrorMessages.ERROR_BAD_PERCENT, Integer.valueOf(i));
            return;
        }
        Width((-i) - 1000);
    }

    public void setLastWidth(int i) {
        this.percentWidthHolder = i;
    }

    public int getSetWidth() {
        int i = this.percentWidthHolder;
        return i == -3 ? Width() : i;
    }

    public void setLastHeight(int i) {
        this.percentHeightHolder = i;
    }

    public int getSetHeight() {
        int i = this.percentHeightHolder;
        return i == -3 ? Height() : i;
    }

    @SimplePropertyCopier
    public void CopyWidth(AndroidViewComponent androidViewComponent) {
        Width(androidViewComponent.lastSetWidth);
    }

    @SimpleProperty
    public int Height() {
        return (int) (((float) getView().getHeight()) / this.container.$form().deviceDensity());
    }

    @SimpleProperty
    public void Height(int i) {
        this.container.setChildHeight(this, i);
        this.lastSetHeight = i;
        if (i <= -1000) {
            this.container.$form().registerPercentLength(this, i, Form.PercentStorageRecord.Dim.HEIGHT);
        }
    }

    @SimpleProperty
    public void HeightPercent(int i) {
        if (i < 0 || i > 100) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HeightPercent", ErrorMessages.ERROR_BAD_PERCENT, Integer.valueOf(i));
            return;
        }
        Height((-i) - 1000);
    }

    @SimplePropertyCopier
    public void CopyHeight(AndroidViewComponent androidViewComponent) {
        Height(androidViewComponent.lastSetHeight);
    }

    @SimpleProperty(userVisible = false)
    public int Column() {
        return this.column;
    }

    @SimpleProperty(userVisible = false)
    public void Column(int i) {
        this.column = i;
    }

    @SimpleProperty(userVisible = false)
    public int Row() {
        return this.row;
    }

    @SimpleProperty(userVisible = false)
    public void Row(int i) {
        this.row = i;
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this.container.$form();
    }
}

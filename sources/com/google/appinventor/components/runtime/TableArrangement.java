package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.PrintStream;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "<p>A formatting element in which to place components that should be displayed in tabular form.</p>", version = 1)
public class TableArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final TableLayout f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public TableArrangement(ComponentContainer componentContainer) {
        super(componentContainer);
        Activity $context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context;
        this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TableLayout($context, 2, 2);
        componentContainer.$add(this);
    }

    @SimpleProperty(userVisible = false)
    public int Columns() {
        return this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumColumns();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
    @SimpleProperty(userVisible = false)
    public void Columns(int i) {
        this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumColumns(i);
    }

    @SimpleProperty(userVisible = false)
    public int Rows() {
        return this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumRows();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
    @SimpleProperty(userVisible = false)
    public void Rows(int i) {
        this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumRows(i);
    }

    public Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.add(androidViewComponent);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        PrintStream printStream = System.err;
        printStream.println("TableArrangement.setChildWidth: width = " + i + " component = " + androidViewComponent);
        if (i <= -1000) {
            int Width = this.container.$form().Width();
            if (Width <= -1000 || Width > 0) {
                PrintStream printStream2 = System.err;
                printStream2.println("%%TableArrangement.setChildWidth(): width = " + i + " parent Width = " + Width + " child = " + androidViewComponent);
                i = (Width * (-(i + 1000))) / 100;
            } else {
                i = -1;
            }
        }
        androidViewComponent.setLastWidth(i);
        ViewUtil.setChildWidthForTableLayout(androidViewComponent.getView(), i);
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        if (i <= -1000) {
            int Height = this.container.$form().Height();
            if (Height <= -1000 || Height > 0) {
                i = (Height * (-(i + 1000))) / 100;
            } else {
                i = -1;
            }
        }
        androidViewComponent.setLastHeight(i);
        ViewUtil.setChildHeightForTableLayout(androidViewComponent.getView(), i);
    }

    public View getView() {
        return this.f260hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutManager();
    }
}

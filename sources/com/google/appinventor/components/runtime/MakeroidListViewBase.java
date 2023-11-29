package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.RecyclerItemClickListener;
import com.google.appinventor.components.runtime.util.DividerItemDecoration;
import com.google.appinventor.components.runtime.util.PaintUtil;

@SimpleObject
public abstract class MakeroidListViewBase extends AndroidViewComponent {
    public static final int DEFAULT_PRIMARY_TEXT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_PRIMARY_TEXT_COLOR);
    public static final int DEFAULT_SECONDARY_TEXT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_SECONDARY_TEXT_COLOR);
    public int backgroundColor = -1;
    public ComponentContainer container;
    public Context context;
    public int dividerColor = 0;
    public Form form;
    public boolean isCompanion = false;
    public final LinearLayout listViewLayout;
    public RecyclerView recyclerView;

    public abstract void click(int i);

    public abstract void longClick(int i);

    public MakeroidListViewBase(ComponentContainer componentContainer, int i) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        LinearLayout linearLayout = new LinearLayout(this.context);
        this.listViewLayout = linearLayout;
        linearLayout.setOrientation(1);
        RecyclerView recyclerView2 = new RecyclerView(this.context);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context, i, false));
        linearLayout.addView(this.recyclerView);
        linearLayout.requestLayout();
        componentContainer.$add(this);
        RecyclerView recyclerView3 = this.recyclerView;
        recyclerView3.addOnItemTouchListener(new RecyclerItemClickListener(this.context, recyclerView3, new RecyclerItemClickListener.OnItemClickListener() {
            public final void onItemClick(View view, int i) {
                MakeroidListViewBase.this.click(i);
            }

            public final void onLongItemClick(View view, int i) {
                MakeroidListViewBase.this.longClick(i);
            }
        }));
        Width(-2);
        Height(-1);
        BackgroundColor(0);
        DividerColor(0);
        if (this.form instanceof ReplForm) {
            this.isCompanion = true;
        }
    }

    public void setRecyclerViewOrientation(int i) {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context, i, false));
    }

    @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the listview")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        RecyclerView recyclerView2 = this.recyclerView;
        if (i == 0) {
            i = 16777215;
        }
        recyclerView2.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the listview.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the divider color of the listview")
    public void DividerColor(int i) {
        this.dividerColor = i;
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.context, i));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the divider color of the listview.")
    public int DividerColor() {
        return this.dividerColor;
    }

    public View getView() {
        return this.listViewLayout;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the height of the list on the view.")
    public void Height(int i) {
        super.Height(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the width of the list on the view.")
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }
}

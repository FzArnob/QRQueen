package com.google.appinventor.components.runtime;

import android.content.Context;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/decoration.png", nonVisible = true, version = 1)
public class Decoration extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;

    public Decoration(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
    }

    @SimpleFunction(description = "Margin is a way for a component to enforce some distance from others components. By specifying margin for a component, we say that keep this much distance from this component.")
    public void SetMargin(AndroidViewComponent androidViewComponent, String str) {
        String trim = str.trim();
        if (!trim.isEmpty()) {
            String[] split = trim.split(",");
            ArrayList arrayList = new ArrayList();
            for (String trim2 : split) {
                arrayList.add(Integer.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Integer.valueOf(trim2.trim()).intValue())));
            }
            try {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) androidViewComponent.getView().getLayoutParams();
                if (split.length == 1) {
                    layoutParams.setMargins(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue());
                    androidViewComponent.getView().setLayoutParams(layoutParams);
                } else if (split.length == 2) {
                    layoutParams.setMargins(((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue());
                    androidViewComponent.getView().setLayoutParams(layoutParams);
                } else if (split.length == 4) {
                    layoutParams.setMargins(((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(3)).intValue(), ((Integer) arrayList.get(2)).intValue());
                    androidViewComponent.getView().setLayoutParams(layoutParams);
                }
            } catch (Exception e) {
                this.form.dispatchErrorOccurredEvent(this, "SetMargin", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
            }
        }
    }

    @SimpleFunction(description = "The padding around the component. Padding is the space inside the border, between the border and the actual component content. Use single number like 5 to specify padding for top, left, bottom, righ. You can also use 4 different numbers like 5,0,10,0 for top, left, bottom right.")
    public void SetPadding(AndroidViewComponent androidViewComponent, String str) {
        String trim = str.trim();
        if (!trim.isEmpty()) {
            String[] split = trim.split(",");
            ArrayList arrayList = new ArrayList();
            for (String trim2 : split) {
                arrayList.add(Integer.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Integer.valueOf(trim2.trim()).intValue())));
            }
            try {
                if (split.length == 1) {
                    androidViewComponent.getView().setPadding(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(0)).intValue());
                } else if (split.length == 2) {
                    androidViewComponent.getView().setPadding(((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue());
                } else if (split.length == 4) {
                    androidViewComponent.getView().setPadding(((Integer) arrayList.get(1)).intValue(), ((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(3)).intValue(), ((Integer) arrayList.get(2)).intValue());
                }
            } catch (Exception e) {
                this.form.dispatchErrorOccurredEvent(this, "SetPadding", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
            }
        }
    }

    @SimpleFunction(description = "This block allows you to create a rectangle or round shape for the visible component. You can use Color for backgroundColor and borderColor. ")
    public void SetShape(AndroidViewComponent androidViewComponent, int i, int i2, boolean z) {
        try {
            ViewUtil.setShape(androidViewComponent.getView(), i, i2, z);
        } catch (Exception e) {
            this.form.dispatchErrorOccurredEvent(this, "SetShape", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
        }
    }

    private int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        return (int) (((double) i) * ((double) this.context.getResources().getDisplayMetrics().density));
    }
}

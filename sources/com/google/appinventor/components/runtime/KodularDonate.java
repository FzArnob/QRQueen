package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MONETIZATION_GENERAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/donate/", iconName = "images/kodulardonate.png", nonVisible = true, version = 1)
public class KodularDonate extends AndroidNonvisibleComponent {
    public KodularDonate(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Log.d("Donate", "Kodular Donate created");
    }
}

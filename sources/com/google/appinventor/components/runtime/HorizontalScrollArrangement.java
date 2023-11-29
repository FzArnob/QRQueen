package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "<p>A formatting element in which to place components that should be displayed from left to right.  If you wish to have components displayed one over another, use <code>VerticalArrangement</code> instead.</p><p>This version is scrollable.", iconName = "images/horizontalScroll.png", version = 8)
public class HorizontalScrollArrangement extends HVArrangement {
    public HorizontalScrollArrangement(ComponentContainer componentContainer) {
        super(componentContainer, 0, ComponentConstants.SCROLLABLE_ARRANGEMENT);
    }
}

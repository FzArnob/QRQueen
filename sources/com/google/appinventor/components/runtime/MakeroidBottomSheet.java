package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", iconName = "images/bottomsheet.png", nonVisible = true, version = 2)
public class MakeroidBottomSheet extends AndroidNonvisibleComponent implements Component {
    private final Context context;
    private BottomSheetDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = true;
    private boolean showStatusBar = true;
    private boolean xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = false;

    public MakeroidBottomSheet(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Bottom Sheet Component", "Bottom Sheet Created");
    }

    @SimpleFunction(description = "Register any layout as example a 'horizontal arrangement', that will be later your bottom sheet dialog.")
    public void RegisterLayoutAsDialog(AndroidViewComponent androidViewComponent) {
        try {
            ViewGroup viewGroup = (ViewGroup) androidViewComponent.getView();
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bottomSheetDialog;
            bottomSheetDialog.setContentView((View) viewGroup);
            AddListener();
            Update();
        } catch (Exception e) {
            Log.e("Bottom Sheet Component", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Register any component as example a 'button', that will be later your bottom sheet dialog.")
    public void RegisterComponentAsDialog(AndroidViewComponent androidViewComponent) {
        try {
            View view = androidViewComponent.getView();
            ((ViewGroup) view.getParent()).removeView(view);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bottomSheetDialog;
            bottomSheetDialog.setContentView(view);
            AddListener();
            Update();
        } catch (Exception e) {
            Log.e("Bottom Sheet Component", String.valueOf(e));
        }
    }

    public void AddListener() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnShowListener(new DialogInterface.OnShowListener() {
            public final void onShow(DialogInterface dialogInterface) {
                MakeroidBottomSheet.this.Opened();
            }
        });
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public final void onDismiss(DialogInterface dialogInterface) {
                MakeroidBottomSheet.this.Closed();
            }
        });
    }

    public void Update() {
        BottomSheetDialog bottomSheetDialog = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bottomSheetDialog != null && bottomSheetDialog.getWindow() != null) {
            if (this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(2);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(2);
            }
            Log.i("Bottom Sheet Component", "Dim Background is set to " + this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX);
            if (this.showStatusBar) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(2048);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(1024);
                return;
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(1024);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(2048);
        }
    }

    @SimpleEvent(description = "Event to detect that the dialog was opened.")
    public void Opened() {
        EventDispatcher.dispatchEvent(this, "Opened", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the dialog was closed.")
    public void Closed() {
        EventDispatcher.dispatchEvent(this, "Closed", new Object[0]);
    }

    @SimpleFunction(description = "Show the bottom sheet dialog.")
    public void ShowDialog() {
        BottomSheetDialog bottomSheetDialog = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show();
        }
    }

    @SimpleFunction(description = "Hide the bottom sheet dialog.")
    public void HideDialog() {
        BottomSheetDialog bottomSheetDialog = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true the user will see a dark background effect. Else the background have then no dark background effect.")
    public void DimBackground(boolean z) {
        this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = z;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Update();
        }
    }

    @SimpleProperty
    public boolean DimBackground() {
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ShowStatusBar(boolean z) {
        this.showStatusBar = z;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Update();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The status bar is the topmost bar on the screen. This property reports whether the status bar is visible.")
    public boolean ShowStatusBar() {
        return this.showStatusBar;
    }
}

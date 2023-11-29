package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic image in Arrangements.", iconName = "images/image.png", nonVisible = true, version = 2)
public final class KodularDynamicImage extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicImage";
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();

    public KodularDynamicImage(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
    }

    @SimpleFunction(description = "Create a new image component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateImage(int i, AndroidViewComponent androidViewComponent, String str, int i2, int i3) {
        try {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.context);
            appCompatImageView.setLayoutParams(linearLayoutParams(i2, i3));
            this.kodularDynamicModelList.add(new KodularDynamicModel(i, appCompatImageView, androidViewComponent));
            ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(appCompatImageView);
            imagePermissionHelper(appCompatImageView, str, "CreateImage");
        } catch (Exception e) {
            Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Update a image component with the given id.")
    public final void UpdateImage(int i, String str) {
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            if (appCompatImageView != null) {
                imagePermissionHelper(appCompatImageView, str, "UpdateImage");
            }
        } catch (Exception e) {
            Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a image component with the given id.")
    public final void DeleteImage(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the image referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetImageById(int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (appCompatImageView != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(appCompatImageView);
        }
        return null;
    }

    @SimpleFunction(description = "Specifies whether a image component with the given id should be resized to match the size of the ImageView.")
    public final void ScalePictureToFit(int i, boolean z) {
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            if (appCompatImageView == null) {
                return;
            }
            if (z) {
                appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                appCompatImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        } catch (Exception e) {
            Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Specifies the angle of a image component with the given id.")
    public final void RotationAngle(int i, double d) {
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            if (appCompatImageView != null) {
                appCompatImageView.setRotation((float) d);
            }
        } catch (Exception e) {
            Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    private void imagePermissionHelper(final AppCompatImageView appCompatImageView, final String str, final String str2) {
        if (!MediaUtil.isExternalFile(str) || !this.form.isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            setImage(appCompatImageView, str);
        } else {
            this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        KodularDynamicImage.this.setImage(appCompatImageView, str);
                    } else {
                        KodularDynamicImage.this.form.dispatchPermissionDeniedEvent((Component) KodularDynamicImage.this, str2, str);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setImage(AppCompatImageView appCompatImageView, String str) {
        try {
            ViewUtil.setImage(appCompatImageView, MediaUtil.getBitmapDrawable(this.form, str));
        } catch (Exception unused) {
            appCompatImageView.setImageDrawable((Drawable) null);
        }
    }

    @SimpleFunction(description = "Update the Width of a image component.")
    public final void SetWidth(int i, int i2) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (appCompatImageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) appCompatImageView.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i2);
            appCompatImageView.setLayoutParams(layoutParams);
            appCompatImageView.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a image component.")
    public final int GetWidth(int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (appCompatImageView != null) {
            return appCompatImageView.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a image component.")
    public final void SetHeight(int i, int i2) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (appCompatImageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) appCompatImageView.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i2);
            appCompatImageView.setLayoutParams(layoutParams);
            appCompatImageView.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a image component.")
    public final int GetHeight(int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (appCompatImageView != null) {
            return appCompatImageView.getHeight();
        }
        return 0;
    }

    private LinearLayout.LayoutParams linearLayoutParams(int i, int i2) {
        int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i);
        int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i2);
        if (i == -1) {
            DpToPixels = -2;
        } else if (i == -2) {
            DpToPixels = -1;
        }
        if (i2 == -1) {
            DpToPixels2 = -2;
        } else if (i2 == -2) {
            DpToPixels2 = -1;
        }
        return new LinearLayout.LayoutParams(DpToPixels, DpToPixels2);
    }
}

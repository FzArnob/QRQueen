package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.microsoft.appcenter.http.DefaultHttpClient;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import net.lingala.zip4j.util.InternalZipConstants;

public class KodularUtil {
    public static String REQUEST_ERROR_MESSAGE = "";
    public static boolean REQUEST_SUCCESS = false;
    /* access modifiers changed from: private */
    public static BitmapDrawable wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    private KodularUtil() {
    }

    public static String GET_REQUEST(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            httpsURLConnection.setRequestMethod(DefaultHttpClient.METHOD_GET);
            httpsURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    REQUEST_SUCCESS = true;
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            Log.e("KodularUtil", String.valueOf(e));
            REQUEST_SUCCESS = false;
            String str2 = e.getMessage();
            REQUEST_ERROR_MESSAGE = str2;
            return new StringBuilder(str2).toString();
        }
    }

    public static void LoadPicture(Context context, String str, AppCompatImageView appCompatImageView, boolean z) {
        try {
            if (!str.startsWith("http://")) {
                if (!str.startsWith("https://")) {
                    if (str.startsWith("file:///")) {
                        Glide.with(context).load(Uri.fromFile(new File(str.substring(7)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(Uri.fromFile(new File(str))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
                    } else if (z) {
                        RequestManager with = Glide.with(context);
                        with.load(new File(QUtil.getReplAssetPath(context) + str)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
                    } else {
                        Glide.with(context).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
                    }
                    appCompatImageView.setAdjustViewBounds(true);
                    appCompatImageView.requestLayout();
                }
            }
            Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView);
            appCompatImageView.setAdjustViewBounds(true);
            appCompatImageView.requestLayout();
        } catch (Exception e) {
            Log.e("KodularUtil", String.valueOf(e));
        }
    }

    public static void LoadPicture(Context context, String str, ImageView imageView, boolean z) {
        try {
            if (!str.startsWith("http://")) {
                if (!str.startsWith("https://")) {
                    if (str.startsWith("file:///")) {
                        Glide.with(context).load(Uri.fromFile(new File(str.substring(7)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(Uri.fromFile(new File(str))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
                    } else if (z) {
                        RequestManager with = Glide.with(context);
                        with.load(new File(QUtil.getReplAssetPath(context) + str)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
                    } else {
                        Glide.with(context).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
                    }
                    imageView.setAdjustViewBounds(true);
                    imageView.requestLayout();
                }
            }
            Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView);
            imageView.setAdjustViewBounds(true);
            imageView.requestLayout();
        } catch (Exception e) {
            Log.e("KodularUtil", String.valueOf(e));
        }
    }

    public static void LoadPicture(Context context, String str, CircleImageView circleImageView, boolean z) {
        try {
            if (!str.startsWith("http://")) {
                if (!str.startsWith("https://")) {
                    if (str.startsWith("file:///")) {
                        Glide.with(context).load(Uri.fromFile(new File(str.substring(7)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(Uri.fromFile(new File(str))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
                    } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
                    } else if (z) {
                        RequestManager with = Glide.with(context);
                        with.load(new File(QUtil.getReplAssetPath(context) + str)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
                    } else {
                        Glide.with(context).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
                    }
                    circleImageView.requestLayout();
                }
            }
            Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView);
            circleImageView.requestLayout();
        } catch (Exception e) {
            Log.e("KodularUtil", String.valueOf(e));
        }
    }

    public static BitmapDrawable getBitmap(Form form, String str) {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
        boolean z = form instanceof ReplForm;
        if (str.startsWith("http://") || str.startsWith("https://")) {
            Glide.with(form.$context()).load(str).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        } else if (str.startsWith("file:///")) {
            Glide.with(form.$context()).load(Uri.fromFile(new File(str.substring(7)))).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            Glide.with(form.$context()).load(Uri.fromFile(new File(str))).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            Glide.with(form.$context()).load(str).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        } else if (z) {
            RequestManager with = Glide.with(form.$context());
            with.load(new File(QUtil.getReplAssetPath(form) + str)).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        } else {
            Glide.with(form.$context()).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str)))).asBitmap().into(new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable unused = KodularUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new BitmapDrawable((Bitmap) obj);
                }
            });
        }
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }
}

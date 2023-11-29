package com.google.appinventor.components.runtime;

import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that allows you to upload media to Cloudinary.", iconName = "images/cloudinary.png", nonVisible = true, version = 1)
@UsesLibraries({"cloudinary-android.jar", "cloudinary-android.aar", "cloudinary-core.jar", "android-job.aar", "android-job.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE"})
public class MakeroidCloudinary extends AndroidNonvisibleComponent implements Component {
    private final ComponentContainer container;
    private boolean havePermission = false;
    /* access modifiers changed from: private */
    public String rwH5QsW1tECSJqEYisIw7mgmF1LBaoVJw606thITSS1io7bESEMnIJXTcF47IT5D;
    /* access modifiers changed from: private */
    public String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    private String xhzCIls1ZjY8rVr9uwfX6Ll4V1k7OjFmFraAgvYK73j7xL9tODrZc0oupoL3seO9;

    public MakeroidCloudinary(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Log.i("Cloudinary", "Cloudinary created");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void CloudName(String str) {
        this.xhzCIls1ZjY8rVr9uwfX6Ll4V1k7OjFmFraAgvYK73j7xL9tODrZc0oupoL3seO9 = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary cloud name.")
    public String CloudName() {
        return this.xhzCIls1ZjY8rVr9uwfX6Ll4V1k7OjFmFraAgvYK73j7xL9tODrZc0oupoL3seO9;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void APIKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary API key.")
    public String APIKey() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void APISecret(String str) {
        this.rwH5QsW1tECSJqEYisIw7mgmF1LBaoVJw606thITSS1io7bESEMnIJXTcF47IT5D = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary API secret.")
    public String APISecret() {
        return this.rwH5QsW1tECSJqEYisIw7mgmF1LBaoVJw606thITSS1io7bESEMnIJXTcF47IT5D;
    }

    @SimpleFunction(description = "Uploads the specified media file to your Cloudinary media library.")
    public void UploadMedia(final String str) {
        if (str == null || str.isEmpty()) {
            this.form.dispatchErrorOccurredEvent(this, "UploadMedia", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, new Object[0]);
        } else if (!this.havePermission) {
            this.form.runOnUiThread(new Runnable() {
                public final void run() {
                    MakeroidCloudinary.this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                MakeroidCloudinary.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidCloudinary.this);
                                MakeroidCloudinary.this.UploadMedia(str);
                                return;
                            }
                            MakeroidCloudinary.this.form.dispatchPermissionDeniedEvent((Component) MakeroidCloudinary.this, "UploadMedia", "android.permission.READ_EXTERNAL_STORAGE");
                        }
                    });
                }
            });
        } else {
            new a(this, (byte) 0).execute(new String[]{str});
        }
    }

    class a extends AsyncTask<String, Void, String[]> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String[] strArr = (String[]) obj;
            if (strArr[0].equalsIgnoreCase("EXCEPTION_0")) {
                MakeroidCloudinary.this.form.dispatchPermissionDeniedEvent((Component) MakeroidCloudinary.this, "UploadMedia", strArr[1]);
            } else if (strArr[0].equalsIgnoreCase("EXCEPTION_1")) {
                MakeroidCloudinary.this.form.dispatchErrorOccurredEvent(MakeroidCloudinary.this, "UploadMedia", Integer.valueOf(strArr[1]).intValue(), new Object[0]);
            } else if (strArr[0].equalsIgnoreCase("EXCEPTION_2")) {
                MakeroidCloudinary.this.form.dispatchErrorOccurredEvent(MakeroidCloudinary.this, "UploadMedia", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, new Object[0]);
            } else {
                MakeroidCloudinary.this.MediaUploaded(strArr[0], strArr[1]);
            }
        }

        private a() {
        }

        /* synthetic */ a(MakeroidCloudinary makeroidCloudinary, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0125, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            android.util.Log.e("Cloudinary", java.lang.String.valueOf(r0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0143, code lost:
            return new java.lang.String[]{r0.getMessage(), ""};
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x015f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0160, code lost:
            android.util.Log.e("Cloudinary", "UploadMedia on cloudinary file exception.");
            r3 = new java.lang.StringBuilder();
            r3.append(r0.getErrorMessageNumber());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x017d, code lost:
            return new java.lang.String[]{"EXCEPTION_1", r3.toString()};
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x017e, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x017f, code lost:
            android.util.Log.e("Cloudinary", java.lang.String.valueOf(r0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x019e, code lost:
            return new java.lang.String[]{"EXCEPTION_0", r0.getMessage()};
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x015f A[ExcHandler: FileException (r0v4 'e' com.google.appinventor.components.runtime.util.FileUtil$FileException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x017e A[ExcHandler: PermissionException (r0v1 'e' com.google.appinventor.components.runtime.errors.PermissionException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String[] doInBackground(java.lang.String... r41) {
            /*
                r40 = this;
                r1 = r40
                java.lang.String r0 = "file://"
                java.lang.String r2 = "Cloudinary"
                r3 = 2
                r4 = 1
                r5 = 0
                r6 = r41[r5]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                boolean r7 = r6.startsWith(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r8 = ""
                if (r7 == 0) goto L_0x0017
                java.lang.String r6 = r6.replace(r0, r8)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
            L_0x0017:
                r0 = 46
                int r0 = r6.lastIndexOf(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                if (r0 <= 0) goto L_0x0025
                int r0 = r0 + r4
                java.lang.String r0 = r6.substring(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                goto L_0x0026
            L_0x0025:
                r0 = r8
            L_0x0026:
                java.io.File r7 = new java.io.File     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r7.<init>(r6)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                com.cloudinary.Cloudinary r6 = new com.cloudinary.Cloudinary     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r9 = 6
                java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r10 = "cloud_name"
                r9[r5] = r10     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                com.google.appinventor.components.runtime.MakeroidCloudinary r10 = com.google.appinventor.components.runtime.MakeroidCloudinary.this     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r10 = com.google.appinventor.components.runtime.MakeroidCloudinary.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.MakeroidCloudinary) r10)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r9[r4] = r10     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r10 = "api_key"
                r9[r3] = r10     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r10 = 3
                com.google.appinventor.components.runtime.MakeroidCloudinary r11 = com.google.appinventor.components.runtime.MakeroidCloudinary.this     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r11 = r11.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r9[r10] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r10 = 4
                java.lang.String r11 = "api_secret"
                r9[r10] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r10 = 5
                com.google.appinventor.components.runtime.MakeroidCloudinary r11 = com.google.appinventor.components.runtime.MakeroidCloudinary.this     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r11 = r11.rwH5QsW1tECSJqEYisIw7mgmF1LBaoVJw606thITSS1io7bESEMnIJXTcF47IT5D     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r9[r10] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.util.Map r9 = com.cloudinary.utils.ObjectUtils.asMap(r9)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r6.<init>(r9)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r10 = "ai"
                java.lang.String r11 = "gif"
                java.lang.String r12 = "webp"
                java.lang.String r13 = "bmp"
                java.lang.String r14 = "djvu"
                java.lang.String r15 = "ps"
                java.lang.String r16 = "ept"
                java.lang.String r17 = "eps"
                java.lang.String r18 = "eps3"
                java.lang.String r19 = "flif"
                java.lang.String r20 = "heif"
                java.lang.String r21 = "heic"
                java.lang.String r22 = "ico"
                java.lang.String r23 = "jpg"
                java.lang.String r24 = "jpe"
                java.lang.String r25 = "jpeg"
                java.lang.String r26 = "jpc"
                java.lang.String r27 = "jp2"
                java.lang.String r28 = "j2k"
                java.lang.String r29 = "wdp"
                java.lang.String r30 = "jxr"
                java.lang.String r31 = "hdp"
                java.lang.String r32 = "png"
                java.lang.String r33 = "psd"
                java.lang.String r34 = "arw"
                java.lang.String r35 = "cr2"
                java.lang.String r36 = "svg"
                java.lang.String r37 = "tga"
                java.lang.String r38 = "tif"
                java.lang.String r39 = "tiff"
                java.lang.String[] r9 = new java.lang.String[]{r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39}     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r10 = "mp4"
                java.lang.String r11 = "webm"
                java.lang.String r12 = "flv"
                java.lang.String r13 = "mov"
                java.lang.String r14 = "ogv"
                java.lang.String r15 = "3gp"
                java.lang.String r16 = "3g2"
                java.lang.String r17 = "wmv"
                java.lang.String r18 = "mpeg"
                java.lang.String r19 = "flv"
                java.lang.String r20 = "mkv"
                java.lang.String r21 = "avi"
                java.lang.String r22 = "mp3"
                java.lang.String r23 = "wav"
                java.lang.String r24 = "aac"
                java.lang.String r25 = "ogg"
                java.lang.String r26 = "wma"
                java.lang.String r27 = "flac"
                java.lang.String[] r10 = new java.lang.String[]{r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27}     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.util.List r9 = java.util.Arrays.asList(r9)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                boolean r9 = r9.contains(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r11 = "resource_type"
                if (r9 == 0) goto L_0x00df
                java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r0[r5] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r9 = "image"
                r0[r4] = r9     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.util.Map r0 = com.cloudinary.utils.ObjectUtils.asMap(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                goto L_0x0102
            L_0x00df:
                java.util.List r9 = java.util.Arrays.asList(r10)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                boolean r0 = r9.contains(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                if (r0 == 0) goto L_0x00f6
                java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r0[r5] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r9 = "video"
                r0[r4] = r9     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.util.Map r0 = com.cloudinary.utils.ObjectUtils.asMap(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                goto L_0x0102
            L_0x00f6:
                java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r0[r5] = r11     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r9 = "raw"
                r0[r4] = r9     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.util.Map r0 = com.cloudinary.utils.ObjectUtils.asMap(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
            L_0x0102:
                com.cloudinary.Uploader r6 = r6.uploader()     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                java.util.Map r0 = r6.upload(r7, r0)     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                r6.<init>()     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                java.lang.String r7 = "secure_url"
                java.lang.Object r0 = r0.get(r7)     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                r6.append(r0)     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                java.lang.String r6 = "sucessful"
                java.lang.String[] r7 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                r7[r5] = r6     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                r7[r4] = r0     // Catch:{ Exception -> 0x0125, PermissionException -> 0x017e, FileException -> 0x015f }
                return r7
            L_0x0125:
                r0 = move-exception
                java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                android.util.Log.e(r2, r6)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r6.<init>()     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r0 = r0.getMessage()     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r6.append(r0)     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String r0 = r6.toString()     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r6[r5] = r0     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                r6[r4] = r8     // Catch:{ PermissionException -> 0x017e, FileException -> 0x015f, Exception -> 0x0144 }
                return r6
            L_0x0144:
                r0 = move-exception
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r0 = r0.getMessage()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                android.util.Log.e(r2, r0)
                java.lang.String r0 = "EXCEPTION_2"
                java.lang.String[] r0 = new java.lang.String[]{r0}
                return r0
            L_0x015f:
                r0 = move-exception
                java.lang.String r6 = "UploadMedia on cloudinary file exception."
                android.util.Log.e(r2, r6)
                java.lang.String[] r2 = new java.lang.String[r3]
                java.lang.String r3 = "EXCEPTION_1"
                r2[r5] = r3
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                int r0 = r0.getErrorMessageNumber()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2[r4] = r0
                return r2
            L_0x017e:
                r0 = move-exception
                java.lang.String r6 = java.lang.String.valueOf(r0)
                android.util.Log.e(r2, r6)
                java.lang.String[] r2 = new java.lang.String[r3]
                java.lang.String r3 = "EXCEPTION_0"
                r2[r5] = r3
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r0 = r0.getMessage()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2[r4] = r0
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidCloudinary.a.doInBackground(java.lang.String[]):java.lang.String[]");
        }
    }

    @SimpleEvent(description = "Event raised after the Upload Media block has been used")
    public void MediaUploaded(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "MediaUploaded", str, str2);
    }
}

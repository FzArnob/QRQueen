package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.microsoft.appcenter.ingestion.models.CommonProperties;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Metadata Component to Read the Meta Data of a File", iconName = "images/metadata.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class Metadata extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;
    private MediaMetadataRetriever hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MediaMetadataRetriever();
    /* access modifiers changed from: private */
    public String plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM;

    public Metadata(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Log.d("KodularMetadata", "Metadata Created");
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty
    public void File(final String str) {
        this.form.runOnUiThread(new Runnable() {
            public final void run() {
                Metadata.this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            if (str == null) {
                                String unused = Metadata.this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = "";
                            } else {
                                String unused2 = Metadata.this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = str;
                            }
                            if (Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Metadata.this).length() > 0) {
                                try {
                                    Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Metadata.this).setDataSource(Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Metadata.this));
                                } catch (PermissionException e) {
                                    Metadata.this.form.dispatchPermissionDeniedEvent((Component) Metadata.this, "File", e);
                                } catch (Exception e2) {
                                    Log.e("KodularMetadata", e2.getMessage());
                                }
                            } else {
                                Log.i("KodularMetadata", "No valid file path: " + Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Metadata.this));
                            }
                        } else {
                            Metadata.this.form.dispatchPermissionDeniedEvent((Component) Metadata.this, "File", "android.permission.READ_EXTERNAL_STORAGE");
                        }
                    }
                });
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file.")
    public String File() {
        return this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM;
    }

    @SimpleFunction(description = "Get the Album from the file.")
    public String Album() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(1, "Album");
    }

    @SimpleFunction(description = "Get the Artist from the file.")
    public String Artist() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(2, "Artist");
    }

    @SimpleFunction(description = "Get the Author from the file.")
    public String Author() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(3, "Author");
    }

    @SimpleFunction(description = "Get the Bitrate from the file.")
    public String Bitrate() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(20, "Bitrate");
    }

    @SimpleFunction(description = "Get the Track Number from the file.")
    public String TrackNumber() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(0, "Track Number");
    }

    @SimpleFunction(description = "Get the Composer from the file.")
    public String Composer() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(4, "Composer");
    }

    @SimpleFunction(description = "Get the Date from the file.")
    public String Date() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(5, "Date");
    }

    @SimpleFunction(description = "Get the Disc Number from the file.")
    public String DiscNumber() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(14, "Disc Number");
    }

    @SimpleFunction(description = "Get the Duration from the file.")
    public String Duration() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(9, "Duration");
    }

    @SimpleFunction(description = "Get the Genre from the file.")
    public String Genre() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(6, "Genre");
    }

    @SimpleFunction(description = "Get the Has Audio from the file.")
    public String HasAudio() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(16, "Has Audio");
    }

    @SimpleFunction(description = "Get the Has Video from the file.")
    public String HasVideo() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(17, "Has Video");
    }

    @SimpleFunction(description = "Get the Location from the file.")
    public String Location() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(23, "Location");
    }

    @SimpleFunction(description = "Get the Mimetype from the file.")
    public String Mimetype() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(12, "Mimetype");
    }

    @SimpleFunction(description = "Get the Title from the file.")
    public String Title() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(7, "Title");
    }

    @SimpleFunction(description = "Get the Video Height from the file.")
    public String VideoHeight() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(19, "Video Height");
    }

    @SimpleFunction(description = "Get the Video Width from the file.")
    public String VideoWidth() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(18, "Video Width");
    }

    @SimpleFunction(description = "Get the Video Rotation from the file.")
    public String VideoRotation() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(24, "Video Rotation");
    }

    @SimpleFunction(description = "Get the Writer from the file.")
    public String Writer() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(11, "Writer");
    }

    @SimpleFunction(description = "Get the Year from the file.")
    public String Year() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(8, "Year");
    }

    @SimpleFunction(description = "Get a custom metadata item from the file\nyou can find a list of ids on https://developer.android.com/reference/android/media/MediaMetadataRetriever.html")
    public String CustomItem(int i) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, "Custom");
    }

    @SimpleFunction(description = "This method finds the optional graphic or album/cover art associated associated with the data source. If there are more than one pictures, (any) one of them is returned.")
    public String EmbeddedPicture() {
        try {
            byte[] embeddedPicture = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getEmbeddedPicture();
            if (embeddedPicture != null) {
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BitmapFactory.decodeByteArray(embeddedPicture, 0, embeddedPicture.length));
            }
        } catch (Exception e) {
            Log.e("KodularMetadata", String.valueOf(e));
        }
        NoMetadata("EmbeddedPicture");
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006a A[SYNTHETIC, Splitter:B:21:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088 A[SYNTHETIC, Splitter:B:29:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a A[SYNTHETIC, Splitter:B:35:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0061=Splitter:B:18:0x0061, B:26:0x007d=Splitter:B:26:0x007d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap r6) {
        /*
            r5 = this;
            java.lang.String r0 = "KodularMetadata"
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r2.<init>()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r4 = 0
            r6.compress(r3, r4, r2)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.File r6 = new java.io.File     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.<init>()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            android.content.Context r4 = r5.context     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.File r4 = com.google.appinventor.components.runtime.util.QUtil.getExternalStorageDir(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r4 = r5.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.append(r4)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.lang.String r3 = r3.toString()     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r6.<init>(r3)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            r3.<init>(r6)     // Catch:{ PermissionException -> 0x007c, Exception -> 0x0060 }
            byte[] r1 = r2.toByteArray()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.write(r1)     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.flush()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.close()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ PermissionException -> 0x005b, Exception -> 0x0058, all -> 0x0055 }
            r3.flush()     // Catch:{ Exception -> 0x004c }
            r3.close()     // Catch:{ Exception -> 0x004c }
            goto L_0x0054
        L_0x004c:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x0054:
            return r6
        L_0x0055:
            r6 = move-exception
            r1 = r3
            goto L_0x0098
        L_0x0058:
            r6 = move-exception
            r1 = r3
            goto L_0x0061
        L_0x005b:
            r6 = move-exception
            r1 = r3
            goto L_0x007d
        L_0x005e:
            r6 = move-exception
            goto L_0x0098
        L_0x0060:
            r6 = move-exception
        L_0x0061:
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x005e }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0079
            r1.flush()     // Catch:{ Exception -> 0x0071 }
            r1.close()     // Catch:{ Exception -> 0x0071 }
            goto L_0x0079
        L_0x0071:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            android.util.Log.e(r0, r6)
        L_0x0079:
            java.lang.String r6 = "ERROR"
            return r6
        L_0x007c:
            r6 = move-exception
        L_0x007d:
            com.google.appinventor.components.runtime.Form r2 = r5.form     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "EmbeddedPicture"
            r2.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r3, (com.google.appinventor.components.runtime.errors.PermissionException) r6)     // Catch:{ all -> 0x005e }
            java.lang.String r6 = "PERMISSION_DENIED_ERROR"
            if (r1 == 0) goto L_0x0097
            r1.flush()     // Catch:{ Exception -> 0x008f }
            r1.close()     // Catch:{ Exception -> 0x008f }
            goto L_0x0097
        L_0x008f:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x0097:
            return r6
        L_0x0098:
            if (r1 == 0) goto L_0x00a9
            r1.flush()     // Catch:{ Exception -> 0x00a1 }
            r1.close()     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00a9
        L_0x00a1:
            r1 = move-exception
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.util.Log.e(r0, r1)
        L_0x00a9:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap):java.lang.String");
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, String str) {
        if (this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM != null) {
            try {
                String extractMetadata = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.extractMetadata(i);
                if (extractMetadata != null) {
                    return extractMetadata;
                }
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, str, e);
            } catch (Exception e2) {
                Log.e("KodularMetadata", String.valueOf(e2));
            }
        }
        NoMetadata(str);
        return "";
    }

    @SimpleEvent(description = "Triggers when there is no metadata found in the file.")
    public void NoMetadata(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder("There was no ");
        sb.append(str.isEmpty() ? CommonProperties.TYPE : str);
        sb.append(" found in the metadata of the file");
        EventDispatcher.dispatchEvent(this, "NoMetadata", sb.toString(), str);
    }
}

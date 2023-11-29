package com.google.appinventor.components.runtime;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Storage", iconName = "images/firebaseDB.png", nonVisible = true, version = 2)
@UsesLibraries({"firebase-annotations.jar", "firebase-appcheck-interop.jar", "firebase-appcheck-interop.aar", "firebase-auth-interop.jar", "firebase-auth-interop.aar", "firebase-common.jar", "firebase-common.aar", "firebase-components.jar", "firebase-components.aar", "firebase-storage.jar", "firebase-storage.aar", "play-services-base.jar", "play-services-base.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-tasks.jar", "play-services-tasks.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK", "com.google.android.c2dm.permission.RECEIVE"})
public class KodularFirebaseStorage extends AndroidNonvisibleComponent {
    private Context context;
    private FirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private StorageReference f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public KodularFirebaseStorage(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        FirebaseStorage instance = FirebaseStorage.getInstance();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = instance;
        this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = instance.getReference();
    }

    @SimpleFunction(description = "Try to upload a file to Firebase Storage")
    public void UploadFile(final String str, final String str2) {
        try {
            final File file = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            final StorageReference child = this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str2);
            child.putStream(fileInputStream).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                public final /* synthetic */ void onProgress(Object obj) {
                    KodularFirebaseStorage.this.UploadProgress(str, str2, ((UploadTask.TaskSnapshot) obj).getBytesTransferred(), file.length());
                }
            }).addOnFailureListener(new OnFailureListener() {
                public final void onFailure(Exception exc) {
                    KodularFirebaseStorage kodularFirebaseStorage = KodularFirebaseStorage.this;
                    String str = str;
                    kodularFirebaseStorage.UploadFailed(str, exc.getMessage());
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    child.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        public final void onComplete(Task<Uri> task) {
                            if (task.isSuccessful()) {
                                KodularFirebaseStorage.this.UploadSuccess(str, str2, ((Uri) task.getResult()).toString());
                            } else {
                                KodularFirebaseStorage.this.UploadSuccess(str, str2, "");
                            }
                        }
                    });
                }
            });
        } catch (FileNotFoundException e) {
            UploadFailed(str, e.getMessage());
        }
    }

    @SimpleFunction(description = "Try to download a file from Firebase Storage")
    public void DownloadFile(final String str, String str2) {
        StorageReference child = this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str);
        File file = new File(str2);
        if (file.exists() || file.mkdirs()) {
            final File file2 = new File(str2, new File(str).getName());
            child.getFile(file2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    KodularFirebaseStorage.this.DownloadSuccess(str, file2.getAbsolutePath());
                }
            }).addOnFailureListener(new OnFailureListener() {
                public final void onFailure(Exception exc) {
                    KodularFirebaseStorage kodularFirebaseStorage = KodularFirebaseStorage.this;
                    String str = str;
                    kodularFirebaseStorage.DownloadFailed(str, exc.getMessage());
                }
            });
            return;
        }
        DownloadFailed(str, "Could not create a folder to store the download");
    }

    @SimpleFunction(description = "Try to delete a file from Firebase Storage")
    public void DeleteFile(final String str) {
        this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                KodularFirebaseStorage.this.DeleteSuccess(str);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public final void onFailure(Exception exc) {
                KodularFirebaseStorage kodularFirebaseStorage = KodularFirebaseStorage.this;
                String str = str;
                kodularFirebaseStorage.DeleteFailed(str, exc.getMessage());
            }
        });
    }

    @SimpleFunction(description = "Pause all the active uploads")
    public void PauseUploads() {
        for (UploadTask pause : this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveUploadTasks()) {
            pause.pause();
        }
    }

    @SimpleFunction(description = "Resume all the active uploads")
    public void ResumeUploads() {
        for (UploadTask resume : this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveUploadTasks()) {
            resume.resume();
        }
    }

    @SimpleFunction(description = "Pause all the active downloads")
    public void PauseDownloads() {
        for (FileDownloadTask pause : this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveDownloadTasks()) {
            pause.pause();
        }
    }

    @SimpleFunction(description = "Resume all the active downloads")
    public void ResumeDownloads() {
        for (FileDownloadTask resume : this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveDownloadTasks()) {
            resume.resume();
        }
    }

    @SimpleFunction(description = "Get the name of the Storage Bucket")
    public String GetBucket() {
        return this.f183hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBucket();
    }

    @SimpleEvent(description = "Triggers when the file was successfully uploaded")
    public void UploadSuccess(String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "UploadSuccess", str, str2, str3);
    }

    @SimpleEvent(description = "Triggers when the file could not be uploaded")
    public void UploadFailed(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "UploadFailed", str, str2);
    }

    @SimpleEvent(description = "Triggers when the file upload progress changed")
    public void UploadProgress(String str, String str2, long j, long j2) {
        EventDispatcher.dispatchEvent(this, "UploadProgress", str, str2, Long.valueOf(j), Long.valueOf(j2));
    }

    @SimpleEvent(description = "Triggers when the file was successfully downloaded")
    public void DownloadSuccess(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "DownloadSuccess", str, str2);
    }

    @SimpleEvent(description = "Triggers when the file could not be downloaded")
    public void DownloadFailed(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "DownloadFailed", str, str2);
    }

    @SimpleEvent(description = "Triggers when the file was successfully deleted")
    public void DeleteSuccess(String str) {
        EventDispatcher.dispatchEvent(this, "DeleteSuccess", str);
    }

    @SimpleEvent(description = "Triggers when the file could not be deleted")
    public void DeleteFailed(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "DeleteFailed", str, str2);
    }
}

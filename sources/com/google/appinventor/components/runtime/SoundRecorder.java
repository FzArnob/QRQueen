package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
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
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.File;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>Multimedia component that records audio.</p>", iconName = "images/soundRecorder.png", nonVisible = true, version = 4)
@SimpleObject
@UsesPermissions({"android.permission.RECORD_AUDIO"})
public final class SoundRecorder extends AndroidNonvisibleComponent implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener, Component {
    private String NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V = "";
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    class a {
        final MediaRecorder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        final String peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w;

        a(String str) throws IOException {
            str = str.equals("") ? FileUtil.getRecordingFile(SoundRecorder.this.form, "3gp").getAbsolutePath() : str;
            this.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w = str;
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mediaRecorder;
            mediaRecorder.setAudioSource(1);
            mediaRecorder.setOutputFormat(1);
            mediaRecorder.setAudioEncoder(1);
            Log.i("SoundRecorder", "Setting output file to " + str);
            mediaRecorder.setOutputFile(str);
            Log.i("SoundRecorder", "preparing");
            mediaRecorder.prepare();
            mediaRecorder.setOnErrorListener(SoundRecorder.this);
            mediaRecorder.setOnInfoListener(SoundRecorder.this);
        }

        /* access modifiers changed from: package-private */
        public final void stop() {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnErrorListener((MediaRecorder.OnErrorListener) null);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnInfoListener((MediaRecorder.OnInfoListener) null);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.reset();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
        }
    }

    public final void Initialize() {
        this.havePermission = !this.form.isDeniedPermission("android.permission.RECORD_AUDIO");
        if (FileUtil.needsWritePermission(this.form.DefaultFileScope())) {
            this.havePermission &= !this.form.isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    public SoundRecorder(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path to the file where the recording should be stored. If this property is the empty string, then starting a recording will create a file in an appropriate location.  If the property is not the empty string, it should specify a complete path to a file in an existing directory, including a file name with the extension .3gp.")
    public final String SavedRecording() {
        return this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public final void SavedRecording(String str) {
        this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V = str;
    }

    @SimpleFunction(description = "Use this block to start the sound recorder.")
    public final void Start() {
        String[] strArr;
        String resolveFileName = FileUtil.resolveFileName(this.form, this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V, this.form.DefaultFileScope());
        if (!this.havePermission) {
            if (FileUtil.needsPermission(this.form, resolveFileName)) {
                strArr = new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
            } else {
                strArr = new String[]{"android.permission.RECORD_AUDIO"};
            }
            if (MediaUtil.isExternalFile(this.form.$context(), this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V)) {
                this.form.askPermission(new BulkPermissionRequest(this, "Start", strArr) {
                    public final void onGranted() {
                        boolean unused = SoundRecorder.this.havePermission = true;
                        SoundRecorder.this.Start();
                    }
                });
            } else {
                this.form.askPermission(new BulkPermissionRequest(this, "Start", "android.permission.RECORD_AUDIO") {
                    public final void onGranted() {
                        boolean unused = SoundRecorder.this.havePermission = true;
                        SoundRecorder.this.Start();
                    }
                });
            }
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("SoundRecorder", "Start() called, but already recording to " + this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w);
        } else {
            Log.i("SoundRecorder", "Start() called");
            if (!FileUtil.isExternalStorageUri(this.form, resolveFileName) || Environment.getExternalStorageState().equals("mounted")) {
                try {
                    if (!this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V.equals("")) {
                        File parentFile = new File(this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V).getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                    }
                    a aVar = new a(this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
                    try {
                        Log.i("SoundRecorder", "starting");
                        aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
                        StartedRecording();
                    } catch (IllegalStateException e) {
                        Log.e("SoundRecorder", "got IllegalStateException. Are there two recorders running?", e);
                        throw new IllegalStateException("Is there another recording running?");
                    } catch (Throwable th) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                        Log.e("SoundRecorder", "Cannot record sound", th);
                        Form form = this.form;
                        form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, th.getMessage());
                    }
                } catch (PermissionException e2) {
                    this.form.dispatchPermissionDeniedEvent((Component) this, "Start", e2);
                } catch (Throwable th2) {
                    Log.e("SoundRecorder", "Cannot record sound", th2);
                    Form form2 = this.form;
                    form2.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, th2.getMessage());
                }
            } else {
                this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
            }
        }
    }

    public final void onError(MediaRecorder mediaRecorder, int i, int i2) {
        a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (aVar == null || mediaRecorder != aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
            Log.w("SoundRecorder", "onError called with wrong recorder. Ignoring.");
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "onError", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
        } catch (Throwable th) {
            Log.w("SoundRecorder", th.getMessage());
        } finally {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        }
    }

    public final void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (aVar == null || mediaRecorder != aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
            Log.w("SoundRecorder", "onInfo called with wrong recorder. Ignoring.");
            return;
        }
        if (i == 1) {
            this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
        } else if (i == 800) {
            this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER_MAX_DURATION_REACHED, new Object[0]);
        } else if (i == 801) {
            this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER_MAX_FILESIZE_REACHED, new Object[0]);
        } else {
            return;
        }
        try {
            Log.i("SoundRecorder", "Recoverable condition while recording. Will attempt to stop normally.");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
        } catch (IllegalStateException e) {
            Log.i("SoundRecorder", "SoundRecorder was not in a recording state.", e);
            this.form.dispatchErrorOccurredEventDialog(this, "Stop", ErrorMessages.ERROR_SOUND_RECORDER_ILLEGAL_STOP, new Object[0]);
        } finally {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:9|10|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r5.form.dispatchErrorOccurredEvent(r5, "Stop", com.google.appinventor.components.runtime.util.ErrorMessages.ERROR_SOUND_RECORDER, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0050, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
        r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        StoppedRecording();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x003e */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Use this block to stop the sound recorder.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void Stop() {
        /*
            r5 = this;
            com.google.appinventor.components.runtime.SoundRecorder$a r0 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            java.lang.String r1 = "SoundRecorder"
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = "Stop() called, but already stopped."
            android.util.Log.i(r1, r0)
            return
        L_0x000c:
            r0 = 0
            java.lang.String r2 = "Stop() called"
            android.util.Log.i(r1, r2)     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "stopping"
            android.util.Log.i(r1, r2)     // Catch:{ all -> 0x003e }
            com.google.appinventor.components.runtime.SoundRecorder$a r2 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ all -> 0x003e }
            r2.stop()     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "Firing AfterSoundRecorded with "
            r2.<init>(r3)     // Catch:{ all -> 0x003e }
            com.google.appinventor.components.runtime.SoundRecorder$a r3 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ all -> 0x003e }
            java.lang.String r3 = r3.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w     // Catch:{ all -> 0x003e }
            r2.append(r3)     // Catch:{ all -> 0x003e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x003e }
            android.util.Log.i(r1, r2)     // Catch:{ all -> 0x003e }
            com.google.appinventor.components.runtime.SoundRecorder$a r1 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ all -> 0x003e }
            java.lang.String r1 = r1.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w     // Catch:{ all -> 0x003e }
            r5.AfterSoundRecorded(r1)     // Catch:{ all -> 0x003e }
            r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            r5.StoppedRecording()
            return
        L_0x003e:
            com.google.appinventor.components.runtime.Form r1 = r5.form     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "Stop"
            r3 = 801(0x321, float:1.122E-42)
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0050 }
            r1.dispatchErrorOccurredEvent(r5, r2, r3, r4)     // Catch:{ all -> 0x0050 }
            r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            r5.StoppedRecording()
            return
        L_0x0050:
            r1 = move-exception
            r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            r5.StoppedRecording()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SoundRecorder.Stop():void");
    }

    @SimpleFunction(description = "Use this block to pause the sound recorder. Works only on Android 6 and above.")
    public final void Pause() {
        a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (aVar != null && Build.VERSION.SDK_INT >= 24) {
            aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
            SoundRecorder.this.PausedRecording();
        }
    }

    @SimpleFunction(description = "Use this block to resume the sound recorder. Works only on Android 6 and above.")
    public final void Resume() {
        a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (aVar != null && Build.VERSION.SDK_INT >= 24) {
            aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resume();
            SoundRecorder.this.ResumedRecording();
        }
    }

    @SimpleEvent(description = "Provides the location of the newly created sound.")
    public final void AfterSoundRecorded(String str) {
        EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", str);
    }

    @SimpleEvent(description = "Indicates that the recorder has started, and can be stopped.")
    public final void StartedRecording() {
        EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has stopped, and can be started again.")
    public final void StoppedRecording() {
        EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has paused, and can be resumed again.")
    public final void PausedRecording() {
        EventDispatcher.dispatchEvent(this, "PausedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has resumed, and can be started again.")
    public final void ResumedRecording() {
        EventDispatcher.dispatchEvent(this, "ResumedRecording", new Object[0]);
    }
}

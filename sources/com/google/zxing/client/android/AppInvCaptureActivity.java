package com.google.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public final class AppInvCaptureActivity extends Activity implements SurfaceHolder.Callback {
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000;
    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500;
    private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE, ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);
    private static final String RAW_PARAM = "raw";
    private static final String RETURN_CODE_PLACEHOLDER = "{CODE}";
    private static final String RETURN_URL_PARAM = "ret";
    private static final String TAG = "AppInvCaptureActivity";
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private String characterSet;
    private boolean copyToClipboard;
    private Collection<BarcodeFormat> decodeFormats;
    private FrameLayout frameLayout;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private Result lastResult;
    private boolean returnRaw;
    private String returnUrlTemplate;
    private Result savedResultToShow;
    private IntentSource source;
    private String sourceUrl;
    private SurfaceView surfaceView;
    private LinearLayout viewLayout;
    private ViewfinderView viewfinderView;

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    /* access modifiers changed from: package-private */
    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public Handler getHandler() {
        return this.handler;
    }

    /* access modifiers changed from: package-private */
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        LinearLayout linearLayout = new LinearLayout(this);
        this.viewLayout = linearLayout;
        linearLayout.setOrientation(0);
        FrameLayout frameLayout2 = new FrameLayout(this);
        this.frameLayout = frameLayout2;
        frameLayout2.addView(this.viewLayout, new ViewGroup.LayoutParams(-1, -1));
        this.frameLayout.setBackgroundColor(-1);
        setContentView(this.frameLayout);
        this.frameLayout.requestLayout();
        this.hasSurface = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.cameraManager = new CameraManager(getApplication());
        ViewfinderView viewfinderView2 = new ViewfinderView(this, (AttributeSet) null);
        this.viewfinderView = viewfinderView2;
        viewfinderView2.setCameraManager(this.cameraManager);
        this.frameLayout.addView(this.viewfinderView);
        this.handler = null;
        this.lastResult = null;
        if (this.surfaceView == null) {
            SurfaceView surfaceView2 = new SurfaceView(this);
            this.surfaceView = surfaceView2;
            this.viewLayout.addView(surfaceView2);
        }
        SurfaceHolder holder = this.surfaceView.getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.surfaceView.setVisibility(0);
        Intent intent = getIntent();
        this.copyToClipboard = true;
        this.source = IntentSource.NONE;
        this.decodeFormats = null;
        this.characterSet = null;
        if (intent != null) {
            String action = intent.getAction();
            intent.getDataString();
            if (Intents.Scan.ACTION.equals(action)) {
                this.source = IntentSource.NATIVE_APP_INTENT;
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int intExtra = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int intExtra2 = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (intExtra > 0 && intExtra2 > 0) {
                        this.cameraManager.setManualFramingRect(intExtra, intExtra2);
                    }
                }
            }
            this.characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        this.cameraManager.closeDriver();
        if (!this.hasSurface) {
            SurfaceView surfaceView2 = new SurfaceView(this);
            this.surfaceView = surfaceView2;
            surfaceView2.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (!(i == 27 || i == 80)) {
                if (i == 24) {
                    this.cameraManager.setTorch(true);
                } else if (i == 25) {
                    this.cameraManager.setTorch(false);
                    return true;
                }
            }
            return true;
        } else if (this.source == IntentSource.NATIVE_APP_INTENT) {
            setResult(0);
            finish();
            return true;
        } else if ((this.source == IntentSource.NONE || this.source == IntentSource.ZXING_LINK) && this.lastResult != null) {
            restartPreviewAfterDelay(0);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        if (this.handler == null) {
            this.savedResultToShow = result;
            return;
        }
        if (result != null) {
            this.savedResultToShow = result;
        }
        this.savedResultToShow = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.hasSurface) {
            this.hasSurface = true;
            initCamera(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    public void handleDecode(Result result, Bitmap bitmap) {
        this.lastResult = result;
        ResultHandler makeResultHandler = ResultHandlerFactory.makeResultHandler(this, result);
        boolean z = bitmap != null;
        if (z) {
            drawResultPoints(bitmap, result);
        }
        int i = AnonymousClass1.$SwitchMap$com$google$zxing$client$android$IntentSource[this.source.ordinal()];
        if (i == 1 || i == 2) {
            handleDecodeExternally(result, makeResultHandler, bitmap);
        } else if (i == 3 && z) {
            Toast.makeText(this, " (bulk scan)", 0).show();
            restartPreviewAfterDelay(1000);
        }
    }

    /* renamed from: com.google.zxing.client.android.AppInvCaptureActivity$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$client$android$IntentSource;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.zxing.client.android.IntentSource[] r0 = com.google.zxing.client.android.IntentSource.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$client$android$IntentSource = r0
                com.google.zxing.client.android.IntentSource r1 = com.google.zxing.client.android.IntentSource.NATIVE_APP_INTENT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$client$android$IntentSource     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.client.android.IntentSource r1 = com.google.zxing.client.android.IntentSource.PRODUCT_SEARCH_LINK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$client$android$IntentSource     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.client.android.IntentSource r1 = com.google.zxing.client.android.IntentSource.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.android.AppInvCaptureActivity.AnonymousClass1.<clinit>():void");
        }
    }

    private void drawResultPoints(Bitmap bitmap, Result result) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints != null && resultPoints.length > 0) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(-1063662592);
            if (resultPoints.length == 2) {
                paint.setStrokeWidth(4.0f);
                drawLine(canvas, paint, resultPoints[0], resultPoints[1]);
            } else if (resultPoints.length == 4 && (result.getBarcodeFormat() == BarcodeFormat.UPC_A || result.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                drawLine(canvas, paint, resultPoints[0], resultPoints[1]);
                drawLine(canvas, paint, resultPoints[2], resultPoints[3]);
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint resultPoint : resultPoints) {
                    canvas.drawPoint(resultPoint.getX(), resultPoint.getY(), paint);
                }
            }
        }
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2) {
        canvas.drawLine(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), paint);
    }

    private void handleDecodeExternally(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        if (bitmap != null) {
            this.viewfinderView.drawResultBitmap(bitmap);
        }
        Intent intent = getIntent();
        long j = DEFAULT_INTENT_RESULT_DURATION_MS;
        if (intent != null) {
            j = getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, DEFAULT_INTENT_RESULT_DURATION_MS);
        }
        if (this.copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            CharSequence displayContents = resultHandler.getDisplayContents();
            if (displayContents != null) {
                clipboardManager.setText(displayContents);
            }
        }
        if (this.source == IntentSource.NATIVE_APP_INTENT) {
            Intent intent2 = new Intent(getIntent().getAction());
            intent2.addFlags(524288);
            intent2.putExtra(Intents.Scan.RESULT, result.toString());
            intent2.putExtra(Intents.Scan.RESULT_FORMAT, result.getBarcodeFormat().toString());
            byte[] rawBytes = result.getRawBytes();
            if (rawBytes != null && rawBytes.length > 0) {
                intent2.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
            }
            Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
            if (resultMetadata != null) {
                if (resultMetadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                    intent2.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
                }
                Integer num = (Integer) resultMetadata.get(ResultMetadataType.ORIENTATION);
                if (num != null) {
                    intent2.putExtra(Intents.Scan.RESULT_ORIENTATION, num.intValue());
                }
                String str = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
                if (str != null) {
                    intent2.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, str);
                }
                Iterable<byte[]> iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
                if (iterable != null) {
                    int i = 0;
                    for (byte[] putExtra : iterable) {
                        intent2.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, putExtra);
                        i++;
                    }
                }
            }
            sendReplyMessage(6, intent2, j);
        }
    }

    private void sendReplyMessage(int i, Object obj, long j) {
        Message obtain = Message.obtain(this.handler, i, obj);
        if (j > 0) {
            this.handler.sendMessageDelayed(obtain, j);
        } else {
            this.handler.sendMessage(obtain);
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        String str = TAG;
        Log.w(str, "initCamera() was called");
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.cameraManager.isOpen()) {
            Log.w(str, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.cameraManager.openDriver(surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet, this.cameraManager);
                }
                decodeOrStoreSavedBitmap((Bitmap) null, (Result) null);
            } catch (IOException e) {
                Log.w(TAG, e);
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e2) {
                Log.w(TAG, "Unexpected error initializing camera", e2);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scanner");
        builder.setMessage("Camera Framework Bug");
        builder.show();
    }

    public void restartPreviewAfterDelay(long j) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.sendEmptyMessageDelayed(5, j);
        }
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }
}

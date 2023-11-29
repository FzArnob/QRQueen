package com.bumptech.glide.gifencoder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AnimatedGifEncoder {
    private static final double MIN_TRANSPARENT_PERCENTAGE = 4.0d;
    private static final String TAG = "AnimatedGifEncoder";
    private boolean closeStream = false;
    private int colorDepth;
    private byte[] colorTab;
    private int delay = 0;
    private int dispose = -1;
    private boolean firstFrame = true;
    private boolean hasTransparentPixels;
    private int height;
    private Bitmap image;
    private byte[] indexedPixels;
    private OutputStream out;
    private int palSize = 7;
    private byte[] pixels;
    private int repeat = -1;
    private int sample = 10;
    private boolean sizeSet = false;
    private boolean started = false;
    private int transIndex;
    private Integer transparent = null;
    private boolean[] usedEntry = new boolean[256];
    private int width;

    public void setDelay(int i) {
        this.delay = Math.round(((float) i) / 10.0f);
    }

    public void setDispose(int i) {
        if (i >= 0) {
            this.dispose = i;
        }
    }

    public void setRepeat(int i) {
        if (i >= 0) {
            this.repeat = i;
        }
    }

    public void setTransparent(int i) {
        this.transparent = Integer.valueOf(i);
    }

    public boolean addFrame(Bitmap bitmap) {
        if (bitmap == null || !this.started) {
            return false;
        }
        try {
            if (!this.sizeSet) {
                setSize(bitmap.getWidth(), bitmap.getHeight());
            }
            this.image = bitmap;
            getImagePixels();
            analyzePixels();
            if (this.firstFrame) {
                writeLSD();
                writePalette();
                if (this.repeat >= 0) {
                    writeNetscapeExt();
                }
            }
            writeGraphicCtrlExt();
            writeImageDesc();
            if (!this.firstFrame) {
                writePalette();
            }
            writePixels();
            this.firstFrame = false;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean finish() {
        boolean z;
        if (!this.started) {
            return false;
        }
        this.started = false;
        try {
            this.out.write(59);
            this.out.flush();
            if (this.closeStream) {
                this.out.close();
            }
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        this.transIndex = 0;
        this.out = null;
        this.image = null;
        this.pixels = null;
        this.indexedPixels = null;
        this.colorTab = null;
        this.closeStream = false;
        this.firstFrame = true;
        return z;
    }

    public void setFrameRate(float f) {
        if (f != 0.0f) {
            this.delay = Math.round(100.0f / f);
        }
    }

    public void setQuality(int i) {
        if (i < 1) {
            i = 1;
        }
        this.sample = i;
    }

    public void setSize(int i, int i2) {
        if (!this.started || this.firstFrame) {
            this.width = i;
            this.height = i2;
            if (i < 1) {
                this.width = ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION;
            }
            if (i2 < 1) {
                this.height = 240;
            }
            this.sizeSet = true;
        }
    }

    public boolean start(OutputStream outputStream) {
        boolean z = false;
        if (outputStream == null) {
            return false;
        }
        this.closeStream = false;
        this.out = outputStream;
        try {
            writeString("GIF89a");
            z = true;
        } catch (IOException unused) {
        }
        this.started = z;
        return z;
    }

    public boolean start(String str) {
        boolean z;
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            this.out = bufferedOutputStream;
            z = start((OutputStream) bufferedOutputStream);
            this.closeStream = true;
        } catch (IOException unused) {
            z = false;
        }
        this.started = z;
        return z;
    }

    private void analyzePixels() {
        byte[] bArr = this.pixels;
        int length = bArr.length;
        int i = length / 3;
        this.indexedPixels = new byte[i];
        NeuQuant neuQuant = new NeuQuant(bArr, length, this.sample);
        this.colorTab = neuQuant.process();
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.colorTab;
            if (i2 >= bArr2.length) {
                break;
            }
            byte b = bArr2[i2];
            int i3 = i2 + 2;
            bArr2[i2] = bArr2[i3];
            bArr2[i3] = b;
            this.usedEntry[i2 / 3] = false;
            i2 += 3;
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            byte[] bArr3 = this.pixels;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int map = neuQuant.map(bArr3[i5] & Ev3Constants.Opcode.TST, bArr3[i6] & Ev3Constants.Opcode.TST, bArr3[i7] & Ev3Constants.Opcode.TST);
            this.usedEntry[map] = true;
            this.indexedPixels[i4] = (byte) map;
            i4++;
            i5 = i7 + 1;
        }
        this.pixels = null;
        this.colorDepth = 8;
        this.palSize = 7;
        Integer num = this.transparent;
        if (num != null) {
            this.transIndex = findClosest(num.intValue());
        } else if (this.hasTransparentPixels) {
            this.transIndex = findClosest(0);
        }
    }

    private int findClosest(int i) {
        if (this.colorTab == null) {
            return -1;
        }
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        int i2 = 16777216;
        int length = this.colorTab.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            byte[] bArr = this.colorTab;
            int i5 = i3 + 1;
            int i6 = red - (bArr[i3] & Ev3Constants.Opcode.TST);
            int i7 = i5 + 1;
            int i8 = green - (bArr[i5] & Ev3Constants.Opcode.TST);
            int i9 = blue - (bArr[i7] & Ev3Constants.Opcode.TST);
            int i10 = (i6 * i6) + (i8 * i8) + (i9 * i9);
            int i11 = i7 / 3;
            if (this.usedEntry[i11] && i10 < i2) {
                i2 = i10;
                i4 = i11;
            }
            i3 = i7 + 1;
        }
        return i4;
    }

    private void getImagePixels() {
        int width2 = this.image.getWidth();
        int height2 = this.image.getHeight();
        int i = this.width;
        if (!(width2 == i && height2 == this.height)) {
            Bitmap createBitmap = Bitmap.createBitmap(i, this.height, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
            this.image = createBitmap;
        }
        int i2 = width2 * height2;
        int[] iArr = new int[i2];
        this.image.getPixels(iArr, 0, width2, 0, 0, width2, height2);
        this.pixels = new byte[(i2 * 3)];
        boolean z = false;
        this.hasTransparentPixels = false;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2) {
            int i6 = iArr[i3];
            if (i6 == 0) {
                i4++;
            }
            byte[] bArr = this.pixels;
            int i7 = i5 + 1;
            bArr[i5] = (byte) (i6 & 255);
            int i8 = i7 + 1;
            bArr[i7] = (byte) ((i6 >> 8) & 255);
            bArr[i8] = (byte) ((i6 >> 16) & 255);
            i3++;
            i5 = i8 + 1;
        }
        double d = ((double) (i4 * 100)) / ((double) i2);
        if (d > MIN_TRANSPARENT_PERCENTAGE) {
            z = true;
        }
        this.hasTransparentPixels = z;
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "got pixels for frame with " + d + "% transparent pixels");
        }
    }

    private void writeGraphicCtrlExt() throws IOException {
        int i;
        int i2;
        this.out.write(33);
        this.out.write(249);
        this.out.write(4);
        if (this.transparent != null || this.hasTransparentPixels) {
            i2 = 1;
            i = 2;
        } else {
            i2 = 0;
            i = 0;
        }
        int i3 = this.dispose;
        if (i3 >= 0) {
            i = i3 & 7;
        }
        this.out.write(i2 | (i << 2) | 0 | 0);
        writeShort(this.delay);
        this.out.write(this.transIndex);
        this.out.write(0);
    }

    private void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(0);
        writeShort(0);
        writeShort(this.width);
        writeShort(this.height);
        if (this.firstFrame) {
            this.out.write(0);
        } else {
            this.out.write(this.palSize | 128);
        }
    }

    private void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(this.palSize | 240);
        this.out.write(0);
        this.out.write(0);
    }

    private void writeNetscapeExt() throws IOException {
        this.out.write(33);
        this.out.write(255);
        this.out.write(11);
        writeString("NETSCAPE2.0");
        this.out.write(3);
        this.out.write(1);
        writeShort(this.repeat);
        this.out.write(0);
    }

    private void writePalette() throws IOException {
        OutputStream outputStream = this.out;
        byte[] bArr = this.colorTab;
        outputStream.write(bArr, 0, bArr.length);
        int length = 768 - this.colorTab.length;
        for (int i = 0; i < length; i++) {
            this.out.write(0);
        }
    }

    private void writePixels() throws IOException {
        new LZWEncoder(this.width, this.height, this.indexedPixels, this.colorDepth).encode(this.out);
    }

    private void writeShort(int i) throws IOException {
        this.out.write(i & 255);
        this.out.write((i >> 8) & 255);
    }

    private void writeString(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.out.write((byte) str.charAt(i));
        }
    }
}

package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

public class GifDecoder {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = "GifDecoder";
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;
    private int[] act;
    private BitmapProvider bitmapProvider;
    private final byte[] block = new byte[256];
    private byte[] data;
    private int framePointer;
    private GifHeader header;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct = new int[256];
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider2) {
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return -1;
        }
        return getDelay(i);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bb, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r7.header     // Catch:{ all -> 0x00bc }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00bc }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r7.framePointer     // Catch:{ all -> 0x00bc }
            if (r0 >= 0) goto L_0x0039
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00bc }
            boolean r3 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00bc }
            if (r3 == 0) goto L_0x0037
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            r3.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.String r4 = "unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00bc }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00bc }
            r3.append(r4)     // Catch:{ all -> 0x00bc }
            java.lang.String r4 = " framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00bc }
            int r4 = r7.framePointer     // Catch:{ all -> 0x00bc }
            r3.append(r4)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00bc }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00bc }
        L_0x0037:
            r7.status = r2     // Catch:{ all -> 0x00bc }
        L_0x0039:
            int r0 = r7.status     // Catch:{ all -> 0x00bc }
            r3 = 0
            if (r0 == r2) goto L_0x009c
            r4 = 2
            if (r0 != r4) goto L_0x0042
            goto L_0x009c
        L_0x0042:
            r0 = 0
            r7.status = r0     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00bc }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00bc }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00bc }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00bc }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00bc }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0061
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00bc }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r6 = r6.frames     // Catch:{ all -> 0x00bc }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00bc }
            goto L_0x0062
        L_0x0061:
            r5 = r3
        L_0x0062:
            int[] r6 = r4.lct     // Catch:{ all -> 0x00bc }
            if (r6 == 0) goto L_0x0069
            int[] r6 = r4.lct     // Catch:{ all -> 0x00bc }
            goto L_0x006d
        L_0x0069:
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00bc }
            int[] r6 = r6.gct     // Catch:{ all -> 0x00bc }
        L_0x006d:
            r7.act = r6     // Catch:{ all -> 0x00bc }
            if (r6 != 0) goto L_0x0082
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00bc }
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x007e
            java.lang.String r1 = "No Valid Color Table"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00bc }
        L_0x007e:
            r7.status = r2     // Catch:{ all -> 0x00bc }
            monitor-exit(r7)
            return r3
        L_0x0082:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x0096
            int[] r1 = r7.act     // Catch:{ all -> 0x00bc }
            int[] r2 = r7.pct     // Catch:{ all -> 0x00bc }
            int r3 = r1.length     // Catch:{ all -> 0x00bc }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00bc }
            int[] r1 = r7.pct     // Catch:{ all -> 0x00bc }
            r7.act = r1     // Catch:{ all -> 0x00bc }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00bc }
            r1[r2] = r0     // Catch:{ all -> 0x00bc }
        L_0x0096:
            android.graphics.Bitmap r0 = r7.setPixels(r4, r5)     // Catch:{ all -> 0x00bc }
            monitor-exit(r7)
            return r0
        L_0x009c:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00bc }
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x00ba
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            r1.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00bc }
            int r2 = r7.status     // Catch:{ all -> 0x00bc }
            r1.append(r2)     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00bc }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r7)
            return r3
        L_0x00bc:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.rawData = wrap;
        wrap.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.mainPixels = new byte[(gifHeader.width * gifHeader.height)];
        this.mainScratch = new int[(gifHeader.width * gifHeader.height)];
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.rawData = wrap;
            wrap.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[(this.header.width * this.header.height)];
            this.mainScratch = new int[(this.header.width * this.header.height)];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return this.status;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        if (r0.header.bgIndex == r1.transIndex) goto L_0x0036;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap setPixels(com.bumptech.glide.gifdecoder.GifFrame r18, com.bumptech.glide.gifdecoder.GifFrame r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            com.bumptech.glide.gifdecoder.GifHeader r3 = r0.header
            int r3 = r3.width
            com.bumptech.glide.gifdecoder.GifHeader r4 = r0.header
            int r12 = r4.height
            int[] r13 = r0.mainScratch
            r14 = 0
            if (r2 != 0) goto L_0x0016
            java.util.Arrays.fill(r13, r14)
        L_0x0016:
            r15 = 3
            r11 = 2
            if (r2 == 0) goto L_0x0066
            int r4 = r2.dispose
            if (r4 <= 0) goto L_0x0066
            int r4 = r2.dispose
            if (r4 != r11) goto L_0x0052
            boolean r4 = r1.transparency
            if (r4 != 0) goto L_0x0036
            com.bumptech.glide.gifdecoder.GifHeader r4 = r0.header
            int r4 = r4.bgColor
            int[] r5 = r1.lct
            if (r5 == 0) goto L_0x0037
            com.bumptech.glide.gifdecoder.GifHeader r5 = r0.header
            int r5 = r5.bgIndex
            int r6 = r1.transIndex
            if (r5 != r6) goto L_0x0037
        L_0x0036:
            r4 = 0
        L_0x0037:
            int r5 = r2.iy
            int r5 = r5 * r3
            int r6 = r2.ix
            int r5 = r5 + r6
            int r6 = r2.ih
            int r6 = r6 * r3
            int r6 = r6 + r5
        L_0x0043:
            if (r5 >= r6) goto L_0x0066
            int r7 = r2.iw
            int r7 = r7 + r5
            r8 = r5
        L_0x0049:
            if (r8 >= r7) goto L_0x0050
            r13[r8] = r4
            int r8 = r8 + 1
            goto L_0x0049
        L_0x0050:
            int r5 = r5 + r3
            goto L_0x0043
        L_0x0052:
            int r2 = r2.dispose
            if (r2 != r15) goto L_0x0066
            android.graphics.Bitmap r4 = r0.previousImage
            if (r4 == 0) goto L_0x0066
            r6 = 0
            r8 = 0
            r9 = 0
            r5 = r13
            r7 = r3
            r10 = r3
            r2 = 2
            r11 = r12
            r4.getPixels(r5, r6, r7, r8, r9, r10, r11)
            goto L_0x0067
        L_0x0066:
            r2 = 2
        L_0x0067:
            r17.decodeBitmapData(r18)
            r4 = 8
            r11 = 1
            r5 = 0
            r6 = 1
        L_0x006f:
            int r7 = r1.ih
            if (r14 >= r7) goto L_0x00d1
            boolean r7 = r1.interlace
            if (r7 == 0) goto L_0x008f
            int r7 = r1.ih
            r8 = 4
            if (r5 < r7) goto L_0x008c
            int r6 = r6 + 1
            if (r6 == r2) goto L_0x008b
            if (r6 == r15) goto L_0x0088
            if (r6 == r8) goto L_0x0085
            goto L_0x008c
        L_0x0085:
            r4 = 2
            r5 = 1
            goto L_0x008c
        L_0x0088:
            r4 = 4
            r5 = 2
            goto L_0x008c
        L_0x008b:
            r5 = 4
        L_0x008c:
            int r7 = r5 + r4
            goto L_0x0091
        L_0x008f:
            r7 = r5
            r5 = r14
        L_0x0091:
            int r8 = r1.iy
            int r5 = r5 + r8
            com.bumptech.glide.gifdecoder.GifHeader r8 = r0.header
            int r8 = r8.height
            if (r5 >= r8) goto L_0x00cd
            com.bumptech.glide.gifdecoder.GifHeader r8 = r0.header
            int r8 = r8.width
            int r5 = r5 * r8
            int r8 = r1.ix
            int r8 = r8 + r5
            int r9 = r1.iw
            int r9 = r9 + r8
            com.bumptech.glide.gifdecoder.GifHeader r10 = r0.header
            int r10 = r10.width
            int r10 = r10 + r5
            if (r10 >= r9) goto L_0x00b2
            com.bumptech.glide.gifdecoder.GifHeader r9 = r0.header
            int r9 = r9.width
            int r9 = r9 + r5
        L_0x00b2:
            int r5 = r1.iw
            int r5 = r5 * r14
        L_0x00b6:
            if (r8 >= r9) goto L_0x00cd
            byte[] r10 = r0.mainPixels
            int r16 = r5 + 1
            byte r5 = r10[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int[] r10 = r0.act
            r5 = r10[r5]
            if (r5 == 0) goto L_0x00c8
            r13[r8] = r5
        L_0x00c8:
            int r8 = r8 + 1
            r5 = r16
            goto L_0x00b6
        L_0x00cd:
            int r14 = r14 + 1
            r5 = r7
            goto L_0x006f
        L_0x00d1:
            boolean r2 = r0.savePrevious
            if (r2 == 0) goto L_0x00f3
            int r2 = r1.dispose
            if (r2 == 0) goto L_0x00dd
            int r1 = r1.dispose
            if (r1 != r11) goto L_0x00f3
        L_0x00dd:
            android.graphics.Bitmap r1 = r0.previousImage
            if (r1 != 0) goto L_0x00e7
            android.graphics.Bitmap r1 = r17.getNextBitmap()
            r0.previousImage = r1
        L_0x00e7:
            android.graphics.Bitmap r4 = r0.previousImage
            r6 = 0
            r8 = 0
            r9 = 0
            r5 = r13
            r7 = r3
            r10 = r3
            r11 = r12
            r4.setPixels(r5, r6, r7, r8, r9, r10, r11)
        L_0x00f3:
            android.graphics.Bitmap r1 = r17.getNextBitmap()
            r6 = 0
            r8 = 0
            r9 = 0
            r4 = r1
            r5 = r13
            r7 = r3
            r10 = r3
            r11 = r12
            r4.setPixels(r5, r6, r7, r8, r9, r10, r11)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.setPixels(com.bumptech.glide.gifdecoder.GifFrame, com.bumptech.glide.gifdecoder.GifFrame):android.graphics.Bitmap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r2v18, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x013b A[LOOP:4: B:57:0x0139->B:58:0x013b, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.header
            int r1 = r1.width
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.height
            int r1 = r1 * r2
            goto L_0x0020
        L_0x001a:
            int r2 = r1.iw
            int r1 = r1.ih
            int r1 = r1 * r2
        L_0x0020:
            byte[] r2 = r0.mainPixels
            if (r2 == 0) goto L_0x0027
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002b
        L_0x0027:
            byte[] r2 = new byte[r1]
            r0.mainPixels = r2
        L_0x002b:
            short[] r2 = r0.prefix
            r3 = 4096(0x1000, float:5.74E-42)
            if (r2 != 0) goto L_0x0035
            short[] r2 = new short[r3]
            r0.prefix = r2
        L_0x0035:
            byte[] r2 = r0.suffix
            if (r2 != 0) goto L_0x003d
            byte[] r2 = new byte[r3]
            r0.suffix = r2
        L_0x003d:
            byte[] r2 = r0.pixelStack
            if (r2 != 0) goto L_0x0047
            r2 = 4097(0x1001, float:5.741E-42)
            byte[] r2 = new byte[r2]
            r0.pixelStack = r2
        L_0x0047:
            int r2 = r27.read()
            r4 = 1
            int r5 = r4 << r2
            int r6 = r5 + 1
            int r7 = r5 + 2
            int r2 = r2 + r4
            int r8 = r4 << r2
            int r8 = r8 - r4
            r9 = 0
            r10 = 0
        L_0x0058:
            if (r10 >= r5) goto L_0x0066
            short[] r11 = r0.prefix
            r11[r10] = r9
            byte[] r11 = r0.suffix
            byte r12 = (byte) r10
            r11[r10] = r12
            int r10 = r10 + 1
            goto L_0x0058
        L_0x0066:
            r10 = -1
            r19 = r2
            r17 = r7
            r18 = r8
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r20 = -1
            r21 = 0
            r22 = 0
        L_0x007a:
            if (r11 >= r1) goto L_0x0172
            r9 = 3
            if (r12 != 0) goto L_0x008a
            int r12 = r27.readBlock()
            if (r12 > 0) goto L_0x0089
            r0.status = r9
            goto L_0x0172
        L_0x0089:
            r13 = 0
        L_0x008a:
            byte[] r3 = r0.block
            byte r3 = r3[r13]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r14
            int r15 = r15 + r3
            int r14 = r14 + 8
            int r13 = r13 + r4
            int r12 = r12 + r10
            r3 = r17
            r4 = r19
            r23 = r20
            r24 = r21
        L_0x009e:
            if (r14 < r4) goto L_0x015c
            r10 = r15 & r18
            int r15 = r15 >> r4
            int r14 = r14 - r4
            if (r10 != r5) goto L_0x00ae
            r4 = r2
            r3 = r7
            r18 = r8
            r10 = -1
            r23 = -1
            goto L_0x009e
        L_0x00ae:
            if (r10 <= r3) goto L_0x00b3
            r0.status = r9
            goto L_0x00b5
        L_0x00b3:
            if (r10 != r6) goto L_0x00c3
        L_0x00b5:
            r17 = r3
            r19 = r4
            r20 = r23
            r21 = r24
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 1
            r9 = 0
            r10 = -1
            goto L_0x007a
        L_0x00c3:
            r19 = r2
            r9 = r23
            r2 = -1
            if (r9 != r2) goto L_0x00df
            byte[] r9 = r0.pixelStack
            int r21 = r22 + 1
            byte[] r2 = r0.suffix
            byte r2 = r2[r10]
            r9[r22] = r2
            r23 = r10
            r24 = r23
            r2 = r19
            r22 = r21
            r9 = 3
            r10 = -1
            goto L_0x009e
        L_0x00df:
            if (r10 < r3) goto L_0x00f0
            byte[] r2 = r0.pixelStack
            int r21 = r22 + 1
            r25 = r6
            r6 = r24
            byte r6 = (byte) r6
            r2[r22] = r6
            r2 = r9
            r22 = r21
            goto L_0x00f3
        L_0x00f0:
            r25 = r6
            r2 = r10
        L_0x00f3:
            if (r2 < r5) goto L_0x010a
            byte[] r6 = r0.pixelStack
            int r21 = r22 + 1
            r24 = r5
            byte[] r5 = r0.suffix
            byte r5 = r5[r2]
            r6[r22] = r5
            short[] r5 = r0.prefix
            short r2 = r5[r2]
            r22 = r21
            r5 = r24
            goto L_0x00f3
        L_0x010a:
            r24 = r5
            byte[] r5 = r0.suffix
            byte r2 = r5[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r6 = r0.pixelStack
            int r21 = r22 + 1
            r26 = r7
            byte r7 = (byte) r2
            r6[r22] = r7
            r6 = 4096(0x1000, float:5.74E-42)
            if (r3 >= r6) goto L_0x0135
            short[] r6 = r0.prefix
            short r9 = (short) r9
            r6[r3] = r9
            r5[r3] = r7
            int r3 = r3 + 1
            r5 = r3 & r18
            if (r5 != 0) goto L_0x0135
            r5 = 4096(0x1000, float:5.74E-42)
            if (r3 >= r5) goto L_0x0137
            int r4 = r4 + 1
            int r18 = r18 + r3
            goto L_0x0137
        L_0x0135:
            r5 = 4096(0x1000, float:5.74E-42)
        L_0x0137:
            r22 = r21
        L_0x0139:
            if (r22 <= 0) goto L_0x014c
            int r22 = r22 + -1
            byte[] r6 = r0.mainPixels
            int r7 = r16 + 1
            byte[] r9 = r0.pixelStack
            byte r9 = r9[r22]
            r6[r16] = r9
            int r11 = r11 + 1
            r16 = r7
            goto L_0x0139
        L_0x014c:
            r23 = r10
            r5 = r24
            r6 = r25
            r7 = r26
            r9 = 3
            r10 = -1
            r24 = r2
            r2 = r19
            goto L_0x009e
        L_0x015c:
            r25 = r6
            r9 = r23
            r6 = r24
            r17 = r3
            r19 = r4
            r21 = r6
            r20 = r9
            r6 = r25
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 1
            r9 = 0
            goto L_0x007a
        L_0x0172:
            r2 = r16
        L_0x0174:
            if (r2 >= r1) goto L_0x017e
            byte[] r3 = r0.mainPixels
            r4 = 0
            r3[r2] = r4
            int r2 = r2 + 1
            goto L_0x0174
        L_0x017e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int read() {
        try {
            return this.rawData.get() & Ev3Constants.Opcode.TST;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int read = read();
        int i = 0;
        if (read > 0) {
            while (i < read) {
                int i2 = read - i;
                try {
                    this.rawData.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return i;
    }

    private Bitmap getNextBitmap() {
        BitmapProvider bitmapProvider2 = this.bitmapProvider;
        int i = this.header.width;
        int i2 = this.header.height;
        Bitmap.Config config = BITMAP_CONFIG;
        Bitmap obtain = bitmapProvider2.obtain(i, i2, config);
        if (obtain == null) {
            obtain = Bitmap.createBitmap(this.header.width, this.header.height, config);
        }
        setAlpha(obtain);
        return obtain;
    }

    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}

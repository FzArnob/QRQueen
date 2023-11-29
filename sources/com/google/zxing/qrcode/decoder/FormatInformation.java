package com.google.zxing.qrcode.decoder;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.appinventor.components.runtime.util.ErrorMessages;

final class FormatInformation {
    private static final int[] BITS_SET_IN_HALF_BYTE = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = {new int[]{FORMAT_INFO_MASK_QR, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{ErrorMessages.ERROR_CANNOT_DELETE_FILE, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    private static final int FORMAT_INFO_MASK_QR = 21522;
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;

    private FormatInformation(int i) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((i >> 3) & 3);
        this.dataMask = (byte) (i & 7);
    }

    static int numBitsDiffering(int i, int i2) {
        int i3 = i ^ i2;
        int[] iArr = BITS_SET_IN_HALF_BYTE;
        return iArr[i3 & 15] + iArr[(i3 >>> 4) & 15] + iArr[(i3 >>> 8) & 15] + iArr[(i3 >>> 12) & 15] + iArr[(i3 >>> 16) & 15] + iArr[(i3 >>> 20) & 15] + iArr[(i3 >>> 24) & 15] + iArr[(i3 >>> 28) & 15];
    }

    static FormatInformation decodeFormatInformation(int i, int i2) {
        FormatInformation doDecodeFormatInformation = doDecodeFormatInformation(i, i2);
        if (doDecodeFormatInformation != null) {
            return doDecodeFormatInformation;
        }
        return doDecodeFormatInformation(i ^ FORMAT_INFO_MASK_QR, i2 ^ FORMAT_INFO_MASK_QR);
    }

    private static FormatInformation doDecodeFormatInformation(int i, int i2) {
        int numBitsDiffering;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i4 = 0;
        for (int[] iArr : FORMAT_INFO_DECODE_LOOKUP) {
            int i5 = iArr[0];
            if (i5 == i || i5 == i2) {
                return new FormatInformation(iArr[1]);
            }
            int numBitsDiffering2 = numBitsDiffering(i, i5);
            if (numBitsDiffering2 < i3) {
                i4 = iArr[1];
                i3 = numBitsDiffering2;
            }
            if (i != i2 && (numBitsDiffering = numBitsDiffering(i2, i5)) < i3) {
                i4 = iArr[1];
                i3 = numBitsDiffering;
            }
        }
        if (i3 <= 3) {
            return new FormatInformation(i4);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    /* access modifiers changed from: package-private */
    public byte getDataMask() {
        return this.dataMask;
    }

    public int hashCode() {
        return (this.errorCorrectionLevel.ordinal() << 3) | this.dataMask;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FormatInformation)) {
            return false;
        }
        FormatInformation formatInformation = (FormatInformation) obj;
        if (this.errorCorrectionLevel == formatInformation.errorCorrectionLevel && this.dataMask == formatInformation.dataMask) {
            return true;
        }
        return false;
    }
}
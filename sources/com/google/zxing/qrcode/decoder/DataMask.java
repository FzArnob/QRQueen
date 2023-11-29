package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask {
    private static final DataMask[] DATA_MASKS = {new DataMask000(), new DataMask001(), new DataMask010(), new DataMask011(), new DataMask100(), new DataMask101(), new DataMask110(), new DataMask111()};

    /* access modifiers changed from: package-private */
    public abstract boolean isMasked(int i, int i2);

    private DataMask() {
    }

    /* access modifiers changed from: package-private */
    public final void unmaskBitMatrix(BitMatrix bitMatrix, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (isMasked(i2, i3)) {
                    bitMatrix.flip(i3, i2);
                }
            }
        }
    }

    static DataMask forReference(int i) {
        if (i >= 0 && i <= 7) {
            return DATA_MASKS[i];
        }
        throw new IllegalArgumentException();
    }

    private static final class DataMask000 extends DataMask {
        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }

        private DataMask000() {
            super();
        }
    }

    private static final class DataMask001 extends DataMask {
        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return (i & 1) == 0;
        }

        private DataMask001() {
            super();
        }
    }

    private static final class DataMask010 extends DataMask {
        private DataMask010() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return i2 % 3 == 0;
        }
    }

    private static final class DataMask011 extends DataMask {
        private DataMask011() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    }

    private static final class DataMask100 extends DataMask {
        private DataMask100() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return (((i >>> 1) + (i2 / 3)) & 1) == 0;
        }
    }

    private static final class DataMask101 extends DataMask {
        private DataMask101() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            int i3 = i * i2;
            return (i3 & 1) + (i3 % 3) == 0;
        }
    }

    private static final class DataMask110 extends DataMask {
        private DataMask110() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            int i3 = i * i2;
            return (((i3 & 1) + (i3 % 3)) & 1) == 0;
        }
    }

    private static final class DataMask111 extends DataMask {
        private DataMask111() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean isMasked(int i, int i2) {
            return ((((i + i2) & 1) + ((i * i2) % 3)) & 1) == 0;
        }
    }
}

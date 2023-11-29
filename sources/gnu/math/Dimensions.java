package gnu.math;

import androidx.appcompat.widget.ActivityChooserView;

public class Dimensions {
    public static Dimensions Empty = new Dimensions();
    private static Dimensions[] hashTable = new Dimensions[100];
    BaseUnit[] bases;
    private Dimensions chain;
    int hash_code;
    short[] powers;

    public final int hashCode() {
        return this.hash_code;
    }

    private void enterHash(int i) {
        this.hash_code = i;
        int i2 = i & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        Dimensions[] dimensionsArr = hashTable;
        int length = i2 % dimensionsArr.length;
        this.chain = dimensionsArr[length];
        dimensionsArr[length] = this;
    }

    private Dimensions() {
        BaseUnit[] baseUnitArr = new BaseUnit[1];
        this.bases = baseUnitArr;
        baseUnitArr[0] = Unit.Empty;
        enterHash(0);
    }

    Dimensions(BaseUnit baseUnit) {
        BaseUnit[] baseUnitArr = new BaseUnit[2];
        this.bases = baseUnitArr;
        this.powers = new short[1];
        baseUnitArr[0] = baseUnit;
        baseUnitArr[1] = Unit.Empty;
        this.powers[0] = 1;
        enterHash(baseUnit.index);
    }

    private Dimensions(Dimensions dimensions, int i, Dimensions dimensions2, int i2, int i3) {
        int i4;
        this.hash_code = i3;
        int i5 = 0;
        int i6 = 0;
        while (dimensions.bases[i6] != Unit.Empty) {
            i6++;
        }
        int i7 = 0;
        while (dimensions2.bases[i7] != Unit.Empty) {
            i7++;
        }
        int i8 = i6 + i7 + 1;
        this.bases = new BaseUnit[i8];
        this.powers = new short[i8];
        int i9 = 0;
        int i10 = 0;
        while (true) {
            BaseUnit baseUnit = dimensions.bases[i5];
            BaseUnit baseUnit2 = dimensions2.bases[i9];
            if (baseUnit.index < baseUnit2.index) {
                i4 = dimensions.powers[i5] * i;
                i5++;
            } else if (baseUnit2.index < baseUnit.index) {
                i9++;
                BaseUnit baseUnit3 = baseUnit2;
                i4 = dimensions2.powers[i9] * i2;
                baseUnit = baseUnit3;
            } else if (baseUnit2 == Unit.Empty) {
                this.bases[i10] = Unit.Empty;
                enterHash(i3);
                return;
            } else {
                i4 = (dimensions.powers[i5] * i) + (dimensions2.powers[i9] * i2);
                i5++;
                i9++;
                if (i4 == 0) {
                    continue;
                }
            }
            short s = (short) i4;
            if (s == i4) {
                this.bases[i10] = baseUnit;
                this.powers[i10] = s;
                i10++;
            } else {
                throw new ArithmeticException("overflow in dimensions");
            }
        }
    }

    private boolean matchesProduct(Dimensions dimensions, int i, Dimensions dimensions2, int i2) {
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            BaseUnit baseUnit = dimensions.bases[i4];
            BaseUnit baseUnit2 = dimensions2.bases[i5];
            if (baseUnit.index < baseUnit2.index) {
                i3 = dimensions.powers[i4] * i;
                i4++;
            } else if (baseUnit2.index < baseUnit.index) {
                i5++;
                BaseUnit baseUnit3 = baseUnit2;
                i3 = dimensions2.powers[i5] * i2;
                baseUnit = baseUnit3;
            } else if (baseUnit2 != Unit.Empty) {
                i3 = (dimensions.powers[i4] * i) + (dimensions2.powers[i5] * i2);
                i4++;
                i5++;
                if (i3 == 0) {
                    continue;
                }
            } else if (this.bases[i6] == baseUnit2) {
                return true;
            } else {
                return false;
            }
            if (this.bases[i6] != baseUnit || this.powers[i6] != i3) {
                return false;
            }
            i6++;
        }
        return false;
    }

    public static Dimensions product(Dimensions dimensions, int i, Dimensions dimensions2, int i2) {
        int hashCode = (dimensions.hashCode() * i) + (dimensions2.hashCode() * i2);
        Dimensions[] dimensionsArr = hashTable;
        for (Dimensions dimensions3 = dimensionsArr[(Integer.MAX_VALUE & hashCode) % dimensionsArr.length]; dimensions3 != null; dimensions3 = dimensions3.chain) {
            if (dimensions3.hash_code == hashCode && dimensions3.matchesProduct(dimensions, i, dimensions2, i2)) {
                return dimensions3;
            }
        }
        return new Dimensions(dimensions, i, dimensions2, i2, hashCode);
    }

    public int getPower(BaseUnit baseUnit) {
        for (int i = 0; this.bases[i].index <= baseUnit.index; i++) {
            if (this.bases[i] == baseUnit) {
                return this.powers[i];
            }
        }
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; this.bases[i] != Unit.Empty; i++) {
            if (i > 0) {
                stringBuffer.append('*');
            }
            stringBuffer.append(this.bases[i]);
            short s = this.powers[i];
            if (s != 1) {
                stringBuffer.append('^');
                stringBuffer.append(s);
            }
        }
        return stringBuffer.toString();
    }
}

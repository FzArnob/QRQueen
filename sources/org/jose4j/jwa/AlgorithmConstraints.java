package org.jose4j.jwa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.InvalidAlgorithmException;

public class AlgorithmConstraints {
    public static final AlgorithmConstraints ALLOW_ONLY_NONE = new AlgorithmConstraints(ConstraintType.PERMIT, AlgorithmIdentifiers.NONE);
    public static final AlgorithmConstraints DISALLOW_NONE = new AlgorithmConstraints(ConstraintType.BLOCK, AlgorithmIdentifiers.NONE);
    public static final AlgorithmConstraints NO_CONSTRAINTS = new AlgorithmConstraints(ConstraintType.BLOCK, new String[0]);
    private final Set<String> algorithms;
    private final ConstraintType type;

    public enum ConstraintType {
        WHITELIST,
        BLACKLIST,
        PERMIT,
        BLOCK
    }

    public AlgorithmConstraints(ConstraintType constraintType, String... strArr) {
        if (constraintType != null) {
            this.type = constraintType;
            this.algorithms = new HashSet(Arrays.asList(strArr));
            return;
        }
        throw new NullPointerException("ConstraintType cannot be null");
    }

    /* renamed from: org.jose4j.jwa.AlgorithmConstraints$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.jose4j.jwa.AlgorithmConstraints$ConstraintType[] r0 = org.jose4j.jwa.AlgorithmConstraints.ConstraintType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType = r0
                org.jose4j.jwa.AlgorithmConstraints$ConstraintType r1 = org.jose4j.jwa.AlgorithmConstraints.ConstraintType.PERMIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jose4j.jwa.AlgorithmConstraints$ConstraintType r1 = org.jose4j.jwa.AlgorithmConstraints.ConstraintType.WHITELIST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jose4j.jwa.AlgorithmConstraints$ConstraintType r1 = org.jose4j.jwa.AlgorithmConstraints.ConstraintType.BLOCK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.jose4j.jwa.AlgorithmConstraints$ConstraintType r1 = org.jose4j.jwa.AlgorithmConstraints.ConstraintType.BLACKLIST     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwa.AlgorithmConstraints.AnonymousClass1.<clinit>():void");
        }
    }

    public void checkConstraint(String str) throws InvalidAlgorithmException {
        int i = AnonymousClass1.$SwitchMap$org$jose4j$jwa$AlgorithmConstraints$ConstraintType[this.type.ordinal()];
        if (i == 1 || i == 2) {
            if (!this.algorithms.contains(str)) {
                throw new InvalidAlgorithmException("'" + str + "' is not a permitted algorithm.");
            }
        } else if ((i == 3 || i == 4) && this.algorithms.contains(str)) {
            throw new InvalidAlgorithmException("'" + str + "' is a blocked algorithm.");
        }
    }
}

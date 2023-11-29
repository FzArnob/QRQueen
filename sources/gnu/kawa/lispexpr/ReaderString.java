package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderString extends ReadTableEntry {
    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        return readString(lexer, i, i2);
    }

    /* JADX WARNING: type inference failed for: r3v17, types: [char[]] */
    /* JADX WARNING: type inference failed for: r3v18, types: [char] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r3v18, types: [char] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readString(gnu.text.Lexer r8, int r9, int r10) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            int r10 = r8.tokenBufferLength
            gnu.text.LineBufferedReader r0 = r8.getPort()
            boolean r1 = r0 instanceof gnu.mapping.InPort
            r2 = 10
            if (r1 == 0) goto L_0x0015
            r3 = r0
            gnu.mapping.InPort r3 = (gnu.mapping.InPort) r3
            char r4 = r3.readState
            char r5 = (char) r9
            r3.readState = r5
            goto L_0x0016
        L_0x0015:
            r4 = 0
        L_0x0016:
            r3 = r9
        L_0x0017:
            r5 = 13
            if (r3 != r5) goto L_0x0022
            int r3 = r0.read()     // Catch:{ all -> 0x0089 }
            if (r3 != r2) goto L_0x0039
            goto L_0x0017
        L_0x0022:
            int r6 = r0.pos     // Catch:{ all -> 0x0089 }
            int r7 = r0.limit     // Catch:{ all -> 0x0089 }
            if (r6 >= r7) goto L_0x0035
            if (r3 == r2) goto L_0x0035
            char[] r3 = r0.buffer     // Catch:{ all -> 0x0089 }
            int r6 = r0.pos     // Catch:{ all -> 0x0089 }
            int r7 = r6 + 1
            r0.pos = r7     // Catch:{ all -> 0x0089 }
            char r3 = r3[r6]     // Catch:{ all -> 0x0089 }
            goto L_0x0039
        L_0x0035:
            int r3 = r0.read()     // Catch:{ all -> 0x0089 }
        L_0x0039:
            if (r3 != r9) goto L_0x0052
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x0089 }
            char[] r2 = r8.tokenBuffer     // Catch:{ all -> 0x0089 }
            int r3 = r8.tokenBufferLength     // Catch:{ all -> 0x0089 }
            int r3 = r3 - r10
            r9.<init>(r2, r10, r3)     // Catch:{ all -> 0x0089 }
            java.lang.String r9 = r9.intern()     // Catch:{ all -> 0x0089 }
            r8.tokenBufferLength = r10
            if (r1 == 0) goto L_0x0051
            gnu.mapping.InPort r0 = (gnu.mapping.InPort) r0
            r0.readState = r4
        L_0x0051:
            return r9
        L_0x0052:
            if (r3 == r5) goto L_0x007a
            r5 = 92
            if (r3 == r5) goto L_0x0059
            goto L_0x006f
        L_0x0059:
            boolean r3 = r8 instanceof gnu.kawa.lispexpr.LispReader     // Catch:{ all -> 0x0089 }
            if (r3 == 0) goto L_0x0065
            r3 = r8
            gnu.kawa.lispexpr.LispReader r3 = (gnu.kawa.lispexpr.LispReader) r3     // Catch:{ all -> 0x0089 }
            int r3 = r3.readEscape()     // Catch:{ all -> 0x0089 }
            goto L_0x0069
        L_0x0065:
            int r3 = r0.read()     // Catch:{ all -> 0x0089 }
        L_0x0069:
            r5 = -2
            if (r3 != r5) goto L_0x006f
            r3 = 10
            goto L_0x0017
        L_0x006f:
            if (r3 >= 0) goto L_0x0076
            java.lang.String r5 = "unexpected EOF in string literal"
            r8.eofError(r5)     // Catch:{ all -> 0x0089 }
        L_0x0076:
            r8.tokenBufferAppend(r3)     // Catch:{ all -> 0x0089 }
            goto L_0x0017
        L_0x007a:
            boolean r6 = r0.getConvertCR()     // Catch:{ all -> 0x0089 }
            if (r6 == 0) goto L_0x0083
            r5 = 10
            goto L_0x0085
        L_0x0083:
            r3 = 32
        L_0x0085:
            r8.tokenBufferAppend(r5)     // Catch:{ all -> 0x0089 }
            goto L_0x0017
        L_0x0089:
            r9 = move-exception
            r8.tokenBufferLength = r10
            if (r1 == 0) goto L_0x0092
            gnu.mapping.InPort r0 = (gnu.mapping.InPort) r0
            r0.readState = r4
        L_0x0092:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderString.readString(gnu.text.Lexer, int, int):java.lang.String");
    }
}

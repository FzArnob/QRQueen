package gnu.kawa.lispexpr;

public class ReaderTypespec extends ReadTableEntry {
    public int getKind() {
        return 6;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v12 ?, r10v3 ?, r10v4 ?, r10v1 ?, r10v0 ?, r10v8 ?, r10v11 ?, r10v14 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public java.lang.Object read(gnu.text.Lexer r9, int r10, int r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r8 = this;
            int r11 = r9.tokenBufferLength
            gnu.text.LineBufferedReader r0 = r9.getPort()
            gnu.kawa.lispexpr.ReadTable r1 = gnu.kawa.lispexpr.ReadTable.getCurrent()
            r9.tokenBufferAppend(r10)
            boolean r2 = r0 instanceof gnu.mapping.InPort
            r3 = 0
            if (r2 == 0) goto L_0x001b
            r4 = r0
            gnu.mapping.InPort r4 = (gnu.mapping.InPort) r4
            char r5 = r4.readState
            char r6 = (char) r10
            r4.readState = r6
            goto L_0x001c
        L_0x001b:
            r5 = 0
        L_0x001c:
            r4 = 0
        L_0x001d:
            int r6 = r0.pos     // Catch:{ all -> 0x0084 }
            int r7 = r0.limit     // Catch:{ all -> 0x0084 }
            if (r6 >= r7) goto L_0x0032
            r6 = 10
            if (r10 == r6) goto L_0x0032
            char[] r10 = r0.buffer     // Catch:{ all -> 0x0084 }
            int r6 = r0.pos     // Catch:{ all -> 0x0084 }
            int r7 = r6 + 1
            r0.pos = r7     // Catch:{ all -> 0x0084 }
            char r10 = r10[r6]     // Catch:{ all -> 0x0084 }
            goto L_0x0036
        L_0x0032:
            int r10 = r0.read()     // Catch:{ all -> 0x0084 }
        L_0x0036:
            r6 = 92
            if (r10 != r6) goto L_0x004b
            boolean r10 = r9 instanceof gnu.kawa.lispexpr.LispReader     // Catch:{ all -> 0x0084 }
            if (r10 == 0) goto L_0x0046
            r10 = r9
            gnu.kawa.lispexpr.LispReader r10 = (gnu.kawa.lispexpr.LispReader) r10     // Catch:{ all -> 0x0084 }
            int r10 = r10.readEscape()     // Catch:{ all -> 0x0084 }
            goto L_0x001d
        L_0x0046:
            int r10 = r0.read()     // Catch:{ all -> 0x0084 }
            goto L_0x001d
        L_0x004b:
            if (r4 != 0) goto L_0x0053
            r6 = 91
            if (r10 != r6) goto L_0x0053
            r4 = 1
            goto L_0x0066
        L_0x0053:
            if (r4 == 0) goto L_0x005b
            r6 = 93
            if (r10 != r6) goto L_0x005b
            r4 = 0
            goto L_0x0066
        L_0x005b:
            gnu.kawa.lispexpr.ReadTableEntry r6 = r1.lookup(r10)     // Catch:{ all -> 0x0084 }
            int r6 = r6.getKind()     // Catch:{ all -> 0x0084 }
            r7 = 2
            if (r6 != r7) goto L_0x006a
        L_0x0066:
            r9.tokenBufferAppend(r10)     // Catch:{ all -> 0x0084 }
            goto L_0x001d
        L_0x006a:
            r9.unread(r10)     // Catch:{ all -> 0x0084 }
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x0084 }
            char[] r1 = r9.tokenBuffer     // Catch:{ all -> 0x0084 }
            int r3 = r9.tokenBufferLength     // Catch:{ all -> 0x0084 }
            int r3 = r3 - r11
            r10.<init>(r1, r11, r3)     // Catch:{ all -> 0x0084 }
            java.lang.String r10 = r10.intern()     // Catch:{ all -> 0x0084 }
            r9.tokenBufferLength = r11
            if (r2 == 0) goto L_0x0083
            gnu.mapping.InPort r0 = (gnu.mapping.InPort) r0
            r0.readState = r5
        L_0x0083:
            return r10
        L_0x0084:
            r10 = move-exception
            r9.tokenBufferLength = r11
            if (r2 == 0) goto L_0x008d
            gnu.mapping.InPort r0 = (gnu.mapping.InPort) r0
            r0.readState = r5
        L_0x008d:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderTypespec.read(gnu.text.Lexer, int, int):java.lang.Object");
    }
}

package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

public class XMLParser {
    private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
    private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
    static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
    static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
    private static final int BEGIN_ELEMENT_STATE = 2;
    private static final int DOCTYPE_NAME_SEEN_STATE = 16;
    private static final int DOCTYPE_SEEN_STATE = 13;
    private static final int END_ELEMENT_STATE = 4;
    private static final int EXPECT_NAME_MODIFIER = 1;
    private static final int EXPECT_RIGHT_STATE = 27;
    private static final int INIT_LEFT_QUEST_STATE = 30;
    private static final int INIT_LEFT_STATE = 34;
    private static final int INIT_STATE = 0;
    private static final int INIT_TEXT_STATE = 31;
    private static final int INVALID_VERSION_DECL = 35;
    private static final int MAYBE_ATTRIBUTE_STATE = 10;
    private static final int PREV_WAS_CR_STATE = 28;
    private static final int SAW_AMP_SHARP_STATE = 26;
    private static final int SAW_AMP_STATE = 25;
    private static final int SAW_ENTITY_REF = 6;
    private static final int SAW_EOF_ERROR = 37;
    private static final int SAW_ERROR = 36;
    private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
    private static final int SAW_LEFT_EXCL_STATE = 20;
    private static final int SAW_LEFT_QUEST_STATE = 21;
    private static final int SAW_LEFT_SLASH_STATE = 19;
    private static final int SAW_LEFT_STATE = 14;
    private static final int SKIP_SPACES_MODIFIER = 2;
    private static final int TEXT_STATE = 1;

    public static void parse(Object obj, SourceMessages sourceMessages, Consumer consumer) throws IOException {
        parse(Path.openInputStream(obj), obj, sourceMessages, consumer);
    }

    public static LineInputStreamReader XMLStreamReader(InputStream inputStream) throws IOException {
        int i;
        int i2;
        LineInputStreamReader lineInputStreamReader = new LineInputStreamReader(inputStream);
        int i3 = lineInputStreamReader.getByte();
        int i4 = -1;
        if (i3 < 0) {
            i = -1;
        } else {
            i = lineInputStreamReader.getByte();
        }
        if (i < 0) {
            i2 = -1;
        } else {
            i2 = lineInputStreamReader.getByte();
        }
        if (i3 == 239 && i == 187 && i2 == 191) {
            lineInputStreamReader.resetStart(3);
            lineInputStreamReader.setCharset("UTF-8");
        } else if (i3 == 255 && i == 254 && i2 != 0) {
            lineInputStreamReader.resetStart(2);
            lineInputStreamReader.setCharset("UTF-16LE");
        } else if (i3 == 254 && i == 255 && i2 != 0) {
            lineInputStreamReader.resetStart(2);
            lineInputStreamReader.setCharset("UTF-16BE");
        } else {
            if (i2 >= 0) {
                i4 = lineInputStreamReader.getByte();
            }
            if (i3 == 76 && i == 111 && i2 == 167 && i4 == 148) {
                throw new RuntimeException("XMLParser: EBCDIC encodings not supported");
            }
            lineInputStreamReader.resetStart(0);
            if ((i3 == 60 && ((i == 63 && i2 == 120 && i4 == 109) || (i == 0 && i2 == 63 && i4 == 0))) || (i3 == 0 && i == 60 && i2 == 0 && i4 == 63)) {
                char[] cArr = lineInputStreamReader.buffer;
                if (cArr == null) {
                    cArr = new char[8192];
                    lineInputStreamReader.buffer = cArr;
                }
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    int i7 = lineInputStreamReader.getByte();
                    if (i7 != 0) {
                        if (i7 < 0) {
                            break;
                        }
                        int i8 = i5 + 1;
                        cArr[i5] = (char) (i7 & 255);
                        if (i6 == 0) {
                            if (i7 == 62) {
                                i5 = i8;
                                break;
                            } else if (i7 == 39 || i7 == 34) {
                                i6 = i7;
                            }
                        } else if (i7 == i6) {
                            i6 = 0;
                        }
                        i5 = i8;
                    }
                }
                lineInputStreamReader.pos = 0;
                lineInputStreamReader.limit = i5;
            } else {
                lineInputStreamReader.setCharset("UTF-8");
            }
        }
        lineInputStreamReader.setKeepFullLines(false);
        return lineInputStreamReader;
    }

    public static void parse(InputStream inputStream, Object obj, SourceMessages sourceMessages, Consumer consumer) throws IOException {
        LineInputStreamReader XMLStreamReader = XMLStreamReader(inputStream);
        if (obj != null) {
            XMLStreamReader.setName(obj);
        }
        parse((LineBufferedReader) XMLStreamReader, sourceMessages, consumer);
        XMLStreamReader.close();
    }

    public static void parse(LineBufferedReader lineBufferedReader, SourceMessages sourceMessages, Consumer consumer) throws IOException {
        XMLFilter xMLFilter = new XMLFilter(consumer);
        xMLFilter.setMessages(sourceMessages);
        xMLFilter.setSourceLocator(lineBufferedReader);
        xMLFilter.startDocument();
        Path path = lineBufferedReader.getPath();
        if (path != null) {
            xMLFilter.writeDocumentUri(path);
        }
        parse(lineBufferedReader, xMLFilter);
        xMLFilter.endDocument();
    }

    public static void parse(LineBufferedReader lineBufferedReader, SourceMessages sourceMessages, XMLFilter xMLFilter) throws IOException {
        xMLFilter.setMessages(sourceMessages);
        xMLFilter.setSourceLocator(lineBufferedReader);
        xMLFilter.startDocument();
        Path path = lineBufferedReader.getPath();
        if (path != null) {
            xMLFilter.writeDocumentUri(path);
        }
        parse(lineBufferedReader, xMLFilter);
        xMLFilter.endDocument();
        lineBufferedReader.close();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0047, code lost:
        r8 = r2 + 1;
        r15 = r5[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004b, code lost:
        if (r15 != '>') goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        r17 = r1;
        r9 = r4;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0052, code lost:
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        r9 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x02f8, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x04d6, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r15 != ';') goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x04f8, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008a, code lost:
        r0.pos = r2;
        r7.emitCharacterReference(r12, r5, r3, (r2 - 1) - r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0092, code lost:
        r9 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x053e, code lost:
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x0561, code lost:
        r19 = '<';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x05a9, code lost:
        r1 = 36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009a, code lost:
        if (r15 != 'x') goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009c, code lost:
        if (r18 != 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009e, code lost:
        r18 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a5, code lost:
        if (r12 < 134217728) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a8, code lost:
        if (r18 != 0) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00aa, code lost:
        r8 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ad, code lost:
        r8 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:490:0x0663, code lost:
        if (r12 != 0) goto L_0x0733;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:492:0x0667, code lost:
        if (r1 != 8) goto L_0x066f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:493:0x0669, code lost:
        r1 = "missing or invalid attribute name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:494:0x066b, code lost:
        r17 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:496:0x0670, code lost:
        if (r1 == 2) goto L_0x0679;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:498:0x0673, code lost:
        if (r1 != 4) goto L_0x0676;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x0676, code lost:
        r1 = "missing or invalid name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00af, code lost:
        r10 = java.lang.Character.digit(r15, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x0679, code lost:
        r1 = "missing or invalid element name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x0693, code lost:
        r3 = r9;
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:505:0x0695, code lost:
        r9 = '<';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b3, code lost:
        if (r10 >= 0) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b5, code lost:
        r0.pos = r2;
        r7.error('e', "invalid character reference");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bd, code lost:
        r12 = (r12 * r8) + r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:538:0x070f, code lost:
        r3 = r2 - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:539:0x0711, code lost:
        if (r3 <= 0) goto L_0x0718;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c0, code lost:
        if (r2 >= r4) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:540:0x0713, code lost:
        r0.pos = r2;
        r7.textFromParser(r5, r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:541:0x0718, code lost:
        r4 = r5.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:546:0x072c, code lost:
        if (r2 >= r9) goto L_0x0736;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:547:0x072e, code lost:
        r15 = r5[r2];
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:548:0x0733, code lost:
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:549:0x0736, code lost:
        r4 = r2 - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c2, code lost:
        r15 = r5[r2];
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:550:0x0738, code lost:
        if (r4 <= 0) goto L_0x0741;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:552:?, code lost:
        r0.pos = r3;
        r0.mark(r4 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:553:0x0741, code lost:
        r0.pos = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:554:0x0747, code lost:
        if (r21.read() >= 0) goto L_0x0754;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:555:0x0749, code lost:
        if (r1 == r6) goto L_0x0753;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:557:0x074d, code lost:
        if (r1 != 28) goto L_0x0750;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:559:0x0750, code lost:
        r1 = 37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:560:0x0754, code lost:
        if (r4 <= 0) goto L_0x075d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:561:0x0756, code lost:
        r21.reset();
        r0.skip(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:562:0x075d, code lost:
        r21.unread_quick();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:563:0x0760, code lost:
        r2 = r0.pos;
        r5 = r0.buffer;
        r3 = r0.limit;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:564:0x0766, code lost:
        if (r4 <= 0) goto L_0x076b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:565:0x0768, code lost:
        r4 = r2 - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:566:0x076b, code lost:
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:567:0x076c, code lost:
        r15 = r5[r2];
        r2 = r2 + 1;
        r9 = '<';
        r20 = r4;
        r4 = r3;
        r3 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:568:0x077a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:570:0x0784, code lost:
        throw new java.lang.RuntimeException(r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:642:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:643:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003f, code lost:
        r0.pos = r2;
        r7.error('e', r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0044, code lost:
        if (r2 < r4) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0205 A[LOOP:8: B:151:0x01fb->B:155:0x0205, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0213  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void parse(gnu.text.LineBufferedReader r21, gnu.xml.XMLFilter r22) {
        /*
            r0 = r21
            r7 = r22
            char[] r1 = r0.buffer
            int r2 = r0.pos
            int r3 = r0.limit
            r9 = 60
            r11 = 35
            r6 = 1
            r4 = 14
            r5 = 0
            r4 = r3
            r17 = r5
            r12 = 0
            r15 = 32
            r16 = 14
            r18 = -1
            r19 = 60
            r5 = r1
            r1 = 0
        L_0x0020:
            r8 = 133(0x85, float:1.86E-43)
            r13 = 101(0x65, float:1.42E-43)
            r10 = 62
            r14 = 10
            switch(r1) {
                case 0: goto L_0x0727;
                case 1: goto L_0x0699;
                case 2: goto L_0x0685;
                case 3: goto L_0x05d4;
                case 4: goto L_0x05c6;
                case 5: goto L_0x05d4;
                case 6: goto L_0x05ad;
                case 7: goto L_0x05d4;
                case 8: goto L_0x057a;
                case 9: goto L_0x05d4;
                case 10: goto L_0x054c;
                case 11: goto L_0x051c;
                case 12: goto L_0x04fb;
                case 13: goto L_0x04f2;
                case 14: goto L_0x04cc;
                case 15: goto L_0x04fb;
                case 16: goto L_0x0460;
                case 17: goto L_0x05d4;
                case 18: goto L_0x002b;
                case 19: goto L_0x0457;
                case 20: goto L_0x0389;
                case 21: goto L_0x00da;
                case 22: goto L_0x002b;
                case 23: goto L_0x04fb;
                case 24: goto L_0x05d4;
                case 25: goto L_0x00c8;
                case 26: goto L_0x0086;
                case 27: goto L_0x007f;
                case 28: goto L_0x0068;
                case 29: goto L_0x04fb;
                case 30: goto L_0x00da;
                case 31: goto L_0x0060;
                case 32: goto L_0x04fb;
                case 33: goto L_0x05d4;
                case 34: goto L_0x0054;
                case 35: goto L_0x0039;
                case 36: goto L_0x0036;
                case 37: goto L_0x002c;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x005b
        L_0x002c:
            r0.pos = r2
            r0 = 102(0x66, float:1.43E-43)
            java.lang.String r1 = "unexpected end-of-file"
            r7.error(r0, r1)
            return
        L_0x0036:
            r1 = r17
            goto L_0x003f
        L_0x0039:
            java.lang.String r17 = "invalid xml version specifier"
            r1 = r17
            r2 = r18
        L_0x003f:
            r0.pos = r2
            r7.error(r13, r1)
        L_0x0044:
            if (r2 < r4) goto L_0x0047
            return
        L_0x0047:
            int r8 = r2 + 1
            char r15 = r5[r2]
            if (r15 != r10) goto L_0x0052
            r17 = r1
            r9 = r4
            r2 = r8
            goto L_0x0093
        L_0x0052:
            r2 = r8
            goto L_0x0044
        L_0x0054:
            r1 = 63
            if (r15 != r1) goto L_0x005d
            r1 = 33
            r3 = r2
        L_0x005b:
            r9 = r4
            goto L_0x0094
        L_0x005d:
            r1 = 14
            goto L_0x0020
        L_0x0060:
            if (r15 != r9) goto L_0x0066
            r9 = r4
            r1 = 34
            goto L_0x0094
        L_0x0066:
            r1 = 1
            goto L_0x0020
        L_0x0068:
            if (r15 != r14) goto L_0x006c
            r1 = 1
            goto L_0x006d
        L_0x006c:
            r1 = 0
        L_0x006d:
            if (r15 != r8) goto L_0x0071
            r8 = 1
            goto L_0x0072
        L_0x0071:
            r8 = 0
        L_0x0072:
            r1 = r1 | r8
            if (r1 == 0) goto L_0x0079
            r0.incrLineNumber(r6, r2)
            goto L_0x0092
        L_0x0079:
            int r1 = r2 + -1
            r0.incrLineNumber(r6, r1)
            goto L_0x0066
        L_0x007f:
            if (r15 == r10) goto L_0x0092
            java.lang.String r17 = "missing '>'"
            r1 = 36
            goto L_0x0020
        L_0x0086:
            r8 = 59
            if (r15 != r8) goto L_0x0098
            r0.pos = r2
            int r1 = r2 + -1
            int r1 = r1 - r3
            r7.emitCharacterReference(r12, r5, r3, r1)
        L_0x0092:
            r9 = r4
        L_0x0093:
            r1 = 1
        L_0x0094:
            r13 = 9
            goto L_0x072c
        L_0x0098:
            r8 = 120(0x78, float:1.68E-43)
            if (r15 != r8) goto L_0x00a3
            if (r18 != 0) goto L_0x00a3
            r8 = 16
            r18 = 16
            goto L_0x00c0
        L_0x00a3:
            r8 = 134217728(0x8000000, float:3.85186E-34)
            if (r12 < r8) goto L_0x00a8
            goto L_0x00b5
        L_0x00a8:
            if (r18 != 0) goto L_0x00ad
            r8 = 10
            goto L_0x00af
        L_0x00ad:
            r8 = r18
        L_0x00af:
            int r10 = java.lang.Character.digit(r15, r8)
            if (r10 >= 0) goto L_0x00bd
        L_0x00b5:
            r0.pos = r2
            java.lang.String r1 = "invalid character reference"
            r7.error(r13, r1)
            goto L_0x0092
        L_0x00bd:
            int r12 = r12 * r8
            int r12 = r12 + r10
        L_0x00c0:
            if (r2 >= r4) goto L_0x005b
            int r8 = r2 + 1
            char r15 = r5[r2]
            r2 = r8
            goto L_0x0086
        L_0x00c8:
            if (r15 != r11) goto L_0x00d5
            r1 = 26
            r3 = r2
            r9 = r4
            r12 = 0
            r13 = 9
            r18 = 0
            goto L_0x072c
        L_0x00d5:
            int r3 = r2 + -1
            r1 = 7
            goto L_0x0020
        L_0x00da:
            if (r18 >= 0) goto L_0x00e1
            int r8 = r2 + -1
            r14 = r8
            r8 = r2
            goto L_0x00e4
        L_0x00e1:
            r8 = r2
            r14 = r18
        L_0x00e4:
            if (r15 != r10) goto L_0x0372
            int r2 = r8 + -2
            char r6 = r5[r2]
            r9 = 63
            if (r6 != r9) goto L_0x0372
            if (r2 < r14) goto L_0x0372
            r0.pos = r8
            r9 = 3
            if (r12 != r9) goto L_0x035a
            char r6 = r5[r3]
            r10 = 120(0x78, float:1.68E-43)
            if (r6 != r10) goto L_0x035a
            int r6 = r3 + 1
            char r6 = r5[r6]
            r10 = 109(0x6d, float:1.53E-43)
            if (r6 != r10) goto L_0x035a
            int r6 = r3 + 2
            char r6 = r5[r6]
            r10 = 108(0x6c, float:1.51E-43)
            if (r6 != r10) goto L_0x035a
            r6 = 30
            if (r1 != r6) goto L_0x0351
            int r1 = r14 + 7
            if (r2 <= r1) goto L_0x034b
            char r6 = r5[r14]
            r10 = 118(0x76, float:1.65E-43)
            if (r6 != r10) goto L_0x034b
            int r6 = r14 + 1
            char r6 = r5[r6]
            if (r6 != r13) goto L_0x034b
            int r6 = r14 + 2
            char r6 = r5[r6]
            r10 = 114(0x72, float:1.6E-43)
            if (r6 != r10) goto L_0x034b
            int r6 = r14 + 3
            char r6 = r5[r6]
            r10 = 115(0x73, float:1.61E-43)
            if (r6 != r10) goto L_0x034b
            int r6 = r14 + 4
            char r6 = r5[r6]
            r10 = 105(0x69, float:1.47E-43)
            if (r6 != r10) goto L_0x034b
            int r6 = r14 + 5
            char r6 = r5[r6]
            r10 = 111(0x6f, float:1.56E-43)
            if (r6 != r10) goto L_0x034b
            int r6 = r14 + 6
            char r6 = r5[r6]
            r10 = 110(0x6e, float:1.54E-43)
            if (r6 == r10) goto L_0x0149
            goto L_0x034b
        L_0x0149:
            char r6 = r5[r1]
            r15 = r6
        L_0x014c:
            boolean r6 = java.lang.Character.isWhitespace(r15)
            if (r6 == 0) goto L_0x0159
            int r1 = r1 + 1
            if (r1 >= r2) goto L_0x0159
            char r15 = r5[r1]
            goto L_0x014c
        L_0x0159:
            r18 = r1
            r1 = 61
            if (r15 == r1) goto L_0x0164
        L_0x015f:
            r2 = r8
            r1 = 35
            goto L_0x04f8
        L_0x0164:
            int r18 = r18 + 1
            char r1 = r5[r18]
        L_0x0168:
            boolean r6 = java.lang.Character.isWhitespace(r1)
            if (r6 == 0) goto L_0x0179
            int r6 = r18 + 1
            if (r6 >= r2) goto L_0x0177
            char r1 = r5[r6]
            r18 = r6
            goto L_0x0168
        L_0x0177:
            r18 = r6
        L_0x0179:
            r6 = 39
            if (r1 == r6) goto L_0x0183
            r6 = 34
            if (r1 == r6) goto L_0x0183
            r15 = r1
            goto L_0x015f
        L_0x0183:
            int r18 = r18 + 1
            r15 = r1
            r6 = r18
        L_0x0188:
            if (r6 != r2) goto L_0x018b
            goto L_0x015f
        L_0x018b:
            char r15 = r5[r6]
            if (r15 != r1) goto L_0x0346
            int r1 = r18 + 3
            if (r6 != r1) goto L_0x01aa
            char r1 = r5[r18]
            r14 = 49
            if (r1 != r14) goto L_0x01aa
            int r1 = r18 + 1
            char r1 = r5[r1]
            r14 = 46
            if (r1 != r14) goto L_0x01aa
            int r1 = r18 + 2
            char r1 = r5[r1]
            r14 = 48
            if (r1 == r14) goto L_0x01af
            r15 = r1
        L_0x01aa:
            r1 = 49
            if (r15 != r1) goto L_0x015f
            r1 = r15
        L_0x01af:
            int r6 = r6 + 1
            if (r6 >= r2) goto L_0x01bc
            char r14 = r5[r6]
            boolean r14 = java.lang.Character.isWhitespace(r14)
            if (r14 == 0) goto L_0x01bc
            goto L_0x01af
        L_0x01bc:
            int r14 = r6 + 7
            if (r2 <= r14) goto L_0x0267
            char r15 = r5[r6]
            if (r15 != r13) goto L_0x0267
            int r15 = r6 + 1
            char r15 = r5[r15]
            if (r15 != r10) goto L_0x0267
            int r15 = r6 + 2
            char r15 = r5[r15]
            r9 = 99
            if (r15 != r9) goto L_0x0267
            int r9 = r6 + 3
            char r9 = r5[r9]
            r15 = 111(0x6f, float:1.56E-43)
            if (r9 != r15) goto L_0x0267
            int r9 = r6 + 4
            char r9 = r5[r9]
            r15 = 100
            if (r9 != r15) goto L_0x0267
            int r9 = r6 + 5
            char r9 = r5[r9]
            r15 = 105(0x69, float:1.47E-43)
            if (r9 != r15) goto L_0x0267
            int r9 = r6 + 6
            char r9 = r5[r9]
            if (r9 != r10) goto L_0x0267
            char r9 = r5[r14]
            r14 = 103(0x67, float:1.44E-43)
            if (r9 != r14) goto L_0x0267
            int r6 = r6 + 8
            char r1 = r5[r6]
            r15 = r1
        L_0x01fb:
            boolean r1 = java.lang.Character.isWhitespace(r15)
            if (r1 == 0) goto L_0x0208
            int r6 = r6 + 1
            if (r6 >= r2) goto L_0x0208
            char r15 = r5[r6]
            goto L_0x01fb
        L_0x0208:
            r18 = r6
            r1 = 61
            if (r15 == r1) goto L_0x0213
            java.lang.String r17 = "bad 'encoding' declaration"
        L_0x0210:
            r2 = r8
            goto L_0x0356
        L_0x0213:
            int r18 = r18 + 1
            char r1 = r5[r18]
            r15 = r1
        L_0x0218:
            boolean r1 = java.lang.Character.isWhitespace(r15)
            if (r1 == 0) goto L_0x0229
            int r1 = r18 + 1
            if (r1 >= r2) goto L_0x0227
            char r15 = r5[r1]
            r18 = r1
            goto L_0x0218
        L_0x0227:
            r18 = r1
        L_0x0229:
            r1 = 39
            if (r15 == r1) goto L_0x0234
            r1 = 34
            if (r15 == r1) goto L_0x0234
            java.lang.String r17 = "bad 'encoding' declaration"
            goto L_0x0210
        L_0x0234:
            int r9 = r18 + 1
            r6 = r9
            r1 = r15
        L_0x0238:
            if (r6 != r2) goto L_0x0242
            java.lang.String r17 = "bad 'encoding' declaration"
            r15 = r1
            r2 = r8
            r18 = r9
            goto L_0x0356
        L_0x0242:
            char r1 = r5[r6]
            if (r1 != r15) goto L_0x0264
            java.lang.String r14 = new java.lang.String
            int r15 = r6 - r9
            r14.<init>(r5, r9, r15)
            boolean r9 = r0 instanceof gnu.text.LineInputStreamReader
            if (r9 == 0) goto L_0x0257
            r9 = r0
            gnu.text.LineInputStreamReader r9 = (gnu.text.LineInputStreamReader) r9
            r9.setCharset((java.lang.String) r14)
        L_0x0257:
            int r6 = r6 + 1
            if (r6 >= r2) goto L_0x0267
            char r9 = r5[r6]
            boolean r9 = java.lang.Character.isWhitespace(r9)
            if (r9 == 0) goto L_0x0267
            goto L_0x0257
        L_0x0264:
            int r6 = r6 + 1
            goto L_0x0238
        L_0x0267:
            int r9 = r6 + 9
            if (r2 <= r9) goto L_0x033b
            char r14 = r5[r6]
            r15 = 115(0x73, float:1.61E-43)
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 1
            char r14 = r5[r14]
            r15 = 116(0x74, float:1.63E-43)
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 2
            char r14 = r5[r14]
            r15 = 97
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 3
            char r14 = r5[r14]
            if (r14 != r10) goto L_0x033b
            int r14 = r6 + 4
            char r14 = r5[r14]
            r15 = 100
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 5
            char r14 = r5[r14]
            r15 = 97
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 6
            char r14 = r5[r14]
            r15 = 108(0x6c, float:1.51E-43)
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 7
            char r14 = r5[r14]
            r15 = 111(0x6f, float:1.56E-43)
            if (r14 != r15) goto L_0x033b
            int r14 = r6 + 8
            char r14 = r5[r14]
            if (r14 != r10) goto L_0x033b
            char r9 = r5[r9]
            if (r9 != r13) goto L_0x033b
            int r6 = r6 + 10
            char r1 = r5[r6]
            r15 = r1
        L_0x02b6:
            boolean r1 = java.lang.Character.isWhitespace(r15)
            if (r1 == 0) goto L_0x02c3
            int r6 = r6 + 1
            if (r6 >= r2) goto L_0x02c3
            char r15 = r5[r6]
            goto L_0x02b6
        L_0x02c3:
            r18 = r6
            r1 = 61
            if (r15 == r1) goto L_0x02cd
            java.lang.String r17 = "bad 'standalone' declaration"
            goto L_0x0210
        L_0x02cd:
            int r18 = r18 + 1
            char r1 = r5[r18]
            r15 = r1
        L_0x02d2:
            boolean r1 = java.lang.Character.isWhitespace(r15)
            if (r1 == 0) goto L_0x02e3
            int r1 = r18 + 1
            if (r1 >= r2) goto L_0x02e1
            char r15 = r5[r1]
            r18 = r1
            goto L_0x02d2
        L_0x02e1:
            r18 = r1
        L_0x02e3:
            r1 = 39
            if (r15 == r1) goto L_0x02ef
            r1 = 34
            if (r15 == r1) goto L_0x02ef
            java.lang.String r17 = "bad 'standalone' declaration"
            goto L_0x0210
        L_0x02ef:
            int r18 = r18 + 1
            r1 = r15
            r6 = r18
        L_0x02f4:
            if (r6 != r2) goto L_0x02fb
            java.lang.String r17 = "bad 'standalone' declaration"
        L_0x02f8:
            r15 = r1
            goto L_0x0210
        L_0x02fb:
            char r1 = r5[r6]
            if (r1 != r15) goto L_0x0338
            int r9 = r18 + 3
            if (r6 != r9) goto L_0x0318
            char r9 = r5[r18]
            r14 = 121(0x79, float:1.7E-43)
            if (r9 != r14) goto L_0x0318
            int r9 = r18 + 1
            char r9 = r5[r9]
            if (r9 != r13) goto L_0x0318
            int r9 = r18 + 2
            char r9 = r5[r9]
            r13 = 115(0x73, float:1.61E-43)
            if (r9 != r13) goto L_0x0318
            goto L_0x0328
        L_0x0318:
            int r9 = r18 + 2
            if (r6 != r9) goto L_0x0335
            char r9 = r5[r18]
            if (r9 != r10) goto L_0x0335
            int r9 = r18 + 1
            char r9 = r5[r9]
            r10 = 111(0x6f, float:1.56E-43)
            if (r9 != r10) goto L_0x0335
        L_0x0328:
            int r6 = r6 + 1
            if (r6 >= r2) goto L_0x033b
            char r9 = r5[r6]
            boolean r9 = java.lang.Character.isWhitespace(r9)
            if (r9 == 0) goto L_0x033b
            goto L_0x0328
        L_0x0335:
            java.lang.String r17 = "bad 'standalone' declaration"
            goto L_0x02f8
        L_0x0338:
            int r6 = r6 + 1
            goto L_0x02f4
        L_0x033b:
            r15 = r1
            if (r2 == r6) goto L_0x0342
            java.lang.String r17 = "junk at end of xml declaration"
            r2 = r6
            goto L_0x034e
        L_0x0342:
            r9 = r4
            r10 = r5
            r13 = 1
            goto L_0x0367
        L_0x0346:
            int r6 = r6 + 1
            r9 = 3
            goto L_0x0188
        L_0x034b:
            java.lang.String r17 = "xml declaration without version"
            r2 = r14
        L_0x034e:
            r18 = r2
            goto L_0x0356
        L_0x0351:
            java.lang.String r17 = "<?xml must be at start of file"
            r2 = r8
            r18 = r14
        L_0x0356:
            r1 = 36
            goto L_0x04f8
        L_0x035a:
            int r6 = r2 - r14
            r1 = r22
            r2 = r5
            r9 = r4
            r4 = r12
            r10 = r5
            r5 = r14
            r13 = 1
            r1.processingInstructionFromParser(r2, r3, r4, r5, r6)
        L_0x0367:
            r2 = r8
            r3 = r9
            r5 = r10
            r1 = 1
            r6 = 1
            r13 = 9
            r18 = -1
            goto L_0x072c
        L_0x0372:
            r9 = r4
            r6 = r5
            r5 = 1
            if (r8 >= r9) goto L_0x0383
            int r2 = r8 + 1
            char r15 = r6[r8]
            r8 = r2
            r5 = r6
            r4 = r9
            r6 = 1
            r9 = 60
            goto L_0x00e4
        L_0x0383:
            r5 = r6
            r2 = r8
            r18 = r14
            goto L_0x04d6
        L_0x0389:
            r9 = r4
            r6 = r5
            r5 = 1
        L_0x038c:
            if (r15 != r10) goto L_0x040f
            int r4 = r2 + -1
            int r12 = r4 - r3
            r4 = 4
            if (r12 < r4) goto L_0x03b7
            char r4 = r6[r3]
            r8 = 45
            if (r4 != r8) goto L_0x03b7
            int r4 = r3 + 1
            char r4 = r6[r4]
            if (r4 != r8) goto L_0x03b7
            int r4 = r2 + -2
            char r4 = r6[r4]
            if (r4 != r8) goto L_0x044b
            int r4 = r2 + -3
            char r4 = r6[r4]
            if (r4 != r8) goto L_0x044b
            r0.pos = r2
            int r3 = r3 + 2
            int r1 = r12 + -4
            r7.commentFromParser(r6, r3, r1)
            goto L_0x040a
        L_0x03b7:
            r4 = 6
            if (r12 < r4) goto L_0x040a
            char r4 = r6[r3]
            r8 = 91
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 1
            char r4 = r6[r4]
            r8 = 67
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 2
            char r4 = r6[r4]
            r8 = 68
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 3
            char r4 = r6[r4]
            r8 = 65
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 4
            char r4 = r6[r4]
            r8 = 84
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 5
            char r4 = r6[r4]
            r8 = 65
            if (r4 != r8) goto L_0x040a
            int r4 = r3 + 6
            char r4 = r6[r4]
            r8 = 91
            if (r4 != r8) goto L_0x040a
            int r4 = r2 + -2
            char r4 = r6[r4]
            r8 = 93
            if (r4 != r8) goto L_0x044b
            int r4 = r2 + -3
            char r4 = r6[r4]
            r8 = 93
            if (r4 != r8) goto L_0x044b
            r0.pos = r2
            int r1 = r3 + 7
            int r4 = r2 + -10
            int r4 = r4 - r3
            r7.writeCDATA(r6, r1, r4)
        L_0x040a:
            r5 = r6
            r3 = r9
            r1 = 1
            goto L_0x04d6
        L_0x040f:
            int r4 = r3 + 7
            if (r2 != r4) goto L_0x044b
            char r4 = r6[r3]
            r8 = 68
            if (r4 != r8) goto L_0x044b
            int r4 = r3 + 1
            char r4 = r6[r4]
            r8 = 79
            if (r4 != r8) goto L_0x044b
            int r4 = r3 + 2
            char r4 = r6[r4]
            r8 = 67
            if (r4 != r8) goto L_0x044b
            int r4 = r3 + 3
            char r4 = r6[r4]
            r8 = 84
            if (r4 != r8) goto L_0x044b
            int r4 = r3 + 4
            char r4 = r6[r4]
            r8 = 89
            if (r4 != r8) goto L_0x044b
            int r4 = r3 + 5
            char r4 = r6[r4]
            r8 = 80
            if (r4 != r8) goto L_0x044b
            r4 = 69
            if (r15 != r4) goto L_0x044b
            r1 = 15
            r5 = r6
            r3 = r9
            goto L_0x04d6
        L_0x044b:
            if (r2 >= r9) goto L_0x0454
            int r4 = r2 + 1
            char r15 = r6[r2]
            r2 = r4
            goto L_0x038c
        L_0x0454:
            r5 = r6
            goto L_0x04d6
        L_0x0457:
            r9 = r4
            r6 = r5
            r5 = 1
            int r3 = r2 + -1
            r1 = 5
            r5 = r6
            goto L_0x04f8
        L_0x0460:
            r9 = r4
            r6 = r5
            r5 = 1
            if (r18 >= 0) goto L_0x046d
            int r4 = r2 + -1
            int r4 = r4 - r3
            int r18 = r4 << 1
            r8 = r2
            r2 = 0
            goto L_0x0470
        L_0x046d:
            r8 = r2
            r2 = r19
        L_0x0470:
            r4 = 39
        L_0x0472:
            if (r15 == r4) goto L_0x04b3
            r4 = 34
            if (r15 != r4) goto L_0x0479
            goto L_0x04b3
        L_0x0479:
            if (r2 != 0) goto L_0x0483
            r4 = 91
            if (r15 != r4) goto L_0x0486
            r4 = r18 | 1
        L_0x0481:
            r18 = r4
        L_0x0483:
            r5 = r6
            r14 = 1
            goto L_0x04bc
        L_0x0486:
            r4 = 93
            if (r15 != r4) goto L_0x048d
            r4 = r18 & -2
            goto L_0x0481
        L_0x048d:
            if (r15 != r10) goto L_0x0483
            r4 = r18 & 1
            if (r4 != 0) goto L_0x0483
            r0.pos = r8
            int r1 = r18 >> 1
            int r10 = r1 + r3
            int r1 = r8 + -1
            int r13 = r1 - r10
            r1 = r22
            r2 = r6
            r4 = r12
            r14 = 1
            r5 = r10
            r10 = r6
            r6 = r13
            r1.emitDoctypeDecl(r2, r3, r4, r5, r6)
            r2 = r8
            r3 = r9
            r5 = r10
            r1 = 1
            r6 = 1
            r13 = 9
            r18 = -1
            goto L_0x0561
        L_0x04b3:
            r5 = r6
            r14 = 1
            if (r2 != 0) goto L_0x04b9
            r2 = r15
            goto L_0x04bc
        L_0x04b9:
            if (r2 != r15) goto L_0x04bc
            r2 = 0
        L_0x04bc:
            if (r8 >= r9) goto L_0x04c8
            int r4 = r8 + 1
            char r15 = r5[r8]
            r8 = r4
            r6 = r5
            r4 = 39
            r5 = 1
            goto L_0x0472
        L_0x04c8:
            r19 = r2
            r2 = r8
            goto L_0x04d6
        L_0x04cc:
            r9 = r4
            r14 = 1
            r1 = 47
            if (r15 != r1) goto L_0x04d9
            r6 = 19
            r1 = 19
        L_0x04d6:
            r6 = 1
            goto L_0x0094
        L_0x04d9:
            r1 = 63
            if (r15 != r1) goto L_0x04e3
            r6 = 24
            r3 = r2
            r1 = 24
            goto L_0x04d6
        L_0x04e3:
            r1 = 33
            if (r15 != r1) goto L_0x04ed
            r6 = 20
            r3 = r2
            r1 = 20
            goto L_0x04d6
        L_0x04ed:
            int r3 = r2 + -1
            r4 = r9
            r1 = 3
            goto L_0x04f8
        L_0x04f2:
            r9 = r4
            r14 = 1
            r1 = 17
            int r3 = r2 + -1
        L_0x04f8:
            r6 = 1
            goto L_0x0695
        L_0x04fb:
            r9 = r4
            r4 = 32
            if (r15 == r4) goto L_0x0094
            r4 = 9
            if (r15 != r4) goto L_0x0506
            goto L_0x0094
        L_0x0506:
            if (r15 == r14) goto L_0x0517
            r4 = 13
            if (r15 == r4) goto L_0x0517
            if (r15 == r8) goto L_0x0517
            r4 = 8232(0x2028, float:1.1535E-41)
            if (r15 != r4) goto L_0x0513
            goto L_0x0517
        L_0x0513:
            int r1 = r1 + -2
            goto L_0x0733
        L_0x0517:
            r0.incrLineNumber(r6, r2)
            goto L_0x0094
        L_0x051c:
            r9 = r4
            r4 = 39
            if (r15 == r4) goto L_0x0541
            r4 = 34
            if (r15 != r4) goto L_0x0526
            goto L_0x0541
        L_0x0526:
            r4 = 32
            if (r15 == r4) goto L_0x0094
            r4 = 9
            if (r15 == r4) goto L_0x0094
            r4 = 13
            if (r15 == r4) goto L_0x0094
            if (r15 == r14) goto L_0x0094
            if (r15 == r8) goto L_0x0094
            r4 = 8232(0x2028, float:1.1535E-41)
            if (r15 != r4) goto L_0x053c
            goto L_0x0094
        L_0x053c:
            java.lang.String r17 = "missing or unquoted attribute value"
        L_0x053e:
            r4 = r9
            goto L_0x05a9
        L_0x0541:
            r1 = 12
            r19 = r15
            r1 = 1
            r13 = 9
            r16 = 12
            goto L_0x072c
        L_0x054c:
            r9 = r4
            r16 = 14
            r1 = 47
            if (r15 != r1) goto L_0x0565
            r0.pos = r2
            r22.emitEndAttributes()
            r1 = 0
            r4 = 0
            r7.emitEndElement(r1, r4, r4)
            r1 = 27
        L_0x055f:
            r13 = 9
        L_0x0561:
            r19 = 60
            goto L_0x072c
        L_0x0565:
            r4 = 0
            if (r15 != r10) goto L_0x056f
            r0.pos = r2
            r22.emitEndAttributes()
            r1 = 1
            goto L_0x055f
        L_0x056f:
            int r3 = r2 + -1
            r4 = r9
            r1 = 9
            r9 = 60
            r19 = 60
            goto L_0x0020
        L_0x057a:
            r9 = r4
            r4 = 0
            r10 = 32
            if (r15 == r10) goto L_0x0094
            r13 = 9
            if (r15 == r13) goto L_0x072c
            r4 = 13
            if (r15 == r4) goto L_0x072c
            if (r15 == r14) goto L_0x072c
            if (r15 == r8) goto L_0x072c
            r4 = 8232(0x2028, float:1.1535E-41)
            if (r15 != r4) goto L_0x0592
            goto L_0x072c
        L_0x0592:
            int r1 = r2 - r12
            r0.pos = r1
            r7.emitStartAttribute(r5, r3, r12)
            r1 = 61
            if (r15 != r1) goto L_0x05a2
            r1 = 11
            r3 = r9
            goto L_0x072c
        L_0x05a2:
            r22.emitEndAttributes()
            java.lang.String r17 = "missing or misplaced '=' after attribute name"
            r3 = r9
            r4 = r3
        L_0x05a9:
            r1 = 36
            goto L_0x0695
        L_0x05ad:
            r9 = r4
            r10 = 32
            r13 = 9
            r0.pos = r2
            r1 = 59
            if (r15 == r1) goto L_0x05bf
            r1 = 119(0x77, float:1.67E-43)
            java.lang.String r4 = "missing ';'"
            r7.error(r1, r4)
        L_0x05bf:
            r7.emitEntityReference(r5, r3, r12)
            r3 = r9
            r1 = 1
            goto L_0x072c
        L_0x05c6:
            r9 = r4
            r10 = 32
            r13 = 9
            r0.pos = r2
            r7.emitEndElement(r5, r3, r12)
            r1 = 29
            goto L_0x0693
        L_0x05d4:
            r9 = r4
            r10 = 32
            r13 = 9
            int r12 = r3 + 1
        L_0x05db:
            r4 = 97
            if (r15 < r4) goto L_0x05e9
            r4 = 122(0x7a, float:1.71E-43)
            if (r15 <= r4) goto L_0x05e4
            goto L_0x05e9
        L_0x05e4:
            r4 = 45
        L_0x05e6:
            r8 = 4
            goto L_0x067c
        L_0x05e9:
            r4 = 65
            if (r15 < r4) goto L_0x05f1
            r4 = 90
            if (r15 <= r4) goto L_0x05e4
        L_0x05f1:
            r4 = 95
            if (r15 == r4) goto L_0x05e4
            r4 = 58
            if (r15 == r4) goto L_0x05e4
            r4 = 192(0xc0, float:2.69E-43)
            if (r15 < r4) goto L_0x0638
            r4 = 767(0x2ff, float:1.075E-42)
            if (r15 <= r4) goto L_0x05e4
            r4 = 880(0x370, float:1.233E-42)
            if (r15 < r4) goto L_0x0638
            r4 = 8191(0x1fff, float:1.1478E-41)
            if (r15 > r4) goto L_0x060d
            r4 = 894(0x37e, float:1.253E-42)
            if (r15 != r4) goto L_0x05e4
        L_0x060d:
            r4 = 8204(0x200c, float:1.1496E-41)
            if (r15 < r4) goto L_0x0638
            r4 = 8205(0x200d, float:1.1498E-41)
            if (r15 <= r4) goto L_0x05e4
            r4 = 8304(0x2070, float:1.1636E-41)
            if (r15 < r4) goto L_0x061d
            r4 = 8591(0x218f, float:1.2039E-41)
            if (r15 <= r4) goto L_0x05e4
        L_0x061d:
            r4 = 11264(0x2c00, float:1.5784E-41)
            if (r15 < r4) goto L_0x0625
            r4 = 12271(0x2fef, float:1.7195E-41)
            if (r15 <= r4) goto L_0x05e4
        L_0x0625:
            r4 = 12289(0x3001, float:1.722E-41)
            if (r15 < r4) goto L_0x062e
            r4 = 55295(0xd7ff, float:7.7485E-41)
            if (r15 <= r4) goto L_0x05e4
        L_0x062e:
            r4 = 63744(0xf900, float:8.9324E-41)
            if (r15 < r4) goto L_0x0638
            r4 = 65533(0xfffd, float:9.1831E-41)
            if (r15 <= r4) goto L_0x05e4
        L_0x0638:
            if (r2 <= r12) goto L_0x0642
            r4 = 48
            if (r15 < r4) goto L_0x0642
            r4 = 57
            if (r15 <= r4) goto L_0x05e4
        L_0x0642:
            r4 = 46
            if (r15 == r4) goto L_0x05e4
            r4 = 45
            if (r15 == r4) goto L_0x05e6
            r8 = 183(0xb7, float:2.56E-43)
            if (r15 == r8) goto L_0x05e6
            r8 = 768(0x300, float:1.076E-42)
            if (r15 <= r8) goto L_0x065f
            r8 = 879(0x36f, float:1.232E-42)
            if (r15 <= r8) goto L_0x05e6
            r8 = 8255(0x203f, float:1.1568E-41)
            if (r15 < r8) goto L_0x065f
            r8 = 8256(0x2040, float:1.1569E-41)
            if (r15 > r8) goto L_0x065f
            goto L_0x05e6
        L_0x065f:
            int r1 = r1 + -1
            int r12 = r2 - r12
            if (r12 != 0) goto L_0x0733
            r4 = 8
            if (r1 != r4) goto L_0x066f
            java.lang.String r1 = "missing or invalid attribute name"
        L_0x066b:
            r17 = r1
            goto L_0x053e
        L_0x066f:
            r4 = 2
            if (r1 == r4) goto L_0x0679
            r8 = 4
            if (r1 != r8) goto L_0x0676
            goto L_0x0679
        L_0x0676:
            java.lang.String r1 = "missing or invalid name"
            goto L_0x066b
        L_0x0679:
            java.lang.String r1 = "missing or invalid element name"
            goto L_0x066b
        L_0x067c:
            if (r2 >= r9) goto L_0x072c
            int r14 = r2 + 1
            char r15 = r5[r2]
            r2 = r14
            goto L_0x05db
        L_0x0685:
            r9 = r4
            r10 = 32
            r13 = 9
            int r1 = r2 - r12
            r0.pos = r1
            r7.emitStartElement(r5, r3, r12)
            r1 = 12
        L_0x0693:
            r3 = r9
            r4 = r3
        L_0x0695:
            r9 = 60
            goto L_0x0020
        L_0x0699:
            r9 = r4
            r10 = 32
            r13 = 9
            int r3 = r2 + -1
            r4 = r3
            r12 = r19
            r3 = r2
        L_0x06a4:
            if (r15 != r12) goto L_0x06aa
            r1 = r16
            goto L_0x070f
        L_0x06aa:
            r10 = 38
            if (r15 != r10) goto L_0x06b2
            r1 = 25
            goto L_0x070f
        L_0x06b2:
            r10 = 13
            if (r15 != r10) goto L_0x06ea
            int r3 = r2 - r3
            r0.pos = r2
            if (r3 <= 0) goto L_0x06bf
            r7.textFromParser(r5, r4, r3)
        L_0x06bf:
            if (r2 >= r9) goto L_0x06e4
            char r15 = r5[r2]
            if (r15 != r14) goto L_0x06c9
            int r3 = r2 + 1
            r4 = r3
            goto L_0x06d2
        L_0x06c9:
            r22.linefeedFromParser()
            if (r15 != r8) goto L_0x06dc
            int r3 = r2 + 1
            int r4 = r3 + 1
        L_0x06d2:
            r0.incrLineNumber(r6, r3)
            r20 = r4
            r4 = r2
            r2 = r3
            r3 = r20
            goto L_0x070b
        L_0x06dc:
            r0.incrLineNumber(r6, r2)
            int r3 = r2 + 1
            r4 = r2
            r2 = r3
            goto L_0x0723
        L_0x06e4:
            r22.linefeedFromParser()
            r1 = 28
            goto L_0x0719
        L_0x06ea:
            if (r15 == r8) goto L_0x06f7
            r10 = 8232(0x2028, float:1.1535E-41)
            if (r15 != r10) goto L_0x06f1
            goto L_0x06f7
        L_0x06f1:
            if (r15 != r14) goto L_0x070b
            r0.incrLineNumber(r6, r2)
            goto L_0x070b
        L_0x06f7:
            int r3 = r2 - r3
            int r10 = r2 + -1
            r0.pos = r10
            if (r3 <= 0) goto L_0x0702
            r7.textFromParser(r5, r4, r3)
        L_0x0702:
            r22.linefeedFromParser()
            r0.incrLineNumber(r6, r2)
            int r3 = r2 + 1
            r4 = r2
        L_0x070b:
            if (r2 != r9) goto L_0x071e
            int r3 = r3 + -1
        L_0x070f:
            int r3 = r2 - r3
            if (r3 <= 0) goto L_0x0718
            r0.pos = r2
            r7.textFromParser(r5, r4, r3)
        L_0x0718:
            int r4 = r5.length
        L_0x0719:
            r19 = r12
            r12 = r3
            r3 = r4
            goto L_0x072c
        L_0x071e:
            int r10 = r2 + 1
            char r15 = r5[r2]
            r2 = r10
        L_0x0723:
            r10 = 32
            goto L_0x06a4
        L_0x0727:
            r9 = r4
            r13 = 9
            r1 = 31
        L_0x072c:
            if (r2 >= r9) goto L_0x0736
            int r4 = r2 + 1
            char r15 = r5[r2]
            r2 = r4
        L_0x0733:
            r4 = r9
            goto L_0x0695
        L_0x0736:
            int r4 = r2 - r3
            if (r4 <= 0) goto L_0x0741
            r0.pos = r3     // Catch:{ IOException -> 0x077a }
            int r8 = r4 + 1
            r0.mark(r8)     // Catch:{ IOException -> 0x077a }
        L_0x0741:
            r0.pos = r2     // Catch:{ IOException -> 0x077a }
            int r8 = r21.read()     // Catch:{ IOException -> 0x077a }
            if (r8 >= 0) goto L_0x0754
            if (r1 == r6) goto L_0x0753
            r4 = 28
            if (r1 != r4) goto L_0x0750
            goto L_0x0753
        L_0x0750:
            r1 = 37
            goto L_0x0733
        L_0x0753:
            return
        L_0x0754:
            if (r4 <= 0) goto L_0x075d
            r21.reset()     // Catch:{ IOException -> 0x077a }
            r0.skip(r4)     // Catch:{ IOException -> 0x077a }
            goto L_0x0760
        L_0x075d:
            r21.unread_quick()     // Catch:{ IOException -> 0x077a }
        L_0x0760:
            int r2 = r0.pos
            char[] r5 = r0.buffer
            int r3 = r0.limit
            if (r4 <= 0) goto L_0x076b
            int r4 = r2 - r4
            goto L_0x076c
        L_0x076b:
            r4 = r3
        L_0x076c:
            int r8 = r2 + 1
            char r15 = r5[r2]
            r2 = r8
            r9 = 60
            r20 = r4
            r4 = r3
            r3 = r20
            goto L_0x0020
        L_0x077a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLParser.parse(gnu.text.LineBufferedReader, gnu.xml.XMLFilter):void");
    }
}

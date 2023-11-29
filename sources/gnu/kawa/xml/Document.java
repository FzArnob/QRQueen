package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.ThreadLocation;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.NodeTree;
import gnu.xml.XMLParser;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Document {
    private static HashMap cache = new HashMap();
    private static ThreadLocation docMapLocation = new ThreadLocation("document-map");
    public static final Document document = new Document();

    public static void parse(Object obj, Consumer consumer) throws Throwable {
        SourceMessages sourceMessages = new SourceMessages();
        boolean z = consumer instanceof XConsumer;
        if (z) {
            ((XConsumer) consumer).beginEntity(obj);
        }
        XMLParser.parse(obj, sourceMessages, consumer);
        if (sourceMessages.seenErrors()) {
            throw new SyntaxException("document function read invalid XML", sourceMessages);
        } else if (z) {
            ((XConsumer) consumer).endEntity();
        }
    }

    public static KDocument parse(Object obj) throws Throwable {
        NodeTree nodeTree = new NodeTree();
        parse(obj, nodeTree);
        return new KDocument(nodeTree, 10);
    }

    private static class DocReference extends SoftReference {
        static ReferenceQueue queue = new ReferenceQueue();
        Path key;

        public DocReference(Path path, KDocument kDocument) {
            super(kDocument, queue);
            this.key = path;
        }
    }

    public static void clearLocalCache() {
        docMapLocation.getLocation().set((Object) null);
    }

    public static void clearSoftCache() {
        cache = new HashMap();
    }

    public static KDocument parseCached(Object obj) throws Throwable {
        return parseCached(Path.valueOf(obj));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1 = (gnu.kawa.xml.Document.DocReference) cache.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r1 == null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r1 = (gnu.kawa.xml.KDocument) r1.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r1 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        cache.remove(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r2.put(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1 = parse(r4);
        r2.put(r4, r1);
        cache.put(r4, new gnu.kawa.xml.Document.DocReference(r4, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        r1 = docMapLocation.getLocation();
        r2 = (java.util.Hashtable) r1.get((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (r2 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r2 = new java.util.Hashtable();
        r1.set(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        r1 = (gnu.kawa.xml.KDocument) r2.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r1 == null) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized gnu.kawa.xml.KDocument parseCached(gnu.text.Path r4) throws java.lang.Throwable {
        /*
            java.lang.Class<gnu.kawa.xml.Document> r0 = gnu.kawa.xml.Document.class
            monitor-enter(r0)
        L_0x0003:
            java.lang.ref.ReferenceQueue r1 = gnu.kawa.xml.Document.DocReference.queue     // Catch:{ all -> 0x0066 }
            java.lang.ref.Reference r1 = r1.poll()     // Catch:{ all -> 0x0066 }
            gnu.kawa.xml.Document$DocReference r1 = (gnu.kawa.xml.Document.DocReference) r1     // Catch:{ all -> 0x0066 }
            if (r1 != 0) goto L_0x005e
            gnu.mapping.ThreadLocation r1 = docMapLocation     // Catch:{ all -> 0x0066 }
            gnu.mapping.NamedLocation r1 = r1.getLocation()     // Catch:{ all -> 0x0066 }
            r2 = 0
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x0066 }
            java.util.Hashtable r2 = (java.util.Hashtable) r2     // Catch:{ all -> 0x0066 }
            if (r2 != 0) goto L_0x0024
            java.util.Hashtable r2 = new java.util.Hashtable     // Catch:{ all -> 0x0066 }
            r2.<init>()     // Catch:{ all -> 0x0066 }
            r1.set(r2)     // Catch:{ all -> 0x0066 }
        L_0x0024:
            java.lang.Object r1 = r2.get(r4)     // Catch:{ all -> 0x0066 }
            gnu.kawa.xml.KDocument r1 = (gnu.kawa.xml.KDocument) r1     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x002e
            monitor-exit(r0)
            return r1
        L_0x002e:
            java.util.HashMap r1 = cache     // Catch:{ all -> 0x0066 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0066 }
            gnu.kawa.xml.Document$DocReference r1 = (gnu.kawa.xml.Document.DocReference) r1     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0066 }
            gnu.kawa.xml.KDocument r1 = (gnu.kawa.xml.KDocument) r1     // Catch:{ all -> 0x0066 }
            if (r1 != 0) goto L_0x0046
            java.util.HashMap r1 = cache     // Catch:{ all -> 0x0066 }
            r1.remove(r4)     // Catch:{ all -> 0x0066 }
            goto L_0x004b
        L_0x0046:
            r2.put(r4, r1)     // Catch:{ all -> 0x0066 }
            monitor-exit(r0)
            return r1
        L_0x004b:
            gnu.kawa.xml.KDocument r1 = parse(r4)     // Catch:{ all -> 0x0066 }
            r2.put(r4, r1)     // Catch:{ all -> 0x0066 }
            java.util.HashMap r2 = cache     // Catch:{ all -> 0x0066 }
            gnu.kawa.xml.Document$DocReference r3 = new gnu.kawa.xml.Document$DocReference     // Catch:{ all -> 0x0066 }
            r3.<init>(r4, r1)     // Catch:{ all -> 0x0066 }
            r2.put(r4, r3)     // Catch:{ all -> 0x0066 }
            monitor-exit(r0)
            return r1
        L_0x005e:
            java.util.HashMap r2 = cache     // Catch:{ all -> 0x0066 }
            gnu.text.Path r1 = r1.key     // Catch:{ all -> 0x0066 }
            r2.remove(r1)     // Catch:{ all -> 0x0066 }
            goto L_0x0003
        L_0x0066:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.Document.parseCached(gnu.text.Path):gnu.kawa.xml.KDocument");
    }
}

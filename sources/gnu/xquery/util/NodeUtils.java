package gnu.xquery.util;

import gnu.kawa.xml.Document;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.SortedNodes;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.xml.NamespaceBinding;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import gnu.xquery.lang.XQuery;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Stack;

public class NodeUtils {
    static String collectionNamespace = "http://gnu.org/kawa/cached-collections";
    public static final Symbol collectionResolverSymbol = Symbol.make(XQuery.LOCAL_NAMESPACE, "collection-resolver", "qexo");

    public static Object nodeName(Object obj) {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if (obj instanceof KNode) {
            Symbol nodeSymbol = ((KNode) obj).getNodeSymbol();
            return nodeSymbol == null ? Values.empty : nodeSymbol;
        }
        throw new WrongType("node-name", 1, obj, "node()?");
    }

    public static String name(Object obj) {
        Object nodeNameObject;
        if (obj == Values.empty || obj == null || (nodeNameObject = ((KNode) obj).getNodeNameObject()) == null || nodeNameObject == Values.empty) {
            return "";
        }
        return nodeNameObject.toString();
    }

    public static String localName(Object obj) {
        if (obj == Values.empty || obj == null) {
            return "";
        }
        if (obj instanceof KNode) {
            Object nodeNameObject = ((KNode) obj).getNodeNameObject();
            if (nodeNameObject == null || nodeNameObject == Values.empty) {
                return "";
            }
            if (nodeNameObject instanceof Symbol) {
                return ((Symbol) nodeNameObject).getName();
            }
            return nodeNameObject.toString();
        }
        throw new WrongType("local-name", 1, obj, "node()?");
    }

    public static Object namespaceURI(Object obj) {
        if (obj == Values.empty || obj == null) {
            return "";
        }
        if (obj instanceof KNode) {
            Object nodeNameObject = ((KNode) obj).getNodeNameObject();
            if (nodeNameObject instanceof Symbol) {
                return QNameUtils.namespaceURIFromQName(nodeNameObject);
            }
            return "";
        }
        throw new WrongType("namespace-uri", 1, obj, "node()?");
    }

    public static void prefixesFromNodetype(XName xName, Consumer consumer) {
        NamespaceBinding namespaceNodes = xName.getNamespaceNodes();
        for (NamespaceBinding namespaceBinding = namespaceNodes; namespaceBinding != null; namespaceBinding = namespaceBinding.getNext()) {
            if (namespaceBinding.getUri() != null) {
                String prefix = namespaceBinding.getPrefix();
                NamespaceBinding namespaceBinding2 = namespaceNodes;
                while (true) {
                    if (namespaceBinding2 == namespaceBinding) {
                        if (prefix == null) {
                            prefix = "";
                        }
                        consumer.writeObject(prefix);
                    } else if (namespaceBinding2.getPrefix() == prefix) {
                        break;
                    } else {
                        namespaceBinding2 = namespaceBinding2.getNext();
                    }
                }
            }
        }
    }

    public static void inScopePrefixes$X(Object obj, CallContext callContext) {
        Object nodeNameObject = ((KElement) obj).getNodeNameObject();
        if (nodeNameObject instanceof XName) {
            prefixesFromNodetype((XName) nodeNameObject, callContext.consumer);
        } else {
            callContext.consumer.writeObject("xml");
        }
    }

    public static void data$X(Object obj, CallContext callContext) {
        Consumer consumer = callContext.consumer;
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int startPos = values.startPos();
            while (true) {
                startPos = values.nextPos(startPos);
                if (startPos != 0) {
                    consumer.writeObject(KNode.atomicValue(values.getPosPrevious(startPos)));
                } else {
                    return;
                }
            }
        } else {
            consumer.writeObject(KNode.atomicValue(obj));
        }
    }

    public static Object root(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (obj instanceof KNode) {
            KNode kNode = (KNode) obj;
            return Nodes.root((NodeTree) kNode.sequence, kNode.getPos());
        }
        throw new WrongType("root", 1, obj, "node()?");
    }

    public static KDocument rootDocument(Object obj) {
        if (obj instanceof KNode) {
            KNode kNode = (KNode) obj;
            KNode root = Nodes.root((NodeTree) kNode.sequence, kNode.getPos());
            if (root instanceof KDocument) {
                return (KDocument) root;
            }
            throw new WrongType("root-document", 1, obj, "document()");
        }
        throw new WrongType("root-document", 1, obj, "node()?");
    }

    public static String getLang(KNode kNode) {
        NodeTree nodeTree = (NodeTree) kNode.sequence;
        int ancestorAttribute = nodeTree.ancestorAttribute(kNode.ipos, NamespaceBinding.XML_NAMESPACE, "lang");
        if (ancestorAttribute == 0) {
            return null;
        }
        return KNode.getNodeValue(nodeTree, ancestorAttribute);
    }

    public static boolean lang(Object obj, Object obj2) {
        String stringValue = (obj == null || obj == Values.empty) ? "" : TextUtils.stringValue(obj);
        String lang = getLang((KNode) obj2);
        if (lang == null) {
            return false;
        }
        int length = lang.length();
        int length2 = stringValue.length();
        if (length > length2 && lang.charAt(length2) == '-') {
            lang = lang.substring(0, length2);
        }
        return lang.equalsIgnoreCase(stringValue);
    }

    public static Object documentUri(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (obj instanceof KNode) {
            KNode kNode = (KNode) obj;
            Object documentUriOfPos = ((NodeTree) kNode.sequence).documentUriOfPos(kNode.ipos);
            return documentUriOfPos == null ? Values.empty : documentUriOfPos;
        }
        throw new WrongType("xs:document-uri", 1, obj, "node()?");
    }

    public static Object nilled(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (!(obj instanceof KNode)) {
            throw new WrongType("nilled", 1, obj, "node()?");
        } else if (!(obj instanceof KElement)) {
            return Values.empty;
        } else {
            return Boolean.FALSE;
        }
    }

    public static Object baseUri(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (obj instanceof KNode) {
            Path baseURI = ((KNode) obj).baseURI();
            return baseURI == null ? Values.empty : baseURI;
        }
        throw new WrongType("base-uri", 1, obj, "node()?");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005d A[EDGE_INSN: B:42:0x005d->B:25:0x005d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object getIDs(java.lang.Object r5, java.lang.Object r6) {
        /*
            boolean r0 = r5 instanceof gnu.kawa.xml.KNode
            if (r0 == 0) goto L_0x0008
            java.lang.Object r5 = gnu.kawa.xml.KNode.atomicValue(r5)
        L_0x0008:
            boolean r0 = r5 instanceof gnu.mapping.Values
            if (r0 == 0) goto L_0x001e
            gnu.mapping.Values r5 = (gnu.mapping.Values) r5
            java.lang.Object[] r5 = r5.getValues()
            int r0 = r5.length
        L_0x0013:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x007e
            r1 = r5[r0]
            java.lang.Object r6 = getIDs(r1, r6)
            goto L_0x0013
        L_0x001e:
            java.lang.String r0 = "fn:id"
            r1 = 1
            java.lang.String r2 = ""
            java.lang.String r5 = gnu.xquery.util.StringUtils.coerceToString(r5, r0, r1, r2)
            int r0 = r5.length()
            r1 = 0
        L_0x002c:
            if (r1 >= r0) goto L_0x007e
            int r2 = r1 + 1
            char r1 = r5.charAt(r1)
            boolean r3 = java.lang.Character.isWhitespace(r1)
            if (r3 == 0) goto L_0x003c
            r1 = r2
            goto L_0x002c
        L_0x003c:
            boolean r1 = gnu.xml.XName.isNameStart(r1)
            if (r1 == 0) goto L_0x005b
            int r1 = r2 + -1
        L_0x0044:
            if (r2 >= r0) goto L_0x005d
            char r3 = r5.charAt(r2)
            boolean r4 = java.lang.Character.isWhitespace(r3)
            if (r4 == 0) goto L_0x0051
            goto L_0x005d
        L_0x0051:
            int r2 = r2 + 1
            if (r1 >= r0) goto L_0x0044
            boolean r3 = gnu.xml.XName.isNamePart(r3)
            if (r3 != 0) goto L_0x0044
        L_0x005b:
            r1 = r0
            goto L_0x0044
        L_0x005d:
            if (r1 >= r0) goto L_0x007b
            java.lang.String r1 = r5.substring(r1, r2)
            if (r6 != 0) goto L_0x0067
            r6 = r1
            goto L_0x007b
        L_0x0067:
            boolean r3 = r6 instanceof java.util.Stack
            if (r3 == 0) goto L_0x006f
            r3 = r6
            java.util.Stack r3 = (java.util.Stack) r3
            goto L_0x0078
        L_0x006f:
            java.util.Stack r3 = new java.util.Stack
            r3.<init>()
            r3.push(r6)
            r6 = r3
        L_0x0078:
            r3.push(r1)
        L_0x007b:
            int r1 = r2 + 1
            goto L_0x002c
        L_0x007e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.NodeUtils.getIDs(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static void id$X(Object obj, Object obj2, CallContext callContext) {
        KNode kNode = (KNode) obj2;
        NodeTree nodeTree = (NodeTree) kNode.sequence;
        KDocument kDocument = (KDocument) Nodes.root(nodeTree, kNode.ipos);
        Consumer consumer = callContext.consumer;
        Object iDs = getIDs(obj, (Object) null);
        if (iDs != null) {
            nodeTree.makeIDtableIfNeeded();
            if ((consumer instanceof PositionConsumer) && ((iDs instanceof String) || (consumer instanceof SortedNodes))) {
                idScan(iDs, nodeTree, (PositionConsumer) consumer);
            } else if (iDs instanceof String) {
                int lookupID = nodeTree.lookupID((String) iDs);
                if (lookupID != -1) {
                    consumer.writeObject(KNode.make(nodeTree, lookupID));
                }
            } else {
                SortedNodes sortedNodes = new SortedNodes();
                idScan(iDs, nodeTree, sortedNodes);
                Values.writeValues(sortedNodes, consumer);
            }
        }
    }

    private static void idScan(Object obj, NodeTree nodeTree, PositionConsumer positionConsumer) {
        if (obj instanceof String) {
            int lookupID = nodeTree.lookupID((String) obj);
            if (lookupID != -1) {
                positionConsumer.writePosition(nodeTree, lookupID);
            }
        } else if (obj instanceof Stack) {
            Stack stack = (Stack) obj;
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                idScan(stack.elementAt(i), nodeTree, positionConsumer);
            }
        }
    }

    public static Object idref(Object obj, Object obj2) {
        KNode kNode = (KNode) obj2;
        KDocument kDocument = (KDocument) Nodes.root((NodeTree) kNode.sequence, kNode.getPos());
        return Values.empty;
    }

    public static void setSavedCollection(Object obj, Object obj2, Environment environment) {
        if (obj == null) {
            obj = "#default";
        }
        environment.put(Symbol.make(collectionNamespace, obj.toString()), (Object) null, obj2);
    }

    public static void setSavedCollection(Object obj, Object obj2) {
        setSavedCollection(obj, obj2, Environment.getCurrent());
    }

    public static Object getSavedCollection(Object obj, Environment environment) {
        if (obj == null) {
            obj = "#default";
        }
        Object obj2 = environment.get(Symbol.make(collectionNamespace, obj.toString()), (Object) null, (Object) null);
        if (obj2 != null) {
            return obj2;
        }
        throw new RuntimeException("collection '" + obj + "' not found");
    }

    public static Object getSavedCollection(Object obj) {
        return getSavedCollection(obj, Environment.getCurrent());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        r6 = r2.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object collection(java.lang.Object r5, java.lang.Object r6) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "collection"
            java.lang.Object r5 = resolve(r5, r6, r0)
            gnu.mapping.Environment r6 = gnu.mapping.Environment.getCurrent()
            gnu.mapping.Symbol r0 = collectionResolverSymbol
            r1 = 0
            java.lang.Object r2 = r6.get(r0, r1, r1)
            if (r2 != 0) goto L_0x0023
            java.lang.String r2 = r0.getLocalName()
            java.lang.String r0 = r0.getPrefix()
            gnu.mapping.Symbol r0 = gnu.mapping.Symbol.makeWithUnknownNamespace(r2, r0)
            java.lang.Object r2 = r6.get(r0, r1, r1)
        L_0x0023:
            if (r2 != 0) goto L_0x002a
            java.lang.Object r5 = getSavedCollection(r5)
            return r5
        L_0x002a:
            boolean r6 = r2 instanceof java.lang.String
            java.lang.String r0 = "invalid collection-resolver: "
            if (r6 != 0) goto L_0x0034
            boolean r6 = r2 instanceof gnu.kawa.xml.UntypedAtomic
            if (r6 == 0) goto L_0x00af
        L_0x0034:
            java.lang.String r6 = r2.toString()
            r1 = 58
            int r1 = r6.indexOf(r1)
            if (r1 <= 0) goto L_0x00af
            r2 = 0
            java.lang.String r3 = r6.substring(r2, r1)
            int r1 = r1 + 1
            java.lang.String r6 = r6.substring(r1)
            java.lang.Class r1 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x0093, all -> 0x007d }
            gnu.bytecode.Type r1 = gnu.bytecode.ClassType.make(r1)
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            gnu.xquery.lang.XQuery r4 = gnu.xquery.lang.XQuery.instance
            gnu.mapping.MethodProc r2 = gnu.kawa.reflect.ClassMethods.apply(r1, r6, r2, r4)
            if (r2 == 0) goto L_0x005e
            goto L_0x00af
        L_0x005e:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "invalid collection-resolver: no method "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = " in "
            r0.append(r6)
            r0.append(r3)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        L_0x007d:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r6.<init>(r5)
            throw r6
        L_0x0093:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "invalid collection-resolver: class "
            r6.append(r0)
            r6.append(r3)
            java.lang.String r0 = " not found"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x00af:
            boolean r6 = r2 instanceof gnu.mapping.Procedure
            if (r6 == 0) goto L_0x00ba
            gnu.mapping.Procedure r2 = (gnu.mapping.Procedure) r2
            java.lang.Object r5 = r2.apply1(r5)
            return r5
        L_0x00ba:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r0)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.NodeUtils.collection(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    static Object resolve(Object obj, Object obj2, String str) throws Throwable {
        if (!(obj instanceof File) && !(obj instanceof Path) && !(obj instanceof URI) && !(obj instanceof URL)) {
            obj = StringUtils.coerceToString(obj, str, 1, (String) null);
        }
        if (obj == Values.empty || obj == null) {
            return null;
        }
        return Path.currentPath().resolve(Path.valueOf(obj));
    }

    public static Object docCached(Object obj, Object obj2) throws Throwable {
        Object resolve = resolve(obj, obj2, "doc");
        if (resolve == null) {
            return Values.empty;
        }
        return Document.parseCached(resolve);
    }

    public static boolean availableCached(Object obj, Object obj2) throws Throwable {
        Object resolve = resolve(obj, obj2, "doc-available");
        if (resolve == null) {
            return false;
        }
        try {
            Document.parseCached(resolve);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}

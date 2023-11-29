package gnu.xquery.util;

import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XStringType;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.net.URISyntaxException;

public class QNameUtils {
    public static Object resolveQNameUsingElement(Object obj, KElement kElement) {
        String str;
        String str2;
        Object atomicValue = KNode.atomicValue(obj);
        if (atomicValue == Values.empty || atomicValue == null) {
            return atomicValue;
        }
        if ((atomicValue instanceof Values) || (!(atomicValue instanceof String) && !(atomicValue instanceof UntypedAtomic))) {
            throw new RuntimeException("bad argument to QName");
        }
        String replaceWhitespace = TextUtils.replaceWhitespace(atomicValue.toString(), true);
        int indexOf = replaceWhitespace.indexOf(58);
        if (indexOf < 0) {
            str2 = null;
            str = replaceWhitespace;
        } else {
            String intern = replaceWhitespace.substring(0, indexOf).intern();
            str = replaceWhitespace.substring(indexOf + 1);
            str2 = intern;
        }
        String lookupNamespaceURI = kElement.lookupNamespaceURI(str2);
        if (lookupNamespaceURI == null) {
            if (str2 == null) {
                lookupNamespaceURI = "";
            } else {
                throw new RuntimeException("unknown namespace for '" + replaceWhitespace + "'");
            }
        }
        if (!validNCName(str) || (str2 != null && !validNCName(str2))) {
            throw new RuntimeException("invalid QName syntax '" + replaceWhitespace + "'");
        }
        if (str2 == null) {
            str2 = "";
        }
        return Symbol.make(lookupNamespaceURI, str, str2);
    }

    public static Object resolveQName(Object obj, NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        String str;
        String str2;
        Object atomicValue = KNode.atomicValue(obj);
        if (atomicValue instanceof Symbol) {
            return atomicValue;
        }
        if ((atomicValue instanceof Values) || (!(atomicValue instanceof String) && !(atomicValue instanceof UntypedAtomic))) {
            throw new RuntimeException("bad argument to QName");
        }
        String replaceWhitespace = TextUtils.replaceWhitespace(atomicValue.toString(), true);
        int indexOf = replaceWhitespace.indexOf(58);
        if (indexOf < 0) {
            str = null;
            str2 = replaceWhitespace;
        } else {
            str = replaceWhitespace.substring(0, indexOf).intern();
            str2 = replaceWhitespace.substring(indexOf + 1);
        }
        if (!validNCName(str2) || (str != null && !validNCName(str))) {
            throw new RuntimeException("invalid QName syntax '" + replaceWhitespace + "'");
        }
        String resolvePrefix = resolvePrefix(str, namespaceBinding, namespaceBinding2);
        if (str == null) {
            str = "";
        }
        return Symbol.make(resolvePrefix, str2, str);
    }

    public static String lookupPrefix(String str, NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        String resolve;
        while (true) {
            if (namespaceBinding == null) {
                resolve = namespaceBinding2.resolve(str);
                break;
            } else if (namespaceBinding.getPrefix() == str) {
                resolve = namespaceBinding.getUri();
                break;
            } else {
                namespaceBinding = namespaceBinding.getNext();
            }
        }
        return (resolve == null && str == null) ? "" : resolve;
    }

    public static String resolvePrefix(String str, NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        String lookupPrefix = lookupPrefix(str, namespaceBinding, namespaceBinding2);
        if (lookupPrefix != null) {
            return lookupPrefix;
        }
        throw new RuntimeException("unknown namespace prefix '" + str + "'");
    }

    public static boolean validNCName(String str) {
        return XName.isName(str);
    }

    public static Symbol makeQName(Object obj, String str) {
        String str2;
        String str3;
        if (obj == null || obj == Values.empty) {
            obj = "";
        }
        int indexOf = str.indexOf(58);
        String str4 = (String) obj;
        if (indexOf < 0) {
            str2 = "";
            str3 = str;
        } else {
            str3 = str.substring(indexOf + 1);
            str2 = str.substring(0, indexOf).intern();
        }
        if (!validNCName(str3) || (indexOf >= 0 && !validNCName(str2))) {
            throw new IllegalArgumentException("invalid QName syntax '" + str + "'");
        } else if (indexOf < 0 || str4.length() != 0) {
            return Symbol.make(str4, str3, str2);
        } else {
            throw new IllegalArgumentException("empty uri for '" + str + "'");
        }
    }

    public static Object localNameFromQName(Object obj) {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if (obj instanceof Symbol) {
            return XStringType.makeNCName(((Symbol) obj).getName());
        }
        throw new WrongType("local-name-from-QName", 1, obj, "xs:QName");
    }

    public static Object prefixFromQName(Object obj) {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if (obj instanceof Symbol) {
            String prefix = ((Symbol) obj).getPrefix();
            if (prefix == null || prefix.length() == 0) {
                return Values.empty;
            }
            return XStringType.makeNCName(prefix);
        }
        throw new WrongType("prefix-from-QName", 1, obj, "xs:QName");
    }

    public static Object namespaceURIFromQName(Object obj) {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        try {
            return URIPath.makeURI(((Symbol) obj).getNamespaceURI());
        } catch (ClassCastException unused) {
            throw new WrongType("namespace-uri", 1, obj, "xs:QName");
        }
    }

    public static Object namespaceURIForPrefix(Object obj, Object obj2) {
        KNode coerce = KNode.coerce(obj2);
        if (coerce != null) {
            String str = null;
            if (!(obj == null || obj == Values.empty)) {
                if ((obj instanceof String) || (obj instanceof UntypedAtomic)) {
                    String intern = obj.toString().intern();
                    if (intern != "") {
                        str = intern;
                    }
                } else {
                    throw new WrongType("namespace-uri-for-prefix", 1, obj2, "xs:string");
                }
            }
            String lookupNamespaceURI = coerce.lookupNamespaceURI(str);
            if (lookupNamespaceURI == null) {
                return Values.empty;
            }
            return lookupNamespaceURI;
        }
        throw new WrongType("namespace-uri-for-prefix", 2, obj2, "node()");
    }

    public static Object resolveURI(Object obj, Object obj2) throws URISyntaxException {
        if (obj instanceof KNode) {
            obj = KNode.atomicValue(obj);
        }
        if (obj2 instanceof KNode) {
            obj2 = KNode.atomicValue(obj2);
        }
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if (obj instanceof UntypedAtomic) {
            obj = obj.toString();
        }
        if (obj2 instanceof UntypedAtomic) {
            obj2 = obj2.toString();
        }
        Path makeURI = obj2 instanceof Path ? (Path) obj2 : URIPath.makeURI(obj2);
        if (obj instanceof Path) {
            return makeURI.resolve((Path) obj);
        }
        return makeURI.resolve(obj.toString());
    }
}

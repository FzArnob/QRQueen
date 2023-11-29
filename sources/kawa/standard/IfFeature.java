package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.SyntaxForm;

public class IfFeature {
    public static boolean testFeature(Object obj) {
        if (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if ((obj instanceof String) || (obj instanceof SimpleSymbol)) {
            return hasFeature(obj.toString());
        }
        return false;
    }

    public static boolean hasFeature(String str) {
        if (str == "kawa" || str == "srfi-0" || str == "srfi-4" || str == "srfi-6" || str == "srfi-8" || str == "srfi-9" || str == "srfi-11" || str == "srfi-16" || str == "srfi-17" || str == "srfi-23" || str == "srfi-25" || str == "srfi-26" || str == "srfi-28" || str == "srfi-30" || str == "srfi-39") {
            return true;
        }
        if (str == "in-http-server" || str == "in-servlet") {
            int flags = ModuleContext.getContext().getFlags();
            if (str == "in-http-server") {
                if ((ModuleContext.IN_HTTP_SERVER & flags) != 0) {
                    return true;
                }
                return false;
            } else if (str == "in-servlet") {
                if ((ModuleContext.IN_SERVLET & flags) != 0) {
                    return true;
                }
                return false;
            }
        }
        Declaration lookup = Compilation.getCurrent().lookup(("%provide%" + str).intern(), -1);
        return lookup != null && !lookup.getFlag(65536);
    }
}

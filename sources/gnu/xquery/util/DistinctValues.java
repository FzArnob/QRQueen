package gnu.xquery.util;

import gnu.mapping.CallContext;
import gnu.mapping.Values;

public class DistinctValues {
    public static void distinctValues$X(Object obj, NamedCollator namedCollator, CallContext callContext) {
        Values.writeValues(obj, new DistinctValuesConsumer(namedCollator, callContext.consumer));
    }
}

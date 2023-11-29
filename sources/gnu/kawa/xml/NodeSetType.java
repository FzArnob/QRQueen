package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;

public class NodeSetType extends OccurrenceType {
    public NodeSetType(Type type) {
        super(type, 0, -1);
    }

    public static Type getInstance(Type type) {
        return new NodeSetType(type);
    }

    public String toString() {
        return super.toString() + "node-set";
    }
}

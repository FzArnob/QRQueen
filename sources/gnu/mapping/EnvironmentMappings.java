package gnu.mapping;

import java.util.AbstractSet;
import java.util.Iterator;

/* compiled from: SimpleEnvironment */
class EnvironmentMappings extends AbstractSet {
    SimpleEnvironment env;

    public EnvironmentMappings(SimpleEnvironment simpleEnvironment) {
        this.env = simpleEnvironment;
    }

    public int size() {
        return this.env.size();
    }

    public Iterator iterator() {
        return new LocationEnumeration(this.env);
    }
}

package gnu.mapping;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocationEnumeration implements Iterator<Location>, Enumeration<Location> {
    NamedLocation[] bindings;
    SimpleEnvironment env;
    int index;
    LocationEnumeration inherited;
    NamedLocation nextLoc;
    NamedLocation prevLoc;

    public LocationEnumeration(NamedLocation[] namedLocationArr, int i) {
        this.bindings = namedLocationArr;
        this.index = i;
    }

    public LocationEnumeration(SimpleEnvironment simpleEnvironment) {
        this(simpleEnvironment.table, 1 << simpleEnvironment.log2Size);
    }

    public boolean hasMoreElements() {
        return this.env.hasMoreElements(this);
    }

    public Location nextElement() {
        return nextLocation();
    }

    public Location nextLocation() {
        NamedLocation namedLocation;
        if (this.nextLoc != null || hasMoreElements()) {
            if (this.prevLoc == null && this.nextLoc != (namedLocation = this.bindings[this.index])) {
                this.prevLoc = namedLocation;
            }
            while (true) {
                NamedLocation namedLocation2 = this.prevLoc;
                if (namedLocation2 == null || namedLocation2.next == this.nextLoc) {
                    NamedLocation namedLocation3 = this.nextLoc;
                    this.nextLoc = namedLocation3.next;
                } else {
                    this.prevLoc = this.prevLoc.next;
                }
            }
            NamedLocation namedLocation32 = this.nextLoc;
            this.nextLoc = namedLocation32.next;
            return namedLocation32;
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        return hasMoreElements();
    }

    public Location next() {
        return nextElement();
    }

    public void remove() {
        NamedLocation namedLocation = this.prevLoc;
        NamedLocation namedLocation2 = namedLocation != null ? namedLocation.next : this.bindings[this.index];
        if (namedLocation2 == null || namedLocation2.next != this.nextLoc) {
            throw new IllegalStateException();
        }
        namedLocation2.next = null;
        NamedLocation namedLocation3 = this.prevLoc;
        if (namedLocation3 != null) {
            namedLocation3.next = this.nextLoc;
        } else {
            this.bindings[this.index] = this.nextLoc;
        }
        SimpleEnvironment simpleEnvironment = this.env;
        simpleEnvironment.num_bindings--;
    }
}

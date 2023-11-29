package gnu.mapping;

public class LocationProc extends Procedure0or1 implements HasSetter {
    Location loc;

    public LocationProc(Location location) {
        this.loc = location;
    }

    public static LocationProc makeNamed(Symbol symbol, Location location) {
        LocationProc locationProc = new LocationProc(location);
        locationProc.setSymbol(symbol);
        return locationProc;
    }

    public LocationProc(Location location, Procedure procedure) {
        this.loc = location;
        if (procedure != null) {
            pushConverter(procedure);
        }
    }

    public void pushConverter(Procedure procedure) {
        this.loc = ConstrainedLocation.make(this.loc, procedure);
    }

    public Object apply0() throws Throwable {
        return this.loc.get();
    }

    public Object apply1(Object obj) throws Throwable {
        set0(obj);
        return Values.empty;
    }

    public void set0(Object obj) throws Throwable {
        this.loc.set(obj);
    }

    public Procedure getSetter() {
        return new Setter0(this);
    }

    public final Location getLocation() {
        return this.loc;
    }

    public String toString() {
        if (getSymbol() != null) {
            return super.toString();
        }
        return "#<location-proc " + this.loc + ">";
    }
}

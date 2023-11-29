package gnu.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Options {
    public static final int BOOLEAN_OPTION = 1;
    public static final int STRING_OPTION = 2;
    public static final String UNKNOWN = "unknown option name";
    OptionInfo first;
    HashMap<String, OptionInfo> infoTable;
    OptionInfo last;
    Options previous;
    HashMap<String, Object> valueTable;

    public static final class OptionInfo {
        Object defaultValue;
        String documentation;
        String key;
        int kind;
        OptionInfo next;
    }

    public Options() {
    }

    public Options(Options options) {
        this.previous = options;
    }

    public OptionInfo add(String str, int i, String str2) {
        return add(str, i, (Object) null, str2);
    }

    public OptionInfo add(String str, int i, Object obj, String str2) {
        HashMap<String, OptionInfo> hashMap = this.infoTable;
        if (hashMap == null) {
            this.infoTable = new HashMap<>();
        } else if (hashMap.get(str) != null) {
            throw new RuntimeException("duplicate option key: " + str);
        }
        OptionInfo optionInfo = new OptionInfo();
        optionInfo.key = str;
        optionInfo.kind = i;
        optionInfo.defaultValue = obj;
        optionInfo.documentation = str2;
        if (this.first == null) {
            this.first = optionInfo;
        } else {
            this.last.next = optionInfo;
        }
        this.last = optionInfo;
        this.infoTable.put(str, optionInfo);
        return optionInfo;
    }

    static Object valueOf(OptionInfo optionInfo, String str) {
        if ((optionInfo.kind & 1) == 0) {
            return str;
        }
        if (str == null || str.equals("1") || str.equals("on") || str.equals("yes") || str.equals("true")) {
            return Boolean.TRUE;
        }
        if (str.equals("0") || str.equals("off") || str.equals("no") || str.equals("false")) {
            return Boolean.FALSE;
        }
        return null;
    }

    private void error(String str, SourceMessages sourceMessages) {
        if (sourceMessages != null) {
            sourceMessages.error('e', str);
            return;
        }
        throw new RuntimeException(str);
    }

    public void set(String str, Object obj) {
        set(str, obj, (SourceMessages) null);
    }

    public void set(String str, Object obj, SourceMessages sourceMessages) {
        OptionInfo info = getInfo(str);
        if (info == null) {
            error("invalid option key: " + str, sourceMessages);
            return;
        }
        if ((info.kind & 1) != 0) {
            if (obj instanceof String) {
                obj = valueOf(info, (String) obj);
            }
            if (!(obj instanceof Boolean)) {
                error("value for option " + str + " must be boolean or yes/no/true/false/on/off/1/0", sourceMessages);
                return;
            }
        } else if (obj == null) {
            obj = "";
        }
        if (this.valueTable == null) {
            this.valueTable = new HashMap<>();
        }
        this.valueTable.put(str, obj);
    }

    public void reset(String str, Object obj) {
        if (this.valueTable == null) {
            this.valueTable = new HashMap<>();
        }
        if (obj == null) {
            this.valueTable.remove(str);
        } else {
            this.valueTable.put(str, obj);
        }
    }

    public String set(String str, String str2) {
        OptionInfo info = getInfo(str);
        if (info == null) {
            return UNKNOWN;
        }
        Object valueOf = valueOf(info, str2);
        if (valueOf != null || (info.kind & 1) == 0) {
            if (this.valueTable == null) {
                this.valueTable = new HashMap<>();
            }
            this.valueTable.put(str, valueOf);
            return null;
        }
        return "value of option " + str + " must be yes/no/true/false/on/off/1/0";
    }

    public OptionInfo getInfo(String str) {
        Options options;
        HashMap<String, OptionInfo> hashMap = this.infoTable;
        OptionInfo optionInfo = hashMap == null ? null : hashMap.get(str);
        if (optionInfo == null && (options = this.previous) != null) {
            optionInfo = options.getInfo(str);
        }
        return optionInfo;
    }

    public Object get(String str, Object obj) {
        OptionInfo info = getInfo(str);
        if (info != null) {
            return get(info, obj);
        }
        throw new RuntimeException("invalid option key: " + str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r1.defaultValue == null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r6 = r1.defaultValue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        r0 = r0.previous;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(gnu.text.Options.OptionInfo r5, java.lang.Object r6) {
        /*
            r4 = this;
            r0 = r4
        L_0x0001:
            if (r0 == 0) goto L_0x0027
            r1 = r5
        L_0x0004:
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = r0.valueTable
            if (r2 != 0) goto L_0x000a
            r2 = 0
            goto L_0x0010
        L_0x000a:
            java.lang.String r3 = r1.key
            java.lang.Object r2 = r2.get(r3)
        L_0x0010:
            if (r2 == 0) goto L_0x0013
            return r2
        L_0x0013:
            java.lang.Object r2 = r1.defaultValue
            boolean r2 = r2 instanceof gnu.text.Options.OptionInfo
            if (r2 == 0) goto L_0x001e
            java.lang.Object r1 = r1.defaultValue
            gnu.text.Options$OptionInfo r1 = (gnu.text.Options.OptionInfo) r1
            goto L_0x0004
        L_0x001e:
            java.lang.Object r2 = r1.defaultValue
            if (r2 == 0) goto L_0x0024
            java.lang.Object r6 = r1.defaultValue
        L_0x0024:
            gnu.text.Options r0 = r0.previous
            goto L_0x0001
        L_0x0027:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Options.get(gnu.text.Options$OptionInfo, java.lang.Object):java.lang.Object");
    }

    public Object get(OptionInfo optionInfo) {
        return get(optionInfo, (Object) null);
    }

    public Object getLocal(String str) {
        HashMap<String, Object> hashMap = this.valueTable;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(str);
    }

    public boolean getBoolean(String str) {
        return ((Boolean) get(str, (Object) Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) get(str, (Object) z ? Boolean.TRUE : Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(OptionInfo optionInfo, boolean z) {
        return ((Boolean) get(optionInfo, (Object) z ? Boolean.TRUE : Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(OptionInfo optionInfo) {
        Object obj = get(optionInfo, (Object) null);
        if (obj == null) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public void pushOptionValues(Vector vector) {
        int size = vector.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            vector.setElementAt(vector.elementAt(i2), i2);
            set((String) vector.elementAt(i), vector.elementAt(i3));
            i = i3 + 1;
        }
    }

    public void popOptionValues(Vector vector) {
        int size = vector.size();
        while (true) {
            size -= 3;
            if (size >= 0) {
                int i = size + 1;
                Object elementAt = vector.elementAt(i);
                vector.setElementAt((Object) null, i);
                reset((String) vector.elementAt(size), elementAt);
            } else {
                return;
            }
        }
    }

    public ArrayList<String> keys() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Options options = this; options != null; options = options.previous) {
            HashMap<String, OptionInfo> hashMap = options.infoTable;
            if (hashMap != null) {
                for (String next : hashMap.keySet()) {
                    if (!arrayList.contains(next)) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    public String getDoc(String str) {
        OptionInfo info = getInfo(str);
        if (str == null) {
            return null;
        }
        return info.documentation;
    }
}

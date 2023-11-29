package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.FString;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class YailDictionary extends LinkedHashMap<Object, Object> implements YailObject<YailList> {
    public static final Object ALL = new Object() {
        public final String toString() {
            return "ALL_ITEMS";
        }
    };

    public YailDictionary() {
    }

    public YailDictionary(Map<Object, Object> map) {
        super(map);
    }

    public static YailDictionary makeDictionary() {
        return new YailDictionary();
    }

    public static YailDictionary makeDictionary(Map<Object, Object> map) {
        return new YailDictionary(map);
    }

    public static YailDictionary makeDictionary(Object... objArr) {
        if (objArr.length % 2 != 1) {
            YailDictionary yailDictionary = new YailDictionary();
            for (int i = 0; i < objArr.length; i += 2) {
                yailDictionary.put(objArr[i], objArr[i + 1]);
            }
            return yailDictionary;
        }
        throw new IllegalArgumentException("Expected an even number of key-value entries.");
    }

    public static YailDictionary makeDictionary(List<YailList> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (YailList next : list) {
            Object object = next.getObject(0);
            Object object2 = next.getObject(1);
            if (object2 instanceof YailList) {
                YailList yailList = (YailList) object2;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList).booleanValue()) {
                    linkedHashMap.put(object, alistToDict(yailList));
                } else {
                    linkedHashMap.put(object, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList));
                }
            } else {
                linkedHashMap.put(object, object2);
            }
        }
        return new YailDictionary(linkedHashMap);
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static Boolean m15hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        Iterator it = ((LList) yailList.getCdr()).iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof YailList)) {
                return Boolean.FALSE;
            }
            if (((YailList) next).size() != 2) {
                return Boolean.FALSE;
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static YailDictionary alistToDict(YailList yailList) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            YailList yailList2 = (YailList) it.next();
            Object object = yailList2.getObject(0);
            Object object2 = yailList2.getObject(1);
            boolean z = object2 instanceof YailList;
            if (z) {
                YailList yailList3 = (YailList) object2;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList3).booleanValue()) {
                    linkedHashMap.put(object, alistToDict(yailList3));
                }
            }
            if (z) {
                linkedHashMap.put(object, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) object2));
            } else {
                linkedHashMap.put(object, object2);
            }
        }
        return new YailDictionary(linkedHashMap);
    }

    private static YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        Object[] objArr = new Object[yailList.size()];
        Iterator it = yailList.iterator();
        it.next();
        boolean z = false;
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof YailList) {
                YailList yailList2 = (YailList) next;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList2).booleanValue()) {
                    objArr[i] = alistToDict(yailList2);
                } else {
                    YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList2);
                    objArr[i] = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == next) {
                    }
                }
                z = true;
            } else {
                objArr[i] = next;
            }
            i++;
        }
        return z ? YailList.makeList(objArr) : yailList;
    }

    public static YailList dictToAlist(YailDictionary yailDictionary) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : yailDictionary.entrySet()) {
            arrayList.add(YailList.makeList(new Object[]{entry.getKey(), entry.getValue()}));
        }
        return YailList.makeList((List) arrayList);
    }

    public void setPair(YailList yailList) {
        put(yailList.getObject(0), yailList.getObject(1));
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static Object m16hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<?> list, Object obj) {
        boolean z = !(list instanceof YailList);
        try {
            if (obj instanceof FString) {
                return list.get(Integer.parseInt(obj.toString()) - z);
            }
            if (obj instanceof String) {
                return list.get(Integer.parseInt((String) obj) - z);
            }
            if (obj instanceof Number) {
                return list.get(((Number) obj).intValue() - (z ? 1 : 0));
            }
            return null;
        } catch (NumberFormatException e) {
            Log.w("YailDictionary", "Unable to parse key as integer: ".concat(String.valueOf(obj)), e);
            throw new YailRuntimeError("Unable to parse key as integer: ".concat(String.valueOf(obj)), "NumberParseException");
        } catch (IndexOutOfBoundsException e2) {
            Log.w("YailDictionary", "Requested too large of an index: ".concat(String.valueOf(obj)), e2);
            throw new YailRuntimeError("Requested too large of an index: ".concat(String.valueOf(obj)), "IndexOutOfBoundsException");
        }
    }

    public Object getObjectAtKeyPath(List<?> list) {
        Object obj;
        Object obj2 = this;
        for (Object next : list) {
            if (obj2 instanceof Map) {
                obj = ((Map) obj2).get(next);
            } else {
                if (obj2 instanceof YailList) {
                    YailList yailList = (YailList) obj2;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList).booleanValue()) {
                        obj = alistToDict(yailList).get(next);
                    }
                }
                if (!(obj2 instanceof List)) {
                    return null;
                }
                obj = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj2, (Object) next);
            }
            obj2 = obj;
        }
        return obj2;
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList, Object obj) {
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof YailList)) {
                break;
            }
            YailList yailList2 = (YailList) next;
            if (yailList2.getObject(0).equals(obj)) {
                return yailList2.getObject(1);
            }
        }
        return null;
    }

    private static <T> List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, List<T> list, List<Object> list2) {
        Collection<Object> collection;
        if (list.isEmpty()) {
            if (obj != null) {
                list2.add(obj);
            }
            return list2;
        } else if (obj == null) {
            return list2;
        } else {
            T t = list.get(0);
            List<T> subList = list.subList(1, list.size());
            if (t == ALL) {
                if (obj instanceof Map) {
                    collection = ((Map) obj).values();
                } else if (obj instanceof List) {
                    collection = (List) obj;
                    if (collection instanceof YailList) {
                        YailList yailList = (YailList) collection;
                        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList).booleanValue()) {
                            ArrayList arrayList = new ArrayList();
                            Iterator it = ((LList) yailList.getCdr()).iterator();
                            while (it.hasNext()) {
                                arrayList.add(((YailList) it.next()).getObject(1));
                            }
                            collection = arrayList;
                        } else {
                            collection = (Collection) yailList.getCdr();
                        }
                    }
                } else {
                    collection = Collections.emptyList();
                }
                for (Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME : collection) {
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, subList, list2);
                }
            } else if (obj instanceof Map) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(((Map) obj).get(t), subList, list2);
            } else {
                if (obj instanceof YailList) {
                    YailList yailList2 = (YailList) obj;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList2).booleanValue()) {
                        Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList2, (Object) t);
                        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, subList, list2);
                        }
                    }
                }
                if (obj instanceof List) {
                    try {
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(((List) obj).get(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj, (Object) t)), subList, list2);
                    } catch (Exception unused) {
                    }
                }
            }
            return list2;
        }
    }

    public static <T> List<Object> walkKeyPath(YailObject<?> yailObject, List<T> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailObject, list, new ArrayList());
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<?> list, Object obj) {
        int i;
        boolean z = !(list instanceof YailList);
        if (obj instanceof Number) {
            i = ((Number) obj).intValue();
        } else {
            try {
                i = Integer.parseInt(obj.toString());
            } catch (NumberFormatException unused) {
                throw new DispatchableError(ErrorMessages.ERROR_NUMBER_FORMAT_EXCEPTION, obj.toString());
            }
        }
        int i2 = i - (z ? 1 : 0);
        if (i2 >= 0 && i2 < (list.size() + 1) - z) {
            return i2;
        }
        try {
            throw new DispatchableError(ErrorMessages.ERROR_INDEX_MISSING_IN_LIST, Integer.valueOf(i2 + z), JsonUtil.getJsonRepresentation(list));
        } catch (JSONException e) {
            Log.e("YailDictionary", "Unable to serialize object as JSON", e);
            throw new YailRuntimeError(e.getMessage(), "JSON Error");
        }
    }

    public void setValueForKeyPath(List<?> list, Object obj) {
        Object obj2;
        String str;
        Iterator<?> it = list.iterator();
        if (!list.isEmpty()) {
            Object obj3 = this;
            while (it.hasNext()) {
                Object next = it.next();
                if (it.hasNext()) {
                    if (obj3 instanceof YailDictionary) {
                        obj2 = ((YailDictionary) obj3).get(next);
                    } else if (obj3 instanceof List) {
                        List list2 = (List) obj3;
                        obj2 = list2.get(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) list2, (Object) next));
                    } else {
                        Object[] objArr = new Object[1];
                        if (obj3 == null) {
                            str = "null";
                        } else {
                            str = obj3.getClass().getSimpleName();
                        }
                        objArr[0] = str;
                        throw new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH, objArr);
                    }
                } else if (obj3 instanceof YailDictionary) {
                    ((YailDictionary) obj3).put(next, obj);
                    obj2 = obj3;
                } else if (obj3 instanceof YailList) {
                    ((LList) obj3).getIterator(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj3, (Object) next)).set(obj);
                    obj2 = obj3;
                } else if (obj3 instanceof List) {
                    List list3 = (List) obj3;
                    list3.set(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) list3, (Object) next), obj);
                    obj2 = obj3;
                } else {
                    throw new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH);
                }
                obj3 = obj2;
            }
        }
    }

    public boolean containsKey(Object obj) {
        if (obj instanceof FString) {
            return super.containsKey(obj.toString());
        }
        return super.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        if (obj instanceof FString) {
            return super.containsValue(obj.toString());
        }
        return super.containsValue(obj);
    }

    public Object get(Object obj) {
        if (obj instanceof FString) {
            return super.get(obj.toString());
        }
        return super.get(obj);
    }

    public Object put(Object obj, Object obj2) {
        if (obj instanceof FString) {
            obj = obj.toString();
        }
        if (obj2 instanceof FString) {
            obj2 = obj2.toString();
        }
        return super.put(obj, obj2);
    }

    public Object remove(Object obj) {
        if (obj instanceof FString) {
            return super.remove(obj.toString());
        }
        return super.remove(obj);
    }

    public String toString() {
        try {
            return JsonUtil.getJsonRepresentation(this);
        } catch (JSONException e) {
            throw new YailRuntimeError(e.getMessage(), "JSON Error");
        }
    }

    public Object getObject(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        for (Map.Entry entry : entrySet()) {
            if (i == 0) {
                return Lists.newArrayList(entry.getKey(), entry.getValue());
            }
            i--;
        }
        throw new IndexOutOfBoundsException();
    }

    public Iterator<YailList> iterator() {
        return new a(entrySet().iterator());
    }

    static class a implements Iterator<YailList> {
        private Iterator<Map.Entry<Object, Object>> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        a(Iterator<Map.Entry<Object, Object>> it) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = it;
        }

        public final boolean hasNext() {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.hasNext();
        }

        public final void remove() {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
        }

        public final /* synthetic */ Object next() {
            Map.Entry next = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.next();
            return YailList.makeList(new Object[]{next.getKey(), next.getValue()});
        }
    }
}

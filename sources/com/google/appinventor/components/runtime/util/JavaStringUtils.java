package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStringUtils {
    private static final c B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new b((byte) 0);
    public static final String LOG_TAG_JOIN_STRINGS = "JavaJoinListOfStrings";
    private static final c hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new c((byte) 0);

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static final Comparator<d> f295hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new e((byte) 0);
    private static final c wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new a((byte) 0);

    static class c {
        public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<String> list, String str) {
        }

        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }
    }

    static class b extends c {
        private b() {
            super((byte) 0);
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<String> list, String str) {
            Collections.sort(list, new Comparator<String>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return Integer.compare(((String) obj2).length(), ((String) obj).length());
                }
            });
        }
    }

    static class a extends c {
        private a() {
            super((byte) 0);
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<String> list, String str) {
            final HashMap hashMap = new HashMap();
            for (String next : list) {
                int indexOf = str.indexOf(next);
                if (indexOf == -1) {
                    indexOf = str.length() + hashMap.size();
                }
                hashMap.put(next, Integer.valueOf(indexOf));
            }
            Collections.sort(list, new Comparator<String>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    String str = (String) obj;
                    String str2 = (String) obj2;
                    int intValue = ((Integer) hashMap.get(str)).intValue();
                    int intValue2 = ((Integer) hashMap.get(str2)).intValue();
                    if (intValue == intValue2) {
                        return Integer.compare(str2.length(), str.length());
                    }
                    return Integer.compare(intValue, intValue2);
                }
            });
        }
    }

    static class d {
        int KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw;
        String iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
        int siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL;

        public d(int i, int i2, String str) {
            this.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL = i;
            this.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw = i2;
            this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = str;
        }
    }

    static class e implements Comparator<d> {
        private e() {
        }

        /* synthetic */ e(byte b) {
            this();
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            d dVar = (d) obj;
            d dVar2 = (d) obj2;
            if (Math.max(dVar.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL, dVar2.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL) < Math.min(dVar.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw, dVar2.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw)) {
                return 0;
            }
            return Integer.compare(dVar2.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw, dVar.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw);
        }
    }

    public static String replaceAllMappingsDictionaryOrder(String str, Map<Object, Object> map) {
        return replaceAllMappings(str, map, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public static String replaceAllMappingsLongestStringOrder(String str, Map<Object, Object> map) {
        return replaceAllMappings(str, map, B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    public static String replaceAllMappingsEarliestOccurrenceOrder(String str, Map<Object, Object> map) {
        return replaceAllMappings(str, map, wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
    }

    public static String replaceAllMappings(String str, Map<Object, Object> map, c cVar) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            String obj = next.getKey().toString();
            String obj2 = next.getValue().toString();
            if (!hashMap.containsKey(obj)) {
                arrayList.add(obj);
            }
            hashMap.put(obj, obj2);
        }
        cVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(arrayList, str);
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, hashMap, arrayList);
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Map<String, String> map, List<String> list) {
        TreeSet treeSet = new TreeSet(f295hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        for (String next : list) {
            Matcher matcher = Pattern.compile(Pattern.quote(next)).matcher(str);
            String str2 = map.get(next);
            while (matcher.find()) {
                treeSet.add(new d(matcher.start(), matcher.end(), str2));
            }
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            str = str.substring(0, dVar.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL) + dVar.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik + str.substring(dVar.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw);
        }
        return str;
    }

    public static String joinStrings(List<Object> list, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object next : list) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(next.toString());
        }
        return sb.toString();
    }
}

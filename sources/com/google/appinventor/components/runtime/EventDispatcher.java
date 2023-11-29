package com.google.appinventor.components.runtime;

import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventDispatcher {
    private static final Map<HandlesEventDispatching, b> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new HashMap();

    static final class a {
        final String I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q;
        final String PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR;

        /* synthetic */ a(String str, String str2, byte b) {
            this(str, str2);
        }

        private a(String str, String str2) {
            this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = str;
            this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR.equals(aVar.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR) && this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q.equals(aVar.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q);
        }

        public final int hashCode() {
            return (this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q.hashCode() * 31) + this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR.hashCode();
        }
    }

    static final class b {
        private final HandlesEventDispatching hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        final HashMap<String, Set<a>> f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap<>();

        b(HandlesEventDispatching handlesEventDispatching) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = handlesEventDispatching;
        }
    }

    private EventDispatcher() {
    }

    /* access modifiers changed from: private */
    public static b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HandlesEventDispatching handlesEventDispatching) {
        Map<HandlesEventDispatching, b> map = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
        b bVar = map.get(handlesEventDispatching);
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(handlesEventDispatching);
        map.put(handlesEventDispatching, bVar2);
        return bVar2;
    }

    public static void registerEventForDelegation(HandlesEventDispatching handlesEventDispatching, String str, String str2) {
        b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(handlesEventDispatching);
        Set set = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str2);
        if (set == null) {
            set = new HashSet();
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(str2, set);
        }
        set.add(new a(str, str2, (byte) 0));
    }

    public static void unregisterEventForDelegation(HandlesEventDispatching handlesEventDispatching, String str, String str2) {
        Set<a> set = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(handlesEventDispatching).f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str2);
        if (set != null && !set.isEmpty()) {
            HashSet<a> hashSet = new HashSet<>();
            for (a aVar : set) {
                if (aVar.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR.equals(str)) {
                    hashSet.add(aVar);
                }
            }
            for (a remove : hashSet) {
                set.remove(remove);
            }
        }
    }

    public static void unregisterAllEventsForDelegation() {
        for (b bVar : hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.values()) {
            bVar.f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clear();
        }
    }

    public static boolean dispatchEvent(Component component, String str, Object... objArr) {
        Object[] optionListsFromValues = OptionHelper.optionListsFromValues(component, str, objArr);
        HandlesEventDispatching dispatchDelegate = component.getDispatchDelegate();
        boolean z = false;
        if (dispatchDelegate.canDispatchEvent(component, str)) {
            Set set = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(dispatchDelegate).f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str);
            if (set != null && set.size() > 0) {
                z = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(dispatchDelegate, set, component, optionListsFromValues);
            }
            dispatchDelegate.dispatchGenericEvent(component, str, !z, optionListsFromValues);
        }
        return z;
    }

    public static void dispatchEvent(View view, final Component component, final String str, final Object... objArr) {
        view.post(new Runnable() {
            public final void run() {
                HandlesEventDispatching dispatchDelegate = component.getDispatchDelegate();
                if (dispatchDelegate.canDispatchEvent(component, str)) {
                    Set set = EventDispatcher.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(dispatchDelegate).f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str);
                    dispatchDelegate.dispatchGenericEvent(component, str, !((set == null || set.size() <= 0) ? false : EventDispatcher.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(dispatchDelegate, set, component, objArr)), objArr);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HandlesEventDispatching handlesEventDispatching, Set<a> set, Component component, Object... objArr) {
        boolean z = false;
        for (a next : set) {
            if (handlesEventDispatching.dispatchEvent(component, next.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR, next.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q, objArr)) {
                z = true;
            }
        }
        return z;
    }

    public static String makeFullEventName(String str, String str2) {
        return str + '$' + str2;
    }

    public static void removeDispatchDelegate(HandlesEventDispatching handlesEventDispatching) {
        b remove = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.remove(handlesEventDispatching);
        if (remove != null) {
            remove.f88hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clear();
        }
    }
}

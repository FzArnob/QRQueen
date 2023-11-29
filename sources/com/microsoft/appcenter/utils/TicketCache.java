package com.microsoft.appcenter.utils;

import java.util.HashMap;
import java.util.Map;

public class TicketCache {
    private static final Map<String, String> sTickets = new HashMap();

    public static String getTicket(String str) {
        return sTickets.get(str);
    }

    public static void putTicket(String str, String str2) {
        sTickets.put(str, str2);
    }

    public static void clear() {
        sTickets.clear();
    }
}

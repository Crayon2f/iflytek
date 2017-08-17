package com.ivan.iflytek.common;

import java.text.SimpleDateFormat;

public class DebugLog {

    public static void Log(String tag, String log) {

            System.out.println(log);
    }

    public static void Log(String log) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new java.util.Date());

            System.out.println("<" + date + ">" + log);
    }

    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        if (string.isEmpty()) {
            return true;
        }
        return false;
    }
}

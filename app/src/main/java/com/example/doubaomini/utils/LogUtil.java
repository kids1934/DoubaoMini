package com.example.doubaomini.utils;

import android.util.Log;

/**
 * log输出工具类
 */
public class LogUtil {

    private static final int SEGMENT_SIZE = 3 * 1024;


    public static void d(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        long length = msg.length();
        if (length > SEGMENT_SIZE) {
            while (msg.length() > SEGMENT_SIZE) {
                String logContent = msg.substring(0, SEGMENT_SIZE);
                msg = msg.replace(logContent, "");
                Log.d(tag, logContent);
            }
        }
        Log.d(tag, msg);
    }


    public static void i(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        long length = msg.length();
        if (length > SEGMENT_SIZE) {
            while (msg.length() > SEGMENT_SIZE) {
                String logContent = msg.substring(0, SEGMENT_SIZE);
                msg = msg.replace(logContent, "");
                Log.i(tag, logContent);
            }
        }
        Log.i(tag, msg);
    }

    /**
     * 截断输出日志
     *
     * @param msg log
     */
    public static void w(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        long length = msg.length();
        if (length > SEGMENT_SIZE) {
            while (msg.length() > SEGMENT_SIZE) {
                String logContent = msg.substring(0, SEGMENT_SIZE);
                msg = msg.replace(logContent, "");
                Log.w(tag, logContent);
            }
        }
        Log.w(tag, msg);
    }


    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }

        long length = msg.length();
        if (length > SEGMENT_SIZE) {
            while (msg.length() > SEGMENT_SIZE) {
                String logContent = msg.substring(0, SEGMENT_SIZE);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
        }
        Log.e(tag, msg);
    }
}

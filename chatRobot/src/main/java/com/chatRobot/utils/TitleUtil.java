package com.chatRobot.utils;

/**
 * Created by Administrator on 2017/12/18.一个字符串包含另一个字符串的去除
 */
public class TitleUtil {
    public static String removeComomTitle(String a,String b){
        int length = Math.min(a.length(), b.length());
        int pos = 0;
        while (pos < length) {
            if (0 != (a.charAt(pos) ^ b.charAt(pos))) {
                break;
            }
            pos++;
        }
        return b.substring(pos);
    }
}

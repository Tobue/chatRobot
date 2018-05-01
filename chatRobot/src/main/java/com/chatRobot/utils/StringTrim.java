package com.chatRobot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 去除一段字符串多个空格的工具类
 */
public class StringTrim {

	public static String replaceBlank(String str) {
        String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
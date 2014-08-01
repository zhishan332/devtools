package com.yermoon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Html工具类
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
public class HtmlUtils {
    /**
     * 功能：去掉所有的<*>标记,去除html标签
     *
     * @param content
     * @return
     */
    public static String removeTagFromText(String content) {
        Pattern p = null;
        Matcher m = null;
        String value = null;

        // 去掉<>标签
        p = Pattern.compile("(<[^>]*>)");
        m = p.matcher(content);
        String temp = content;
        while (m.find()) {
            value = m.group(0);
            temp = temp.replace(value, "");
        }

        // 去掉换行或回车符号
        p = Pattern.compile("(/r+|/n+)");
        m = p.matcher(temp);
        while (m.find()) {
            value = m.group(0);
            temp = temp.replace(value, " ");
            // System.out.println("....." + value);
        }

        return temp;
    }
}

package com.yermoon.dao;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-23 下午4:37
 */
public class Test {
    /**
     * 判断是否是整数
     *
     * @param str
     * @return
     */
    public static boolean isInt(String str) {
        if (str == null || "".equals(str.trim())) return false;
        try {
            int val = Integer.parseInt(str.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(Test.isInt("-1a"));
    }
}

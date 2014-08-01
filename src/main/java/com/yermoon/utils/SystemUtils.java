package com.yermoon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 操作系统的一些工具方法
 *
 * @author wangqing
 * @since 14-4-16 下午1:42
 */
public class SystemUtils {

    /**
     * Win32下执行命令，并返回执行结果
     *
     * @param cmd
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String exeInWin32(String cmd) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        if (cmd == null || cmd.trim().equals("")) return null;
        Process p = Runtime.getRuntime().exec(cmd.trim());
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
        return sb.toString();
    }
}

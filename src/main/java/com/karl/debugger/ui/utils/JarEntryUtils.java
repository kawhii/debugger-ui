package com.karl.debugger.ui.utils;

import java.util.jar.JarEntry;

/**
 * 采用springboot的jar包读取工具
 *
 * @author karl
 * @date 2018/8/6
 */
public class JarEntryUtils {
    /**
     * spring boot的classes前缀
     */
    public static final String PREFIX = "BOOT-INF/classes/";

    /**
     * 根据jar条目获取文件名称
     * @param jarEntry
     * @return
     */
    public static String getName(JarEntry jarEntry) {
        //截止名称开始索引，结束索引，名称长度
        int startIndex, endIndex, nameLength = jarEntry.getName().length();
        String endWith = "/";
        //若最后以目录结尾则切割/
        if (jarEntry.isDirectory() || jarEntry.getName().endsWith(endWith)) {
            endIndex = nameLength - 1;
            String name = jarEntry.getName().substring(0, endIndex);
            startIndex = name.lastIndexOf("/");
        } else {
            //正常切割文件名
            startIndex = jarEntry.getName().lastIndexOf("/");
            endIndex = nameLength;
        }
        return jarEntry.getName().substring(startIndex + 1, endIndex);
    }

    /**
     * 获取路径
     * @param jarEntry
     * @return
     */
    public static String getPath(JarEntry jarEntry) {
        if (jarEntry.isDirectory()) {
            return jarEntry.getName().substring(PREFIX.length(), jarEntry.getName().length() - 1);
        }
        return jarEntry.getName().substring(PREFIX.length());
    }
}

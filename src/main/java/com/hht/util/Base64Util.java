/**
 * Project Name:cluster-quartz
 * File Name:Base64Util.java
 * Package Name:com.hht.util
 * Date:2018年12月8日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author zhangguokang
 *
 * @description
 */
public class Base64Util {

    // 将 file 转化为 Base64
    public static String fileToBase64(String path) {
        File file = new File(path);
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return new BASE64Encoder().encode(buffer);
        } catch (Exception e) {
            throw new RuntimeException("文件路径无效\n" + e.getMessage());
        }
    }

    // 将 base64 转化为 file
    public static boolean base64ToFile(String base64, String path) {
        byte[] buffer;
        try {
            buffer = new BASE64Decoder().decodeBuffer(base64);

            FileOutputStream out = new FileOutputStream(path);
            out.write(buffer);
            out.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileToBase64 = fileToBase64("D:\\常用工具\\mysql-5.7.17.msi");
        System.out.println(fileToBase64);
        base64ToFile(fileToBase64, "E:\\mysql-5.7.17.msi");
    }

}

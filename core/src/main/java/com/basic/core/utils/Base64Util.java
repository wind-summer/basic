package com.basic.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

/**
 * <p>
 *  base64工具类
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/15
 */
public class Base64Util {

    /**
     * 文件转base64字符串
     * @param file
     * @return
     */
    public static String fileToBase64(MultipartFile file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = file.getInputStream();
            byte[] bytes=file.getBytes();
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    /**
     * base64字符串转byes[]
     * @param base64
     * @return
     */
    public static byte[] base64ToBytes(String base64){
        byte[] bytes = Base64.getDecoder().decode(base64);
        return bytes;
    }

    public static void base64ToFile(String destPath,String base64, String fileName) {

        File file = null;
        //创建文件目录
        String filePath=destPath;
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file= new File(filePath+"/"+fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

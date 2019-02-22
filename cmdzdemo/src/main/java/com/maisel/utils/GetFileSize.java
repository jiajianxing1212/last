package com.maisel.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;

/**
 * @aucthor:jjx
 * @Create:2019-01-08 8:57
 */
public class GetFileSize {
    public static void main(String[] args) throws Exception {

        File f = new File("D:\\testPoi\\123.pdf");
        long length = f.length();
        System.out.println(length);
        double fileSize = (double)length;
        String size = null;
        BigDecimal bigDecimal = null;
        BigDecimal bd2 = null;

        if (f.exists() && f.isFile()) {
            /*FileInputStream fis = new FileInputStream(f);
            FileChannel fc = fis.getChannel();*/

            bigDecimal = new BigDecimal(fileSize / (float) 1024);
            bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

            System.out.println(bd2);
            size = fileSize + "b";
            if (fileSize >= 1024) {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//kb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "kb";
            } else if (fileSize >= 1024) {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//mb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "mb";
            } else {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//Gb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "gb";
            }
            System.out.println(fileSize);
            System.out.println(size);
        } else {
            System.out.println("文件不存在或者所选择的不是个文件");
        }


    }
}

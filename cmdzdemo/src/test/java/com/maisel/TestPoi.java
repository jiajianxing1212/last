package com.maisel;

import com.maisel.dao.GuruDao;
import com.maisel.entity.Guru;
import com.maisel.annotation.ExcelName;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-01-07 11:12
 */

public class TestPoi extends TestApp {
    @Autowired
    private GuruDao guruDao;

    @Test
    public void test1() throws Exception {
        // 1.准备好数据
        List<String> list = Arrays.asList("ID", "Name", "Sex", "Age", "Height");
        // 2.创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 3.创建表对象
        HSSFSheet sheet = workbook.createSheet();
        // 4.创建行对象
        HSSFRow row = sheet.createRow(0);
        // 5.创建单元格对象

        for (int i = 0; i < list.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(list.get(i));
        }
        // 6.将数据写入到表中
        // 7.将工作簿写到磁盘中
        workbook.write(new FileOutputStream(new File("d://testPoi//userPoi.xls")));
    }
    @Test
    public void test2() throws Exception{

        // 1.准备好数据
        List<String> list1 = Arrays.asList("ID", "Name", "Nickname", "GuruImage", "GuruStatus");
        List<Guru> list = guruDao.selectList(null);

        // 2.创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 3.创建表对象
        HSSFSheet sheet = workbook.createSheet("guru");
        HSSFFont font = workbook.createFont();
        font.setColor((short)10);// 颜色
        font.setFontHeightInPoints((short)5);// 字号
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//加粗
        font.setFontName("微软雅黑");// 字体
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        // 4.创建 *标题* 行，并填入数据
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < list1.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list1.get(i));
        }

        // 5.将数据写入到其他行中
        for (int i = 0; i < list.size(); i++) {
            // 由于第一行为标题，所以该数据从第二行开始
            HSSFRow row1 = sheet.createRow(i + 1);
            // 创建第一列
            HSSFCell cell1 = row1.createCell(0);
            // 为第一列填入数据：上师id
            cell1.setCellValue(list.get(i).getGuruId());

            // 创建第二列
            HSSFCell cell2 = row1.createCell(1);
            // 为第一列填入数据：上师名字
            cell2.setCellValue(list.get(i).getGuruName());

            // 创建第3列
            HSSFCell cell3 = row1.createCell(2);
            // 为第一列填入数据：上师昵称
            cell3.setCellValue(list.get(i).getGuruNickname());

            // 创建第4列
            HSSFCell cell4 = row1.createCell(3);
            // 为第一列填入数据：上师图片
            cell4.setCellValue(list.get(i).getGuruImage());

            // 创建第5列
            HSSFCell cell5 = row1.createCell(4);
            // 为第一列填入数据：上师状态
            cell5.setCellValue(list.get(i).getGuruStatus());
        }

        // 将工作表导出到本地磁盘
        workbook.write(new FileOutputStream(new File("d://testPoi//guruAll.xls")));
    }

    @Test
    public void testFile() throws Exception {
        File file = new File("e://testPoi1//use.txt");
        boolean directory = file.isDirectory();
        System.out.println(directory);
        if (!directory) {
            file.mkdir();
        } else {
            throw new Exception("cuowu");
        }

    }
    @Test
    public void testAnnotation() throws Exception{
        // 准备数据
        List<Guru> guruList = guruDao.selectList(null);
        // 1.创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2.创建sheet表
        HSSFSheet sheet = workbook.createSheet("guru");
        // 3.创建并写入标题栏数据
        HSSFRow titleRow = sheet.createRow(0);

        /*********** 优化标题栏数据开始 *********************/
        // 1.通过反射得到类对象
        Class<? extends Guru> guruClass = guruList.get(0).getClass();
        // 2.拿到类对象属性数组
        Field[] guruClassDeclaredFields = guruClass.getDeclaredFields();
        // 3.遍历拿到对应注解的值
        for (int i = 0; i < guruClassDeclaredFields.length; i++) {
            // 4.通过反射拿到注解对象
            ExcelName annotation = guruClassDeclaredFields[i].getAnnotation(ExcelName.class);
            // 5.拿到注解的值
            String name = annotation.name();
            // 6.创建单元格
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(name);
        }
        /*********** 优化标题栏数据结束 *********************/

        // 4.填入guru对象的数据
        for (int i = 0; i < guruList.size(); i++) {
            // 创建行对象
            HSSFRow row = sheet.createRow(i+1);
            // 通过反射拿到类对象
            Class<? extends Guru> guruClass1 = guruList.get(i).getClass();
            Field[] fieldsName = guruClass1.getDeclaredFields();
            for (int j = 0; j < fieldsName.length; j++) {
                String name = fieldsName[j].getName();
                String getName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Object invoke = guruClass1.getDeclaredMethod(getName, null).invoke(guruList.get(j), null);
                HSSFCell rowCell = row.createCell(j);
                if(invoke instanceof Integer){
                    rowCell.setCellValue((Integer) invoke);
                }else if(invoke instanceof Date){
                    rowCell.setCellValue((Date) invoke);
                }else {
                    rowCell.setCellValue((String) invoke);
                }
            }


            workbook.write(new FileOutputStream(new File("d://testPoi//guruAll.xls")));


        }

    }


}

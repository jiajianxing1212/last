package com.maisel;

import com.maisel.entity.Guru;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

/**
 * @aucthor:jjx
 * @Create:2019-01-07 17:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
public class TestPoiFan {
    @Test
    public void test1()throws Exception{

        // 1.读取数据
        FileInputStream fileInputStream = new FileInputStream("d://testPoi//guruAll.xls");
        // 2.处理流中的数据
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        // 3.从工作簿中取出表对象
        HSSFSheet guruSheet = workbook.getSheet("guru");
        // 不取标题栏
        int lastRowNum = guruSheet.getLastRowNum();
        for (int i = 1; i <=lastRowNum ; i++) {
        // 4.从表对象中取出行对象
            HSSFRow row = guruSheet.getRow(i);
        // 5.从行对象中取出单元格对象，取出值
        // 6.封装成上师对象
            Guru guru = new Guru();
            guru.setGuruId((int) row.getCell(0).getNumericCellValue());
            guru.setGuruName(row.getCell(1).getStringCellValue());
            guru.setGuruNickname(row.getCell(2).getStringCellValue());
            guru.setGuruImage(row.getCell(3).getStringCellValue());
            guru.setGuruStatus((int) row.getCell(4).getNumericCellValue());
            System.out.println(guru);
        }
        // 7.插入数据库中
    }
}

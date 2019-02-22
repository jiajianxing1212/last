package com.maisel;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maisel.dao.GuruDao;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.maisel.dao.UserDao;
import com.maisel.entity.Guru;
import com.maisel.entity.User;
import com.maisel.utils.PlaceUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-08 11:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
public class GetUserTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private GuruDao guruDao;
    @Test
    public void test1() {

        for (int i = 0; i < 10000; i++) {
            User user = PlaceUtil.getUser();
            int insert = userDao.insert(user);
        }
    }
    @Test
    public void test2()throws Exception{
        List<Guru> guruList = guruDao.selectList(null);
        // 1.定义导出的相关参数
        ExportParams exportParams = new ExportParams("上师信息", "guru", ExcelType.HSSF);
        // 2.创建workbook对象
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Guru.class, guruList);

        workbook.write(new FileOutputStream(new File("easypoiguru.xls")));


    }
    @Test
    public void test3()throws Exception{
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);

        // 使用工具类读取文件
        List<Guru> objectList = ExcelImportUtil.importExcel(new File("easypoiguru.xls"), Guru.class, importParams);
        for (Guru guru : objectList) {
            System.out.println(guru);
        }
    }
    @Test
    public void test4(){
        /*int i = userDao.getCountByWeekendOfRegister1();
        System.out.println(i);*/
        List<Map<String, Object>> countByArea = userDao.getCountByArea();
        for (Map<String, Object> stringObjectMap : countByArea) {
            for (String s : stringObjectMap.keySet()) {
                System.out.println(s);
                System.out.println(stringObjectMap.get(s));
            }
        }
    }
}

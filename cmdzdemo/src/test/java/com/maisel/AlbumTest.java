package com.maisel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.maisel.dao.AlbumDao;
import com.maisel.entity.Album;
import com.maisel.service.AlbumService;
import com.maisel.service.AudioService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-08 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
public class AlbumTest {

    @Autowired
    private AudioService audioService;
    @Test
    public void test1()throws Exception{
        List<Album> list = audioService.getAll();
        // System.out.println(list);
        ExportParams exportParams = new ExportParams("专辑信息", "album", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Album.class,list);
        workbook.write(new FileOutputStream(new File("album.xls")));
    }

    @Test
    public void test2()throws Exception{

        //        1.定义导入参数
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);
//        2.读取文件  使用工具类
        List<Album> objects = ExcelImportUtil.importExcel(new File("album.xls"), Album.class, importParams);
        for (Album album : objects) {
            System.out.println("album = " + album);
        }
    }
}

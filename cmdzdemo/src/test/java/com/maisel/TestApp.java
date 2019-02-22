package com.maisel;

import com.maisel.dao.AudioDao;
import com.maisel.dao.BannerDao;
import com.maisel.dao.MenuDao;
import com.maisel.dao.UserDao;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;
import com.maisel.entity.Menu;
import com.maisel.entity.User;
import com.maisel.service.AudioService;
import com.maisel.service.BannerService;
import com.maisel.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 19:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
public class TestApp {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AudioService audioService;
    @Autowired
    private AudioDao audioDao;
    @Test
    public void test1() throws Exception {
        List<Menu> all = menuDao.getAll();
        System.out.println(all);
    }

    @Test
    public void test2() throws Exception {
        Map map = bannerService.getAll(1, 3, null);
        System.out.println(map);
    }

    @Test
    public void test3() throws NoSuchMethodException, Exception {
        Map map= userService.getAll(1, 3, "");
        System.out.println(map);
    }
    @Test
    public void test4(){
        User user = userDao.selectById(1);
        System.out.println(user);
    }
    @Test
    public void test5(){
        List<Album> list = audioService.getAll();
        System.out.println(list);
    }
    @Test
    public void test6(){
        List<Album> list = audioDao.getAll();
        System.out.println(list);
    }
    @Test
    public void testPoi() throws Exception {

        //    1.创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //    2.创建一个表对象
        HSSFSheet sheet = workbook.createSheet();
        //    3.创建一个行对象
        HSSFRow row = sheet.createRow(0);
        //    4.创建一个单元格对象
        HSSFCell cell = row.createCell(0);
        //    5.将数据写入到单元格中
        cell.setCellValue("ID");
        //    6.将工作簿保存到本地
        workbook.write(new FileOutputStream(new File("d://testPoi//user.xls")));
    }

}

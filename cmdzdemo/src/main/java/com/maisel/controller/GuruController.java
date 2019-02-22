package com.maisel.controller;

import com.maisel.entity.Guru;
import com.maisel.service.GuruService;
import com.maisel.annotation.ExcelName;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
*
 * @aucthor:jjx
 * @Create:2019-01-05 13:48
*/


@Controller
@RequestMapping("/guru")
public class GuruController {
    private final static Logger logger = LoggerFactory.getLogger(GuruController.class);
    @Autowired
    private GuruService guruService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String guruName) {
        return guruService.getAll(page, rows, guruName);
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Guru guru, MultipartFile file1, HttpServletRequest request) throws IOException {

        String fileName = file1.getOriginalFilename();
        logger.debug("fileName="+fileName);
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileType;
        String path = "shangshi/" + newFileName;
        String realPath = request.getServletContext().getRealPath(path);
        file1.transferTo(new File(realPath));
        guru.setGuruImage("/shangshi/"+newFileName);
        try {
            guruService.add(guru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(MultipartFile file1, Guru guru, HttpServletRequest request) throws IOException {

        logger.debug("guru="+guru);
        logger.debug("file1="+file1);
        String realpath = request.getServletContext().getRealPath("");
        String pathname = realpath + guru.getGuruImage().substring(1);
        logger.debug("pathname="+pathname);
        file1.transferTo(new File(pathname));
        try {
            guruService.update(guru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public boolean updateStatus(Guru guru) {
        try {
            guruService.updateStatus(guru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @RequestMapping("/multiRemove")
    @ResponseBody
    public boolean multiRemove(Integer[] ids) {
        try {
            guruService.multiRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/import1")
    public String import1(MultipartFile file,Guru guru)throws Exception{
        String originalFilename = file.getOriginalFilename();
        // System.out.println(originalFilename);
        FileInputStream fileInputStream = new FileInputStream("d://testPoi//" + originalFilename);
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheet( "guru");

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <=lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            guru.setGuruId((int) row.getCell(0).getNumericCellValue());
            guru.setGuruName(row.getCell(1).getStringCellValue());
            guru.setGuruImage(row.getCell(2).getStringCellValue());
            guru.setGuruNickname(row.getCell(3).getStringCellValue());
            guru.setGuruStatus((int) row.getCell(4).getNumericCellValue());
            guruService.add(guru);
        }
        return "redirect:/getAll";
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception {

        // 准备好数据
        List<Guru> guruList = guruService.getAll();
        // 创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表对象
        HSSFSheet sheet = workbook.createSheet("guru");
        // 创建标题行并写入数据
        HSSFRow row = sheet.createRow(0);

        /*********** 优化标题栏数据开始 *********************/
        // 通过反射拿到类对象
        Guru guru = guruList.get(0);
        Class<? extends Guru> guruClass = guru.getClass();
        // 拿到对象所有的属性
        Field[] fields = guruClass.getDeclaredFields();
        // 遍历拿到注解的值
        for (int i = 0; i <fields.length; i++) {
            // 通过反射拿到注解对象
            ExcelName annotation = fields[i].getAnnotation(ExcelName.class);
            // 拿到注解值
            String name = annotation.name();
            // 创建单元格对象，并赋值
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(name);
        }
        /*********** 优化标题栏数据结束 *********************/
        
        // 填入guru对象的数据
        for (int i = 0; i < guruList.size(); i++) {
            // 创建行对象
            HSSFRow row1 = sheet.createRow(i + 1);
            // 通过反射拿到类对象
            Class<? extends Guru> guruClass1 = guruList.get(i).getClass();
            Field[] declaredFields = guruClass1.getDeclaredFields();
            for (int j = 0; j < declaredFields.length; j++) {
                String name = declaredFields[j].getName();
                String getName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Object invoke = guruClass1.getDeclaredMethod(getName, null).invoke(guruList.get(i), null);
                HSSFCell cell = row1.createCell(j);
                if (invoke instanceof Integer) {
                    cell.setCellValue((Integer)invoke);
                } else if (invoke instanceof Date) {
                    cell.setCellValue((Date)invoke);
                }else {
                    cell.setCellValue((String) invoke);
                }
            }
        }
        workbook.write(new FileOutputStream(new File("d://testPoi//guruAll.xls")));
        String fileName = "guruAll.xls";
        String path="d://testPoi";
        File file = new File(path + "/" + fileName);

        response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/octet-stream;charset=UTF-8");
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1"); // 为了解决中文名称乱码问题
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);

        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);

        while (true) {
            int i = fileInputStream.read();
            if (i==-1) break;
            outputStream.write(i);
        }
        fileInputStream.close();
        outputStream.close();
        file.delete();
    }
}

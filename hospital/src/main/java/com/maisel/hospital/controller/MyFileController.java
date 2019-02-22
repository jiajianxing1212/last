package com.maisel.hospital.controller;

import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.MyFile;
import com.maisel.hospital.service.MyFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:56
 */
@RestController
@RequestMapping("/file")
public class MyFileController {
    @Autowired
    private MyFileService myFileService;

    @ServiceLog("上传文件")
    @RequestMapping("/upload")
    public int upload(MultipartFile file) {

        //拿到的文件名字
        String fileName = file.getOriginalFilename();
        //生成的新名字
        String saveName = UUID.randomUUID().toString() + fileName;
        //拿到文件大小
        long size = file.getSize();
        String fileSize = MyFileUtils.getPrintSize(size);
        //存进数据库
        MyFile newFile = new MyFile();
        newFile.setFileName(fileName);
        newFile.setSaveName(saveName);
        newFile.setUpTime(new Date());
        newFile.setFileSize(fileSize);
        //将文件写入磁盘
        File upFile = new File("C:\\Users\\Administrator\\IdeaProjects\\last\\hospital\\src\\main\\webapp\\file", saveName);
        try {
            file.transferTo(upFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFileService.insert(newFile);

    }

    @ServiceLog("分页展示文件信息")
    @RequestMapping("/getByPage")
    public Map getByPage(int page, int rows, String fileName) {
//        System.out.println(fileName);
        return myFileService.getByPage(page, rows, fileName);
    }

    @ServiceLog("下载文件")
    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        MyFile getFile = myFileService.getByFileName(fileName);
        String path = "C:\\Users\\Administrator\\IdeaProjects\\last\\hospital\\src\\main\\webapp\\file" + getFile.getSaveName();
        File file = new File(path);

        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        try {
            FileUtils.copyFile(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

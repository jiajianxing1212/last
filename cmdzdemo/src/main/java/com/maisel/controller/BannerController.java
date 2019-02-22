package com.maisel.controller;

import com.maisel.dao.BannerDao;
import com.maisel.entity.Banner;
import com.maisel.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 21:12
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerDao bannerDao;

    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll(Integer page, Integer rows, String bannerOldName) {
        Map map = bannerService.getAll(page, rows, bannerOldName);
        return map;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean add(MultipartFile file1, Banner banner, HttpServletRequest req) throws IOException {
        // System.out.println("*****************banner = [" + banner + "]");
        try {
            String fileName = file1.getOriginalFilename();
            // System.out.println(1+""+fileName);
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileType;
            String path = "img/shouye/" + newFileName;
            String realPath = req.getServletContext().getRealPath(path);
            file1.transferTo(new File(realPath));
            banner.setBannerImageUrl("/shouye/" + newFileName);
            bannerService.add(banner);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(MultipartFile file1, Banner banner, HttpServletRequest request) throws IOException {

        //删除原有文件
       /* boolean delete = oldFile.delete();
        System.out.println(delete);
        String newName = "/shouye/"+UUID.randomUUID().toString()+file1.getOriginalFilename();
        banner.setBannerOldName(file1.getOriginalFilename());
        banner.setBannerImageUrl(banner.getBannerImageUrl());*/

        String realpath = request.getServletContext().getRealPath("/img");
        file1.transferTo(new File(realpath + "/" + banner.getBannerImageUrl()));
        try {
            bannerService.update(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/multiRemove")
    @ResponseBody
    public boolean multiRemove(Integer[] ids, HttpServletRequest req) throws IOException {
        try {
            for (Integer id : ids) {
                Banner banner = bannerDao.selectById(id);
                bannerService.mutilRemove(banner);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public boolean updateState(Banner banner){
        try {
            bannerService.updateStatus(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

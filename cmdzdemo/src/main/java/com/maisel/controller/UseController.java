package com.maisel.controller;

import com.maisel.annotation.ServiceLog;
import com.maisel.dao.UserDao;
import com.maisel.entity.User;
import com.maisel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 22:56
 */
@Controller
@RequestMapping("/user")
public class UseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String name) {
        return userService.getAll(page, rows, name);
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(MultipartFile file1, User user, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            String filename = file1.getOriginalFilename();
            String fileType = filename.substring(filename.lastIndexOf("."));
            String path = "userpicture/" + UUID.randomUUID().toString() + fileType;
            System.out.println(path);
            String realPath = request.getServletContext().getRealPath(path);
            file1.transferTo(new File(realPath));
            userService.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(MultipartFile file1, User user, HttpServletRequest request) {

        try {
            String userImage = user.getUserImage();
            /*String filename = file1.getOriginalFilename();
            String fileType = filename.substring(filename.lastIndexOf("."));*/
            String path = "userpicture/" + userImage;
            System.out.println(path);
            String realPath = request.getServletContext().getRealPath(path);
            file1.transferTo(new File(realPath));
            userService.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/multiRemove")
    @ResponseBody
    public boolean multiRemove(Integer[] ids, HttpServletRequest request) {
        User user = null;
        try {
            for (Integer id : ids) {
                user = userDao.selectById(id);
                userService.mutilRemove(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public Map getCount() {
        return userService.getCountSex();
    }

    @RequestMapping("/getCountByWeekendOfRegister")
    @ResponseBody
    public Map getCountByWeekendOfRegister() {
        return userService.getCountByWeekendOfRegister();
    }

    @RequestMapping("/getCountByArea")
    @ResponseBody
    public List<Map<String,Object>> getCountByArea() {
        List<Map<String, Object>> mapList = userService.getCountByArea();

        return mapList;
    }

    @RequestMapping("/getCount1")
    @ResponseBody
    @ServiceLog("查询所用统计信息1")
    public Map getCount1() {
        long start = System.currentTimeMillis();
        Map map = new HashMap();
        List<Map<String, Object>> mapList = userService.getCountByArea();
        Map countByWeekendOfRegister = userService.getCountByWeekendOfRegister();
        Map countSex = userService.getCountSex();
        map.put("province", mapList);
        map.put("week", countByWeekendOfRegister);
        map.put("sex", countSex);
        long end = System.currentTimeMillis();
        System.out.println("优化前总运行时间为"+(end - start));
        return map;
    }
    @RequestMapping("/getAllCount")
    @ResponseBody
    @ServiceLog("查询所用统计信息3")
    public Map getAllCount(){
        long start = System.currentTimeMillis();
        Map map = userService.getAllCount();
        long end = System.currentTimeMillis();
        System.out.println("优化后总运行时间为"+(end - start));
        return map;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public boolean updateStatus(User user) {
        try {
            userService.updateStatus(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.maisel.controller;

import com.maisel.dao.AlbumDao;
import com.maisel.entity.Album;
import com.maisel.service.AdminService;
import com.maisel.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:52
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String albumName) {
        return albumService.getAll(page, rows, albumName);
    }




}

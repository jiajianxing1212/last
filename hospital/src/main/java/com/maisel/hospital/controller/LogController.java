package com.maisel.hospital.controller;

import com.maisel.hospital.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-13 18:15
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String content) {
        return logService.getAll(page, rows, content);
    }
}

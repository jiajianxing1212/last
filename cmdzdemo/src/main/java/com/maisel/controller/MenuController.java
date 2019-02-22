package com.maisel.controller;

import com.maisel.entity.Menu;
import com.maisel.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 19:51
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/getAll")
    public List<Menu> getAll() {
        List<Menu> list = menuService.getAll();
        return list;
    }
}

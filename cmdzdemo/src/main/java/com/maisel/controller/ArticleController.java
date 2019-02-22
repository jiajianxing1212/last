package com.maisel.controller;

import com.maisel.entity.Article;
import com.maisel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 14:09
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("/getAll")
    public Map getAll(Integer page, Integer rows, String articleName) {
        return articleService.getAll(page, rows, articleName);
    }

    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Article article, MultipartFile file1, HttpServletRequest request) throws IOException {

        String originalFilename = file1.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String path = "si/" + newFileName;
        String realPath = request.getServletContext().getRealPath(path);
        file1.transferTo(new File(realPath));
        article.setArticleImage("/si/"+newFileName);
        try {
            article.setArticleDate(new Date());
            articleService.add(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Article article, MultipartFile file1,HttpServletRequest request) throws IOException {
        String realPath = request.getServletContext().getRealPath("/si");
        file1.transferTo(new File(realPath + "/" + article.getArticleImage()));
        try {
            articleService.update(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @ResponseBody
    @RequestMapping("/updateStatus")
    public boolean updateStatus(Article article){
        try {
            articleService.updateStatus(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @ResponseBody
    @RequestMapping("/multiRemove")
    public boolean multiRemove(Integer[] ids) {
        try {
            System.out.println(ids);
            articleService.mutilRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}

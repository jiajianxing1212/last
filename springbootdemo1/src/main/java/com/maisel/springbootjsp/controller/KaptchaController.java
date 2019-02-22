package com.maisel.springbootjsp.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @aucthor:jjx
 * @Date:2018/12/3 000319:32
 */
@Controller
public class KaptchaController {

    // google提供的生成验证码的类
    @Autowired
    private Producer producer;
    @RequestMapping("/getImage")
    public void getImage(HttpServletResponse response, HttpSession session){
        // 通过Google提供的类（接口）生成验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        // 把生成的验证码存放再session中
        session.setAttribute("code", text);
        // 通过验证码工具生成图片,以流的方式响应给客户端浏览器
        try {
            ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.maisel.springbootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-28 17:08
 */
@Controller
public class FileController {
    @RequestMapping(value = "singleFileUpload",method = RequestMethod.POST)
    public String singleFileUpload(MultipartFile file1, HttpServletResponse response) throws IOException {

        String fileName = file1.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
        File fileDir = new File("src/main/webapp/tmp");
        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        file1.transferTo(new File(fileDir.getAbsolutePath(),fileName));
         // String path = fileDir.getAbsolutePath();
        /*Map<String ,String> result = new HashMap<String, String>();
        result.put("name",file1.getOriginalFilename());
        result.put("size",String.valueOf(file1.getSize()));*/
        /*ResponseDto dto = new ResponseDto();
        dto.setData(result);
        dto.setStatus("200");
        dto.setTimestamp(String.valueOf(System.currentTimeMillis()));*/
        return "";
    }
}

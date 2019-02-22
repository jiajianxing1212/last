package com.maisel.controller;

import com.maisel.entity.Album;
import com.maisel.entity.Audio;
import com.maisel.service.AlbumService;
import com.maisel.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 19:26
 */
@Controller
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Album> getll() {
        return audioService.getAll();
    }
    @RequestMapping("add")
    @ResponseBody
    public boolean addAlbum(Album album, MultipartFile file,HttpServletRequest request) {
//        System.out.println("album = " + album);

        try {
            String fileName = file.getOriginalFilename();
            // System.out.println(1+""+fileName);
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileType;
            String path = "audioCollection/" + newFileName;
            String realPath = request.getServletContext().getRealPath(path);
            file.transferTo(new File(realPath));
            album.setAlbumDate(new Date());
            album.setAlbumImage("/audioCollection/" + newFileName);
            audioService.save(album);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/addAudio")
    @ResponseBody
    public boolean addAudio(Audio audio, MultipartFile file, HttpServletRequest request) {

        /*try {
            String originalFilename = file.getOriginalFilename();
            float size =  file.getSize();
            DecimalFormat df = new DecimalFormat("0.0");
            int i=1;
            while (true) {
                size=  size/1000;
                if (size < 1000) break;
                i++;
            }
            String unit = "byte";
            switch (i) {
                case 1:
                    unit="kb";
                    break;
                case 2:
                    unit="M";
                    break;
                case 3:
                    unit="G";
                    break;

                case 4:
                    unit="T";
                    break;
                default:
                    unit = "未知";
                    break;
            }
            audio.setAudioSize(df.format(size)+unit);

            String uuid = UUID.randomUUID().toString();
            String newName = uuid + "_" + originalFilename;
            File copyFile = new File("D:\\服务器\\audio", newName);
            file.transferTo(copyFile);
            audio.setAudioUrl("/audio/" + newName);
            audioService.save(audio);*/
        try {
            double fileSize = (double) file.getSize();
            String size = null;
            BigDecimal bigDecimal = null;
            BigDecimal bd2 = null;
            bigDecimal = new BigDecimal(fileSize / (float) 1024);
            bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

            System.out.println(bd2);
            size = fileSize + "b";
            if (fileSize >= 1024) {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//kb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "kb";
            } else if (fileSize >= 1024) {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//mb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "mb";
            } else {
                bigDecimal = new BigDecimal(fileSize / (float) 1024);//Gb
                bd2 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                size = bd2 + "gb";
            }
            String fileName = file.getOriginalFilename();
            // System.out.println(1+""+fileName);
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileType;
            String path = "audio/" + newFileName;
            String realPath = request.getServletContext().getRealPath(path);
            file.transferTo(new File(realPath));
            audio.setAudioUrl("audio/" + newFileName);
            audio.setAudioSize(size);
            albumService.save(audio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

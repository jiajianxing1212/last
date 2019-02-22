package com.maisel.hospital.controller;




import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.Patient;
import com.maisel.hospital.entity.dto.TriageDTO;
import com.maisel.hospital.service.PatientService;
import com.maisel.hospital.service.TriageService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/patient")
public class PatientController {
    private final static Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;
    @Autowired
    private TriageService triageService;

    @ServiceLog("分页展示患者")
    @RequestMapping("/getByPage")
    public Map getByPage(int page, int rows, String name) {
        // logger.debug("**********"+patientService.getByPage(page, rows, name));
        return patientService.getByPage(page, rows, name);
    }

    @ServiceLog("添加患者")
    @RequestMapping("/insert")
    public int insert(Patient patient) {
        return patientService.insert(patient);
    }

    @ServiceLog("批量删除患者")
    @RequestMapping("/deleteSome")
    public int deleteSome(int[] ids) {
        return patientService.deleteSome(ids);
    }

    @ServiceLog("修改患者信息")
    @RequestMapping("/update")
    public int update(Patient patient) {
        return patientService.update(patient);
    }

    @ServiceLog("下载诊断信息")
    @RequestMapping("download")
    public void download(int id, HttpServletResponse response) throws Exception {
        //文件地址
        String path = "E:\\excel\\" + UUID.randomUUID().toString() + ".xls";
        ArrayList<TriageDTO> list = new ArrayList<>();
        //查到的数据
        TriageDTO triageDTO = triageService.getByPid(id);
        logger.debug(triageDTO.toString());
        list.add(triageDTO);
        //
        ExportParams exportParams = new ExportParams("诊断信息", "triage", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, TriageDTO.class, list);
        workbook.write(new FileOutputStream(new File(path)));
        File file = new File(path);
        String fileName = file.getName();
        fileName = URLEncoder.encode("诊断信息", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + "诊断信息");
        FileUtils.copyFile(file, response.getOutputStream());
    }
}


package com.maisel.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.maisel.entity.AdminLog;
import com.maisel.service.AdminLogService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-11 20:51
 */
@Controller
@RequestMapping("adminLog")
public class AdminLogController {
    @Autowired
    private AdminLogService adminLogService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String logAction) {
        return adminLogService.getAll(page, rows, logAction);
    }

    @RequestMapping("/multiRemove")
    @ResponseBody
    public boolean multiRemove(Integer[]ids) {
        try {
            adminLogService.multiPart(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*@RequestMapping("/import1")
    @ResponseBody
    public void import1(){
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        // 读取文件，使用工具类导入
        List<AdminLog> objects = ExcelImportUtil.importExcel(new File("g://log.xls"), AdminLog.class, importParams);
        for (AdminLog object : objects) {
            adminLogService.
        }

    }*/
    @RequestMapping
    @ResponseBody
    public void export() throws Exception{
        List<AdminLog> list = adminLogService.getAll();
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, AdminLog.class, list);
        workbook.write(new FileOutputStream(new File("e://adminlog.xls")));
    }
}

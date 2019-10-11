package com.taoxd.easypoiexcel.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.taoxd.easypoiexcel.handler.UserExcelHandler;
import com.taoxd.easypoiexcel.handler.UserVerifyHandler;
import com.taoxd.easypoiexcel.pojo.User;
import com.taoxd.easypoiexcel.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ExcelImportController {


    @PostMapping("excelImport1")
    public Map<String, Object> excelImport1(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        ImportParams importParams = new ImportParams();
        // 数据处理
        IExcelDataHandler<User> handler = new UserExcelHandler();
        handler.setNeedHandlerFields(new String[]{"姓名"});// 注意这里对应的是excel的列名。也就是对象上指定的列名。
        importParams.setDataHandler(handler);

        IExcelVerifyHandler<User> userVerifyHandler = new UserVerifyHandler();
        importParams.setVerifyHandler(userVerifyHandler);
        // 需要验证
        importParams.setNeedVerfiy(true);

        try {
            ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class,
                    importParams);

            List<User> successList = result.getList();
            List<User> failList = result.getFailList();

            log.info("是否存在验证未通过的数据:" + result.isVerfiyFail());
            log.info("验证通过的数量:" + successList.size());
            log.info("验证未通过的数量:" + failList.size());

            map.put("successList", successList);
            map.put("failList", failList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return map;
    }

    @PostMapping("excelImport2")
    public List<User> excelImport2(@RequestParam("file") MultipartFile file) throws IOException {
        List<User> users = ExcelUtils.importExcel(file, 0, 1, true, User.class);
        return users;
    }
}

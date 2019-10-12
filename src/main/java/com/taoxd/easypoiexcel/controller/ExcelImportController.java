package com.taoxd.easypoiexcel.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.taoxd.easypoiexcel.handler.UserExcelHandler;
import com.taoxd.easypoiexcel.handler.UserVerifyHandler;
import com.taoxd.easypoiexcel.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ExcelImportController {


    @PostMapping("excelImport1")
    public Map<String, Object> excelImport1(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ImportParams importParams = new ImportParams();
        // 数据处理
        IExcelDataHandler<User> handler = new UserExcelHandler();
        // 注意这里对应的是excel的列名。也就是对象上指定的列名。
        handler.setNeedHandlerFields(new String[]{"姓名"});
        importParams.setDataHandler(handler);
        //数据自定义校验，例如查询数据库是否存在某条数据
        IExcelVerifyHandler<User> userVerifyHandler = new UserVerifyHandler();
        //注解校验
        importParams.setVerifyHandler(userVerifyHandler);
        // 需要验证
        importParams.setNeedVerfiy(true);

        ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class,
                importParams);

        List<User> successList = result.getList();
        List<User> failList = result.getFailList();

        log.info("是否存在验证未通过的数据:" + result.isVerfiyFail());
        log.info("验证通过的数量:" + successList.size());
        log.info("验证未通过的数量:" + failList.size());

        map.put("successList", successList);
        map.put("failList", failList);
        return map;
    }

}

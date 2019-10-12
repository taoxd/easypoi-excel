package com.taoxd.easypoiexcel.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.taoxd.easypoiexcel.pojo.User;
import lombok.extern.slf4j.Slf4j;

/**
 * easypoi 的handler是用来处理一些特殊事情的。
 * 数据处理接口,以此为主,replace,format都在这后面
 *
 * 比如处理某列数据，如下
 * 将Excel中 姓名 列的 test ，改为 哈哈
 *
 */
@Slf4j
public class UserExcelHandler extends ExcelDataHandlerDefaultImpl<User> {

    @Override
    public Object importHandler(User obj, String name, Object value) {
        log.info(name + ":" + value);
        if ("姓名".equals(name) &&  "test".equals(value)) {
            return super.importHandler(obj, name, "哈哈");
        }
        return super.importHandler(obj, name, value);
    }

}

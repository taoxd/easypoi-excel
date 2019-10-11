package com.taoxd.easypoiexcel.handler;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.taoxd.easypoiexcel.pojo.User;
import lombok.extern.slf4j.Slf4j;

/**
 * easypoi 的handler是用来处理一些特殊事情的。
 * 比如导入的数据某些信息不能与现有数据库中的数据冲突，那么必然会查询数据库。
 * 这样的处理是上面hibernate验证框架也无法轻易实现的。所以他这里设计了一个handler。
 */
@Slf4j
public class UserExcelHandler extends ExcelDataHandlerDefaultImpl<User> {
    @Override
    public Object importHandler(User obj, String name, Object value) {
        log.info("UserExcelHandler " + name + ":" + value);
        return super.importHandler(obj, name, value);
    }

}

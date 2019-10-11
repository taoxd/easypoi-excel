package com.taoxd.easypoiexcel.handler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.taoxd.easypoiexcel.pojo.User;

public class UserVerifyHandler implements IExcelVerifyHandler<User> {

    @Override
    public ExcelVerifyHandlerResult verifyHandler(User user) {

        //如果用户存在，我们就返回一个false
        if("闪闪".equals(user.getName())){
            ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(false);
            result.setMsg("用户名已经存在！");
            return result;
        }
        return  new ExcelVerifyHandlerResult(true);
    }
}

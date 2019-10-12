package com.taoxd.easypoiexcel.handler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.taoxd.easypoiexcel.pojo.User;

/**
 * 校验处理接口,自定义校验
 *
 * 例如 数据库中存在 欧豪 用户 ，会响应 用户已存在
 */
public class UserVerifyHandler implements IExcelVerifyHandler<User> {

    @Override
    public ExcelVerifyHandlerResult verifyHandler(User user) {

        //如果用户存在，我们就返回一个false
        if("欧豪".equals(user.getName())){
            ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(false);
            result.setMsg("用户名已经存在！");
            return result;
        }
        return  new ExcelVerifyHandlerResult(true);
    }
}

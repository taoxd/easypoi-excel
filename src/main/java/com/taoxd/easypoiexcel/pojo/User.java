package com.taoxd.easypoiexcel.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class User implements IExcelModel, IExcelDataModel {
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 行号
     */
    private int rowNum;

    @Override
    public void setRowNum(int rowNum) {
        this.rowNum = ++rowNum;
    }

    @Excel(name = "id")
    @NotBlank(message = "该字段不能为空")
    private String id;

    @Excel(name = "姓名")
    @Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}", message = "姓名中文2-5位")
    private String name;

    @Max(value = 20)
    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "生日", format = "yyyy-MM-dd")
    private Date birthday;

    @Excel(name = "性别", replace = {"男_1", "女_2"}, suffix = "生")
    private int sex;

}

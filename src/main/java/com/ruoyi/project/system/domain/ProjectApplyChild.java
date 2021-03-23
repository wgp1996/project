package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 项目部信息对象 project_apply_child
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public class ProjectApplyChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String deptCode;

    /** 项目部名称 */
    @Excel(name = "项目部名称")
    private String deptName;

    /** 工程分项编号 */
    @Excel(name = "工程分项编号")
    private String enginnerNum;

    /** 工程编号 */
    @Excel(name = "工程编号")
    private String enginnerCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setEnginnerNum(String enginnerNum) 
    {
        this.enginnerNum = enginnerNum;
    }

    public String getEnginnerNum() 
    {
        return enginnerNum;
    }
    public void setEnginnerCode(String enginnerCode) 
    {
        this.enginnerCode = enginnerCode;
    }

    public String getEnginnerCode() 
    {
        return enginnerCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptCode", getDeptCode())
            .append("deptName", getDeptName())
            .append("enginnerNum", getEnginnerNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("enginnerCode", getEnginnerCode())
            .toString();
    }
}

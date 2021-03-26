package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 预算编制对象 project_invent
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public class ProjectInvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 标题 */
    @Excel(name = "标题")
    private String djTitle;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    private String fileRows;

    public String getFileRows() {
        return fileRows;
    }
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setFileRows(String fileRows) {
        this.fileRows = fileRows;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setDjTitle(String djTitle) 
    {
        this.djTitle = djTitle;
    }

    public String getDjTitle() 
    {
        return djTitle;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("status", getStatus())
            .append("djTime", getDjTime())
            .append("djTitle", getDjTitle())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("projectCode", getProjectCode())
            .toString();
    }
}

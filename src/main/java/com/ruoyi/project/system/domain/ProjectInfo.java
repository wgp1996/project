package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 项目建档对象 project_info
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public class ProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目简称 */
    @Excel(name = "项目简称")
    private String projectNameJc;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 项目经理名称 */
    @Excel(name = "项目经理名称")
    private String projectManagerName;

    /** 项目经理编码 */
    @Excel(name = "项目经理编码")
    private String projectManagerCode;

    /** 计划开工日期 */
    @Excel(name = "计划开工日期")
    private String projectStartTime;

    /** 计划完工日期 */
    @Excel(name = "计划完工日期")
    private String projectEndTime;

    /** 是否启用预算 */
    @Excel(name = "是否启用预算")
    private Integer ysStatus;

    private String rows;

    private List<ProjectUser> childrenList;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<ProjectUser> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ProjectUser> childrenList) {
        this.childrenList = childrenList;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectNameJc(String projectNameJc) 
    {
        this.projectNameJc = projectNameJc;
    }

    public String getProjectNameJc() 
    {
        return projectNameJc;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setProjectManagerName(String projectManagerName) 
    {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectManagerName() 
    {
        return projectManagerName;
    }
    public void setProjectManagerCode(String projectManagerCode) 
    {
        this.projectManagerCode = projectManagerCode;
    }

    public String getProjectManagerCode() 
    {
        return projectManagerCode;
    }
    public void setProjectStartTime(String projectStartTime) 
    {
        this.projectStartTime = projectStartTime;
    }

    public String getProjectStartTime() 
    {
        return projectStartTime;
    }
    public void setProjectEndTime(String projectEndTime) 
    {
        this.projectEndTime = projectEndTime;
    }

    public String getProjectEndTime() 
    {
        return projectEndTime;
    }
    public void setYsStatus(Integer ysStatus) 
    {
        this.ysStatus = ysStatus;
    }

    public Integer getYsStatus() 
    {
        return ysStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("projectNameJc", getProjectNameJc())
            .append("projectType", getProjectType())
            .append("projectManagerName", getProjectManagerName())
            .append("projectManagerCode", getProjectManagerCode())
            .append("projectStartTime", getProjectStartTime())
            .append("projectEndTime", getProjectEndTime())
            .append("ysStatus", getYsStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 项目人员对象 project_user
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public class ProjectUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 人员名称 */
    @Excel(name = "人员名称")
    private String userName;

    /** 人员编码 */
    @Excel(name = "人员编码")
    private String userCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 岗位编码 */
    @Excel(name = "岗位编码")
    private String projectPost;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String projectPostName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
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
    public void setProjectPost(String projectPost) 
    {
        this.projectPost = projectPost;
    }

    public String getProjectPost() 
    {
        return projectPost;
    }
    public void setProjectPostName(String projectPostName) 
    {
        this.projectPostName = projectPostName;
    }

    public String getProjectPostName() 
    {
        return projectPostName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("userCode", getUserCode())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("projectPost", getProjectPost())
            .append("projectPostName", getProjectPostName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

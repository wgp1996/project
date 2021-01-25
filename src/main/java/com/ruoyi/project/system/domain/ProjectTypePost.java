package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类对象 goods_type
 *
 * @author ruoyi
 * @date 2020-08-14
 */
public class ProjectTypePost extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品分类id */
    private Integer projectTypeId;

    /** 商品分类父id */
    @Excel(name = "商品分类父id")
    private Integer projectTypePid;

    /** 商品分类名称 */
    @Excel(name = "商品分类名称")
    private String projectTypeName;

    /** 档案类型 */
    @Excel(name = "档案类型")
    private Integer type;
    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectTypeId(Integer projectTypeId)
    {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProjectTypeId()
    {
        return projectTypeId;
    }
    public void setProjectTypePid(Integer projectTypePid)
    {
        this.projectTypePid = projectTypePid;
    }

    public Integer getProjectTypePid()
    {
        return projectTypePid;
    }
    public void setProjectTypeName(String projectTypeName)
    {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectTypeName()
    {
        return projectTypeName;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    /** 子菜单 */
    private List<ProjectTypePost> children = new ArrayList<ProjectTypePost>();

    @Override
    public List<ProjectTypePost> getChildren() {
        return children;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectTypeId", getProjectTypeId())
            .append("projectTypePid", getProjectTypePid())
            .append("projectTypeName", getProjectTypeName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("type", getType())
            .toString();
    }
}

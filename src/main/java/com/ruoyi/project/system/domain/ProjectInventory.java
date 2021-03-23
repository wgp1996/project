package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 编制清单对象 project_inventory
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
public class ProjectInventory extends TreeEntity
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

    /** 是否末级 */
    @Excel(name = "是否末级")
    private Integer isLast;

    /** 单位 */
    @Excel(name = "单位")
    private String inventoryUnit;

    /** 数量 */
    @Excel(name = "数量")
    private String inventoryNum;

    /** 单价 */
    @Excel(name = "单价")
    private String inventoryPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String inventoryMoney;

    /** 分项编码 */
    @Excel(name = "分项编码")
    private String inventoryCode;

    /** 分项名称 */
    @Excel(name = "分项名称")
    private String inventoryName;

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
    public void setIsLast(Integer isLast) 
    {
        this.isLast = isLast;
    }

    public Integer getIsLast() 
    {
        return isLast;
    }
    public void setInventoryUnit(String inventoryUnit) 
    {
        this.inventoryUnit = inventoryUnit;
    }

    public String getInventoryUnit() 
    {
        return inventoryUnit;
    }
    public void setInventoryNum(String inventoryNum) 
    {
        this.inventoryNum = inventoryNum;
    }

    public String getInventoryNum() 
    {
        return inventoryNum;
    }
    public void setInventoryPrice(String inventoryPrice) 
    {
        this.inventoryPrice = inventoryPrice;
    }

    public String getInventoryPrice() 
    {
        return inventoryPrice;
    }
    public void setInventoryMoney(String inventoryMoney) 
    {
        this.inventoryMoney = inventoryMoney;
    }

    public String getInventoryMoney() 
    {
        return inventoryMoney;
    }
    public void setInventoryCode(String inventoryCode) 
    {
        this.inventoryCode = inventoryCode;
    }

    public String getInventoryCode() 
    {
        return inventoryCode;
    }
    public void setInventoryName(String inventoryName) 
    {
        this.inventoryName = inventoryName;
    }

    public String getInventoryName() 
    {
        return inventoryName;
    }

    /** 子菜单 */
    private List<ProjectInventory> children = new ArrayList<ProjectInventory>();

    @Override
    public List<ProjectInventory> getChildren() {
        return children;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("isLast", getIsLast())
            .append("inventoryUnit", getInventoryUnit())
            .append("inventoryNum", getInventoryNum())
            .append("inventoryPrice", getInventoryPrice())
            .append("inventoryMoney", getInventoryMoney())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("inventoryCode", getInventoryCode())
            .append("inventoryName", getInventoryName())
            .toString();
    }
}

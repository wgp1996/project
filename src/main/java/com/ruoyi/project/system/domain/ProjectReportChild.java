package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 产值明细对象 project_report_child
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public class ProjectReportChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 分项ID */
    @Excel(name = "分项ID")
    private String inventoryId;

    /** 分项名称 */
    @Excel(name = "分项名称")
    private String inventoryName;

    /** 单位 */
    @Excel(name = "单位")
    private String inventoryUnit;

    /** 数量 */
    @Excel(name = "数量")
    private String inventoryNum;

    /** 复查数量 */
    @Excel(name = "复查数量")
    private String inventoryCheckNum;

    /** 单价 */
    @Excel(name = "单价")
    private String inventoryPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String inventoryMoney;

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
    public void setInventoryId(String inventoryId) 
    {
        this.inventoryId = inventoryId;
    }

    public String getInventoryId() 
    {
        return inventoryId;
    }
    public void setInventoryName(String inventoryName) 
    {
        this.inventoryName = inventoryName;
    }

    public String getInventoryName() 
    {
        return inventoryName;
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
    public void setInventoryCheckNum(String inventoryCheckNum) 
    {
        this.inventoryCheckNum = inventoryCheckNum;
    }

    public String getInventoryCheckNum() 
    {
        return inventoryCheckNum;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("inventoryId", getInventoryId())
            .append("inventoryName", getInventoryName())
            .append("inventoryUnit", getInventoryUnit())
            .append("inventoryNum", getInventoryNum())
            .append("inventoryCheckNum", getInventoryCheckNum())
            .append("inventoryPrice", getInventoryPrice())
            .append("inventoryMoney", getInventoryMoney())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

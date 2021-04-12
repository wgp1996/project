package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 库存管理对象 stock_info
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public class StockInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String storeCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 实际入库时间 */
    @Excel(name = "实际入库时间")
    private String rkTime;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String goodsCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String goodsName;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 数量 */
    @Excel(name = "数量")
    private Integer goodsNum;

    /** 单价 */
    @Excel(name = "单价")
    private String goodsPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String goodsMoney;

    /** 入库单 ID */
    private String rkOrderId;

    @Excel(name = "类型")
    private Integer djType;
    /** 供货商编码 */
    @Excel(name = "供货商编码")
    private String khCode;

    /** 供货商名称 */
    @Excel(name = "供货商名称")
    private String khName;

    private String surplusNum;

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getKhCode() {
        return khCode;
    }

    public void setKhCode(String khCode) {
        this.khCode = khCode;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }

    public Integer getDjType() {
        return djType;
    }

    public void setDjType(Integer djType) {
        this.djType = djType;
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
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setRkTime(String rkTime) 
    {
        this.rkTime = rkTime;
    }

    public String getRkTime() 
    {
        return rkTime;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setGoodsNum(Integer goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsNum() 
    {
        return goodsNum;
    }
    public void setGoodsPrice(String goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setGoodsMoney(String goodsMoney) 
    {
        this.goodsMoney = goodsMoney;
    }

    public String getGoodsMoney() 
    {
        return goodsMoney;
    }
    public void setRkOrderId(String rkOrderId) 
    {
        this.rkOrderId = rkOrderId;
    }

    public String getRkOrderId() 
    {
        return rkOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("storeCode", getStoreCode())
            .append("storeName", getStoreName())
            .append("djTime", getDjTime())
            .append("rkTime", getRkTime())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsDw", getGoodsDw())
            .append("goodsGg", getGoodsGg())
            .append("goodsNum", getGoodsNum())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsMoney", getGoodsMoney())
            .append("rkOrderId", getRkOrderId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

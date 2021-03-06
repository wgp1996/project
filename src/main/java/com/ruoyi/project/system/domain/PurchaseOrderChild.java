package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购订单子表对象 purchase_order_child
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public class PurchaseOrderChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 物资编码 */
    @Excel(name = "物资编码")
    private String goodsCode;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String goodsName;

    /** 发起数量 */
    @Excel(name = "发起数量")
    private Integer goodsNum;

    /** 审批单价 */
    @Excel(name = "审批单价")
    private String goodsSpPrice;

    /** 单价 */
    @Excel(name = "单价")
    private String goodsPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String goodsMoney;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    private String surplusNum;

    private String djType;

    private String khCode;

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getDjType() {
        return djType;
    }

    public void setDjType(String djType) {
        this.djType = djType;
    }

    public String getKhCode() {
        return khCode;
    }

    public void setKhCode(String khCode) {
        this.khCode = khCode;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
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
    public void setGoodsNum(Integer goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public String getGoodsSpPrice() {
        return goodsSpPrice;
    }

    public void setGoodsSpPrice(String goodsSpPrice) {
        this.goodsSpPrice = goodsSpPrice;
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
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsNum", getGoodsNum())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsMoney", getGoodsMoney())
            .append("goodsGg", getGoodsGg())
            .append("goodsDw", getGoodsDw())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

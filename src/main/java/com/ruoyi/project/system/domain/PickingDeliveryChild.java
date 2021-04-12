package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 领料出库单子表对象 picking_delivery_child
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public class PickingDeliveryChild extends BaseEntity
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
    /** 单位 */
    @Excel(name = "入库单号")
    private String rkDjNumber;
    /** 单位 */
    @Excel(name = "入库明细ID")
    private String rkOrderId;

    private Integer oldGoodsNum;

    private Integer surplusNum;

    public String getRkDjNumber() {
        return rkDjNumber;
    }

    public void setRkDjNumber(String rkDjNumber) {
        this.rkDjNumber = rkDjNumber;
    }

    public String getRkOrderId() {
        return rkOrderId;
    }

    public void setRkOrderId(String rkOrderId) {
        this.rkOrderId = rkOrderId;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }
    public Integer getOldGoodsNum() {
        return oldGoodsNum;
    }

    public void setOldGoodsNum(Integer oldGoodsNum) {
        this.oldGoodsNum = oldGoodsNum;
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

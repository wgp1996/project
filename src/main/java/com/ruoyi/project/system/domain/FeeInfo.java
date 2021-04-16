package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 费用项目对象 fee_info
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 费用项目名称 */
    @Excel(name = "费用项目名称")
    private String feeName;

    /** 费用项目编码 */
    @Excel(name = "费用项目编码")
    private String feeCode;

    /** 分类编码 */
    @Excel(name = "分类编码")
    private String feeTypeCode;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String feeTypeName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setFeeName(String feeName) 
    {
        this.feeName = feeName;
    }

    public String getFeeName() 
    {
        return feeName;
    }
    public void setFeeCode(String feeCode) 
    {
        this.feeCode = feeCode;
    }

    public String getFeeCode() 
    {
        return feeCode;
    }
    public void setFeeTypeCode(String feeTypeCode) 
    {
        this.feeTypeCode = feeTypeCode;
    }

    public String getFeeTypeCode() 
    {
        return feeTypeCode;
    }
    public void setFeeTypeName(String feeTypeName) 
    {
        this.feeTypeName = feeTypeName;
    }

    public String getFeeTypeName() 
    {
        return feeTypeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeName", getFeeName())
            .append("feeCode", getFeeCode())
            .append("feeTypeCode", getFeeTypeCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("feeTypeName", getFeeTypeName())
            .toString();
    }
}

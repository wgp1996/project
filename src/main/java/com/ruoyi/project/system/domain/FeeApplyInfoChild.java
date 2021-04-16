package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 费用报销子表对象 fee_apply_info_child
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FeeApplyInfoChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 费用项目名称 */
    @Excel(name = "费用项目名称")
    private String feeName;

    /** 费用项目编码 */
    @Excel(name = "费用项目编码")
    private String feeCode;

    /** 报销事由 */
    @Excel(name = "报销事由")
    private String applyContent;

    /** 凭证张数 */
    @Excel(name = "凭证张数")
    private Integer feeNum;

    /** 报销金额 */
    @Excel(name = "报销金额")
    private Double feeMoney;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

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
    public void setApplyContent(String applyContent) 
    {
        this.applyContent = applyContent;
    }

    public String getApplyContent() 
    {
        return applyContent;
    }
    public void setFeeNum(Integer feeNum) 
    {
        this.feeNum = feeNum;
    }

    public Integer getFeeNum() 
    {
        return feeNum;
    }
    public void setFeeMoney(Double feeMoney) 
    {
        this.feeMoney = feeMoney;
    }

    public Double getFeeMoney() 
    {
        return feeMoney;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("feeName", getFeeName())
            .append("feeCode", getFeeCode())
            .append("applyContent", getApplyContent())
            .append("feeNum", getFeeNum())
            .append("feeMoney", getFeeMoney())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

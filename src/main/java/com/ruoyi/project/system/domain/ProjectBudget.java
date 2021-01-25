package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 项目预算对象 project_budget
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public class ProjectBudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 项目预算收入 */
    @Excel(name = "项目预算收入")
    private String budgetSr;

    /** 来源 */
    @Excel(name = "来源")
    private String budgetSrFrom;

    /** 设置预算毛利率 */
    @Excel(name = "设置预算毛利率")
    private String budgetRate;

    /** 项目预算毛利 */
    @Excel(name = "项目预算毛利")
    private String budgetProfit;

    /** 销项税率 */
    @Excel(name = "销项税率")
    private String budgetXxRate;

    /** 税金 */
    @Excel(name = "税金")
    private String budgetTaxes;

    /** 预算成本 */
    @Excel(name = "预算成本")
    private String budgetCost;

    /** 纯利润 */
    @Excel(name = "纯利润")
    private String budgetNetProfit;

    /** 成本预算 */
    @Excel(name = "成本预算")
    private String costBudget;

    /** 劳务类预算 */
    @Excel(name = "劳务类预算")
    private String labourBudget;

    /** 劳务类预算来源 */
    @Excel(name = "劳务类预算来源")
    private String labourBudgetFrom;

    /** 材料预算 */
    @Excel(name = "材料预算")
    private String materialBudget;

    /** 材料预算来源 */
    @Excel(name = "材料预算来源")
    private String materialBudgetFrom;

    /** 其他类预算 */
    @Excel(name = "其他类预算")
    private String outherBudget;

    /** 其他类预算来源 */
    @Excel(name = "其他类预算来源")
    private String outherBudgetFrom;

    /** 间接类预算 */
    @Excel(name = "间接类预算")
    private String indirectBudget;

    /** 间接类预算来源 */
    @Excel(name = "间接类预算来源")
    private String indirectBudgetFrom;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBudgetSr(String budgetSr) 
    {
        this.budgetSr = budgetSr;
    }

    public String getBudgetSr() 
    {
        return budgetSr;
    }
    public void setBudgetSrFrom(String budgetSrFrom) 
    {
        this.budgetSrFrom = budgetSrFrom;
    }

    public String getBudgetSrFrom() 
    {
        return budgetSrFrom;
    }
    public void setBudgetRate(String budgetRate) 
    {
        this.budgetRate = budgetRate;
    }

    public String getBudgetRate() 
    {
        return budgetRate;
    }
    public void setBudgetProfit(String budgetProfit) 
    {
        this.budgetProfit = budgetProfit;
    }

    public String getBudgetProfit() 
    {
        return budgetProfit;
    }
    public void setBudgetXxRate(String budgetXxRate) 
    {
        this.budgetXxRate = budgetXxRate;
    }

    public String getBudgetXxRate() 
    {
        return budgetXxRate;
    }
    public void setBudgetTaxes(String budgetTaxes) 
    {
        this.budgetTaxes = budgetTaxes;
    }

    public String getBudgetTaxes() 
    {
        return budgetTaxes;
    }
    public void setBudgetCost(String budgetCost) 
    {
        this.budgetCost = budgetCost;
    }

    public String getBudgetCost() 
    {
        return budgetCost;
    }
    public void setBudgetNetProfit(String budgetNetProfit) 
    {
        this.budgetNetProfit = budgetNetProfit;
    }

    public String getBudgetNetProfit() 
    {
        return budgetNetProfit;
    }
    public void setCostBudget(String costBudget) 
    {
        this.costBudget = costBudget;
    }

    public String getCostBudget() 
    {
        return costBudget;
    }
    public void setLabourBudget(String labourBudget) 
    {
        this.labourBudget = labourBudget;
    }

    public String getLabourBudget() 
    {
        return labourBudget;
    }
    public void setLabourBudgetFrom(String labourBudgetFrom) 
    {
        this.labourBudgetFrom = labourBudgetFrom;
    }

    public String getLabourBudgetFrom() 
    {
        return labourBudgetFrom;
    }
    public void setMaterialBudget(String materialBudget) 
    {
        this.materialBudget = materialBudget;
    }

    public String getMaterialBudget() 
    {
        return materialBudget;
    }
    public void setMaterialBudgetFrom(String materialBudgetFrom) 
    {
        this.materialBudgetFrom = materialBudgetFrom;
    }

    public String getMaterialBudgetFrom() 
    {
        return materialBudgetFrom;
    }
    public void setOutherBudget(String outherBudget) 
    {
        this.outherBudget = outherBudget;
    }

    public String getOutherBudget() 
    {
        return outherBudget;
    }
    public void setOutherBudgetFrom(String outherBudgetFrom) 
    {
        this.outherBudgetFrom = outherBudgetFrom;
    }

    public String getOutherBudgetFrom() 
    {
        return outherBudgetFrom;
    }
    public void setIndirectBudget(String indirectBudget) 
    {
        this.indirectBudget = indirectBudget;
    }

    public String getIndirectBudget() 
    {
        return indirectBudget;
    }
    public void setIndirectBudgetFrom(String indirectBudgetFrom) 
    {
        this.indirectBudgetFrom = indirectBudgetFrom;
    }

    public String getIndirectBudgetFrom() 
    {
        return indirectBudgetFrom;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("budgetSr", getBudgetSr())
            .append("budgetSrFrom", getBudgetSrFrom())
            .append("budgetRate", getBudgetRate())
            .append("budgetProfit", getBudgetProfit())
            .append("budgetXxRate", getBudgetXxRate())
            .append("budgetTaxes", getBudgetTaxes())
            .append("budgetCost", getBudgetCost())
            .append("budgetNetProfit", getBudgetNetProfit())
            .append("costBudget", getCostBudget())
            .append("labourBudget", getLabourBudget())
            .append("labourBudgetFrom", getLabourBudgetFrom())
            .append("materialBudget", getMaterialBudget())
            .append("materialBudgetFrom", getMaterialBudgetFrom())
            .append("outherBudget", getOutherBudget())
            .append("outherBudgetFrom", getOutherBudgetFrom())
            .append("indirectBudget", getIndirectBudget())
            .append("indirectBudgetFrom", getIndirectBudgetFrom())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .toString();
    }
}

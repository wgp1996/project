package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 费用项目分类对象 fee_type
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FeeType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用分类id */
    private Long feeTypeId;

    /** 费用分类父id */
    private Long feeTypePid;

    /** 费用分类名称 */
    @Excel(name = "费用分类名称")
    private String feeTypeName;

    /** 档案类型 0类 1款 2项 */
    @Excel(name = "档案类型 0类 1款 2项")
    private Integer type;

    private List<FeeType> children = new ArrayList<FeeType>();
    public void setFeeTypeId(Long feeTypeId)
    {
        this.feeTypeId = feeTypeId;
    }

    public Long getFeeTypeId()
    {
        return feeTypeId;
    }
    public void setFeeTypePid(Long feeTypePid)
    {
        this.feeTypePid = feeTypePid;
    }

    public Long getFeeTypePid()
    {
        return feeTypePid;
    }
    public void setFeeTypeName(String feeTypeName) 
    {
        this.feeTypeName = feeTypeName;
    }

    public String getFeeTypeName() 
    {
        return feeTypeName;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    @Override
    public List<FeeType> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feeTypeId", getFeeTypeId())
            .append("feeTypePid", getFeeTypePid())
            .append("feeTypeName", getFeeTypeName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("type", getType())
            .toString();
    }
}

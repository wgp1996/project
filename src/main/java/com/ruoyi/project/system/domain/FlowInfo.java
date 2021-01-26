package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 流程表对象 flow_info
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public class FlowInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流程ID */
    private Integer id;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String flowName;

    /** 流程号 */
    @Excel(name = "流程号")
    private String flowNo;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    private String statusName;

    private List<FlowNode> childrenList;

    private String rows;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<FlowNode> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<FlowNode> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setFlowName(String flowName) 
    {
        this.flowName = flowName;
    }

    public String getFlowName() 
    {
        return flowName;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flowName", getFlowName())
            .append("flowNo", getFlowNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }
}

package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 审批表对象 flow_audit
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public class FlowAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号或单据ID */
    @Excel(name = "单号或单据ID")
    private String djId;

    /** 节点编号 */
    @Excel(name = "节点编号")
    private Integer nodeNo;

    /** 审批人id */
    @Excel(name = "审批人id")
    private String userId;

    /** 审批人姓名 */
    @Excel(name = "审批人姓名")
    private String userName;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String auditInfo;

    /** 审批日期 */
    @Excel(name = "审批日期")
    private String auditTime;
    /** 人员或角色id */
    @Excel(name = "人员或角色id")
    private String prId;

    /** 人员或角色名称 */
    @Excel(name = "人员或角色名称")
    private String prName;

    /** 是否允许结束 */
    @Excel(name = "是否允许结束")
    private Integer isEnd;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer status;

    /** 历史状态 */
    @Excel(name = "历史状态")
    private Integer flowStatus;

    private String stepStatus;

    public String getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(String stepStatus) {
        this.stepStatus = stepStatus;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    private String statusName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    private String isEndName;

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public String getIsEndName() {
        return isEndName;
    }

    public void setIsEndName(String isEndName) {
        this.isEndName = isEndName;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDjId(String djId) 
    {
        this.djId = djId;
    }

    public String getDjId() 
    {
        return djId;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setAuditInfo(String auditInfo) 
    {
        this.auditInfo = auditInfo;
    }

    public String getAuditInfo() 
    {
        return auditInfo;
    }
    public void setAuditTime(String auditTime) 
    {
        this.auditTime = auditTime;
    }

    public String getAuditTime() 
    {
        return auditTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djId", getDjId())
            .append("nodeNo", getNodeNo())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("auditInfo", getAuditInfo())
            .append("auditTime", getAuditTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

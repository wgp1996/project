package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 费用报销单对象 fee_apply_info
 * 
 * @author xiaoyu
 * @date 2021-04-16
 */
public class FeeApplyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 类型 */
    @Excel(name = "类型")
    private Integer djType;

    /** 流程号 */
    @Excel(name = "流程号")
    private String flowNo;

    /** 节点编号 */
    @Excel(name = "节点编号")
    private Integer nodeNo;

    /** 是否审批 */
    @Excel(name = "是否审批")
    private Integer isSp;

    private String rows;

    private String fileRows;

    private String statusName;

    private String userId;

    private String  roleId;

    private String type;

    private String auditInfo;

    private Integer spStatus;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getFileRows() {
        return fileRows;
    }

    public void setFileRows(String fileRows) {
        this.fileRows = fileRows;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    /** 报销事由 */
    @Excel(name = "报销事由")
    private String applyContent;

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
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDjType(Integer djType) 
    {
        this.djType = djType;
    }

    public Integer getDjType() 
    {
        return djType;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setNodeNo(Integer nodeNo) 
    {
        this.nodeNo = nodeNo;
    }

    public Integer getNodeNo() 
    {
        return nodeNo;
    }
    public void setIsSp(Integer isSp) 
    {
        this.isSp = isSp;
    }

    public Integer getIsSp() 
    {
        return isSp;
    }
    public void setApplyContent(String applyContent) 
    {
        this.applyContent = applyContent;
    }

    public String getApplyContent() 
    {
        return applyContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("status", getStatus())
            .append("djType", getDjType())
            .append("flowNo", getFlowNo())
            .append("nodeNo", getNodeNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("isSp", getIsSp())
            .append("applyContent", getApplyContent())
            .toString();
    }
}

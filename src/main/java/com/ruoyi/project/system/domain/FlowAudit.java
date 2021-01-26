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
    private String nodeNo;

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
    public void setNodeNo(String nodeNo) 
    {
        this.nodeNo = nodeNo;
    }

    public String getNodeNo() 
    {
        return nodeNo;
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

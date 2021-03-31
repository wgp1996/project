package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购订单对象 purchase_order
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public class PurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
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

    /** 订单类型 */
    @Excel(name = "订单类型")
    private Integer djType;

    /** 供货商编码 */
    @Excel(name = "供货商编码")
    private String khCode;

    /** 供货商名称 */
    @Excel(name = "供货商名称")
    private String khName;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

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

    public Integer getDjType() {
        return djType;
    }

    public void setDjType(Integer djType) {
        this.djType = djType;
    }

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

    public void setKhCode(String khCode)
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setKhName(String khName) 
    {
        this.khName = khName;
    }

    public String getKhName() 
    {
        return khName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
            .append("status", getStatus())
            .append("type", getType())
            .append("khCode", getKhCode())
            .append("khName", getKhName())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("flowNo", getFlowNo())
            .append("nodeNo", getNodeNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("isSp", getIsSp())
            .toString();
    }
}

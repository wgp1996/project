package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 领料出库单对象 picking_delivery
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public class PickingDelivery extends BaseEntity
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

    /** 领料类型 */
    @Excel(name = "领料类型")
    private Integer packType;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 班组编码 */
    @Excel(name = "班组编码")
    private String khCode;

    /** 班组名称 */
    @Excel(name = "班组名称")
    private String khName;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String storeCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;

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

    private String surplusNum;

    private String djType;

    public String getDjType() {
        return djType;
    }

    public void setDjType(String djType) {
        this.djType = djType;
    }

    public String getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
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
    public void setPackType(Integer packType) 
    {
        this.packType = packType;
    }

    public Integer getPackType() 
    {
        return packType;
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
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
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
            .append("packType", getPackType())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("khCode", getKhCode())
            .append("khName", getKhName())
            .append("storeCode", getStoreCode())
            .append("storeName", getStoreName())
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

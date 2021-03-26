package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 产值提报对象 project_report
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public class ProjectReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 天数 */
    @Excel(name = "天数")
    private Integer dayNum;

    /** 单据标题 */
    @Excel(name = "单据标题")
    private String djTitle;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String htDjNumber;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private String htMoney;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 已付金额 */
    @Excel(name = "已付金额")
    private String yfMoney;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private String fpMoney;

    /** 产值金额 */
    @Excel(name = "产值金额")
    private String czMoney;

    /** 流程号 */
    @Excel(name = "流程号")
    private String flowNo;

    /** 节点编号 */
    @Excel(name = "节点编号")
    private Integer nodeNo;

    private String rows;

    private String fileRows;

    private String statusName;

    private String userId;

    private String  roleId;

    private String type;

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public String getDjTitle() {
        return djTitle;
    }

    public void setDjTitle(String djTitle) {
        this.djTitle = djTitle;
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

    /** 是否审批 */
    @Excel(name = "是否审批")
    private Long isSp;

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
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setHtDjNumber(String htDjNumber) 
    {
        this.htDjNumber = htDjNumber;
    }

    public String getHtDjNumber() 
    {
        return htDjNumber;
    }
    public void setHtMoney(String htMoney) 
    {
        this.htMoney = htMoney;
    }

    public String getHtMoney() 
    {
        return htMoney;
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
    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setYfMoney(String yfMoney) 
    {
        this.yfMoney = yfMoney;
    }

    public String getYfMoney() 
    {
        return yfMoney;
    }
    public void setFpMoney(String fpMoney) 
    {
        this.fpMoney = fpMoney;
    }

    public String getFpMoney() 
    {
        return fpMoney;
    }
    public void setCzMoney(String czMoney) 
    {
        this.czMoney = czMoney;
    }

    public String getCzMoney() 
    {
        return czMoney;
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
    public void setIsSp(Long isSp) 
    {
        this.isSp = isSp;
    }

    public Long getIsSp() 
    {
        return isSp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("status", getStatus())
            .append("djTime", getDjTime())
            .append("htDjNumber", getHtDjNumber())
            .append("htMoney", getHtMoney())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("yfMoney", getYfMoney())
            .append("fpMoney", getFpMoney())
            .append("czMoney", getCzMoney())
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

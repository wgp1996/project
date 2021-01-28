package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 总包合同对象 contract_genera
 * 
 * @author ruoyi
 * @date 2021-01-26
 */
public class ContractGenera extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 是否审批 */
    @Excel(name = "是否审批")
    private Integer isSp;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 甲方编码 */
    @Excel(name = "甲方编码")
    private String projectJpersonCode;

    /** 甲方名称 */
    @Excel(name = "甲方名称")
    private String projectJpersonName;

    /** 乙方编码 */
    @Excel(name = "乙方编码")
    private String projectYpersonCode;

    /** 乙方名称 */
    @Excel(name = "乙方名称")
    private String projectYpersonName;

    /** 丙方编码 */
    @Excel(name = "丙方编码")
    private String projectBpersonCode;

    /** 丙方名称 */
    @Excel(name = "丙方名称")
    private String projectBpersonName;

    /** 合同总额 */
    @Excel(name = "合同总额")
    private String contractMoney;

    /** 流程号 */
    @Excel(name = "流程号")
    private String flowNo;

    /** 节点编号 */
    @Excel(name = "节点编号")
    private Integer nodeNo;

    private String fileRows;
    private String statusName;

    private String type;
    private String userId;
    private String roleId;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSp() {
        return isSp;
    }

    public void setIsSp(Integer isSp) {
        this.isSp = isSp;
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
    public void setProjectJpersonCode(String projectJpersonCode) 
    {
        this.projectJpersonCode = projectJpersonCode;
    }

    public String getProjectJpersonCode() 
    {
        return projectJpersonCode;
    }
    public void setProjectJpersonName(String projectJpersonName) 
    {
        this.projectJpersonName = projectJpersonName;
    }

    public String getProjectJpersonName() 
    {
        return projectJpersonName;
    }
    public void setProjectYpersonCode(String projectYpersonCode) 
    {
        this.projectYpersonCode = projectYpersonCode;
    }

    public String getProjectYpersonCode() 
    {
        return projectYpersonCode;
    }
    public void setProjectYpersonName(String projectYpersonName) 
    {
        this.projectYpersonName = projectYpersonName;
    }

    public String getProjectYpersonName() 
    {
        return projectYpersonName;
    }
    public void setProjectBpersonCode(String projectBpersonCode) 
    {
        this.projectBpersonCode = projectBpersonCode;
    }

    public String getProjectBpersonCode() 
    {
        return projectBpersonCode;
    }
    public void setProjectBpersonName(String projectBpersonName) 
    {
        this.projectBpersonName = projectBpersonName;
    }

    public String getProjectBpersonName() 
    {
        return projectBpersonName;
    }
    public void setContractMoney(String contractMoney) 
    {
        this.contractMoney = contractMoney;
    }

    public String getContractMoney() 
    {
        return contractMoney;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())
            .append("status", getStatus())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("projectJpersonCode", getProjectJpersonCode())
            .append("projectJpersonName", getProjectJpersonName())
            .append("projectYpersonCode", getProjectYpersonCode())
            .append("projectYpersonName", getProjectYpersonName())
            .append("projectBpersonCode", getProjectBpersonCode())
            .append("projectBpersonName", getProjectBpersonName())
            .append("contractMoney", getContractMoney())
            .append("flowNo", getFlowNo())
            .append("nodeNo", getNodeNo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

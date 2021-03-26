package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 分包合同对象 project_contract
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public class ProjectContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 合约名称 */
    @Excel(name = "合约名称")
    private String contractName;

    /** 合约编号 */
    @Excel(name = "合约编号")
    private String contractCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 工种 */
    @Excel(name = "工种")
    private String projectTypeWork;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String lxrPhone;

    /** 合约金额 */
    @Excel(name = "合约金额")
    private String contractMoney;

    /** 合同乙方 */
    @Excel(name = "合同乙方")
    private String khName;
    /** 合同乙方编码 */
    @Excel(name = "合同乙方编码")
    private String khCode;

    /** 状态 */
    private Integer status;

    /** 流程号 */
    private String flowNo;

    /** 节点编号 */
    private Integer nodeNo;

    /** 是否审批 */
    private Integer isSp;

    private String rows;

    private String fileRows;

    private String statusName;

    private String userId;

    private String roleId;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }

    public String getKhCode() {
        return khCode;
    }

    public void setKhCode(String khCode) {
        this.khCode = khCode;
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
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractCode(String contractCode) 
    {
        this.contractCode = contractCode;
    }

    public String getContractCode() 
    {
        return contractCode;
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
    public void setProjectTypeWork(String projectTypeWork) 
    {
        this.projectTypeWork = projectTypeWork;
    }

    public String getProjectTypeWork() 
    {
        return projectTypeWork;
    }
    public void setLxrPhone(String lxrPhone) 
    {
        this.lxrPhone = lxrPhone;
    }

    public String getLxrPhone() 
    {
        return lxrPhone;
    }
    public void setContractMoney(String contractMoney) 
    {
        this.contractMoney = contractMoney;
    }

    public String getContractMoney() 
    {
        return contractMoney;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("contractName", getContractName())
            .append("contractCode", getContractCode())
            .append("projectName", getProjectName())
            .append("projectCode", getProjectCode())
            .append("projectTypeWork", getProjectTypeWork())
            .append("lxrPhone", getLxrPhone())
            .append("contractMoney", getContractMoney())
            .append("status", getStatus())
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

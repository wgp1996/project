package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 立项申请对象 project_apply
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public class ProjectApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 工程来源 */
    @Excel(name = "工程来源")
    private String engineerFrom;

    /** 工程编号 */
    @Excel(name = "工程编号")
    private String engineerCode;

    /** 工程名称 */
    @Excel(name = "工程名称")
    private String engineerName;

    /** 是否正式编码 */
    @Excel(name = "是否正式编码")
    private Long isFormal;

    /** 是否生成总包合同 */
    @Excel(name = "是否生成总包合同")
    private Long isZb;

    /** 建设内容 */
    @Excel(name = "建设内容")
    private String createContent;

    /** 工程地址 */
    @Excel(name = "工程地址")
    private String engineerAddress;

    /** 建设单位 */
    @Excel(name = "建设单位")
    private String engineerUnit;

    /** 联系人 */
    @Excel(name = "联系人")
    private String enginnerLxr;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String enginnerLxrPhone;

    /** 施工面积 */
    @Excel(name = "施工面积")
    private String enginnerArea;

    /** 设备 */
    @Excel(name = "设备")
    private String enginnerDevice;

    /** 周期 */
    @Excel(name = "周期")
    private Long enginnerTime;

    /** 工程开始日期 */
    @Excel(name = "工程开始日期")
    private String enginnerStartTime;

    /** 工程结束日期 */
    @Excel(name = "工程结束日期")
    private String enginnerEndTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    @Excel(name = "单号")
    private String djNumber;

    private Integer isSp;
    private String flowNo;
    private Integer nodeNo;
    private String statusName;
    private String type;
    private String rows;

    private String fileRows;

    private String userId;

    private String roleId;

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

    public String getDjNumber() {
        return djNumber;
    }

    public void setDjNumber(String djNumber) {
        this.djNumber = djNumber;
    }

    public Integer getIsSp() {
        return isSp;
    }

    public void setIsSp(Integer isSp) {
        this.isSp = isSp;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFileRows() {
        return fileRows;
    }

    public void setFileRows(String fileRows) {
        this.fileRows = fileRows;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEngineerFrom(String engineerFrom) 
    {
        this.engineerFrom = engineerFrom;
    }

    public String getEngineerFrom() 
    {
        return engineerFrom;
    }
    public void setEngineerCode(String engineerCode) 
    {
        this.engineerCode = engineerCode;
    }

    public String getEngineerCode() 
    {
        return engineerCode;
    }
    public void setEngineerName(String engineerName) 
    {
        this.engineerName = engineerName;
    }

    public String getEngineerName() 
    {
        return engineerName;
    }
    public void setIsFormal(Long isFormal) 
    {
        this.isFormal = isFormal;
    }

    public Long getIsFormal() 
    {
        return isFormal;
    }
    public void setIsZb(Long isZb) 
    {
        this.isZb = isZb;
    }

    public Long getIsZb() 
    {
        return isZb;
    }
    public void setCreateContent(String createContent) 
    {
        this.createContent = createContent;
    }

    public String getCreateContent() 
    {
        return createContent;
    }
    public void setEngineerAddress(String engineerAddress) 
    {
        this.engineerAddress = engineerAddress;
    }

    public String getEngineerAddress() 
    {
        return engineerAddress;
    }
    public void setEngineerUnit(String engineerUnit) 
    {
        this.engineerUnit = engineerUnit;
    }

    public String getEngineerUnit() 
    {
        return engineerUnit;
    }
    public void setEnginnerLxr(String enginnerLxr) 
    {
        this.enginnerLxr = enginnerLxr;
    }

    public String getEnginnerLxr() 
    {
        return enginnerLxr;
    }
    public void setEnginnerLxrPhone(String enginnerLxrPhone) 
    {
        this.enginnerLxrPhone = enginnerLxrPhone;
    }

    public String getEnginnerLxrPhone() 
    {
        return enginnerLxrPhone;
    }
    public void setEnginnerArea(String enginnerArea) 
    {
        this.enginnerArea = enginnerArea;
    }

    public String getEnginnerArea() 
    {
        return enginnerArea;
    }
    public void setEnginnerDevice(String enginnerDevice) 
    {
        this.enginnerDevice = enginnerDevice;
    }

    public String getEnginnerDevice() 
    {
        return enginnerDevice;
    }
    public void setEnginnerTime(Long enginnerTime) 
    {
        this.enginnerTime = enginnerTime;
    }

    public Long getEnginnerTime() 
    {
        return enginnerTime;
    }
    public void setEnginnerStartTime(String enginnerStartTime) 
    {
        this.enginnerStartTime = enginnerStartTime;
    }

    public String getEnginnerStartTime() 
    {
        return enginnerStartTime;
    }
    public void setEnginnerEndTime(String enginnerEndTime) 
    {
        this.enginnerEndTime = enginnerEndTime;
    }

    public String getEnginnerEndTime() 
    {
        return enginnerEndTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("engineerFrom", getEngineerFrom())
            .append("engineerCode", getEngineerCode())
            .append("engineerName", getEngineerName())
            .append("isFormal", getIsFormal())
            .append("isZb", getIsZb())
            .append("createContent", getCreateContent())
            .append("engineerAddress", getEngineerAddress())
            .append("engineerUnit", getEngineerUnit())
            .append("enginnerLxr", getEnginnerLxr())
            .append("enginnerLxrPhone", getEnginnerLxrPhone())
            .append("enginnerArea", getEnginnerArea())
            .append("enginnerDevice", getEnginnerDevice())
            .append("enginnerTime", getEnginnerTime())
            .append("enginnerStartTime", getEnginnerStartTime())
            .append("enginnerEndTime", getEnginnerEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }
}

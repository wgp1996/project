package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务管理对象 task_info
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
public class TaskInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务编码 */
    @Excel(name = "任务编码")
    private String taskCode;

    /** 截止日期 */
    @Excel(name = "截止日期")
    private String taskEndTime;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 文件 */
    @Excel(name = "文件")
    private String fileName;

    /** 执行人编码 */
    @Excel(name = "执行人编码")
    private String implementUserCode;

    /** 执行人名称 */
    @Excel(name = "执行人名称")
    private String implementUserName;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Integer status;

    /** 任务优先级状态 */
    @Excel(name = "任务优先级状态")
    private Integer urgentStatus;

    /** 是否催办 */
    @Excel(name = "是否催办")
    private Integer isUrge;

    /** 是否阅读 */
    @Excel(name = "是否阅读")
    private Integer isRead;

    /** 任务进度 */
    @Excel(name = "任务进度")
    private String taskNum;

    /** 通过或不通过建议 */
    @Excel(name = "通过或不通过建议")
    private String message;

    /** 评价星星数量 */
    @Excel(name = "评价星星数量")
    private Integer resultNum;

    /** 派给我的是否已读 */
    @Excel(name = "是否已读")
    private Integer sendIsRead;

    public Integer getSendIsRead() {
        return sendIsRead;
    }
    private String statusName;

    private String urgentStatusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUrgentStatusName() {
        return urgentStatusName;
    }

    public void setUrgentStatusName(String urgentStatusName) {
        this.urgentStatusName = urgentStatusName;
    }

    public void setSendIsRead(Integer sendIsRead) {
        this.sendIsRead = sendIsRead;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskCode(String taskCode) 
    {
        this.taskCode = taskCode;
    }

    public String getTaskCode() 
    {
        return taskCode;
    }
    public void setTaskEndTime(String taskEndTime) 
    {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskEndTime() 
    {
        return taskEndTime;
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
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setImplementUserCode(String implementUserCode) 
    {
        this.implementUserCode = implementUserCode;
    }

    public String getImplementUserCode() 
    {
        return implementUserCode;
    }
    public void setImplementUserName(String implementUserName) 
    {
        this.implementUserName = implementUserName;
    }

    public String getImplementUserName() 
    {
        return implementUserName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUrgentStatus(Integer urgentStatus) 
    {
        this.urgentStatus = urgentStatus;
    }

    public Integer getUrgentStatus() {
        return urgentStatus;
    }

    public Integer getIsUrge() {
        return isUrge;
    }

    public void setIsUrge(Integer isUrge) {
        this.isUrge = isUrge;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getResultNum() {
        return resultNum;
    }

    public void setResultNum(Integer resultNum) {
        this.resultNum = resultNum;
    }

    public void setTaskNum(String taskNum)
    {
        this.taskNum = taskNum;
    }

    public String getTaskNum() 
    {
        return taskNum;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskName", getTaskName())
            .append("taskCode", getTaskCode())
            .append("taskEndTime", getTaskEndTime())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("fileName", getFileName())
            .append("implementUserCode", getImplementUserCode())
            .append("implementUserName", getImplementUserName())
            .append("status", getStatus())
            .append("urgentStatus", getUrgentStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("isUrge", getIsUrge())
            .append("isRead", getIsRead())
            .append("taskNum", getTaskNum())
            .append("message", getMessage())
            .append("resultNum", getResultNum())
            .toString();
    }
}

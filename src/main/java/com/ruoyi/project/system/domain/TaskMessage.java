package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务对话详情对象 task_message
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
public class TaskMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private Integer type;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String message;

    /** 发送日期 */
    @Excel(name = "发送日期")
    private String time;

    /** 通过后星星数量 */
    @Excel(name = "通过后星星数量")
    private Integer num;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务编码 */
    @Excel(name = "任务编码")
    private String taskCode;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getTime() 
    {
        return time;
    }
    public void setNum(Integer num) 
    {
        this.num = num;
    }

    public Integer getNum() 
    {
        return num;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("message", getMessage())
            .append("time", getTime())
            .append("num", getNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("taskName", getTaskName())
            .append("taskCode", getTaskCode())
            .toString();
    }
}

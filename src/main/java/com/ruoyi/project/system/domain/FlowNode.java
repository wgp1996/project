package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 节点表对象 flow_node
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public class FlowNode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 流程编号 */
    @Excel(name = "流程编号")
    private String flowNo;

    /** 节点编号 */
    @Excel(name = "节点编号")
    private String nodeNo;

    /** 顺序 */
    @Excel(name = "顺序")
    private String nodeNum;

    /** 是否角色 */
    @Excel(name = "是否角色")
    private Integer isRole;

    /** 人员或角色id */
    @Excel(name = "人员或角色id")
    private String prId;

    /** 人员或角色名称 */
    @Excel(name = "人员或角色名称")
    private String prName;

    /** 是否允许结束 */
    @Excel(name = "是否允许结束")
    private Integer isEnd;

    private String isRoleName;
    private String isEndName;

    public String getIsRoleName() {
        return isRoleName;
    }

    public void setIsRoleName(String isRoleName) {
        this.isRoleName = isRoleName;
    }

    public String getIsEndName() {
        return isEndName;
    }

    public void setIsEndName(String isEndName) {
        this.isEndName = isEndName;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }
    public void setNodeNo(String nodeNo) 
    {
        this.nodeNo = nodeNo;
    }

    public String getNodeNo() 
    {
        return nodeNo;
    }
    public void setNodeNum(String nodeNum) 
    {
        this.nodeNum = nodeNum;
    }

    public String getNodeNum() 
    {
        return nodeNum;
    }
    public void setIsRole(Integer isRole) 
    {
        this.isRole = isRole;
    }

    public Integer getIsRole() 
    {
        return isRole;
    }
    public void setPrId(String prId) 
    {
        this.prId = prId;
    }

    public String getPrId() 
    {
        return prId;
    }
    public void setIsEnd(Integer isEnd) 
    {
        this.isEnd = isEnd;
    }

    public Integer getIsEnd() 
    {
        return isEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flowNo", getFlowNo())
            .append("nodeNo", getNodeNo())
            .append("nodeNum", getNodeNum())
            .append("isRole", getIsRole())
            .append("prId", getPrId())
            .append("isEnd", getIsEnd())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

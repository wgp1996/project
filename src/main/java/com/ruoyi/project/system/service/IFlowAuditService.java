package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.FlowAudit;

/**
 * 审批表Service接口
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public interface IFlowAuditService 
{
    /**
     * 查询审批表
     * 
     * @param id 审批表ID
     * @return 审批表
     */
    public FlowAudit selectFlowAuditById(Integer id);


    /**
     * 根据单号跟节点查询流程信息
     *
     * @param djId 单号/ID
     * @param nodeNo 节点编号
     * @return 审批表
     */
    public FlowAudit selectFlowAuditNoAndDjId(String djId,Integer nodeNo);
    /**
     * 查询审批表列表
     * 
     * @param flowAudit 审批表
     * @return 审批表集合
     */
    public List<FlowAudit> selectFlowAuditList(FlowAudit flowAudit);

    /**
     获取末级节点
     */
    public Integer getEndNode(String djId);
    /**
     * 新增审批表
     * 
     * @param flowAudit 审批表
     * @return 结果
     */
    public int insertFlowAudit(FlowAudit flowAudit);

    /**
     * 修改审批表
     * 
     * @param flowAudit 审批表
     * @return 结果
     */
    public int updateFlowAudit(FlowAudit flowAudit);

    /**
     * 批量删除审批表
     * 
     * @param ids 需要删除的审批表ID
     * @return 结果
     */
    public int deleteFlowAuditByIds(Integer[] ids);

    /**
     * 删除审批表信息
     * 
     * @param id 审批表ID
     * @return 结果
     */
    public int deleteFlowAuditById(Integer id);

    /**
     * 根据单据ID/编码删除审批表信息
     *
     * @param djId 审批表单据ID
     * @return 结果
     */
    public int deleteFlowAuditByDjId(String  djId);
    /**
     * 根据单据ID/编码修改审批为历史流程
     *
     * @param djId 审批表单据ID
     * @return 结果
     */
    public int updateFlowAuditByHistory(String  djId);

}

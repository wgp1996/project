package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FlowAuditMapper;
import com.ruoyi.project.system.domain.FlowAudit;
import com.ruoyi.project.system.service.IFlowAuditService;

/**
 * 审批表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@Service
public class FlowAuditServiceImpl implements IFlowAuditService 
{
    @Autowired
    private FlowAuditMapper flowAuditMapper;

    /**
     * 查询审批表
     * 
     * @param id 审批表ID
     * @return 审批表
     */
    @Override
    public FlowAudit selectFlowAuditById(Integer id)
    {
        return flowAuditMapper.selectFlowAuditById(id);
    }

    /**
     * 查询审批表列表
     * 
     * @param flowAudit 审批表
     * @return 审批表
     */
    @Override
    public List<FlowAudit> selectFlowAuditList(FlowAudit flowAudit)
    {
        return flowAuditMapper.selectFlowAuditList(flowAudit);
    }

    /**
     * 新增审批表
     * 
     * @param flowAudit 审批表
     * @return 结果
     */
    @Override
    public int insertFlowAudit(FlowAudit flowAudit)
    {
        flowAudit.setCreateTime(DateUtils.getNowDate());
        return flowAuditMapper.insertFlowAudit(flowAudit);
    }

    /**
     * 修改审批表
     * 
     * @param flowAudit 审批表
     * @return 结果
     */
    @Override
    public int updateFlowAudit(FlowAudit flowAudit)
    {
        flowAudit.setUpdateTime(DateUtils.getNowDate());
        return flowAuditMapper.updateFlowAudit(flowAudit);
    }

    /**
     * 批量删除审批表
     * 
     * @param ids 需要删除的审批表ID
     * @return 结果
     */
    @Override
    public int deleteFlowAuditByIds(Integer[] ids)
    {
        return flowAuditMapper.deleteFlowAuditByIds(ids);
    }

    /**
     * 删除审批表信息
     * 
     * @param id 审批表ID
     * @return 结果
     */
    @Override
    public int deleteFlowAuditById(Integer id)
    {
        return flowAuditMapper.deleteFlowAuditById(id);
    }
}

package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FlowInfoMapper;
import com.ruoyi.project.system.domain.FlowInfo;
import com.ruoyi.project.system.service.IFlowInfoService;

/**
 * 流程表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@Service
public class FlowInfoServiceImpl implements IFlowInfoService 
{
    @Autowired
    private FlowInfoMapper flowInfoMapper;

    /**
     * 查询流程表
     * 
     * @param id 流程表ID
     * @return 流程表
     */
    @Override
    public FlowInfo selectFlowInfoById(Integer id)
    {
        return flowInfoMapper.selectFlowInfoById(id);
    }

    /**
     * 查询流程表列表
     * 
     * @param flowInfo 流程表
     * @return 流程表
     */
    @Override
    public List<FlowInfo> selectFlowInfoList(FlowInfo flowInfo)
    {
        return flowInfoMapper.selectFlowInfoList(flowInfo);
    }

    /**
     * 新增流程表
     * 
     * @param flowInfo 流程表
     * @return 结果
     */
    @Override
    public int insertFlowInfo(FlowInfo flowInfo)
    {
        flowInfo.setCreateTime(DateUtils.getNowDate());
        return flowInfoMapper.insertFlowInfo(flowInfo);
    }

    /**
     * 修改流程表
     * 
     * @param flowInfo 流程表
     * @return 结果
     */
    @Override
    public int updateFlowInfo(FlowInfo flowInfo)
    {
        flowInfo.setUpdateTime(DateUtils.getNowDate());
        return flowInfoMapper.updateFlowInfo(flowInfo);
    }

    /**
     * 批量删除流程表
     * 
     * @param ids 需要删除的流程表ID
     * @return 结果
     */
    @Override
    public int deleteFlowInfoByIds(Integer[] ids)
    {
        return flowInfoMapper.deleteFlowInfoByIds(ids);
    }

    /**
     * 删除流程表信息
     * 
     * @param id 流程表ID
     * @return 结果
     */
    @Override
    public int deleteFlowInfoById(Integer id)
    {
        return flowInfoMapper.deleteFlowInfoById(id);
    }
}

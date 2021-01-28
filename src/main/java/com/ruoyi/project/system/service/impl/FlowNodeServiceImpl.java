package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FlowNodeMapper;
import com.ruoyi.project.system.domain.FlowNode;
import com.ruoyi.project.system.service.IFlowNodeService;

/**
 * 节点表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@Service
public class FlowNodeServiceImpl implements IFlowNodeService 
{
    @Autowired
    private FlowNodeMapper flowNodeMapper;

    /**
     * 查询节点表
     * 
     * @param id 节点表ID
     * @return 节点表
     */
    @Override
    public FlowNode selectFlowNodeById(Integer id)
    {
        return flowNodeMapper.selectFlowNodeById(id);
    }

    /**
     * 查询节点表列表
     * 
     * @param flowNode 节点表
     * @return 节点表
     */
    @Override
    public List<FlowNode> selectFlowNodeList(FlowNode flowNode)
    {
        return flowNodeMapper.selectFlowNodeList(flowNode);
    }
    /**
     * 获取末级节点
     * @param flowNo
     * @return
     */
    @Override
    public String getEndFlowNode(String flowNo){
        return flowNodeMapper.getEndFlowNode(flowNo);
    }
    /**
     * 新增节点表
     * 
     * @param flowNode 节点表
     * @return 结果
     */
    @Override
    public int insertFlowNode(FlowNode flowNode)
    {
        flowNode.setCreateTime(DateUtils.getNowDate());
        return flowNodeMapper.insertFlowNode(flowNode);
    }

    /**
     * 修改节点表
     * 
     * @param flowNode 节点表
     * @return 结果
     */
    @Override
    public int updateFlowNode(FlowNode flowNode)
    {
        flowNode.setUpdateTime(DateUtils.getNowDate());
        return flowNodeMapper.updateFlowNode(flowNode);
    }

    /**
     * 批量删除节点表
     * 
     * @param ids 需要删除的节点表ID
     * @return 结果
     */
    @Override
    public int deleteFlowNodeByIds(Integer[] ids)
    {
        return flowNodeMapper.deleteFlowNodeByIds(ids);
    }

    /**
     * 删除节点表信息
     * 
     * @param id 节点表ID
     * @return 结果
     */
    @Override
    public int deleteFlowNodeById(Integer id)
    {
        return flowNodeMapper.deleteFlowNodeById(id);
    }
    /**
     * 批量删除节点
     * @param ids
     * @return
     */
    @Override
    public int deleteFlowNodeByPid(Integer[] ids){
        return flowNodeMapper.deleteFlowNodeByPid(ids);
    }
}

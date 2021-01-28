package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.FlowNode;

/**
 * 节点表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public interface FlowNodeMapper 
{
    /**
     * 查询节点表
     * 
     * @param id 节点表ID
     * @return 节点表
     */
    public FlowNode selectFlowNodeById(Integer id);

    /**
     * 查询节点表列表
     * 
     * @param flowNode 节点表
     * @return 节点表集合
     */
    public List<FlowNode> selectFlowNodeList(FlowNode flowNode);

    /**
     * 新增节点表
     * 
     * @param flowNode 节点表
     * @return 结果
     */
    public int insertFlowNode(FlowNode flowNode);
    /**
     * 获取末级节点
     * @param flowNo
     * @return
     */
    public String getEndFlowNode(String flowNo);

    /**
     * 修改节点表
     * 
     * @param flowNode 节点表
     * @return 结果
     */
    public int updateFlowNode(FlowNode flowNode);

    /**
     * 删除节点表
     * 
     * @param id 节点表ID
     * @return 结果
     */
    public int deleteFlowNodeById(Integer id);

    /**
     * 批量删除节点表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlowNodeByIds(Integer[] ids);

    /**
     * 批量删除节点
     * @param ids
     * @return
     */
    public int deleteFlowNodeByPid(Integer[] ids);


}

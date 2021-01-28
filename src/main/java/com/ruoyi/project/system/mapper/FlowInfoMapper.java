package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.FlowInfo;

/**
 * 流程表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public interface FlowInfoMapper 
{
    /**
     * 查询流程表
     * 
     * @param id 流程表ID
     * @return 流程表
     */
    public FlowInfo selectFlowInfoById(Integer id);

    /**
     * 查询流程表列表
     * 
     * @param flowInfo 流程表
     * @return 流程表集合
     */
    public List<FlowInfo> selectFlowInfoList(FlowInfo flowInfo);

    /**
     * 新增流程表
     * 
     * @param flowInfo 流程表
     * @return 结果
     */
    public int insertFlowInfo(FlowInfo flowInfo);
    /**
     * 检查流程是否重复
     * @param flowNo
     * @param id
     * @return
     */
    public int checkFlow(String flowNo,Integer id);
    /**
     * 修改流程表
     * 
     * @param flowInfo 流程表
     * @return 结果
     */
    public int updateFlowInfo(FlowInfo flowInfo);

    /**
     * 删除流程表
     * 
     * @param id 流程表ID
     * @return 结果
     */
    public int deleteFlowInfoById(Integer id);

    /**
     * 批量删除流程表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlowInfoByIds(Integer[] ids);
}

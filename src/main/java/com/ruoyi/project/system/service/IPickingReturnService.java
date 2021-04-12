package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.project.system.domain.FlowAudit;
import com.ruoyi.project.system.domain.PickingReturn;

/**
 * 借用归还单Service接口
 * 
 * @author xy
 * @date 2021-04-12
 */
public interface IPickingReturnService 
{
    /**
     * 查询借用归还单
     * 
     * @param id 借用归还单ID
     * @return 借用归还单
     */
    public PickingReturn selectPickingReturnById(Integer id);

    /**
     * 查询借用归还单列表
     * 
     * @param pickingReturn 借用归还单
     * @return 借用归还单集合
     */
    public List<PickingReturn> selectPickingReturnList(PickingReturn pickingReturn);

    /**
     * 查询借用归还单审核列表
     *
     * @param pickingReturn 借用归还单
     * @return 借用归还单集合
     */
    public List<PickingReturn> selectPickingReturnShList(PickingReturn pickingReturn);

    /**
     * 新增借用归还单
     * 
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    public int insertPickingReturn(PickingReturn pickingReturn);

    /**
     * 修改借用归还单
     * 
     * @param pickingReturn 借用归还单
     * @return 结果
     */
    public int updatePickingReturn(PickingReturn pickingReturn);

    /**
     * 批量删除借用归还单
     * 
     * @param ids 需要删除的借用归还单ID
     * @return 结果
     */
    public int deletePickingReturnByIds(Integer[] ids);

    //提交
    public int effect(Integer[] ids);
    //取消提交
    public int cancel(Integer[] ids);
    //审核
    public int examine(FlowAudit flowAudit);
    //取消提交
    public int cancelAudit(String[] djIds,Integer[] nodeNos);
    /**
     * 删除借用归还单信息
     * 
     * @param id 借用归还单ID
     * @return 结果
     */
    public int deletePickingReturnById(Integer id);
}

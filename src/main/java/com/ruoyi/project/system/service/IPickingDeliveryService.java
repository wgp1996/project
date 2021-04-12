package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.project.system.domain.FlowAudit;
import com.ruoyi.project.system.domain.PickingDelivery;

/**
 * 领料出库单Service接口
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public interface IPickingDeliveryService 
{
    /**
     * 查询领料出库单
     * 
     * @param id 领料出库单ID
     * @return 领料出库单
     */
    public PickingDelivery selectPickingDeliveryById(Integer id);

    /**
     * 查询领料出库单列表
     * 
     * @param pickingDelivery 领料出库单
     * @return 领料出库单集合
     */
    public List<PickingDelivery> selectPickingDeliveryList(PickingDelivery pickingDelivery);
    /**
     * 查询领料出库单审核列表
     *
     * @param pickingDelivery 领料出库单
     * @return 领料出库单
     */
    public List<PickingDelivery> selectPickingDeliveryShList(PickingDelivery pickingDelivery);
    /**
     * 新增领料出库单
     * 
     * @param pickingDelivery 领料出库单
     * @return 结果
     */
    public int insertPickingDelivery(PickingDelivery pickingDelivery);


    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetPickingDeliveryStatusOrNodeNo(String djNumber,Integer status,int type);

    //提交
    public int effect(Integer[] ids);
    //取消提交
    public int cancel(Integer[] ids);
    //审核
    public int examine(FlowAudit flowAudit);
    //取消提交
    public int cancelAudit(String[] djIds,Integer[] nodeNos);
    /**
     * 修改领料出库单
     * 
     * @param pickingDelivery 领料出库单
     * @return 结果
     */
    public int updatePickingDelivery(PickingDelivery pickingDelivery);

    /**
     * 批量删除领料出库单
     * 
     * @param ids 需要删除的领料出库单ID
     * @return 结果
     */
    public int deletePickingDeliveryByIds(Integer[] ids);

    /**
     * 删除领料出库单信息
     * 
     * @param id 领料出库单ID
     * @return 结果
     */
    public int deletePickingDeliveryById(Integer id);
}

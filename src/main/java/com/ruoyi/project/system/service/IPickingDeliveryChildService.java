package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PickingDeliveryChild;

/**
 * 领料出库单子表Service接口
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public interface IPickingDeliveryChildService 
{
    /**
     * 查询领料出库单子表
     * 
     * @param id 领料出库单子表ID
     * @return 领料出库单子表
     */
    public PickingDeliveryChild selectPickingDeliveryChildById(String id);

    /**
     * 查询领料出库单子表列表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 领料出库单子表集合
     */
    public List<PickingDeliveryChild> selectPickingDeliveryChildList(PickingDeliveryChild pickingDeliveryChild);

    /**
     * 新增领料出库单子表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 结果
     */
    public int insertPickingDeliveryChild(PickingDeliveryChild pickingDeliveryChild);

    /**
     * 修改领料出库单子表
     * 
     * @param pickingDeliveryChild 领料出库单子表
     * @return 结果
     */
    public int updatePickingDeliveryChild(PickingDeliveryChild pickingDeliveryChild);

    /**
     * 批量删除领料出库单子表
     * 
     * @param ids 需要删除的领料出库单子表ID
     * @return 结果
     */
    public int deletePickingDeliveryChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除领料出库单子表
     *
     * @param ids 需要删除的领料出库单主表ID
     * @return 结果
     */
    public int deletePickingDeliveryChildByPIds(Integer[] ids);

    /**
     * 删除领料出库单子表信息
     * 
     * @param id 领料出库单子表ID
     * @return 结果
     */
    public int deletePickingDeliveryChildById(String id);
}

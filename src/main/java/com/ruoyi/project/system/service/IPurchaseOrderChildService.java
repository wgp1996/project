package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PurchaseOrderChild;

/**
 * 采购订单子表Service接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface IPurchaseOrderChildService 
{
    /**
     * 查询采购订单子表
     * 
     * @param id 采购订单子表ID
     * @return 采购订单子表
     */
    public PurchaseOrderChild selectPurchaseOrderChildById(String id);

    /**
     * 查询采购订单子表列表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 采购订单子表集合
     */
    public List<PurchaseOrderChild> selectPurchaseOrderChildList(PurchaseOrderChild purchaseOrderChild);

    /**
     * 入库单选择采购订单
     *
     * @return 采购订单子表集合
     */
    public List<PurchaseOrderChild> selectPurchaseOrderListByWave(PurchaseOrderChild purchaseOrder);



    /**
     * 新增采购订单子表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 结果
     */
    public int insertPurchaseOrderChild(PurchaseOrderChild purchaseOrderChild);

    /**
     * 取消批复 恢复单价
     *
     * @param djNumber 单号
     * @return 结果
     */
    public int updateOrderPriceByReply(String djNumber);



    /**
     * 修改采购订单子表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 结果
     */
    public int updatePurchaseOrderChild(PurchaseOrderChild purchaseOrderChild);

    /**
     * 批量删除采购订单子表
     * 
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    public int deletePurchaseOrderChildByIds(String[] ids);

    /**
     * 根据主表ID批量删除采购订单子表
     *
     * @param ids 需要删除的采购订单主表ID
     * @return 结果
     */
    public int deletePurchaseOrderChildByPIds(Integer[] ids);



    /**
     * 删除采购订单子表信息
     * 
     * @param id 采购订单子表ID
     * @return 结果
     */
    public int deletePurchaseOrderChildById(String id);
}

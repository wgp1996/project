package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PurchaseWareChild;

/**
 * 采购入库子表Service接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface IPurchaseWareChildService
{
    /**
     * 查询采购入库子表
     * 
     * @param id 采购入库子表ID
     * @return 采购入库子表
     */
    public PurchaseWareChild selectPurchaseWareChildById(String id);

    /**
     * 查询采购入库子表列表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 采购入库子表集合
     */
    public List<PurchaseWareChild> selectPurchaseWareChildList(PurchaseWareChild PurchaseWareChild);

    /**
     * 采购接收单查询采购入库单列表
     *
     * @param PurchaseWareChild 采购入库子表
     * @return 采购入库子表集合
     */
    public List<PurchaseWareChild> selectPurchaseWareListBySettlement(PurchaseWareChild PurchaseWareChild);

    /**
     * 新增采购入库子表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 结果
     */
    public int insertPurchaseWareChild(PurchaseWareChild PurchaseWareChild);

    /**
     * 修改采购入库子表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 结果
     */
    public int updatePurchaseWareChild(PurchaseWareChild PurchaseWareChild);

    /**
     * 批量删除采购入库子表
     * 
     * @param ids 需要删除的采购入库子表ID
     * @return 结果
     */
    public int deletePurchaseWareChildByIds(String[] ids);

    /**
     * 根据主表id批量删除采购入库子表
     *
     * @param ids 需要删除的采购入库主表ID
     * @return 结果
     */
    public int deletePurchaseWareChildByPIds(Integer[] ids);



    /**
     * 删除采购入库子表信息
     * 
     * @param id 采购入库子表ID
     * @return 结果
     */
    public int deletePurchaseWareChildById(String id);
}

package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PurchaseWare;

/**
 * 采购入库Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface PurchaseWareMapper 
{
    /**
     * 查询采购入库
     * 
     * @param id 采购入库ID
     * @return 采购入库
     */
    public PurchaseWare selectPurchaseWareById(Integer id);

    /**
     * 查询采购入库列表
     * 
     * @param purchaseWare 采购入库
     * @return 采购入库集合
     */
    public List<PurchaseWare> selectPurchaseWareList(PurchaseWare purchaseWare);

    /**
     * 查询采购入库审核列表
     *
     * @param PurchaseWare 采购入库
     * @return 采购入库集合
     */
    public List<PurchaseWare> selectPurchaseWareShList(PurchaseWare PurchaseWare);


    /**
     * 查询订单是否被引用
     *
     * @param djNumber 入库单单号
     * @return 结果
     */
    public int checkWageOnSettlement(String djNumber);

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetPurchaseWareStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 新增采购入库
     * 
     * @param purchaseWare 采购入库
     * @return 结果
     */
    public int insertPurchaseWare(PurchaseWare purchaseWare);

    /**
     * 修改采购入库
     * 
     * @param purchaseWare 采购入库
     * @return 结果
     */
    public int updatePurchaseWare(PurchaseWare purchaseWare);

    /**
     * 删除采购入库
     * 
     * @param id 采购入库ID
     * @return 结果
     */
    public int deletePurchaseWareById(Integer id);


    /**
     * 批量删除采购入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseWareByIds(Integer[] ids);
}

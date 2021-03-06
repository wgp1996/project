package com.ruoyi.project.system.mapper;

import java.util.List;

import com.ruoyi.project.system.domain.ProjectReport;
import com.ruoyi.project.system.domain.PurchaseOrder;

/**
 * 采购订单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface PurchaseOrderMapper 
{
    /**
     * 查询采购订单
     * 
     * @param id 采购订单ID
     * @return 采购订单
     */
    public PurchaseOrder selectPurchaseOrderById(Integer id);

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder);

    /**
     * 查询采购订单审核列表
     *
     * @param purchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<PurchaseOrder> selectPurchaseOrderShList(PurchaseOrder purchaseOrder);

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetPurchaseOrderStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 查询订单是否被引用
     *
     * @param djNumber 采购订单单号
     * @return 结果
     */
    public int checkOrderOnWage(String djNumber);
    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 删除采购订单
     * 
     * @param id 采购订单ID
     * @return 结果
     */
    public int deletePurchaseOrderById(Integer id);

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderByIds(Integer[] ids);
}

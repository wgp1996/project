package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.ProjectReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseOrderMapper;
import com.ruoyi.project.system.domain.PurchaseOrder;
import com.ruoyi.project.system.service.IPurchaseOrderService;

/**
 * 采购订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService 
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单ID
     * @return 采购订单
     */
    @Override
    public PurchaseOrder selectPurchaseOrderById(Integer id)
    {
        return purchaseOrderMapper.selectPurchaseOrderById(id);
    }

    /**
     * 查询采购订单列表
     *
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setCreateTime(DateUtils.getNowDate());
        return purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
    }

    /**
     * 修改采购订单
     *
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setUpdateTime(DateUtils.getNowDate());
        return purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    /**
     * 查询采购订单审核列表
     *
     * @param projectReport 采购订单
     * @return 采购订单集合
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderShList(PurchaseOrder projectReport){
        return purchaseOrderMapper.selectPurchaseOrderShList(projectReport);
    }

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updatetPurchaseOrderStatusOrNodeNo(String djNumber,Integer status,int type){
        return purchaseOrderMapper.updatetPurchaseOrderStatusOrNodeNo(djNumber,status,type);
    }

    /**
     * 批量删除采购订单
     *
     * @param ids 需要删除的采购订单ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderByIds(Integer[] ids)
    {
        return purchaseOrderMapper.deletePurchaseOrderByIds(ids);
    }

    /**
     * 删除采购订单信息
     *
     * @param id 采购订单ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderById(Integer id)
    {
        return purchaseOrderMapper.deletePurchaseOrderById(id);
    }
}

package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseOrderChildMapper;
import com.ruoyi.project.system.domain.PurchaseOrderChild;
import com.ruoyi.project.system.service.IPurchaseOrderChildService;

/**
 * 采购订单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PurchaseOrderChildServiceImpl implements IPurchaseOrderChildService 
{
    @Autowired
    private PurchaseOrderChildMapper purchaseOrderChildMapper;

    /**
     * 查询采购订单子表
     * 
     * @param id 采购订单子表ID
     * @return 采购订单子表
     */
    @Override
    public PurchaseOrderChild selectPurchaseOrderChildById(String id)
    {
        return purchaseOrderChildMapper.selectPurchaseOrderChildById(id);
    }

    /**
     * 查询采购订单子表列表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 采购订单子表
     */
    @Override
    public List<PurchaseOrderChild> selectPurchaseOrderChildList(PurchaseOrderChild purchaseOrderChild)
    {
        return purchaseOrderChildMapper.selectPurchaseOrderChildList(purchaseOrderChild);
    }

    /**
     * 入库单选择采购订单
     *
     * @return 采购订单子表集合
     */
    public List<PurchaseOrderChild> selectPurchaseOrderListByWave(PurchaseOrderChild purchaseOrder){
        return purchaseOrderChildMapper.selectPurchaseOrderListByWave(purchaseOrder);
    }

    /**
     * 新增采购订单子表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 结果
     */
    @Override
    public int insertPurchaseOrderChild(PurchaseOrderChild purchaseOrderChild)
    {
        purchaseOrderChild.setId(StringUtils.getId());
        purchaseOrderChild.setCreateTime(DateUtils.getNowDate());
        return purchaseOrderChildMapper.insertPurchaseOrderChild(purchaseOrderChild);
    }

    /**
     * 取消批复 恢复单价
     *
     * @param djNumber 单号
     * @return 结果
     */
    @Override
    public int updateOrderPriceByReply(String djNumber){
        return purchaseOrderChildMapper.updateOrderPriceByReply(djNumber);
    }


    /**
     * 修改采购订单子表
     * 
     * @param purchaseOrderChild 采购订单子表
     * @return 结果
     */
    @Override
    public int updatePurchaseOrderChild(PurchaseOrderChild purchaseOrderChild)
    {
        purchaseOrderChild.setUpdateTime(DateUtils.getNowDate());
        return purchaseOrderChildMapper.updatePurchaseOrderChild(purchaseOrderChild);
    }

    /**
     * 批量删除采购订单子表
     * 
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderChildByIds(String[] ids)
    {
        return purchaseOrderChildMapper.deletePurchaseOrderChildByIds(ids);
    }
    /**
     * 根据主表ID批量删除采购订单子表
     *
     * @param ids 需要删除的采购订单主表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderChildByPIds(Integer[] ids){
        return purchaseOrderChildMapper.deletePurchaseOrderChildByPIds(ids);
    }

    /**
     * 删除采购订单子表信息
     * 
     * @param id 采购订单子表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderChildById(String id)
    {
        return purchaseOrderChildMapper.deletePurchaseOrderChildById(id);
    }
}

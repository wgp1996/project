package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseWareMapper;
import com.ruoyi.project.system.domain.PurchaseWare;
import com.ruoyi.project.system.service.IPurchaseWareService;

/**
 * 采购入库Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PurchaseWareServiceImpl implements IPurchaseWareService 
{
    @Autowired
    private PurchaseWareMapper purchaseWareMapper;

    /**
     * 查询采购入库
     * 
     * @param id 采购入库ID
     * @return 采购入库
     */
    @Override
    public PurchaseWare selectPurchaseWareById(Integer id)
    {
        return purchaseWareMapper.selectPurchaseWareById(id);
    }

    /**
     * 查询采购入库列表
     * 
     * @param purchaseWare 采购入库
     * @return 采购入库
     */
    @Override
    public List<PurchaseWare> selectPurchaseWareList(PurchaseWare purchaseWare)
    {
        return purchaseWareMapper.selectPurchaseWareList(purchaseWare);
    }

    /**
     * 查询采购入库审核列表
     *
     * @param PurchaseWare 采购入库
     * @return 采购入库集合
     */
    @Override
    public List<PurchaseWare> selectPurchaseWareShList(PurchaseWare PurchaseWare){
        return purchaseWareMapper.selectPurchaseWareShList(PurchaseWare);
    }


    /**
     * 查询订单是否被引用
     *
     * @param djNumber 入库单单号
     * @return 结果
     */
    @Override
    public int checkWageOnSettlement(String djNumber){
        return purchaseWareMapper.checkWageOnSettlement(djNumber);
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
    public int updatetPurchaseWareStatusOrNodeNo(String djNumber,Integer status,int type){
        return purchaseWareMapper.updatetPurchaseWareStatusOrNodeNo(djNumber,status,type);
    }

    /**
     * 新增采购入库
     * 
     * @param purchaseWare 采购入库
     * @return 结果
     */
    @Override
    public int insertPurchaseWare(PurchaseWare purchaseWare)
    {
        purchaseWare.setCreateTime(DateUtils.getNowDate());
        return purchaseWareMapper.insertPurchaseWare(purchaseWare);
    }

    /**
     * 修改采购入库
     * 
     * @param purchaseWare 采购入库
     * @return 结果
     */
    @Override
    public int updatePurchaseWare(PurchaseWare purchaseWare)
    {
        purchaseWare.setUpdateTime(DateUtils.getNowDate());
        return purchaseWareMapper.updatePurchaseWare(purchaseWare);
    }

    /**
     * 批量删除采购入库
     * 
     * @param ids 需要删除的采购入库ID
     * @return 结果
     */
    @Override
    public int deletePurchaseWareByIds(Integer[] ids)
    {
        return purchaseWareMapper.deletePurchaseWareByIds(ids);
    }

    /**
     * 删除采购入库信息
     * 
     * @param id 采购入库ID
     * @return 结果
     */
    @Override
    public int deletePurchaseWareById(Integer id)
    {
        return purchaseWareMapper.deletePurchaseWareById(id);
    }
}

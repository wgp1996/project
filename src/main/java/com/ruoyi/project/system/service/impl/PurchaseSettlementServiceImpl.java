package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseSettlementMapper;
import com.ruoyi.project.system.domain.PurchaseSettlement;
import com.ruoyi.project.system.service.IPurchaseSettlementService;

/**
 * 结算申请单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@Service
public class PurchaseSettlementServiceImpl implements IPurchaseSettlementService 
{
    @Autowired
    private PurchaseSettlementMapper purchaseSettlementMapper;

    /**
     * 查询结算申请单
     * 
     * @param id 结算申请单ID
     * @return 结算申请单
     */
    @Override
    public PurchaseSettlement selectPurchaseSettlementById(Integer id)
    {
        return purchaseSettlementMapper.selectPurchaseSettlementById(id);
    }


    /**
     * 查询采购结算审核列表
     *
     * @param purchaseSettlement 采购结算
     * @return 采购结算集合
     */
    @Override
    public List<PurchaseSettlement> selectPurchaseSettlementShList(PurchaseSettlement purchaseSettlement){
        return purchaseSettlementMapper.selectPurchaseSettlementShList(purchaseSettlement);
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
    public int updatetPurchaseSettlementStatusOrNodeNo(String djNumber,Integer status,int type){
        return purchaseSettlementMapper.updatetPurchaseSettlementStatusOrNodeNo(djNumber,status,type);
    }

    /**
     * 查询结算申请单列表
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结算申请单
     */
    @Override
    public List<PurchaseSettlement> selectPurchaseSettlementList(PurchaseSettlement purchaseSettlement)
    {
        return purchaseSettlementMapper.selectPurchaseSettlementList(purchaseSettlement);
    }

    /**
     * 新增结算申请单
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结果
     */
    @Override
    public int insertPurchaseSettlement(PurchaseSettlement purchaseSettlement)
    {
        purchaseSettlement.setCreateTime(DateUtils.getNowDate());
        return purchaseSettlementMapper.insertPurchaseSettlement(purchaseSettlement);
    }

    /**
     * 修改结算申请单
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结果
     */
    @Override
    public int updatePurchaseSettlement(PurchaseSettlement purchaseSettlement)
    {
        purchaseSettlement.setUpdateTime(DateUtils.getNowDate());
        return purchaseSettlementMapper.updatePurchaseSettlement(purchaseSettlement);
    }

    /**
     * 批量删除结算申请单
     * 
     * @param ids 需要删除的结算申请单ID
     * @return 结果
     */
    @Override
    public int deletePurchaseSettlementByIds(Integer[] ids)
    {
        return purchaseSettlementMapper.deletePurchaseSettlementByIds(ids);
    }

    /**
     * 删除结算申请单信息
     * 
     * @param id 结算申请单ID
     * @return 结果
     */
    @Override
    public int deletePurchaseSettlementById(Integer id)
    {
        return purchaseSettlementMapper.deletePurchaseSettlementById(id);
    }
}

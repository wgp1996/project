package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseSettlementChildMapper;
import com.ruoyi.project.system.domain.PurchaseSettlementChild;
import com.ruoyi.project.system.service.IPurchaseSettlementChildService;

/**
 * 结算申请单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
@Service
public class PurchaseSettlementChildServiceImpl implements IPurchaseSettlementChildService 
{
    @Autowired
    private PurchaseSettlementChildMapper purchaseSettlementChildMapper;

    /**
     * 查询结算申请单明细
     * 
     * @param id 结算申请单明细ID
     * @return 结算申请单明细
     */
    @Override
    public PurchaseSettlementChild selectPurchaseSettlementChildById(String id)
    {
        return purchaseSettlementChildMapper.selectPurchaseSettlementChildById(id);
    }

    /**
     * 查询结算申请单明细列表
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结算申请单明细
     */
    @Override
    public List<PurchaseSettlementChild> selectPurchaseSettlementChildList(PurchaseSettlementChild purchaseSettlementChild)
    {
        return purchaseSettlementChildMapper.selectPurchaseSettlementChildList(purchaseSettlementChild);
    }

    /**
     * 新增结算申请单明细
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结果
     */
    @Override
    public int insertPurchaseSettlementChild(PurchaseSettlementChild purchaseSettlementChild)
    {
        purchaseSettlementChild.setId(StringUtils.getId());
        purchaseSettlementChild.setCreateTime(DateUtils.getNowDate());
        return purchaseSettlementChildMapper.insertPurchaseSettlementChild(purchaseSettlementChild);
    }

    /**
     * 修改结算申请单明细
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结果
     */
    @Override
    public int updatePurchaseSettlementChild(PurchaseSettlementChild purchaseSettlementChild)
    {
        purchaseSettlementChild.setUpdateTime(DateUtils.getNowDate());
        return purchaseSettlementChildMapper.updatePurchaseSettlementChild(purchaseSettlementChild);
    }

    /**
     * 批量删除结算申请单明细
     * 
     * @param ids 需要删除的结算申请单明细ID
     * @return 结果
     */
    @Override
    public int deletePurchaseSettlementChildByIds(String[] ids)
    {
        return purchaseSettlementChildMapper.deletePurchaseSettlementChildByIds(ids);
    }

    /**
     * 根据主表ID批量删除结算申请单明细
     *
     * @param ids 需要删除的结算申请单主表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseSettlementChildByPIds(Integer[] ids){
        return purchaseSettlementChildMapper.deletePurchaseSettlementChildByPIds(ids);
    }

    /**
     * 删除结算申请单明细信息
     * 
     * @param id 结算申请单明细ID
     * @return 结果
     */
    @Override
    public int deletePurchaseSettlementChildById(String id)
    {
        return purchaseSettlementChildMapper.deletePurchaseSettlementChildById(id);
    }
}

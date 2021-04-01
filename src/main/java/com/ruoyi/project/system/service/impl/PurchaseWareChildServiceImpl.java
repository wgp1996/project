package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PurchaseWareChildMapper;
import com.ruoyi.project.system.domain.PurchaseWareChild;
import com.ruoyi.project.system.service.IPurchaseWareChildService;

/**
 * 采购入库子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class PurchaseWareChildServiceImpl implements IPurchaseWareChildService
{
    @Autowired
    private PurchaseWareChildMapper PurchaseWareChildMapper;

    /**
     * 查询采购入库子表
     * 
     * @param id 采购入库子表ID
     * @return 采购入库子表
     */
    @Override
    public PurchaseWareChild selectPurchaseWareChildById(String id)
    {
        return PurchaseWareChildMapper.selectPurchaseWareChildById(id);
    }


    /**
     * 查询采购入库子表列表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 采购入库子表
     */
    @Override
    public List<PurchaseWareChild> selectPurchaseWareChildList(PurchaseWareChild PurchaseWareChild)
    {
        return PurchaseWareChildMapper.selectPurchaseWareChildList(PurchaseWareChild);
    }

    /**
     * 采购接收单查询采购入库单列表
     *
     * @param PurchaseWareChild 采购入库子表
     * @return 采购入库子表集合
     */
    @Override
    public List<PurchaseWareChild> selectPurchaseWareListBySettlement(PurchaseWareChild PurchaseWareChild){
        return PurchaseWareChildMapper.selectPurchaseWareListBySettlement(PurchaseWareChild);
    }

    /**
     * 新增采购入库子表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 结果
     */
    @Override
    public int insertPurchaseWareChild(PurchaseWareChild PurchaseWareChild)
    {
        PurchaseWareChild.setId(StringUtils.getId());
        PurchaseWareChild.setCreateTime(DateUtils.getNowDate());
        return PurchaseWareChildMapper.insertPurchaseWareChild(PurchaseWareChild);
    }

    /**
     * 修改采购入库子表
     * 
     * @param PurchaseWareChild 采购入库子表
     * @return 结果
     */
    @Override
    public int updatePurchaseWareChild(PurchaseWareChild PurchaseWareChild)
    {
        PurchaseWareChild.setUpdateTime(DateUtils.getNowDate());
        return PurchaseWareChildMapper.updatePurchaseWareChild(PurchaseWareChild);
    }

    /**
     * 批量删除采购入库子表
     * 
     * @param ids 需要删除的采购入库子表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseWareChildByIds(String[] ids)
    {
        return PurchaseWareChildMapper.deletePurchaseWareChildByIds(ids);
    }

    /**
     * 根据主表id批量删除采购入库子表
     *
     * @param ids 需要删除的采购入库主表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseWareChildByPIds(Integer[] ids){
        return PurchaseWareChildMapper.deletePurchaseWareChildByPIds(ids);
    }

    /**
     * 删除采购入库子表信息
     * 
     * @param id 采购入库子表ID
     * @return 结果
     */
    @Override
    public int deletePurchaseWareChildById(String id)
    {
        return PurchaseWareChildMapper.deletePurchaseWareChildById(id);
    }
}

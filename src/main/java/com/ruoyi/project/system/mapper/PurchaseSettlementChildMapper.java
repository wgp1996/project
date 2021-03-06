package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PurchaseSettlementChild;
import com.ruoyi.project.system.domain.PurchaseWareChild;

/**
 * 结算申请单明细Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public interface PurchaseSettlementChildMapper 
{
    /**
     * 查询结算申请单明细
     * 
     * @param id 结算申请单明细ID
     * @return 结算申请单明细
     */
    public PurchaseSettlementChild selectPurchaseSettlementChildById(String id);

    /**
     * 查询结算申请单明细列表
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结算申请单明细集合
     */
    public List<PurchaseSettlementChild> selectPurchaseSettlementChildList(PurchaseSettlementChild purchaseSettlementChild);

    /**
     * 新增结算申请单明细
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结果
     */
    public int insertPurchaseSettlementChild(PurchaseSettlementChild purchaseSettlementChild);

    /**
     * 修改结算申请单明细
     * 
     * @param purchaseSettlementChild 结算申请单明细
     * @return 结果
     */
    public int updatePurchaseSettlementChild(PurchaseSettlementChild purchaseSettlementChild);

    /**
     * 删除结算申请单明细
     * 
     * @param id 结算申请单明细ID
     * @return 结果
     */
    public int deletePurchaseSettlementChildById(String id);

    /**
     * 根据主表ID批量删除结算申请单明细
     *
     * @param ids 需要删除的结算申请单主表ID
     * @return 结果
     */
    public int deletePurchaseSettlementChildByPIds(Integer[] ids);

    /**
     * 批量删除结算申请单明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseSettlementChildByIds(String[] ids);
}

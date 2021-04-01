package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PurchaseSettlement;

/**
 * 结算申请单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-01
 */
public interface PurchaseSettlementMapper 
{
    /**
     * 查询结算申请单
     * 
     * @param id 结算申请单ID
     * @return 结算申请单
     */
    public PurchaseSettlement selectPurchaseSettlementById(Integer id);


    /**
     * 查询采购结算审核列表
     *
     * @param purchaseSettlement 采购结算
     * @return 采购结算集合
     */
    public List<PurchaseSettlement> selectPurchaseSettlementShList(PurchaseSettlement purchaseSettlement);

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetPurchaseSettlementStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 查询结算申请单列表
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结算申请单集合
     */
    public List<PurchaseSettlement> selectPurchaseSettlementList(PurchaseSettlement purchaseSettlement);

    /**
     * 新增结算申请单
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结果
     */
    public int insertPurchaseSettlement(PurchaseSettlement purchaseSettlement);

    /**
     * 修改结算申请单
     * 
     * @param purchaseSettlement 结算申请单
     * @return 结果
     */
    public int updatePurchaseSettlement(PurchaseSettlement purchaseSettlement);

    /**
     * 删除结算申请单
     * 
     * @param id 结算申请单ID
     * @return 结果
     */
    public int deletePurchaseSettlementById(Integer id);

    /**
     * 批量删除结算申请单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseSettlementByIds(Integer[] ids);
}

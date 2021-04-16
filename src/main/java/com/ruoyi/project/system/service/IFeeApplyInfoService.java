package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.FeeApplyInfo;
import com.ruoyi.project.system.domain.FlowAudit;

/**
 * 费用报销单Service接口
 * 
 * @author xiaoyu
 * @date 2021-04-16
 */
public interface IFeeApplyInfoService 
{
    /**
     * 查询费用报销单
     * 
     * @param id 费用报销单ID
     * @return 费用报销单
     */
    public FeeApplyInfo selectFeeApplyInfoById(Integer id);

    /**
     * 查询费用报销单列表
     * 
     * @param feeApplyInfo 费用报销单
     * @return 费用报销单集合
     */
    public List<FeeApplyInfo> selectFeeApplyInfoList(FeeApplyInfo feeApplyInfo);

    /**
     * 新增费用报销单
     * 
     * @param feeApplyInfo 费用报销单
     * @return 结果
     */
    public int insertFeeApplyInfo(FeeApplyInfo feeApplyInfo);

    /**
     * 修改费用报销单
     * 
     * @param feeApplyInfo 费用报销单
     * @return 结果
     */
    public int updateFeeApplyInfo(FeeApplyInfo feeApplyInfo);

    //提交
    public int effect(Integer[] ids);
    //取消提交
    public int cancel(Integer[] ids);
    //审核
    public int examine(FlowAudit flowAudit);
    //取消提交
    public int cancelAudit(String[] djIds,Integer[] nodeNos);
    /**
     * 查询费用报销单审核列表
     *
     * @param feeApplyInfo 借用归还单
     * @return 借用归还单集合
     */
    public List<FeeApplyInfo> selectFeeApplyInfoShList(FeeApplyInfo feeApplyInfo);


    /**
     * 批量删除费用报销单
     * 
     * @param ids 需要删除的费用报销单ID
     * @return 结果
     */
    public int deleteFeeApplyInfoByIds(Integer[] ids);

    /**
     * 删除费用报销单信息
     * 
     * @param id 费用报销单ID
     * @return 结果
     */
    public int deleteFeeApplyInfoById(Integer id);
}

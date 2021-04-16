package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.FeeApplyInfo;
import com.ruoyi.project.system.domain.PickingReturn;

/**
 * 费用报销单Mapper接口
 * 
 * @author xiaoyu
 * @date 2021-04-16
 */
public interface FeeApplyInfoMapper 
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
     *查询费用报销单审核列表
     *
     * @param feeApplyInfo 借用归还单
     * @return 借用归还单集合
     */
    public List<FeeApplyInfo> selectFeeApplyInfoShList(FeeApplyInfo feeApplyInfo);

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetFeeApplyInfoStatusOrNodeNo(String djNumber,Integer status,int type);

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

    /**
     * 删除费用报销单
     * 
     * @param id 费用报销单ID
     * @return 结果
     */
    public int deleteFeeApplyInfoById(Integer id);

    /**
     * 批量删除费用报销单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFeeApplyInfoByIds(Integer[] ids);
}

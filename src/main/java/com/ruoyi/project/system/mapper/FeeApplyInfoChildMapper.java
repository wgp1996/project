package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.FeeApplyInfoChild;

/**
 * 费用报销子表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FeeApplyInfoChildMapper 
{
    /**
     * 查询费用报销子表
     * 
     * @param id 费用报销子表ID
     * @return 费用报销子表
     */
    public FeeApplyInfoChild selectFeeApplyInfoChildById(String id);

    /**
     * 查询费用报销子表列表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 费用报销子表集合
     */
    public List<FeeApplyInfoChild> selectFeeApplyInfoChildList(FeeApplyInfoChild feeApplyInfoChild);

    /**
     * 新增费用报销子表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 结果
     */
    public int insertFeeApplyInfoChild(FeeApplyInfoChild feeApplyInfoChild);

    /**
     * 修改费用报销子表
     * 
     * @param feeApplyInfoChild 费用报销子表
     * @return 结果
     */
    public int updateFeeApplyInfoChild(FeeApplyInfoChild feeApplyInfoChild);

    /**
     * 删除费用报销子表
     * 
     * @param id 费用报销子表ID
     * @return 结果
     */
    public int deleteFeeApplyInfoChildById(String id);

    /**
     * 批量删除费用报销子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFeeApplyInfoChildByIds(String[] ids);

    /**
     * 批量删除费用报销子表
     *
     * @param ids 需要删除的数据主表ID
     * @return 结果
     */
    public int deleteFeeApplyInfoChildByPIds(Integer[] ids);


}

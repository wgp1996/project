package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.FeeInfo;

/**
 * 费用项目Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFeeInfoService 
{
    /**
     * 查询费用项目
     * 
     * @param id 费用项目ID
     * @return 费用项目
     */
    public FeeInfo selectFeeInfoById(Integer id);

    /**
     * 查询费用项目列表
     * 
     * @param feeInfo 费用项目
     * @return 费用项目集合
     */
    public List<FeeInfo> selectFeeInfoList(FeeInfo feeInfo);

    /**
     * 新增费用项目
     * 
     * @param feeInfo 费用项目
     * @return 结果
     */
    public int insertFeeInfo(FeeInfo feeInfo);

    /**
     * 修改费用项目
     * 
     * @param feeInfo 费用项目
     * @return 结果
     */
    public int updateFeeInfo(FeeInfo feeInfo);

    /**
     * 批量删除费用项目
     * 
     * @param ids 需要删除的费用项目ID
     * @return 结果
     */
    public int deleteFeeInfoByIds(Integer[] ids);

    /**
     * 删除费用项目信息
     * 
     * @param id 费用项目ID
     * @return 结果
     */
    public int deleteFeeInfoById(Integer id);
}

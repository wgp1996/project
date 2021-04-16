package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.FeeType;

/**
 * 费用项目分类Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FeeTypeMapper 
{
    /**
     * 查询费用项目分类
     * 
     * @param feeTypeId 费用项目分类ID
     * @return 费用项目分类
     */
    public FeeType selectFeeTypeById(Integer feeTypeId);

    /**
     * 查询费用项目分类列表
     * 
     * @param feeType 费用项目分类
     * @return 费用项目分类集合
     */
    public List<FeeType> selectFeeTypeList(FeeType feeType);

    /**
     * 新增费用项目分类
     * 
     * @param feeType 费用项目分类
     * @return 结果
     */
    public int insertFeeType(FeeType feeType);

    /**
     * 修改费用项目分类
     * 
     * @param feeType 费用项目分类
     * @return 结果
     */
    public int updateFeeType(FeeType feeType);

    /**
     * 删除费用项目分类
     * 
     * @param feeTypeId 费用项目分类ID
     * @return 结果
     */
    public int deleteFeeTypeById(Integer feeTypeId);

    /**
     * 批量删除费用项目分类
     * 
     * @param feeTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFeeTypeByIds(Integer[] feeTypeIds);
}

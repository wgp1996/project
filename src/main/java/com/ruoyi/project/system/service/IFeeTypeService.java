package com.ruoyi.project.system.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.FeeType;
import com.ruoyi.project.system.domain.FeeType;

/**
 * 费用项目分类Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFeeTypeService 
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
     * 构建前端所需要树结构
     *
     * @param goods 树形列表
     * @return 树结构列表
     */
    public List<FeeType> buildGoodsTree(List<FeeType> goods);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildGoodsTreeSelect(List<FeeType> depts);

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
     * 批量删除费用项目分类
     * 
     * @param feeTypeIds 需要删除的费用项目分类ID
     * @return 结果
     */
    public int deleteFeeTypeByIds(Integer[] feeTypeIds);

    /**
     * 删除费用项目分类信息
     * 
     * @param feeTypeId 费用项目分类ID
     * @return 结果
     */
    public int deleteFeeTypeById(Integer feeTypeId);
}

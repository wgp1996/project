package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.FeeType;
import com.ruoyi.project.system.domain.FeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FeeTypeMapper;
import com.ruoyi.project.system.domain.FeeType;
import com.ruoyi.project.system.service.IFeeTypeService;

/**
 * 费用项目分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FeeTypeServiceImpl implements IFeeTypeService 
{
    @Autowired
    private FeeTypeMapper feeTypeMapper;

    /**
     * 查询费用项目分类
     * 
     * @param feeTypeId 费用项目分类ID
     * @return 费用项目分类
     */
    @Override
    public FeeType selectFeeTypeById(Integer feeTypeId)
    {
        return feeTypeMapper.selectFeeTypeById(feeTypeId);
    }

    /**
     * 查询费用项目分类列表
     * 
     * @param feeType 费用项目分类
     * @return 费用项目分类
     */
    @Override
    public List<FeeType> selectFeeTypeList(FeeType feeType)
    {
        return feeTypeMapper.selectFeeTypeList(feeType);
    }


    /**
     * 新增费用项目分类
     * 
     * @param feeType 费用项目分类
     * @return 结果
     */
    @Override
    public int insertFeeType(FeeType feeType)
    {
        feeType.setCreateTime(DateUtils.getNowDate());
        return feeTypeMapper.insertFeeType(feeType);
    }

    /**
     * 修改费用项目分类
     * 
     * @param feeType 费用项目分类
     * @return 结果
     */
    @Override
    public int updateFeeType(FeeType feeType)
    {
        feeType.setUpdateTime(DateUtils.getNowDate());
        return feeTypeMapper.updateFeeType(feeType);
    }

    /**
     * 批量删除费用项目分类
     * 
     * @param feeTypeIds 需要删除的费用项目分类ID
     * @return 结果
     */
    @Override
    public int deleteFeeTypeByIds(Integer[] feeTypeIds)
    {
        return feeTypeMapper.deleteFeeTypeByIds(feeTypeIds);
    }

    /**
     * 删除费用项目分类信息
     * 
     * @param feeTypeId 费用项目分类ID
     * @return 结果
     */
    @Override
    public int deleteFeeTypeById(Integer feeTypeId)
    {
        if (feeTypeMapper.hasChildTypeById(feeTypeId)>0)
        {
            return -1;
        }
        return feeTypeMapper.deleteFeeTypeById(feeTypeId);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param goods 部门列表
     * @return 树结构列表
     */
    @Override
    public List<FeeType> buildGoodsTree(List<FeeType> goods)
    {
        List<FeeType> returnList = new ArrayList<FeeType>();
        List<Long> tempList = new ArrayList<Long>();
        for (FeeType good : goods)
        {
            tempList.add(good.getFeeTypeId().longValue());
        }
        for (Iterator<FeeType> iterator = goods.iterator(); iterator.hasNext();)
        {
            FeeType dept = (FeeType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getFeeTypePid()))
            {
                recursionFn(goods, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = goods;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param goods 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildGoodsTreeSelect(List<FeeType> goods)
    {
        List<FeeType> deptTrees = buildGoodsTree(goods);
        System.out.println(deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 得到子节点列表
     */
    private List<FeeType> getChildList(List<FeeType> list, FeeType t)
    {
        List<FeeType> tlist = new ArrayList<FeeType>();
        Iterator<FeeType> it = list.iterator();
        while (it.hasNext())
        {
            FeeType n = (FeeType) it.next();
            System.out.println("pid:"+n.getFeeTypePid().longValue()+"id:"+t.getFeeTypeId().longValue());
            System.out.println(n.getFeeTypePid().longValue() == t.getFeeTypeId().longValue());
            if (StringUtils.isNotNull(n.getFeeTypePid()) && n.getFeeTypePid().longValue() == t.getFeeTypeId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<FeeType> list, FeeType t)
    {
        // 得到子节点列表
        List<FeeType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (FeeType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<FeeType> it = childList.iterator();
                while (it.hasNext())
                {
                    FeeType n = (FeeType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<FeeType> list, FeeType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}

package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.domain.ProjectInventory;
import com.ruoyi.project.system.domain.ProjectInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectInventoryMapper;
import com.ruoyi.project.system.domain.ProjectInventory;
import com.ruoyi.project.system.service.IProjectInventoryService;

/**
 * 编制清单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
@Service
public class ProjectInventoryServiceImpl implements IProjectInventoryService 
{
    @Autowired
    private ProjectInventoryMapper projectInventoryMapper;

    /**
     * 查询编制清单
     * 
     * @param id 编制清单ID
     * @return 编制清单
     */
    @Override
    public ProjectInventory selectProjectInventoryById(Integer id)
    {
        return projectInventoryMapper.selectProjectInventoryById(id);
    }


    /**
     * 分包合同选择编制清单列表
     *
     * @param projectInventory 编制清单
     * @return 编制清单集合
     */
    @Override
    public List<ProjectInventory> inventoryList(ProjectInventory projectInventory){
        return projectInventoryMapper.inventoryList(projectInventory);
    }

    /**
     * 查询编制清单
     *
     * @param djNumber 编制清单单号
     * @return 编制清单
     */
    @Override
    public List<ProjectInventory> selectProjectInventoryListByDjNumber(String djNumber){
        return projectInventoryMapper.selectProjectInventoryListByDjNumber(djNumber);
    }

    @Override
    public List<ProjectInventory> buildTree(List<ProjectInventory> goods)
    {
        List<ProjectInventory> returnList = new ArrayList<ProjectInventory>();
        List<Long> tempList = new ArrayList<Long>();
        for (ProjectInventory good : goods)
        {
            tempList.add(good.getId().longValue());
        }
        for (Iterator<ProjectInventory> iterator = goods.iterator(); iterator.hasNext();)
        {
            ProjectInventory dept = (ProjectInventory) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
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
    public List<TreeSelect> buildTreeSelect(List<ProjectInventory> goods)
    {
        List<ProjectInventory> deptTrees = buildTree(goods);
        System.out.println(deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<ProjectInventory> list, ProjectInventory t)
    {
        // 得到子节点列表
        List<ProjectInventory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProjectInventory tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<ProjectInventory> it = childList.iterator();
                while (it.hasNext())
                {
                    ProjectInventory n = (ProjectInventory) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<ProjectInventory> getChildList(List<ProjectInventory> list, ProjectInventory t)
    {
        List<ProjectInventory> tlist = new ArrayList<ProjectInventory>();
        Iterator<ProjectInventory> it = list.iterator();
        while (it.hasNext())
        {
            ProjectInventory n = (ProjectInventory) it.next();
            System.out.println("pid:"+n.getParentId().longValue()+"id:"+t.getId().longValue());
            System.out.println(n.getParentId().longValue() == t.getId().longValue());
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProjectInventory> list, ProjectInventory t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 查询编制清单列表
     * 
     * @param projectInventory 编制清单
     * @return 编制清单
     */
    @Override
    public List<ProjectInventory> selectProjectInventoryList(ProjectInventory projectInventory)
    {
        return projectInventoryMapper.selectProjectInventoryList(projectInventory);
    }

    /**
     * 新增编制清单
     * 
     * @param projectInventory 编制清单
     * @return 结果
     */
    @Override
    public int insertProjectInventory(ProjectInventory projectInventory)
    {
        projectInventory.setCreateTime(DateUtils.getNowDate());
        return projectInventoryMapper.insertProjectInventory(projectInventory);
    }

    /**
     * 修改编制清单
     * 
     * @param projectInventory 编制清单
     * @return 结果
     */
    @Override
    public int updateProjectInventory(ProjectInventory projectInventory)
    {
        projectInventory.setUpdateTime(DateUtils.getNowDate());
        return projectInventoryMapper.updateProjectInventory(projectInventory);
    }

    /**
     * 批量删除编制清单
     * 
     * @param ids 需要删除的编制清单ID
     * @return 结果
     */
    @Override
    public int deleteProjectInventoryByIds(Integer[] ids)
    {
        return projectInventoryMapper.deleteProjectInventoryByIds(ids);
    }

    /**
     * 删除编制清单信息
     * 
     * @param id 编制清单ID
     * @return 结果
     */
    @Override
    public int deleteProjectInventoryById(Integer id)
    {
        return projectInventoryMapper.deleteProjectInventoryById(id);
    }

    /**
     * 删除编制清单信息
     *
     * @param id 编制清单ID
     * @return 结果
     */
    @Override
    public int deleteProjectInventoryByPId(Integer id){
        return projectInventoryMapper.deleteProjectInventoryByPId(id);
    }
}

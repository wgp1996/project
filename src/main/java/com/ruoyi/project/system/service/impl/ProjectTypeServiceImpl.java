package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.ProjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectTypeMapper;
import com.ruoyi.project.system.service.IProjectTypeService;

/**
 * 项目分类Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class ProjectTypeServiceImpl implements IProjectTypeService
{
    @Autowired
    private ProjectTypeMapper goodsTypeMapper;

    /**
     * 查询项目分类
     *
     * @param goodsTypeId 项目分类ID
     * @return 项目分类
     */
    @Override
    public ProjectType selectProjectTypeById(Integer goodsTypeId)
    {
        return goodsTypeMapper.selectProjectTypeById(goodsTypeId);
    }
    /**
     * 查询项目分类
     *
     * @param goodsTypePid 项目分类PID
     * @return 项目分类
     */
    public List<ProjectType> selectProjectTypeByPid(Integer goodsTypePid){
        return goodsTypeMapper.selectProjectTypeByPid(goodsTypePid);
    }
    /**
     * 查询项目分类
     *
     * @param goodsTypeId 项目分类ID
     * @return 项目分类
     */
    @Override
    public ProjectType selectProjectTypeByName(String name,Integer goodsTypeId)
    {
        return goodsTypeMapper.selectProjectTypeByName(name,goodsTypeId);
    }
    /**
     * 查询项目分类
     *
     * @param pid 项目分类父ID
     * @return 项目分类
     */
    @Override
    public ProjectType checkProjectTypeNameUnique(String name,Integer pid){
        return goodsTypeMapper.checkProjectTypeNameUnique(name,pid);
    }
    /**
     * 查询项目分类跟项目集合
     *
     * @param projectType 项目分类
     * @return 查询项目分类跟项目集合
     */
    @Override
    public List<ProjectType> selectProjectTypeProjectList(ProjectType projectType){
        return goodsTypeMapper.selectProjectTypeProjectList(projectType);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param goods 部门列表
     * @return 树结构列表
     */
    @Override
    public List<ProjectType> buildGoodsTree(List<ProjectType> goods)
    {
        List<ProjectType> returnList = new ArrayList<ProjectType>();
        List<Long> tempList = new ArrayList<Long>();
        for (ProjectType good : goods)
        {
            tempList.add(good.getProjectTypeId().longValue());
        }
        for (Iterator<ProjectType> iterator = goods.iterator(); iterator.hasNext();)
        {
            ProjectType dept = (ProjectType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getProjectTypePid()))
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

    @Override
    public List<ProjectType> buildProjectTree(List<ProjectType> goods)
    {
        List<ProjectType> returnList = new ArrayList<ProjectType>();
        List<Long> tempList = new ArrayList<Long>();
        for (ProjectType good : goods)
        {
            tempList.add(good.getProjectTypeId().longValue());
        }
        for (Iterator<ProjectType> iterator = goods.iterator(); iterator.hasNext();)
        {
            ProjectType dept = (ProjectType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getProjectTypePid()))
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
    public List<TreeSelect> buildGoodsTreeSelect(List<ProjectType> goods)
    {
        List<ProjectType> deptTrees = buildGoodsTree(goods);
        System.out.println(deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildTreeSelect(List<ProjectType> depts){
        List<ProjectType> deptTrees = buildProjectTree(depts);
        System.out.println(deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    @Override
    public int hasChildProjectTypeById(Integer id){
        return goodsTypeMapper.hasChildProjectTypeById(id);
    }

    /**
     * 查询项目分类列表
     *
     * @param goodsType 项目分类
     * @return 项目分类
     */
    @Override
    public List<ProjectType> selectProjectTypeList(ProjectType goodsType)
    {
        return goodsTypeMapper.selectProjectTypeList(goodsType);
    }

    /**
     * 新增项目分类
     *
     * @param goodsType 项目分类
     * @return 结果
     */
    @Override
    public int insertProjectType(ProjectType goodsType)
    {
        goodsType.setCreateTime(DateUtils.getNowDate());
        return goodsTypeMapper.insertProjectType(goodsType);
    }

    /**
     * 修改项目分类
     *
     * @param goodsType 项目分类
     * @return 结果
     */
    @Override
    public int updateProjectType(ProjectType goodsType)
    {
        goodsType.setUpdateTime(DateUtils.getNowDate());
        return goodsTypeMapper.updateProjectType(goodsType);
    }

    /**
     * 批量删除项目分类
     *
     * @param goodsTypeIds 需要删除的项目分类ID
     * @return 结果
     */
    @Override
    public int deleteProjectTypeByIds(Integer[] goodsTypeIds)
    {
        return goodsTypeMapper.deleteProjectTypeByIds(goodsTypeIds);
    }

    /**
     * 删除项目分类信息
     *
     * @param goodsTypeId 项目分类ID
     * @return 结果
     */
    @Override
    public int deleteProjectTypeById(Integer goodsTypeId)
    {
        return goodsTypeMapper.deleteProjectTypeById(goodsTypeId);
    }
    /**
     * 得到子节点列表
     */
    private List<ProjectType> getChildList(List<ProjectType> list, ProjectType t)
    {
        List<ProjectType> tlist = new ArrayList<ProjectType>();
        Iterator<ProjectType> it = list.iterator();
        while (it.hasNext())
        {
            ProjectType n = (ProjectType) it.next();
            System.out.println("pid:"+n.getProjectTypePid().longValue()+"id:"+t.getProjectTypeId().longValue());
            System.out.println(n.getProjectTypePid().longValue() == t.getProjectTypeId().longValue());
            if (StringUtils.isNotNull(n.getProjectTypePid()) && n.getProjectTypePid().longValue() == t.getProjectTypeId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<ProjectType> list, ProjectType t)
    {
        // 得到子节点列表
        List<ProjectType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProjectType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<ProjectType> it = childList.iterator();
                while (it.hasNext())
                {
                    ProjectType n = (ProjectType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProjectType> list, ProjectType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}

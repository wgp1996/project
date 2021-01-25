package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.ProjectTypePost;
import com.ruoyi.project.system.mapper.ProjectTypePostMapper;
import com.ruoyi.project.system.service.IProjectTypePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目分类Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class ProjectTypePostServiceImpl implements IProjectTypePostService
{
    @Autowired
    private ProjectTypePostMapper goodsTypeMapper;

    /**
     * 查询项目分类
     *
     * @param goodsTypeId 项目分类ID
     * @return 项目分类
     */
    @Override
    public ProjectTypePost selectProjectTypeById(Integer goodsTypeId)
    {
        return goodsTypeMapper.selectProjectTypeById(goodsTypeId);
    }
    /**
     * 查询项目分类
     *
     * @param goodsTypePid 项目分类PID
     * @return 项目分类
     */
    public List<ProjectTypePost> selectProjectTypeByPid(Integer goodsTypePid){
        return goodsTypeMapper.selectProjectTypeByPid(goodsTypePid);
    }
    /**
     * 查询项目分类
     *
     * @param goodsTypeId 项目分类ID
     * @return 项目分类
     */
    @Override
    public ProjectTypePost selectProjectTypeByName(String name,Integer goodsTypeId,String code)
    {
        return goodsTypeMapper.selectProjectTypeByName(name,goodsTypeId,code);
    }
    /**
     * 查询项目分类
     *
     * @param pid 项目分类父ID
     * @return 项目分类
     */
    @Override
    public ProjectTypePost checkProjectTypeNameUnique(String name,Integer pid){
        return goodsTypeMapper.checkProjectTypeNameUnique(name,pid);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param goods 部门列表
     * @return 树结构列表
     */
    @Override
    public List<ProjectTypePost> buildGoodsTree(List<ProjectTypePost> goods)
    {
        List<ProjectTypePost> returnList = new ArrayList<ProjectTypePost>();
        List<Long> tempList = new ArrayList<Long>();
        for (ProjectTypePost good : goods)
        {
            tempList.add(good.getProjectTypeId().longValue());
        }
        for (Iterator<ProjectTypePost> iterator = goods.iterator(); iterator.hasNext();)
        {
            ProjectTypePost dept = (ProjectTypePost) iterator.next();
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
    public List<TreeSelect> buildGoodsTreeSelect(List<ProjectTypePost> goods)
    {
        List<ProjectTypePost> deptTrees = buildGoodsTree(goods);
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
    public List<ProjectTypePost> selectProjectTypeList(ProjectTypePost goodsType)
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
    public int insertProjectType(ProjectTypePost goodsType)
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
    public int updateProjectType(ProjectTypePost goodsType)
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
    private List<ProjectTypePost> getChildList(List<ProjectTypePost> list, ProjectTypePost t)
    {
        List<ProjectTypePost> tlist = new ArrayList<ProjectTypePost>();
        Iterator<ProjectTypePost> it = list.iterator();
        while (it.hasNext())
        {
            ProjectTypePost n = (ProjectTypePost) it.next();
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
    private void recursionFn(List<ProjectTypePost> list, ProjectTypePost t)
    {
        // 得到子节点列表
        List<ProjectTypePost> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProjectTypePost tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<ProjectTypePost> it = childList.iterator();
                while (it.hasNext())
                {
                    ProjectTypePost n = (ProjectTypePost) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProjectTypePost> list, ProjectTypePost t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}

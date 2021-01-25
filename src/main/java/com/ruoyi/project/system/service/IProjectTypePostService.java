package com.ruoyi.project.system.service;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.ProjectTypePost;

import java.util.List;

/**
 * 项目分类Service接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface IProjectTypePostService
{
    /**
     * 查询项目分类
     * 
     * @param projectTypeId 项目分类ID
     * @return 项目分类
     */
    public ProjectTypePost selectProjectTypeById(Integer projectTypeId);

    /**
     * 查询项目分类
     *
     * @param projectTypePid 项目分类PID
     * @return 项目分类
     */
    public List<ProjectTypePost> selectProjectTypeByPid(Integer projectTypePid);

    /**
     * 查询项目分类
     *
     * @param projectTypeId 项目分类ID
     * @return 项目分类
     */
    public ProjectTypePost selectProjectTypeByName(String name,Integer projectTypeId,String code);

    /**
     * 查询项目分类
     *
     * @param pid 项目分类父ID
     * @return 项目分类
     */
    public ProjectTypePost checkProjectTypeNameUnique(String name,Integer pid);
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    public int hasChildProjectTypeById(Integer id);

    /**
     * 构建前端所需要树结构
     *
     * @param goods 树形列表
     * @return 树结构列表
     */
    public List<ProjectTypePost> buildGoodsTree(List<ProjectTypePost> goods);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildGoodsTreeSelect(List<ProjectTypePost> depts);
    /**
     * 查询项目分类列表
     * 
     * @param projectType 项目分类
     * @return 项目分类集合
     */
    public List<ProjectTypePost> selectProjectTypeList(ProjectTypePost projectType);

    /**
     * 新增项目分类
     * 
     * @param projectType 项目分类
     * @return 结果
     */
    public int insertProjectType(ProjectTypePost projectType);

    /**
     * 修改项目分类
     * 
     * @param projectType 项目分类
     * @return 结果
     */
    public int updateProjectType(ProjectTypePost projectType);

    /**
     * 批量删除项目分类
     * 
     * @param projectTypeIds 需要删除的项目分类ID
     * @return 结果
     */
    public int deleteProjectTypeByIds(Integer[] projectTypeIds);

    /**
     * 删除项目分类信息
     * 
     * @param projectTypeId 项目分类ID
     * @return 结果
     */
    public int deleteProjectTypeById(Integer projectTypeId);
}

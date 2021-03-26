package com.ruoyi.project.system.service;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.ProjectType;

import java.util.List;

/**
 * 项目分类Service接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface IProjectTypeService
{
    /**
     * 查询项目分类
     * 
     * @param projectTypeId 项目分类ID
     * @return 项目分类
     */
    public ProjectType selectProjectTypeById(Integer projectTypeId);

    /**
     * 查询项目分类
     *
     * @param projectTypePid 项目分类PID
     * @return 项目分类
     */
    public List<ProjectType> selectProjectTypeByPid(Integer projectTypePid);

    /**
     * 查询项目分类
     *
     * @param projectTypeId 项目分类ID
     * @return 项目分类
     */
    public ProjectType selectProjectTypeByName(String name,Integer projectTypeId);

    /**
     * 查询项目分类
     *
     * @param pid 项目分类父ID
     * @return 项目分类
     */
    public ProjectType checkProjectTypeNameUnique(String name,Integer pid);
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
    public List<ProjectType> buildGoodsTree(List<ProjectType> goods);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildGoodsTreeSelect(List<ProjectType> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildTreeSelect(List<ProjectType> depts);

    public List<ProjectType> buildProjectTree(List<ProjectType> depts);


    /**
     * 查询项目分类列表
     * 
     * @param projectType 项目分类
     * @return 项目分类集合
     */
    public List<ProjectType> selectProjectTypeList(ProjectType projectType);

    /**
     * 查询项目分类跟项目集合
     *
     * @param projectType 项目分类
     * @return 查询项目分类跟项目集合
     */
    public List<ProjectType> selectProjectTypeProjectList(ProjectType projectType);

    /**
     * 新增项目分类
     *
     * @param projectType 项目分类
     * @return 结果
     */
    public int insertProjectType(ProjectType projectType);

    /**
     * 修改项目分类
     * 
     * @param projectType 项目分类
     * @return 结果
     */
    public int updateProjectType(ProjectType projectType);

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

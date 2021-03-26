package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.ProjectType;

import java.util.List;

/**
 * 项目分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface ProjectTypeMapper
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
     * @param id 项目分类ID
     * @return 项目分类
     */
    public ProjectType selectProjectTypeByName(String name,Integer id);
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
    int hasChildProjectTypeById(Integer id);
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
     * 删除项目分类
     * 
     * @param projectTypeId 项目分类ID
     * @return 结果
     */
    public int deleteProjectTypeById(Integer projectTypeId);

    /**
     * 批量删除项目分类
     * 
     * @param projectTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectTypeByIds(Integer[] projectTypeIds);
}

package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectUser;

/**
 * 项目人员Service接口
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
public interface IProjectUserService 
{
    /**
     * 查询项目人员
     * 
     * @param id 项目人员ID
     * @return 项目人员
     */
    public ProjectUser selectProjectUserById(Integer id);

    /**
     * 查询项目人员列表
     * 
     * @param projectUser 项目人员
     * @return 项目人员集合
     */
    public List<ProjectUser> selectProjectUserList(ProjectUser projectUser);

    /**
     * 新增项目人员
     * 
     * @param projectUser 项目人员
     * @return 结果
     */
    public int insertProjectUser(ProjectUser projectUser);

    /**
     * 修改项目人员
     * 
     * @param projectUser 项目人员
     * @return 结果
     */
    public int updateProjectUser(ProjectUser projectUser);

    /**
     * 批量删除项目人员
     * 
     * @param ids 需要删除的项目人员ID
     * @return 结果
     */
    public int deleteProjectUserByIds(Integer[] ids);

    /**
     * 删除项目人员信息
     * 
     * @param id 项目人员ID
     * @return 结果
     */
    public int deleteProjectUserById(Integer id);
}

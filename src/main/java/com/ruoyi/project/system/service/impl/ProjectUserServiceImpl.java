package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectUserMapper;
import com.ruoyi.project.system.domain.ProjectUser;
import com.ruoyi.project.system.service.IProjectUserService;

/**
 * 项目人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-25
 */
@Service
public class ProjectUserServiceImpl implements IProjectUserService 
{
    @Autowired
    private ProjectUserMapper projectUserMapper;

    /**
     * 查询项目人员
     * 
     * @param id 项目人员ID
     * @return 项目人员
     */
    @Override
    public ProjectUser selectProjectUserById(Integer id)
    {
        return projectUserMapper.selectProjectUserById(id);
    }

    /**
     * 查询项目人员列表
     * 
     * @param projectUser 项目人员
     * @return 项目人员
     */
    @Override
    public List<ProjectUser> selectProjectUserList(ProjectUser projectUser)
    {
        return projectUserMapper.selectProjectUserList(projectUser);
    }

    /**
     * 新增项目人员
     * 
     * @param projectUser 项目人员
     * @return 结果
     */
    @Override
    public int insertProjectUser(ProjectUser projectUser)
    {
        projectUser.setCreateTime(DateUtils.getNowDate());
        return projectUserMapper.insertProjectUser(projectUser);
    }

    /**
     * 修改项目人员
     * 
     * @param projectUser 项目人员
     * @return 结果
     */
    @Override
    public int updateProjectUser(ProjectUser projectUser)
    {
        projectUser.setUpdateTime(DateUtils.getNowDate());
        return projectUserMapper.updateProjectUser(projectUser);
    }

    /**
     * 批量删除项目人员
     * 
     * @param ids 需要删除的项目人员ID
     * @return 结果
     */
    @Override
    public int deleteProjectUserByIds(Integer[] ids)
    {
        return projectUserMapper.deleteProjectUserByIds(ids);
    }

    /**
     * 删除项目人员信息
     * 
     * @param id 项目人员ID
     * @return 结果
     */
    @Override
    public int deleteProjectUserById(Integer id)
    {
        return projectUserMapper.deleteProjectUserById(id);
    }
}

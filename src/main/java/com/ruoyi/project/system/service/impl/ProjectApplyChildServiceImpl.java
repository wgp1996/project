package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectApplyChildMapper;
import com.ruoyi.project.system.domain.ProjectApplyChild;
import com.ruoyi.project.system.service.IProjectApplyChildService;

/**
 * 项目部信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Service
public class ProjectApplyChildServiceImpl implements IProjectApplyChildService 
{
    @Autowired
    private ProjectApplyChildMapper projectApplyChildMapper;

    /**
     * 查询项目部信息
     * 
     * @param id 项目部信息ID
     * @return 项目部信息
     */
    @Override
    public ProjectApplyChild selectProjectApplyChildById(Long id)
    {
        return projectApplyChildMapper.selectProjectApplyChildById(id);
    }

    /**
     * 查询项目部信息列表
     * 
     * @param projectApplyChild 项目部信息
     * @return 项目部信息
     */
    @Override
    public List<ProjectApplyChild> selectProjectApplyChildList(ProjectApplyChild projectApplyChild)
    {
        return projectApplyChildMapper.selectProjectApplyChildList(projectApplyChild);
    }

    /**
     * 新增项目部信息
     * 
     * @param projectApplyChild 项目部信息
     * @return 结果
     */
    @Override
    public int insertProjectApplyChild(ProjectApplyChild projectApplyChild)
    {
        projectApplyChild.setCreateTime(DateUtils.getNowDate());
        return projectApplyChildMapper.insertProjectApplyChild(projectApplyChild);
    }

    /**
     * 修改项目部信息
     * 
     * @param projectApplyChild 项目部信息
     * @return 结果
     */
    @Override
    public int updateProjectApplyChild(ProjectApplyChild projectApplyChild)
    {
        projectApplyChild.setUpdateTime(DateUtils.getNowDate());
        return projectApplyChildMapper.updateProjectApplyChild(projectApplyChild);
    }

    /**
     * 批量删除项目部信息
     * 
     * @param ids 需要删除的项目部信息ID
     * @return 结果
     */
    @Override
    public int deleteProjectApplyChildByIds(Long[] ids)
    {
        return projectApplyChildMapper.deleteProjectApplyChildByIds(ids);
    }

    /**
     * 删除项目部信息信息
     * 
     * @param id 项目部信息ID
     * @return 结果
     */
    @Override
    public int deleteProjectApplyChildById(Long id)
    {
        return projectApplyChildMapper.deleteProjectApplyChildById(id);
    }
    /**
     * 根据工程编码删除项目部信息
     * @param ids
     * @return
     */
    @Override
    public int deleteProjectApplyChildByPIds(Long[] ids){
        return projectApplyChildMapper.deleteProjectApplyChildByPIds(ids);
    }
}

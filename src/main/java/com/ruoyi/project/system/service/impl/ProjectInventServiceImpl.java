package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectInventMapper;
import com.ruoyi.project.system.domain.ProjectInvent;
import com.ruoyi.project.system.service.IProjectInventService;

/**
 * 预算编制Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@Service
public class ProjectInventServiceImpl implements IProjectInventService 
{
    @Autowired
    private ProjectInventMapper projectInventMapper;

    /**
     * 查询预算编制
     * 
     * @param id 预算编制ID
     * @return 预算编制
     */
    @Override
    public ProjectInvent selectProjectInventById(Integer id)
    {
        return projectInventMapper.selectProjectInventById(id);
    }

    /**
     * 查询预算编制列表
     * 
     * @param projectInvent 预算编制
     * @return 预算编制
     */
    @Override
    public List<ProjectInvent> selectProjectInventList(ProjectInvent projectInvent)
    {
        return projectInventMapper.selectProjectInventList(projectInvent);
    }

    /**
     * 新增预算编制
     * 
     * @param projectInvent 预算编制
     * @return 结果
     */
    @Override
    public int insertProjectInvent(ProjectInvent projectInvent)
    {
        projectInvent.setCreateTime(DateUtils.getNowDate());
        return projectInventMapper.insertProjectInvent(projectInvent);
    }

    /**
     * 修改预算编制
     * 
     * @param projectInvent 预算编制
     * @return 结果
     */
    @Override
    public int updateProjectInvent(ProjectInvent projectInvent)
    {
        projectInvent.setUpdateTime(DateUtils.getNowDate());
        return projectInventMapper.updateProjectInvent(projectInvent);
    }

    /**
     * 批量删除预算编制
     * 
     * @param ids 需要删除的预算编制ID
     * @return 结果
     */
    @Override
    public int deleteProjectInventByIds(Integer[] ids)
    {
        return projectInventMapper.deleteProjectInventByIds(ids);
    }

    /**
     * 删除预算编制信息
     * 
     * @param id 预算编制ID
     * @return 结果
     */
    @Override
    public int deleteProjectInventById(Integer id)
    {
        return projectInventMapper.deleteProjectInventById(id);
    }
}

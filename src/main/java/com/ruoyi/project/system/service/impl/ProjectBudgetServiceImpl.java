package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectBudgetMapper;
import com.ruoyi.project.system.domain.ProjectBudget;
import com.ruoyi.project.system.service.IProjectBudgetService;

/**
 * 项目预算Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@Service
public class ProjectBudgetServiceImpl implements IProjectBudgetService 
{
    @Autowired
    private ProjectBudgetMapper projectBudgetMapper;

    /**
     * 查询项目预算
     * 
     * @param id 项目预算ID
     * @return 项目预算
     */
    @Override
    public ProjectBudget selectProjectBudgetById(Integer id)
    {
        return projectBudgetMapper.selectProjectBudgetById(id);
    }

    /**
     * 查询项目预算列表
     * 
     * @param projectBudget 项目预算
     * @return 项目预算
     */
    @Override
    public List<ProjectBudget> selectProjectBudgetList(ProjectBudget projectBudget)
    {
        return projectBudgetMapper.selectProjectBudgetList(projectBudget);
    }

    /**
     * 新增项目预算
     * 
     * @param projectBudget 项目预算
     * @return 结果
     */
    @Override
    public int insertProjectBudget(ProjectBudget projectBudget)
    {
        projectBudget.setCreateTime(DateUtils.getNowDate());
        return projectBudgetMapper.insertProjectBudget(projectBudget);
    }

    /**
     * 修改项目预算
     * 
     * @param projectBudget 项目预算
     * @return 结果
     */
    @Override
    public int updateProjectBudget(ProjectBudget projectBudget)
    {
        projectBudget.setUpdateTime(DateUtils.getNowDate());
        return projectBudgetMapper.updateProjectBudget(projectBudget);
    }

    /**
     * 批量删除项目预算
     * 
     * @param ids 需要删除的项目预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectBudgetByIds(Integer[] ids)
    {
        return projectBudgetMapper.deleteProjectBudgetByIds(ids);
    }

    /**
     * 删除项目预算信息
     * 
     * @param id 项目预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectBudgetById(Integer id)
    {
        return projectBudgetMapper.deleteProjectBudgetById(id);
    }
}

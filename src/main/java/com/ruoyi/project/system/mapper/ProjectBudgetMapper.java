package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectBudget;

/**
 * 项目预算Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public interface ProjectBudgetMapper 
{
    /**
     * 查询项目预算
     * 
     * @param id 项目预算ID
     * @return 项目预算
     */
    public ProjectBudget selectProjectBudgetById(Integer id);

    /**
     * 查询项目预算列表
     * 
     * @param projectBudget 项目预算
     * @return 项目预算集合
     */
    public List<ProjectBudget> selectProjectBudgetList(ProjectBudget projectBudget);

    /**
     * 新增项目预算
     * 
     * @param projectBudget 项目预算
     * @return 结果
     */
    public int insertProjectBudget(ProjectBudget projectBudget);

    /**
     * 修改项目预算
     * 
     * @param projectBudget 项目预算
     * @return 结果
     */
    public int updateProjectBudget(ProjectBudget projectBudget);

    /**
     * 删除项目预算
     * 
     * @param id 项目预算ID
     * @return 结果
     */
    public int deleteProjectBudgetById(Integer id);

    /**
     * 批量删除项目预算
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectBudgetByIds(Integer[] ids);
}

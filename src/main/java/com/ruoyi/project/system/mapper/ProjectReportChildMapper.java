package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectReportChild;

/**
 * 产值明细Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public interface ProjectReportChildMapper 
{
    /**
     * 查询产值明细
     * 
     * @param id 产值明细ID
     * @return 产值明细
     */
    public ProjectReportChild selectProjectReportChildById(Integer id);

    /**
     * 查询产值明细列表
     * 
     * @param projectReportChild 产值明细
     * @return 产值明细集合
     */
    public List<ProjectReportChild> selectProjectReportChildList(ProjectReportChild projectReportChild);

    /**
     * 新增产值明细
     * 
     * @param projectReportChild 产值明细
     * @return 结果
     */
    public int insertProjectReportChild(ProjectReportChild projectReportChild);

    /**
     * 修改产值明细
     * 
     * @param projectReportChild 产值明细
     * @return 结果
     */
    public int updateProjectReportChild(ProjectReportChild projectReportChild);

    /**
     * 删除产值明细
     * 
     * @param id 产值明细ID
     * @return 结果
     */
    public int deleteProjectReportChildById(Integer id);
    /**
     * 根据父类ID批量删除产值明细
     *
     * @param ids 需要删除的产值明细父类ID
     * @return 结果
     */
    public int deleteProjectReportChildByPIds(Integer[] ids);
    /**
     * 批量删除产值明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectReportChildByIds(Integer[] ids);
}

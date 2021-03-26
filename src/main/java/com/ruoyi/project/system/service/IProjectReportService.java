package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectReport;

/**
 * 产值提报Service接口
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public interface IProjectReportService 
{
    /**
     * 查询产值提报
     * 
     * @param id 产值提报ID
     * @return 产值提报
     */
    public ProjectReport selectProjectReportById(Integer id);

    /**
     * 查询产值提报列表
     * 
     * @param projectReport 产值提报
     * @return 产值提报集合
     */
    public List<ProjectReport> selectProjectReportList(ProjectReport projectReport);

    /**
     * 查询产值提报审核列表
     *
     * @param projectReport 产值提报
     * @return 产值提报集合
     */
    public List<ProjectReport> selectProjectReportShList(ProjectReport projectReport);

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetProjectReportStatusOrNodeNo(String djNumber,Integer status,int type);

    /**
     * 新增产值提报
     * 
     * @param projectReport 产值提报
     * @return 结果
     */
    public int insertProjectReport(ProjectReport projectReport);

    /**
     * 修改产值提报
     * 
     * @param projectReport 产值提报
     * @return 结果
     */
    public int updateProjectReport(ProjectReport projectReport);

    /**
     * 批量删除产值提报
     * 
     * @param ids 需要删除的产值提报ID
     * @return 结果
     */
    public int deleteProjectReportByIds(Integer[] ids);

    /**
     * 删除产值提报信息
     * 
     * @param id 产值提报ID
     * @return 结果
     */
    public int deleteProjectReportById(Integer id);
}

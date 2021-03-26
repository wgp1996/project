package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectReportMapper;
import com.ruoyi.project.system.domain.ProjectReport;
import com.ruoyi.project.system.service.IProjectReportService;

/**
 * 产值提报Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
@Service
public class ProjectReportServiceImpl implements IProjectReportService 
{
    @Autowired
    private ProjectReportMapper projectReportMapper;

    /**
     * 查询产值提报
     * 
     * @param id 产值提报ID
     * @return 产值提报
     */
    @Override
    public ProjectReport selectProjectReportById(Integer id)
    {
        return projectReportMapper.selectProjectReportById(id);
    }

    /**
     * 查询产值提报列表
     * 
     * @param projectReport 产值提报
     * @return 产值提报
     */
    @Override
    public List<ProjectReport> selectProjectReportList(ProjectReport projectReport)
    {
        return projectReportMapper.selectProjectReportList(projectReport);
    }

    /**
     * 查询产值提报审核列表
     *
     * @param projectReport 产值提报
     * @return 产值提报集合
     */
    @Override
    public List<ProjectReport> selectProjectReportShList(ProjectReport projectReport){
        return projectReportMapper.selectProjectReportShList(projectReport);
    }

    /**
     * 修改审核状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updatetProjectReportStatusOrNodeNo(String djNumber,Integer status,int type){
        return projectReportMapper.updatetProjectReportStatusOrNodeNo(djNumber,status,type);
    }
    /**
     * 新增产值提报
     * 
     * @param projectReport 产值提报
     * @return 结果
     */
    @Override
    public int insertProjectReport(ProjectReport projectReport)
    {
        projectReport.setCreateTime(DateUtils.getNowDate());
        return projectReportMapper.insertProjectReport(projectReport);
    }

    /**
     * 修改产值提报
     * 
     * @param projectReport 产值提报
     * @return 结果
     */
    @Override
    public int updateProjectReport(ProjectReport projectReport)
    {
        projectReport.setUpdateTime(DateUtils.getNowDate());
        return projectReportMapper.updateProjectReport(projectReport);
    }

    /**
     * 批量删除产值提报
     * 
     * @param ids 需要删除的产值提报ID
     * @return 结果
     */
    @Override
    public int deleteProjectReportByIds(Integer[] ids)
    {
        return projectReportMapper.deleteProjectReportByIds(ids);
    }

    /**
     * 删除产值提报信息
     * 
     * @param id 产值提报ID
     * @return 结果
     */
    @Override
    public int deleteProjectReportById(Integer id)
    {
        return projectReportMapper.deleteProjectReportById(id);
    }
}

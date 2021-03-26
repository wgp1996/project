package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectReportChildMapper;
import com.ruoyi.project.system.domain.ProjectReportChild;
import com.ruoyi.project.system.service.IProjectReportChildService;

/**
 * 产值明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
@Service
public class ProjectReportChildServiceImpl implements IProjectReportChildService 
{
    @Autowired
    private ProjectReportChildMapper projectReportChildMapper;

    /**
     * 查询产值明细
     * 
     * @param id 产值明细ID
     * @return 产值明细
     */
    @Override
    public ProjectReportChild selectProjectReportChildById(Integer id)
    {
        return projectReportChildMapper.selectProjectReportChildById(id);
    }

    /**
     * 查询产值明细列表
     * 
     * @param projectReportChild 产值明细
     * @return 产值明细
     */
    @Override
    public List<ProjectReportChild> selectProjectReportChildList(ProjectReportChild projectReportChild)
    {
        return projectReportChildMapper.selectProjectReportChildList(projectReportChild);
    }

    /**
     * 新增产值明细
     * 
     * @param projectReportChild 产值明细
     * @return 结果
     */
    @Override
    public int insertProjectReportChild(ProjectReportChild projectReportChild)
    {
        projectReportChild.setCreateTime(DateUtils.getNowDate());
        return projectReportChildMapper.insertProjectReportChild(projectReportChild);
    }

    /**
     * 修改产值明细
     * 
     * @param projectReportChild 产值明细
     * @return 结果
     */
    @Override
    public int updateProjectReportChild(ProjectReportChild projectReportChild)
    {
        projectReportChild.setUpdateTime(DateUtils.getNowDate());
        return projectReportChildMapper.updateProjectReportChild(projectReportChild);
    }

    /**
     * 批量删除产值明细
     * 
     * @param ids 需要删除的产值明细ID
     * @return 结果
     */
    @Override
    public int deleteProjectReportChildByIds(Integer[] ids)
    {
        return projectReportChildMapper.deleteProjectReportChildByIds(ids);
    }

    /**
     * 根据父类ID批量删除产值明细
     *
     * @param ids 需要删除的产值明细父类ID
     * @return 结果
     */
    @Override
    public int deleteProjectReportChildByPIds(Integer[] ids){
        return projectReportChildMapper.deleteProjectReportChildByPIds(ids);
    }

    /**
     * 删除产值明细信息
     * 
     * @param id 产值明细ID
     * @return 结果
     */
    @Override
    public int deleteProjectReportChildById(Integer id)
    {
        return projectReportChildMapper.deleteProjectReportChildById(id);
    }
}

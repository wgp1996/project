package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectInfoMapper;
import com.ruoyi.project.system.domain.ProjectInfo;
import com.ruoyi.project.system.service.IProjectInfoService;

/**
 * 项目建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@Service
public class ProjectInfoServiceImpl implements IProjectInfoService 
{
    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    /**
     * 查询项目建档
     * 
     * @param id 项目建档ID
     * @return 项目建档
     */
    @Override
    public ProjectInfo selectProjectInfoById(Integer id)
    {
        return projectInfoMapper.selectProjectInfoById(id);
    }

    /**
     * 查询项目建档
     *
     * @param code 项目编码
     * @return 项目建档
     */
    @Override
    public ProjectInfo selectProjectInfoByCode(String code){
        return projectInfoMapper.selectProjectInfoByCode(code);
    }

    /**
     * 查询项目建档列表
     * 
     * @param projectInfo 项目建档
     * @return 项目建档
     */
    @Override
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo)
    {
        return projectInfoMapper.selectProjectInfoList(projectInfo);
    }
    /**
     * 检测项目编码是否重复
     *
     * @return 结果
     */
    @Override
    public int checkProjectCode(String projectCode,Integer id){
        return projectInfoMapper.checkProjectCode(projectCode,id);
    }
    /**
     * 检查项目明细
     *
     * @param ids 项目建档ID
     * @return 结果
     */
    @Override
    public int checkProjectChild(Integer[] ids){
        return projectInfoMapper.checkProjectChild(ids);
    }
    /**
     * 新增项目建档
     * 
     * @param projectInfo 项目建档
     * @return 结果
     */
    @Override
    public int insertProjectInfo(ProjectInfo projectInfo)
    {
        projectInfo.setCreateTime(DateUtils.getNowDate());
        return projectInfoMapper.insertProjectInfo(projectInfo);
    }

    /**
     * 修改项目建档
     * 
     * @param projectInfo 项目建档
     * @return 结果
     */
    @Override
    public int updateProjectInfo(ProjectInfo projectInfo)
    {
        projectInfo.setUpdateTime(DateUtils.getNowDate());
        return projectInfoMapper.updateProjectInfo(projectInfo);
    }

    /**
     * 批量删除项目建档
     * 
     * @param ids 需要删除的项目建档ID
     * @return 结果
     */
    @Override
    public int deleteProjectInfoByIds(Integer[] ids)
    {
        return projectInfoMapper.deleteProjectInfoByIds(ids);
    }

    /**
     * 删除项目建档信息
     * 
     * @param id 项目建档ID
     * @return 结果
     */
    @Override
    public int deleteProjectInfoById(Integer id)
    {
        return projectInfoMapper.deleteProjectInfoById(id);
    }
}

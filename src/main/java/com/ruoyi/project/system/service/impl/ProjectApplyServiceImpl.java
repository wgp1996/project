package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectApplyMapper;
import com.ruoyi.project.system.domain.ProjectApply;
import com.ruoyi.project.system.service.IProjectApplyService;

/**
 * 立项申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Service
public class ProjectApplyServiceImpl implements IProjectApplyService 
{
    @Autowired
    private ProjectApplyMapper projectApplyMapper;

    /**
     * 查询立项申请
     * 
     * @param id 立项申请ID
     * @return 立项申请
     */
    @Override
    public ProjectApply selectProjectApplyById(Long id)
    {
        return projectApplyMapper.selectProjectApplyById(id);
    }

    /**
     * 查询立项申请列表
     * 
     * @param projectApply 立项申请
     * @return 立项申请
     */
    @Override
    public List<ProjectApply> selectProjectApplyList(ProjectApply projectApply)
    {
        return projectApplyMapper.selectProjectApplyList(projectApply);
    }
    @Override
    public List<ProjectApply> selectProjectApplyShList(ProjectApply projectApply){
        return projectApplyMapper.selectProjectApplyShList(projectApply);
    }

    /**
     * 新增立项申请
     * 
     * @param projectApply 立项申请
     * @return 结果
     */
    @Override
    public int insertProjectApply(ProjectApply projectApply)
    {
        projectApply.setCreateTime(DateUtils.getNowDate());
        return projectApplyMapper.insertProjectApply(projectApply);
    }

    /**
     * 修改立项申请
     * 
     * @param projectApply 立项申请
     * @return 结果
     */
    @Override
    public int updateProjectApply(ProjectApply projectApply)
    {
        projectApply.setUpdateTime(DateUtils.getNowDate());
        return projectApplyMapper.updateProjectApply(projectApply);
    }

    /**
     * 批量删除立项申请
     * 
     * @param ids 需要删除的立项申请ID
     * @return 结果
     */
    @Override
    public int deleteProjectApplyByIds(Long[] ids)
    {
        return projectApplyMapper.deleteProjectApplyByIds(ids);
    }

    /**
     * 删除立项申请信息
     * 
     * @param id 立项申请ID
     * @return 结果
     */
    @Override
    public int deleteProjectApplyById(Long id)
    {
        return projectApplyMapper.deleteProjectApplyById(id);
    }

    /**
     * 修改立项申请状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updateProjectApplyStatusOrNodeNo(String djNumber,Integer status,int type){
        return projectApplyMapper.updateProjectApplyStatusOrNodeNo(djNumber,status,type);
    }
}

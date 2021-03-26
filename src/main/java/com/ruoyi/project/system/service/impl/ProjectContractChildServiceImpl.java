package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectContractChildMapper;
import com.ruoyi.project.system.domain.ProjectContractChild;
import com.ruoyi.project.system.service.IProjectContractChildService;

/**
 * 分包合同明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@Service
public class ProjectContractChildServiceImpl implements IProjectContractChildService 
{
    @Autowired
    private ProjectContractChildMapper projectContractChildMapper;

    /**
     * 查询分包合同明细
     * 
     * @param id 分包合同明细ID
     * @return 分包合同明细
     */
    @Override
    public ProjectContractChild selectProjectContractChildById(Integer id)
    {
        return projectContractChildMapper.selectProjectContractChildById(id);
    }

    /**
     * 查询分包合同明细列表
     * 
     * @param projectContractChild 分包合同明细
     * @return 分包合同明细
     */
    @Override
    public List<ProjectContractChild> selectProjectContractChildList(ProjectContractChild projectContractChild)
    {
        return projectContractChildMapper.selectProjectContractChildList(projectContractChild);
    }

    /**
     * 新增分包合同明细
     * 
     * @param projectContractChild 分包合同明细
     * @return 结果
     */
    @Override
    public int insertProjectContractChild(ProjectContractChild projectContractChild)
    {
        projectContractChild.setCreateTime(DateUtils.getNowDate());
        return projectContractChildMapper.insertProjectContractChild(projectContractChild);
    }

    /**
     * 修改分包合同明细
     * 
     * @param projectContractChild 分包合同明细
     * @return 结果
     */
    @Override
    public int updateProjectContractChild(ProjectContractChild projectContractChild)
    {
        projectContractChild.setUpdateTime(DateUtils.getNowDate());
        return projectContractChildMapper.updateProjectContractChild(projectContractChild);
    }

    /**
     * 批量删除分包合同明细
     * 
     * @param ids 需要删除的分包合同明细ID
     * @return 结果
     */
    @Override
    public int deleteProjectContractChildByIds(Integer[] ids)
    {
        return projectContractChildMapper.deleteProjectContractChildByIds(ids);
    }

    /**
     * 根据主表ID批量删除分包合同明细
     *
     * @param ids 需要删除的分包合同ID
     * @return 结果
     */
    @Override
    public int deleteProjectContractChildByPIds(Integer[] ids){
        return projectContractChildMapper.deleteProjectContractChildByPIds(ids);
    }
    /**
     * 删除分包合同明细信息
     * 
     * @param id 分包合同明细ID
     * @return 结果
     */
    @Override
    public int deleteProjectContractChildById(Integer id)
    {
        return projectContractChildMapper.deleteProjectContractChildById(id);
    }
}

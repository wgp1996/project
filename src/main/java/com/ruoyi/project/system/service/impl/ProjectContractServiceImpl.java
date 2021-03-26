package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ProjectContractMapper;
import com.ruoyi.project.system.domain.ProjectContract;
import com.ruoyi.project.system.service.IProjectContractService;

/**
 * 分包合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@Service
public class ProjectContractServiceImpl implements IProjectContractService 
{
    @Autowired
    private ProjectContractMapper projectContractMapper;

    /**
     * 查询分包合同
     * 
     * @param id 分包合同ID
     * @return 分包合同
     */
    @Override
    public ProjectContract selectProjectContractById(Integer id)
    {
        return projectContractMapper.selectProjectContractById(id);
    }

    /**
     * 查询分包合同列表
     * 
     * @param projectContract 分包合同
     * @return 分包合同
     */
    @Override
    public List<ProjectContract> selectProjectContractList(ProjectContract projectContract)
    {
        return projectContractMapper.selectProjectContractList(projectContract);
    }

    /**
     * 产值报告单查询分包合同列表
     *
     * @param projectContract 分包合同
     * @return 分包合同集合
     */
    @Override
    public List<ProjectContract> selectProjectContractListByReport(ProjectContract projectContract){
        return projectContractMapper.selectProjectContractListByReport(projectContract);
    }
    /**
     * 查询分包合同列表
     *
     * @param projectContract 分包合同
     * @return 分包合同集合
     */
    @Override
    public List<ProjectContract> selectProjectContractShList(ProjectContract projectContract){
        return projectContractMapper.selectProjectContractShList(projectContract);
    }

    /**
     * 新增分包合同
     * 
     * @param projectContract 分包合同
     * @return 结果
     */
    @Override
    public int insertProjectContract(ProjectContract projectContract)
    {
        projectContract.setCreateTime(DateUtils.getNowDate());
        return projectContractMapper.insertProjectContract(projectContract);
    }

    /**
     * 修改分包合同
     * 
     * @param projectContract 分包合同
     * @return 结果
     */
    @Override
    public int updateProjectContract(ProjectContract projectContract)
    {
        projectContract.setUpdateTime(DateUtils.getNowDate());
        return projectContractMapper.updateProjectContract(projectContract);
    }

    /**
     * 修改分包合同状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    @Override
    public int updatetProjectContractStatusOrNodeNo(String djNumber,Integer status,int type){
        return projectContractMapper.updatetProjectContractStatusOrNodeNo(djNumber,status,type);
    }

    /**
     * 批量删除分包合同
     * 
     * @param ids 需要删除的分包合同ID
     * @return 结果
     */
    @Override
    public int deleteProjectContractByIds(Integer[] ids)
    {
        return projectContractMapper.deleteProjectContractByIds(ids);
    }

    /**
     * 删除分包合同信息
     * 
     * @param id 分包合同ID
     * @return 结果
     */
    @Override
    public int deleteProjectContractById(Integer id)
    {
        return projectContractMapper.deleteProjectContractById(id);
    }
}

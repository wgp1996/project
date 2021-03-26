package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectContract;

/**
 * 分包合同Service接口
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public interface IProjectContractService 
{
    /**
     * 查询分包合同
     * 
     * @param id 分包合同ID
     * @return 分包合同
     */
    public ProjectContract selectProjectContractById(Integer id);

    /**
     * 查询分包合同列表
     * 
     * @param projectContract 分包合同
     * @return 分包合同集合
     */
    public List<ProjectContract> selectProjectContractShList(ProjectContract projectContract);

    /**
     * 查询分包合同审核列表
     *
     * @param projectContract 分包合同
     * @return 分包合同集合
     */
    public List<ProjectContract> selectProjectContractList(ProjectContract projectContract);

    /**
     * 新增分包合同
     * 
     * @param projectContract 分包合同
     * @return 结果
     */
    public int insertProjectContract(ProjectContract projectContract);

    /**
     * 修改分包合同
     * 
     * @param projectContract 分包合同
     * @return 结果
     */
    public int updateProjectContract(ProjectContract projectContract);

    /**
     * 修改分包合同状态
     *
     * @param djNumber 单号
     * @param status 单据状态
     * @param type 0 修改节点 1 修改状态
     * @return 结果
     */
    public int updatetProjectContractStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 批量删除分包合同
     * 
     * @param ids 需要删除的分包合同ID
     * @return 结果
     */
    public int deleteProjectContractByIds(Integer[] ids);

    /**
     * 删除分包合同信息
     * 
     * @param id 分包合同ID
     * @return 结果
     */
    public int deleteProjectContractById(Integer id);
}

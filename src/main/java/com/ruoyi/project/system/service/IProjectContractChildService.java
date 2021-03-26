package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectContractChild;

/**
 * 分包合同明细Service接口
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public interface IProjectContractChildService 
{
    /**
     * 查询分包合同明细
     * 
     * @param id 分包合同明细ID
     * @return 分包合同明细
     */
    public ProjectContractChild selectProjectContractChildById(Integer id);

    /**
     * 查询分包合同明细列表
     * 
     * @param projectContractChild 分包合同明细
     * @return 分包合同明细集合
     */
    public List<ProjectContractChild> selectProjectContractChildList(ProjectContractChild projectContractChild);

    /**
     * 新增分包合同明细
     * 
     * @param projectContractChild 分包合同明细
     * @return 结果
     */
    public int insertProjectContractChild(ProjectContractChild projectContractChild);

    /**
     * 修改分包合同明细
     * 
     * @param projectContractChild 分包合同明细
     * @return 结果
     */
    public int updateProjectContractChild(ProjectContractChild projectContractChild);

    /**
     * 批量删除分包合同明细
     * 
     * @param ids 需要删除的分包合同明细ID
     * @return 结果
     */
    public int deleteProjectContractChildByIds(Integer[] ids);

    /**
     * 根据主表ID批量删除分包合同明细
     *
     * @param ids 需要删除的分包合同ID
     * @return 结果
     */
    public int deleteProjectContractChildByPIds(Integer[] ids);


    /**
     * 删除分包合同明细信息
     * 
     * @param id 分包合同明细ID
     * @return 结果
     */
    public int deleteProjectContractChildById(Integer id);
}

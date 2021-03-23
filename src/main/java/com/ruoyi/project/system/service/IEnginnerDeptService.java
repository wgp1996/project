package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.EnginnerDept;
import com.ruoyi.project.system.domain.SysDept;

/**
 * 部门Service接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface IEnginnerDeptService 
{
    /**
     * 查询部门
     * 
     * @param deptId 部门ID
     * @return 部门
     */
    public EnginnerDept selectEnginnerDeptById(Long deptId);

    /**
     * 查询部门列表
     * 
     * @param enginnerDept 部门
     * @return 部门集合
     */
    public List<EnginnerDept> selectEnginnerDeptList(EnginnerDept enginnerDept);

    /**
     * 新增部门
     * 
     * @param enginnerDept 部门
     * @return 结果
     */
    public int insertEnginnerDept(EnginnerDept enginnerDept);

    /**
     * 修改部门
     * 
     * @param enginnerDept 部门
     * @return 结果
     */
    public int updateEnginnerDept(EnginnerDept enginnerDept);

    /**
     * 校验部门名称是否唯一
     *
     * @param enginnerDept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(EnginnerDept enginnerDept);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public boolean hasChildByDeptId(Long deptId);
    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门ID
     * @return 结果
     */
    public int deleteEnginnerDeptByIds(Long[] deptIds);

    /**
     * 删除部门信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteEnginnerDeptById(Long deptId);
}

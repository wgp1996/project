package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.EnginnerDept;
import com.ruoyi.project.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

/**
 * 部门Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface EnginnerDeptMapper 
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
     * @return 结果
     */
    public EnginnerDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);
    /**
     * 删除部门
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteEnginnerDeptById(Long deptId);

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEnginnerDeptByIds(Long[] deptIds);
}

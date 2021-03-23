package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.EnginnerDeptMapper;
import com.ruoyi.project.system.domain.EnginnerDept;
import com.ruoyi.project.system.service.IEnginnerDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Service
public class EnginnerDeptServiceImpl implements IEnginnerDeptService 
{
    @Autowired
    private EnginnerDeptMapper enginnerDeptMapper;

    /**
     * 查询部门
     * 
     * @param deptId 部门ID
     * @return 部门
     */
    @Override
    public EnginnerDept selectEnginnerDeptById(Long deptId)
    {
        return enginnerDeptMapper.selectEnginnerDeptById(deptId);
    }

    /**
     * 查询部门列表
     * 
     * @param enginnerDept 部门
     * @return 部门
     */
    @Override
    public List<EnginnerDept> selectEnginnerDeptList(EnginnerDept enginnerDept)
    {
        return enginnerDeptMapper.selectEnginnerDeptList(enginnerDept);
    }

    /**
     * 新增部门
     * 
     * @param enginnerDept 部门
     * @return 结果
     */
    @Override
    public int insertEnginnerDept(EnginnerDept enginnerDept)
    {
        enginnerDept.setCreateTime(DateUtils.getNowDate());
        return enginnerDeptMapper.insertEnginnerDept(enginnerDept);
    }

    /**
     * 修改部门
     * 
     * @param enginnerDept 部门
     * @return 结果
     */
    @Override
    public int updateEnginnerDept(EnginnerDept enginnerDept)
    {
        enginnerDept.setUpdateTime(DateUtils.getNowDate());
        return enginnerDeptMapper.updateEnginnerDept(enginnerDept);
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param enginnerDept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(EnginnerDept enginnerDept)
    {
        Long deptId = StringUtils.isNull(enginnerDept.getDeptId()) ? -1L : enginnerDept.getDeptId();
        EnginnerDept info = enginnerDeptMapper.checkDeptNameUnique(enginnerDept.getDeptName(), enginnerDept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId){
        int result = enginnerDeptMapper.hasChildByDeptId(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门ID
     * @return 结果
     */
    @Override
    public int deleteEnginnerDeptByIds(Long[] deptIds)
    {
        return enginnerDeptMapper.deleteEnginnerDeptByIds(deptIds);
    }

    /**
     * 删除部门信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteEnginnerDeptById(Long deptId)
    {
        return enginnerDeptMapper.deleteEnginnerDeptById(deptId);
    }
}

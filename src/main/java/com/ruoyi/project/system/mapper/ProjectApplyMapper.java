package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectApply;

/**
 * 立项申请Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface ProjectApplyMapper 
{
    /**
     * 查询立项申请
     * 
     * @param id 立项申请ID
     * @return 立项申请
     */
    public ProjectApply selectProjectApplyById(Long id);

    /**
     * 查询立项申请列表
     * 
     * @param projectApply 立项申请
     * @return 立项申请集合
     */
    public List<ProjectApply> selectProjectApplyList(ProjectApply projectApply);

    /**
     * 审核列表
     * @param projectApply
     * @return
     */
    public List<ProjectApply> selectProjectApplyShList(ProjectApply projectApply);
    /**
     * 修改立项申请状态
     * @param djNumber
     * @param status
     * @param type
     * @return
     */
    public int updateProjectApplyStatusOrNodeNo(String djNumber,Integer status,int type);
    /**
     * 新增立项申请
     * 
     * @param projectApply 立项申请
     * @return 结果
     */
    public int insertProjectApply(ProjectApply projectApply);

    /**
     * 修改立项申请
     * 
     * @param projectApply 立项申请
     * @return 结果
     */
    public int updateProjectApply(ProjectApply projectApply);

    /**
     * 删除立项申请
     * 
     * @param id 立项申请ID
     * @return 结果
     */
    public int deleteProjectApplyById(Long id);

    /**
     * 批量删除立项申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectApplyByIds(Long[] ids);
}

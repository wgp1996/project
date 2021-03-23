package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectApplyChild;

/**
 * 项目部信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface ProjectApplyChildMapper 
{
    /**
     * 查询项目部信息
     * 
     * @param id 项目部信息ID
     * @return 项目部信息
     */
    public ProjectApplyChild selectProjectApplyChildById(Long id);

    /**
     * 查询项目部信息列表
     * 
     * @param projectApplyChild 项目部信息
     * @return 项目部信息集合
     */
    public List<ProjectApplyChild> selectProjectApplyChildList(ProjectApplyChild projectApplyChild);

    /**
     * 新增项目部信息
     * 
     * @param projectApplyChild 项目部信息
     * @return 结果
     */
    public int insertProjectApplyChild(ProjectApplyChild projectApplyChild);

    /**
     * 修改项目部信息
     * 
     * @param projectApplyChild 项目部信息
     * @return 结果
     */
    public int updateProjectApplyChild(ProjectApplyChild projectApplyChild);

    /**
     * 删除项目部信息
     * 
     * @param id 项目部信息ID
     * @return 结果
     */
    public int deleteProjectApplyChildById(Long id);

    /**
     * 批量删除项目部信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectApplyChildByIds(Long[] ids);
    /**
     * 根据工程编码删除项目部信息
     * @param ids
     * @return
     */
    public int deleteProjectApplyChildByPIds(Long[] ids);
}

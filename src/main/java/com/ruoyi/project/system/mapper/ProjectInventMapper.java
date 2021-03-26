package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectInvent;

/**
 * 预算编制Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public interface ProjectInventMapper 
{
    /**
     * 查询预算编制
     * 
     * @param id 预算编制ID
     * @return 预算编制
     */
    public ProjectInvent selectProjectInventById(Integer id);

    /**
     * 查询预算编制列表
     * 
     * @param projectInvent 预算编制
     * @return 预算编制集合
     */
    public List<ProjectInvent> selectProjectInventList(ProjectInvent projectInvent);

    /**
     * 新增预算编制
     * 
     * @param projectInvent 预算编制
     * @return 结果
     */
    public int insertProjectInvent(ProjectInvent projectInvent);

    /**
     * 修改预算编制
     * 
     * @param projectInvent 预算编制
     * @return 结果
     */
    public int updateProjectInvent(ProjectInvent projectInvent);

    /**
     * 删除预算编制
     * 
     * @param id 预算编制ID
     * @return 结果
     */
    public int deleteProjectInventById(Integer id);

    /**
     * 批量删除预算编制
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectInventByIds(Integer[] ids);
}

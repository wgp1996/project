package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ProjectInfo;

/**
 * 项目建档Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public interface ProjectInfoMapper 
{
    /**
     * 查询项目建档
     * 
     * @param id 项目建档ID
     * @return 项目建档
     */
    public ProjectInfo selectProjectInfoById(Integer id);

    /**
     * 查询项目建档
     *
     * @param code 项目编码
     * @return 项目建档
     */
    public ProjectInfo selectProjectInfoByCode(String code);

    /**
     * 查询项目建档列表
     * 
     * @param projectInfo 项目建档
     * @return 项目建档集合
     */
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo);

    /**
     * 总包选择项目建档列表
     *
     * @param projectInfo 项目建档
     * @return 项目建档集合
     */
    public List<ProjectInfo> selectProjectInfoListByZb(ProjectInfo projectInfo);
    /**
     * 新增项目建档
     * 
     * @param projectInfo 项目建档
     * @return 结果
     */
    public int insertProjectInfo(ProjectInfo projectInfo);
    /**
     * 检查项目明细
     *
     * @param ids 项目建档ID
     * @return 结果
     */
    public int checkProjectChild(Integer[] ids);
    /**
     * 修改项目建档
     * 
     * @param projectInfo 项目建档
     * @return 结果
     */
    public int updateProjectInfo(ProjectInfo projectInfo);
    /**
     * 检测项目编码是否重复
     *
     * @return 结果
     */
    public int checkProjectCode(String projectCode,Integer id);
    /**
     * 删除项目建档
     * 
     * @param id 项目建档ID
     * @return 结果
     */
    public int deleteProjectInfoById(Integer id);

    /**
     * 批量删除项目建档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectInfoByIds(Integer[] ids);
}

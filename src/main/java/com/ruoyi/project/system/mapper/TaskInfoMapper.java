package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TaskInfo;

/**
 * 任务管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
public interface TaskInfoMapper 
{
    /**
     * 查询任务管理
     * 
     * @param id 任务管理ID
     * @return 任务管理
     */
    public TaskInfo selectTaskInfoById(Integer id);

    /**
     * 查询任务管理列表
     * 
     * @param taskInfo 任务管理
     * @return 任务管理集合
     */
    public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo);

    /**
     * 新增任务管理
     * 
     * @param taskInfo 任务管理
     * @return 结果
     */
    public int insertTaskInfo(TaskInfo taskInfo);

    /**
     * 修改任务管理
     * 
     * @param taskInfo 任务管理
     * @return 结果
     */
    public int updateTaskInfo(TaskInfo taskInfo);

    /**
     * 删除任务管理
     * 
     * @param id 任务管理ID
     * @return 结果
     */
    public int deleteTaskInfoById(Integer id);

    /**
     * 批量删除任务管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskInfoByIds(Integer[] ids);
}

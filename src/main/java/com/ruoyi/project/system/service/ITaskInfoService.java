package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.TaskInfo;

/**
 * 任务管理Service接口
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
public interface ITaskInfoService 
{
    /**
     * 查询任务管理
     * 
     * @param id 任务管理ID
     * @return 任务管理
     */
    public TaskInfo selectTaskInfoById(Integer id);

    /**
     * 查询首页消息
     *
     * @param createBy 制单人
     * @return 任务管理
     */
    public TaskInfo selectIndexCount(String createBy);

    /**
     * 查询任务管理列表
     * 
     * @param taskInfo 任务管理
     * @return 任务管理集合
     */
    public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo);

    /**
     * 查询审核菜单列表跟数量
     *
     * @return 菜单名称跟数量
     */
    public List<TaskInfo> selectShMenuList(String userId,String roleId);



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
     * 批量删除任务管理
     * 
     * @param ids 需要删除的任务管理ID
     * @return 结果
     */
    public int deleteTaskInfoByIds(Integer[] ids);

    /**
     * 删除任务管理信息
     * 
     * @param id 任务管理ID
     * @return 结果
     */
    public int deleteTaskInfoById(Integer id);
}

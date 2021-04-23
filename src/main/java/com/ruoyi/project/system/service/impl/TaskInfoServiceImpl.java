package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TaskInfoMapper;
import com.ruoyi.project.system.domain.TaskInfo;
import com.ruoyi.project.system.service.ITaskInfoService;

/**
 * 任务管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
@Service
public class TaskInfoServiceImpl implements ITaskInfoService 
{
    @Autowired
    private TaskInfoMapper taskInfoMapper;

    /**
     * 查询审核菜单列表跟数量
     *
     * @return 菜单名称跟数量
     */
    @Override
    public List<TaskInfo> selectShMenuList(String userId,String roleId){
        return taskInfoMapper.selectShMenuList(userId,roleId);
    }

    /**
     * 查询任务管理
     * 
     * @param id 任务管理ID
     * @return 任务管理
     */
    @Override
    public TaskInfo selectTaskInfoById(Integer id)
    {
        return taskInfoMapper.selectTaskInfoById(id);
    }

    /**
     * 查询首页消息
     *
     * @param createBy 制单人
     * @return 任务管理
     */
    @Override
    public TaskInfo selectIndexCount(String createBy){
        return taskInfoMapper.selectIndexCount(createBy);
    }

    /**
     * 查询任务管理列表
     * 
     * @param taskInfo 任务管理
     * @return 任务管理
     */
    @Override
    public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo)
    {
        return taskInfoMapper.selectTaskInfoList(taskInfo);
    }

    /**
     * 新增任务管理
     * 
     * @param taskInfo 任务管理
     * @return 结果
     */
    @Override
    public int insertTaskInfo(TaskInfo taskInfo)
    {
        taskInfo.setCreateTime(DateUtils.getNowDate());
        return taskInfoMapper.insertTaskInfo(taskInfo);
    }

    /**
     * 修改任务管理
     * 
     * @param taskInfo 任务管理
     * @return 结果
     */
    @Override
    public int updateTaskInfo(TaskInfo taskInfo)
    {
        taskInfo.setUpdateTime(DateUtils.getNowDate());
        return taskInfoMapper.updateTaskInfo(taskInfo);
    }

    /**
     * 批量删除任务管理
     * 
     * @param ids 需要删除的任务管理ID
     * @return 结果
     */
    @Override
    public int deleteTaskInfoByIds(Integer[] ids)
    {
        return taskInfoMapper.deleteTaskInfoByIds(ids);
    }

    /**
     * 删除任务管理信息
     * 
     * @param id 任务管理ID
     * @return 结果
     */
    @Override
    public int deleteTaskInfoById(Integer id)
    {
        return taskInfoMapper.deleteTaskInfoById(id);
    }
}

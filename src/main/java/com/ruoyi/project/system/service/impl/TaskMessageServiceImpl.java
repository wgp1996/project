package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.TaskMessageMapper;
import com.ruoyi.project.system.domain.TaskMessage;
import com.ruoyi.project.system.service.ITaskMessageService;

/**
 * 任务对话详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
@Service
public class TaskMessageServiceImpl implements ITaskMessageService 
{
    @Autowired
    private TaskMessageMapper taskMessageMapper;

    /**
     * 查询任务对话详情
     * 
     * @param id 任务对话详情ID
     * @return 任务对话详情
     */
    @Override
    public TaskMessage selectTaskMessageById(Integer id)
    {
        return taskMessageMapper.selectTaskMessageById(id);
    }

    /**
     * 查询任务对话详情列表
     * 
     * @param taskMessage 任务对话详情
     * @return 任务对话详情
     */
    @Override
    public List<TaskMessage> selectTaskMessageList(TaskMessage taskMessage)
    {
        return taskMessageMapper.selectTaskMessageList(taskMessage);
    }

    /**
     * 新增任务对话详情
     * 
     * @param taskMessage 任务对话详情
     * @return 结果
     */
    @Override
    public int insertTaskMessage(TaskMessage taskMessage)
    {
        taskMessage.setCreateTime(DateUtils.getNowDate());
        if(taskMessage.getCreateBy()!=null&&!"".equals(taskMessage.getCreateBy())){

        }else{
            taskMessage.setCreateBy(SecurityUtils.getUsername());
        }
        return taskMessageMapper.insertTaskMessage(taskMessage);
    }

    /**
     * 修改任务对话详情
     * 
     * @param taskMessage 任务对话详情
     * @return 结果
     */
    @Override
    public int updateTaskMessage(TaskMessage taskMessage)
    {
        taskMessage.setUpdateTime(DateUtils.getNowDate());
        return taskMessageMapper.updateTaskMessage(taskMessage);
    }

    /**
     * 批量删除任务对话详情
     * 
     * @param ids 需要删除的任务对话详情ID
     * @return 结果
     */
    @Override
    public int deleteTaskMessageByIds(Integer[] ids)
    {
        return taskMessageMapper.deleteTaskMessageByIds(ids);
    }

    /**
     * 删除任务对话详情信息
     * 
     * @param id 任务对话详情ID
     * @return 结果
     */
    @Override
    public int deleteTaskMessageById(Integer id)
    {
        return taskMessageMapper.deleteTaskMessageById(id);
    }
    /**
     * 删除任务对话信息
     *
     * @param taskCode 任务编码
     * @return 结果
     */
    @Override
    public int deleteTaskMessageByTaskCode(String taskCode){
        return taskMessageMapper.deleteTaskMessageByTaskCode(taskCode);
    }
}

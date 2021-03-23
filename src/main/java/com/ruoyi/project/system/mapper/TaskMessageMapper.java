package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.TaskMessage;

/**
 * 任务对话详情Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
public interface TaskMessageMapper 
{
    /**
     * 查询任务对话详情
     * 
     * @param id 任务对话详情ID
     * @return 任务对话详情
     */
    public TaskMessage selectTaskMessageById(Integer id);

    /**
     * 查询任务对话详情列表
     * 
     * @param taskMessage 任务对话详情
     * @return 任务对话详情集合
     */
    public List<TaskMessage> selectTaskMessageList(TaskMessage taskMessage);

    /**
     * 新增任务对话详情
     * 
     * @param taskMessage 任务对话详情
     * @return 结果
     */
    public int insertTaskMessage(TaskMessage taskMessage);

    /**
     * 修改任务对话详情
     * 
     * @param taskMessage 任务对话详情
     * @return 结果
     */
    public int updateTaskMessage(TaskMessage taskMessage);

    /**
     * 删除任务对话详情
     * 
     * @param id 任务对话详情ID
     * @return 结果
     */
    public int deleteTaskMessageById(Integer id);
    /**
     * 删除任务对话信息
     *
     * @param taskCode 任务编码
     * @return 结果
     */
    public int deleteTaskMessageByTaskCode(String taskCode);
    /**
     * 批量删除任务对话详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskMessageByIds(Integer[] ids);
}

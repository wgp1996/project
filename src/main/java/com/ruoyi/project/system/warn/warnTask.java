package com.ruoyi.project.system.warn;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.TaskInfo;
import com.ruoyi.project.system.service.ITaskInfoService;
import com.ruoyi.project.system.service.ITaskMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

/**
 * 检测任务是否超期
 * 
 * @author ruoyi
 */
@Component("warnTask")
public class warnTask
{
    @Autowired
    private ITaskInfoService taskInfoService;

    public void checkTask()
    {
       TaskInfo taskInfo=new TaskInfo();
       List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
       for(TaskInfo info:list){
           //如果超期 修改状态
           if(DateUtils.compare_date(info.getTaskEndTime(), DateUtils.getDate())==1){
               //进行中的任务判断是否超期
               if(taskInfo.getStatus()==1){
                   info.setStatus(-1);
                   taskInfoService.updateTaskInfo(info);
               }
           }
       }
        taskInfo=null;
    }

    public static void main(String[] args) {
        DateUtils.compare_date("2021-03-08", "2021-03-05");
    }
}

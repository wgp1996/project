package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.WebSocket.WebSocketServer;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.TaskMessage;
import com.ruoyi.project.system.service.ITaskMessageService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.TaskInfo;
import com.ruoyi.project.system.service.ITaskInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务管理Controller
 * 
 * @author ruoyi
 * @date 2021-03-09
 */
@RestController
@RequestMapping("/system/taskInfo")
public class TaskInfoController extends BaseController
{
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private ITaskMessageService taskMessageService;
    /**
     * 查询我安排的任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskInfo:list')")
    @GetMapping("/list")
    //@DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(TaskInfo taskInfo)
    {
        taskInfo.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return getDataTable(list);
    }

    /**
     * 查询派给我的任务管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:taskInfo:list')")
    @GetMapping("/sendList")
    public TableDataInfo sendList(TaskInfo taskInfo)
    {
        startPage();
        taskInfo.setImplementUserCode(SecurityUtils.getUsername());
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return getDataTable(list);
    }

    //查询进行中的任务列表
    @GetMapping("/sendAllList")
    public TableDataInfo sendAllList(TaskInfo taskInfo)
    {
       // startPage();
        //1 表示查询首页 未开始跟进行中的任务
        taskInfo.setTaskType(1);
        taskInfo.setImplementUserCode(SecurityUtils.getUsername());
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return getDataTable(list);
    }

    /**
     * 查询菜单跟数量列表
     * @return
     */
    @GetMapping("/selectShMenuList")
    public TableDataInfo selectShMenuList()
    {
        String userId= SecurityUtils.getUsername();
        String roleId=SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleId()+"";
        List<TaskInfo> list =taskInfoService.selectShMenuList(userId,roleId);
        return getDataTable(list);
    }

    /**
     * 导出任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskInfo:export')")
    @Log(title = "任务管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskInfo taskInfo)
    {
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        ExcelUtil<TaskInfo> util = new ExcelUtil<TaskInfo>(TaskInfo.class);
        return util.exportExcel(list, "taskInfo");
    }

    /**
     * 我的安排任务管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setId(id);
        //如果未读改为已读
        taskInfo.setIsRead(1);
        taskInfoService.updateTaskInfo(taskInfo);
        taskInfo=null;
        return AjaxResult.success(taskInfoService.selectTaskInfoById(id));
    }

    /**
     * 派给我的任务管理详细信息
     */
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setId(id);
        //如果催办取消催办
        taskInfo.setIsUrge(0);
        //如果未读改为已读
        taskInfo.setSendIsRead(1);
        taskInfoService.updateTaskInfo(taskInfo);
        taskInfo=null;
        return AjaxResult.success(taskInfoService.selectTaskInfoById(id));
    }

    /**
     * 新增任务管理
     */
    @PreAuthorize("@ss.hasPermi('system:taskInfo:add')")
    @Log(title = "新增任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskInfo taskInfo)
    {
        taskInfo.setTaskCode(StringUtils.getRandomCode("TS"));
        //新任务
        taskInfo.setIsRead(0);
        taskInfo.setSendIsRead(0);
        //未开始
        taskInfo.setStatus(0);
        //默认进度
        taskInfo.setTaskNum("0");
        //是否催办
        taskInfo.setIsUrge(0);
        //任务优先级
        taskInfo.setUrgentStatus(0);
        taskInfo.setCreateBy(SecurityUtils.getUsername());
        //添加创建任务消息
        TaskMessage message=new TaskMessage();
        //通知类消息
        message.setType(1);
        message.setTaskCode(taskInfo.getTaskCode());
        message.setMessage("创建了任务");
        taskMessageService.insertTaskMessage(message);
        message=null;
        //推送给执行人
        WebSocketServer WebSocketServer=new WebSocketServer();
        String text="您有一个新的任务待处理！";
        WebSocketServer.sendOneMessage(taskInfo.getImplementUserCode(),text);
/*          String text1="你们好！这是websocket群体发送！";
            WebSocketServer.sendAllMessage(text1);*/
        return toAjax(taskInfoService.insertTaskInfo(taskInfo));
    }


    /**
     * 修改任务优先级
     */
    @Log(title = "修改任务优先级", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/changeUrgentStatus/{id}/{status}/{taskCode}")
    public AjaxResult changeUrgentStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status,@PathVariable("taskCode") String taskCode)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setId(id);
        taskInfo.setUrgentStatus(status);
        int result=taskInfoService.updateTaskInfo(taskInfo);
        taskInfo=null;
        if(result>0){
            String strStatus="普通";
            if(status==1){
                strStatus="重要";
            }
            if(status==2){
                strStatus="紧急";
            }
            //创建任务消息
            TaskMessage message=new TaskMessage();
            //通知类消息
            message.setType(1);
            message.setTaskCode(taskCode);
            message.setMessage("设置了任务的优先级为："+strStatus);
            taskMessageService.insertTaskMessage(message);
            message=null;
            return toAjaxBySuccess("修改成功");
        }else{
            return toAjaxByError("修改失败");
        }
    }

    /**
     * 任务验收
     */
    @Log(title = "任务验收", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/checkAccept/{id}/{message}/{num}/{type}")
    public AjaxResult checkAccept(@PathVariable("id") Integer id,@PathVariable("message") String message,@PathVariable("num") Integer num,@PathVariable("type") Integer type)
    {
        TaskInfo taskInfo=taskInfoService.selectTaskInfoById(id);
        //创建任务消息
        TaskMessage messageInfo=new TaskMessage();
        taskInfo.setMessage(message);
        taskInfo.setSendIsRead(0);
        WebSocketServer WebSocketServer=new WebSocketServer();
        String text="";
        //验收通过
        if(type==0){
            messageInfo.setMessage("验收通过:"+message);
            messageInfo.setNum(num);
            taskInfo.setTaskNum(num+"");
            //结束任务
            taskInfo.setStatus(4);
            //推送给执行人
            text="["+taskInfo.getTaskName()+"]验收通过：:"+message;
        //不通过
        }else{
            //重新开始任务
            messageInfo.setMessage("验收不通过:"+message);
            text="["+taskInfo.getTaskName()+"]验收不通过："+message;
            taskInfo.setStatus(0);
            taskInfo.setTaskNum("0");
        }
        int result=taskInfoService.updateTaskInfo(taskInfo);
        if(result>0){
            //通知类消息
            messageInfo.setType(1);
            messageInfo.setTaskCode(taskInfo.getTaskCode());
            taskMessageService.insertTaskMessage(messageInfo);
            messageInfo=null;
            WebSocketServer.sendOneMessage(taskInfo.getImplementUserCode(),text);
            taskInfo=null;
            return toAjaxBySuccess("修改成功");
        }else{
            return toAjaxByError("修改失败");
        }
    }

    /**
     * 任务催办
     */
    @Log(title = "任务催办", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/changeIsUrge/{id}")
    public AjaxResult changeIsUrge(@PathVariable("id") Integer id)
    {
        TaskInfo taskInfo=taskInfoService.selectTaskInfoById(id);
        taskInfo.setIsUrge(1);
        int result=taskInfoService.updateTaskInfo(taskInfo);
        if(result>0){
            //创建任务消息
            TaskMessage message=new TaskMessage();
            //通知类消息
            message.setType(1);
            message.setTaskCode(taskInfo.getTaskCode());
            message.setMessage("催办了任务");
            taskMessageService.insertTaskMessage(message);
            message=null;
            //推送给执行人
            WebSocketServer WebSocketServer=new WebSocketServer();
            String text=taskInfo.getCreateBy()+"催办了任务["+taskInfo.getTaskName()+"]";
            WebSocketServer.sendOneMessage(taskInfo.getImplementUserCode(),text);
            taskInfo=null;
            return toAjaxBySuccess("催办成功!");
        }else{
            return toAjaxByError("催办失败!");
        }
    }

    /**
     * 反馈任务进度
     */
    @Log(title = "反馈任务进度", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/changeTaskNum/{id}/{taskNum}")
    public AjaxResult changeTaskNum(@PathVariable("id") Integer id,@PathVariable("taskNum") Integer taskNum)
    {
        TaskInfo taskInfo=taskInfoService.selectTaskInfoById(id);
        taskInfo.setTaskNum(taskNum+"");
        //改为未读
        taskInfo.setIsRead(0);
        //判断是否为100 即判断是否验收
        if(taskNum==100){
            taskInfo.setStatus(3);
        }else{
            //任务进行中
            taskInfo.setStatus(1);
        }
        int result=taskInfoService.updateTaskInfo(taskInfo);
        if(result>0){
            //创建任务消息
            TaskMessage message=new TaskMessage();
            //通知类消息
            message.setType(1);
            message.setTaskCode(taskInfo.getTaskCode());
            message.setMessage("反馈了最新进度："+taskNum+"%");
            taskMessageService.insertTaskMessage(message);
            //推送消息
            WebSocketServer WebSocketServer=new WebSocketServer();
            String text=taskInfo.getImplementUserName()+"反馈["+taskInfo.getTaskName()+"]任务进度:"+taskNum;
            WebSocketServer.sendOneMessage(taskInfo.getCreateBy(),text);
            message=null;
            taskInfo=null;
            return toAjaxBySuccess("修改成功");
        }else{
            return toAjaxByError("修改失败");
        }
    }

    /**
     * 修改任务管理
     */
    @PreAuthorize("@ss.hasPermi('system:taskInfo:edit')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskInfo taskInfo)
    {
        if(taskInfo.getStatus()==4){
            return toAjaxByError("该任务已完成!");
        }
        TaskInfo info=taskInfoService.selectTaskInfoById(taskInfo.getId());
        taskInfo.setSendIsRead(0);
        String changeStr="";
        //比较任务名称
        if(!taskInfo.getTaskName().equals(info.getTaskName())){
            changeStr="修改了任务名称";
        }
        //比较任务描述
        if(!taskInfo.getRemark().equals(info.getRemark())){
            changeStr="修改了任务描述";
        }
        //比较截至日期
        if(!taskInfo.getTaskEndTime().equals(info.getTaskEndTime())){
            changeStr="修改了截至日期";
        }
        //比较附件
        /*if(taskInfo.getFileName().length()>200||!taskInfo.getFileName().equals(info.getFileName())){
            changeStr="修改了附件信息";
        }*/
        //创建任务消息
        TaskMessage message=new TaskMessage();
        //通知类消息
        message.setType(1);
        message.setTaskCode(taskInfo.getTaskCode());
        message.setMessage(changeStr);
        taskMessageService.insertTaskMessage(message);
        message=null;
        return toAjax(taskInfoService.updateTaskInfo(taskInfo));
    }

    /**
     * 删除任务管理
     */
    @PreAuthorize("@ss.hasPermi('system:taskInfo:remove')")
    @Log(title = "删除任务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        TaskInfo info=taskInfoService.selectTaskInfoById(ids[0]);
        taskMessageService.deleteTaskMessageByTaskCode(info.getTaskCode());
        info=null;
        return toAjax(taskInfoService.deleteTaskInfoByIds(ids));
    }
}

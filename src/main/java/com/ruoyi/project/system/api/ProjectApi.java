package com.ruoyi.project.system.api;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JpushClientUtil.JpushClientUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.WebSocket.WebSocketServer;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.sms.SMSUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api("项目管理APP接口")
@RestController
@RequestMapping("/api")
public class ProjectApi extends BaseController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ITaskInfoService taskInfoService;
    @Autowired
    private ITaskMessageService taskMessageService;
    @Autowired
    private IProjectInfoService projectInfoService;
    /**
     * 登录方法
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @ApiOperation("用户登录")
    @Log(title = "用户登录", businessType = BusinessType.OTHER)
    @GetMapping("/login/{userName}/{password}")
    public AjaxResult appLogin(@PathVariable String userName, @PathVariable String password)
    {
        AjaxResult ajax=null;
        SysUser user=userService.selectUserByUserName(userName);
        if(user==null){
            ajax = AjaxResult.error(404,"用户不存在");
        }else{
            if(SecurityUtils.matchesPassword(password,user.getPassword())){
                if(user.getStatus().equals("1")){
                    ajax = AjaxResult.error(403,"用户未审核");
                }else{
                    ajax = new AjaxResult(200,"登录成功",user);
                }
            }else{
                ajax = AjaxResult.error(402,"密码错误");
            }
        }
        return  ajax;
    }


    /**
     * 是否已绑定账号
     *
     * @param openId
     * @return 结果
     */
    @ApiOperation("检测是否已绑定账号")
    @Log(title = "检测是否已绑定账号", businessType = BusinessType.OTHER)
    @GetMapping("/checkBind/{openId}")
    public AjaxResult checkBind(@PathVariable String openId)
    {
        AjaxResult ajax=null;
        SysUser user=userService.selectUserByOpenId(openId);
        if(user==null){
            ajax = AjaxResult.error(403,"未绑定");
        }else{
            ajax = new AjaxResult(200,"已绑定",user);
        }
        return  ajax;
    }
    @ApiOperation("修改账号信息")
    @Log(title = "修改账号信息", businessType = BusinessType.UPDATE)
    @PostMapping("/userUpdate")
    public AjaxResult userUpdate(SysUser user)
    {
        AjaxResult ajax=null;
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            ajax= AjaxResult.error(403,"修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        if(userService.updateUser(user)>0){
            ajax= new AjaxResult(200,"操作成功",user);
        }else{
            ajax = AjaxResult.error(500,"操作失败");
        }
        return  ajax;
    }

    /**
     * 获取用户列表
     */
    @ApiOperation("获取用户列表")
    @GetMapping("/getAllUser/{userName}")
    public AjaxResult getAllUser(@PathVariable String userName)
    {
        List<SysUser> list = userService.selectAllUserList(userName);
        return AjaxResult.success(list);
    }

    /**
     * 获取项目列表
     */
    @ApiOperation("获取项目列表")
    @GetMapping("/getAllProject/{userName}")
    public AjaxResult getAllProject(@PathVariable String userName)
    {
        ProjectInfo info=new ProjectInfo();
        info.setCreateBy(userName);
        List<ProjectInfo> list = projectInfoService.selectProjectInfoList(info);
        info=null;
        return AjaxResult.success(list);
    }

    /**
     * 微信绑定手机号
     *
     * @param openId
     * @return 结果
     */
    @ApiOperation("微信绑定手机号")
    @Log(title = "微信绑定手机号", businessType = BusinessType.OTHER)
    @GetMapping("/wxBind/{openId}/{SMSCode}/{userName}")
    public AjaxResult wxLogin(@PathVariable String openId,@PathVariable String SMSCode,@PathVariable String userName)
    {
        AjaxResult ajax=null;
        //检测手机号是否已注册
        SysUser user=userService.selectUserByUserName(userName);
        try{
            if(SMSCode==null||"".equals(SMSCode)){
                return AjaxResult.error(501,"验证码不能为空!");
            }
            //用手机号做为登录账号
            String smsCode=redisCache.getCacheObject(userName);
            if(smsCode==null||"".equals(smsCode)){
                return AjaxResult.error(502,"验证码已过期!");
            }
            if(!smsCode.equals(SMSCode)){
                return AjaxResult.error(503,"验证码错误!");
            }
            if(user==null){
                //未注册自动注册
                //admin默认为注册用户
                user=new SysUser();
                user.setCreateBy("admin");
                user.setWxOpenId(openId);
                user.setUserName(userName);
                user.setNickName(userName);
                user.setPhonenumber(userName);
                user.setOld("123");//记录原始密码
                user.setPassword(SecurityUtils.encryptPassword("123"));//默认密码123
                //新建部门
                SysDept dept=new SysDept();
                dept.setParentId((long) 100);
                dept.setAncestors("0,100");
                dept.setDeptName(user.getUserName());
                dept.setOrderNum("0");
                dept.setLeader(user.getNickName());
                dept.setPhone(user.getPhonenumber());
                dept.setEmail(user.getEmail());
                dept.setCreateBy("admin");
                dept.setStatus("0");
                dept.setCreateTime(DateUtils.getNowDate());
                deptService.insertDept(dept);
                //根据部门名称查询部门ID
                SysDept item=deptService.selectDeptByName(user.getUserName());
                user.setDeptId(item.getDeptId());
                user.setStatus("0");//不需要审核
                Long [] roles={Long.parseLong("2")};
                user.setRoleIds(roles);
                userService.insertUser(user);
            }else{
                //已注册修改OpenId
                user.setWxOpenId(openId);
                userService.updateUser(user);
            }
            ajax = new AjaxResult(200,"绑定成功!",user);
        }catch (Exception e){
            e.printStackTrace();
            ajax = AjaxResult.error(500,"系统异常");
        }
        return  ajax;
    }

    /**
     * 登录方法
     *
     * @param userName 用户名即手机号
     * @return 结果
     */
    @ApiOperation("发送短信验证码")
    @Log(title = "发送短信验证码", businessType = BusinessType.OTHER)
    @GetMapping("/sendSms/{userName}")
    public AjaxResult sendSms(@PathVariable String userName)
    {
        SMSUtils sms=new SMSUtils();
        String yzc=SMSUtils.createRandom(true,6);//6位随机验证码
        String statusCode= sms.sendSMSMessage(userName,yzc);
        if("000000".equals(statusCode)){
            try{
                String sysCode=redisCache.getCacheObject(userName);
                //删除验证码
                if(sysCode==null){

                }else{
                    redisCache.deleteObject(userName);
                }
            }catch (NullPointerException e){

            }
            //放入缓存中
            redisCache.setCacheObject(userName, yzc, 10, TimeUnit.MINUTES);
            return  toAjaxBySuccess("发送成功!");
        }else{
            return toAjaxByError("发送失败");
        }
    }



    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @Log(title = "用户注册", businessType = BusinessType.INSERT)
    @PostMapping("/register")
    public AjaxResult register(SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error(403,"登录账号已存在!");
        }
        if(user.getSmsCode()==null||"".equals(user.getSmsCode())){
            return AjaxResult.error(501,"验证码不能为空!");
        }
        //用手机号做为登录账号
        String smsCode=redisCache.getCacheObject(user.getUserName());
        if(smsCode==null||"".equals(smsCode)){
            return AjaxResult.error(502,"验证码已过期!");
        }
        if(!smsCode.equals(user.getSmsCode())){
            return AjaxResult.error(503,"验证码错误!");
        }
        //admin默认为注册用户
        user.setCreateBy("admin");
        user.setOld(user.getPassword());//记录原始密码
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        //新建部门
        SysDept dept=new SysDept();
        dept.setParentId((long) 100);
        dept.setAncestors("0,100");
        dept.setDeptName(user.getUserName());
        dept.setOrderNum("0");
        dept.setLeader(user.getNickName());
        dept.setPhone(user.getPhonenumber());
        dept.setEmail(user.getEmail());
        dept.setCreateBy("admin");
        dept.setStatus("0");
        dept.setCreateTime(DateUtils.getNowDate());
        int result=deptService.insertDept(dept);
        if(result>0){
            //根据部门名称查询部门ID
            SysDept item=deptService.selectDeptByName(user.getUserName());
            user.setDeptId(item.getDeptId());
            user.setStatus("0");//需要审核
            Long [] roles={Long.parseLong("2")};
            user.setRoleIds(roles);
            userService.insertUser(user);
            return toAjaxBySuccess("注册成功!");
        }else{
            return toAjaxByError("注册失败!");
        }

    }

    /**
     * 查询我安排的任务管理列表
     */
    @ApiOperation("查询我安排的任务管理列表")
    @GetMapping("/taskList/{createBy}")
    public AjaxResult taskList(@PathVariable String createBy)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setCreateBy(createBy);
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return AjaxResult.success(list);
    }

    /**
     * 查询派给我的任务管理列表
     */
    @ApiOperation("查询派给我的任务管理列表")
    @GetMapping("/sendList/{createBy}")
    public AjaxResult sendList(@PathVariable String createBy)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setImplementUserCode(createBy);
        List<TaskInfo> list = taskInfoService.selectTaskInfoList(taskInfo);
        return AjaxResult.success(list);
    }


    /**
     * 我的安排任务管理详细信息
     */
    @ApiOperation("查询任务详情")
    @GetMapping(value = "/detail/{id}/{type}")
    public AjaxResult getInfo(@PathVariable("id") Integer id,@PathVariable("type") Integer type)
    {
        TaskInfo taskInfo=new TaskInfo();
        taskInfo.setId(id);
        //我的安排
        if(type==0){
            //如果未读改为已读
            taskInfo.setIsRead(1);
        }else{  //派给我的
            //如果催办取消催办
            taskInfo.setIsUrge(0);
            //如果未读改为已读
            taskInfo.setSendIsRead(1);
        }

        taskInfoService.updateTaskInfo(taskInfo);
        taskInfo=null;
        List<TaskInfo> data=new ArrayList<>();
        data.add(taskInfoService.selectTaskInfoById(id));
        return AjaxResult.success(data);
    }

    /**
     * 新增任务管理
     */
    @ApiOperation("新增任务")
    @Log(title = "新增任务", businessType = BusinessType.INSERT)
    @PostMapping("/addTask")
    public AjaxResult addTask(TaskInfo taskInfo)
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
        //添加创建任务消息
        TaskMessage message=new TaskMessage();
        //通知类消息
        message.setType(1);
        message.setCreateBy(taskInfo.getCreateBy());
        message.setTaskCode(taskInfo.getTaskCode());
        message.setMessage("创建了任务");
        taskMessageService.insertTaskMessage(message);
        message=null;
        //极光推送
        JpushClientUtil.sendToRegistrationId(taskInfo.getImplementUserCode(),"项目通","消息通知","您有一个新的任务["+taskInfo.getTaskName()+"]待处理!","createBy");

        return toAjax(taskInfoService.insertTaskInfo(taskInfo));
    }

    /**
     * 修改任务管理
     */
    @ApiOperation("修改任务")
    @Log(title = "修改任务", businessType = BusinessType.UPDATE)
    @PostMapping("/editTask")
    public AjaxResult editTask(TaskInfo taskInfo)
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
        message.setCreateBy(taskInfo.getCreateBy());
        message.setTaskCode(taskInfo.getTaskCode());
        message.setMessage(changeStr);
        taskMessageService.insertTaskMessage(message);
        message=null;
        return toAjax(taskInfoService.updateTaskInfo(taskInfo));
    }
    /**
     * 修改任务优先级
     */
    @ApiOperation("修改任务优先级")
    @Log(title = "修改任务优先级", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/changeUrgentStatus/{id}/{status}")
    public AjaxResult changeUrgentStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status)
    {
        TaskInfo taskInfo=taskInfoService.selectTaskInfoById(id);
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
            message.setCreateBy(taskInfo.getCreateBy());
            message.setTaskCode(taskInfo.getTaskCode());
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
    @ApiOperation("任务验收")
    @Log(title = "任务验收", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/checkAccept/{id}/{message}/{num}/{type}")
    public AjaxResult checkAccept(@PathVariable("id") Integer id,@PathVariable("message") String message,@PathVariable("num") Integer num,@PathVariable("type") Integer type)
    {
        TaskInfo taskInfo=taskInfoService.selectTaskInfoById(id);
        //创建任务消息
        TaskMessage messageInfo=new TaskMessage();
        taskInfo.setMessage(message);
        taskInfo.setSendIsRead(0);
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
        JpushClientUtil.sendToRegistrationId(taskInfo.getImplementUserCode(),"项目通","消息通知",text,"createBy");
        int result=taskInfoService.updateTaskInfo(taskInfo);
        if(result>0){
            //通知类消息
            messageInfo.setType(1);
            messageInfo.setCreateBy(taskInfo.getCreateBy());
            messageInfo.setTaskCode(taskInfo.getTaskCode());
            taskMessageService.insertTaskMessage(messageInfo);
            messageInfo=null;
            taskInfo=null;
            return toAjaxBySuccess("验收成功");
        }else{
            return toAjaxByError("验收失败");
        }
    }

    /**
     * 任务催办
     */
    @ApiOperation("任务催办")
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
            message.setCreateBy(taskInfo.getCreateBy());
            message.setTaskCode(taskInfo.getTaskCode());
            message.setMessage("催办了任务");
            taskMessageService.insertTaskMessage(message);
            JpushClientUtil.sendToRegistrationId(taskInfo.getImplementUserCode(),"项目通","消息通知","催办了"+taskInfo.getTaskName()+"任务!","createBy");
            message=null;
            //推送给执行人
            taskInfo=null;
            return toAjaxBySuccess("催办成功!");
        }else{
            return toAjaxByError("催办失败!");
        }
    }

    /**
     * 反馈任务进度
     */
    @ApiOperation("反馈任务进度")
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
            message.setCreateBy(taskInfo.getCreateBy());
            message.setTaskCode(taskInfo.getTaskCode());
            message.setMessage("反馈了最新进度："+taskNum+"%");
            taskMessageService.insertTaskMessage(message);

            JpushClientUtil.sendToRegistrationId(taskInfo.getCreateBy(),"项目通","消息通知","反馈了最新进度："+taskNum+"%","createBy");
            //推送消息
            message=null;
            taskInfo=null;
            return toAjaxBySuccess("反馈成功");
        }else{
            return toAjaxByError("反馈失败");
        }
    }


    /**
     * 删除任务管理
     */
    @ApiOperation("删除任务管理")
    @Log(title = "删除任务管理", businessType = BusinessType.DELETE)
    @GetMapping("/delTask/{id}")
    public AjaxResult remove(@PathVariable Integer id)
    {
        TaskInfo info=taskInfoService.selectTaskInfoById(id);
        taskMessageService.deleteTaskMessageByTaskCode(info.getTaskCode());
        info=null;
        return toAjax(taskInfoService.deleteTaskInfoById(id));
    }

    /**
     * 任务消息列表
     * @param taskCode
     * @return
     */
    @ApiOperation("任务消息列表")
    @GetMapping("/messageList/{taskCode}")
    public AjaxResult messageList(@PathVariable String taskCode)
    {
        TaskMessage taskMessage=new TaskMessage();
        taskMessage.setTaskCode(taskCode);
        List<TaskMessage> list = taskMessageService.selectTaskMessageList(taskMessage);
        return AjaxResult.success(list);
    }

    /**
     * 新增任务对话详情
     */
    @ApiOperation("新增任务对话")
    @Log(title = "新增任务对话", businessType = BusinessType.INSERT)
    @PostMapping("/addMesssage")
    public AjaxResult addMesssage(TaskMessage taskMessage)
    {
        taskMessage.setType(0);//普通消息
        return toAjax(taskMessageService.insertTaskMessage(taskMessage));
    }
}

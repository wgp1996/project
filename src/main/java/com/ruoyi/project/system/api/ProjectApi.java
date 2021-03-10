package com.ruoyi.project.system.api;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.sms.SMSUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysPostService;
import com.ruoyi.project.system.service.ISysRoleService;
import com.ruoyi.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}

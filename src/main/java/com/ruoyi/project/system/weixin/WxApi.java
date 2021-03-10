package com.ruoyi.project.system.weixin;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/wx/api")
public class WxApi extends BaseController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private RedisCache redisCache;
    /**
     * 根据code获取信息
     *
     * @param code
     * @return 结果
     */
    @GetMapping("/getAccessToken")
    public void getAccessToken(@PathVariable String code) throws IOException
    {
        //拼接url
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxConstant.APPID + "&secret="
                + WxConstant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = doGetJson(url);
        //1.获取微信用户的openid
        String openid = jsonObject.getString("openid");
        //2.获取获取access_token
        String access_token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
                + "&lang=zh_CN";
        //3.获取微信用户信息
        JSONObject userInfo = doGetJson(infoUrl);
        //至此拿到了微信用户的所有信息,剩下的就是业务逻辑处理部分了
        //保存openid和access_token到session
        //request.getSession().setAttribute("openid", openid);
        //request.getSession().setAttribute("access_token", access_token);
        //去数据库查询此微信是否绑定过手机
        //UserVo user = userService.queryByOpenId(openid);
        //String mobile=user==null?"":user.getMobile();
//        if(null == mobile || "".equals(mobile)){
//            //如果无手机信息,则跳转手机绑定页面
//            response.sendRedirect("/front/register.html");
//        }else{
//            //否则直接跳转首页
//            response.sendRedirect("/front/index.html");
//        }
    }

    /**
     * 获取openId
     *
     * @param code
     * @return 结果
     */
    @ApiOperation("获取openId")
    @Log(title = "获取openId", businessType = BusinessType.OTHER)
    @GetMapping("/getOpenId/{code}")
    public AjaxResult getOpenId(@PathVariable String code)
    {
        AjaxResult ajax=null;
        try{
            //拼接url

            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxConstant.APPID + "&secret="
                    + WxConstant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
            JSONObject jsonObject = doGetJson(url);
            System.out.println(jsonObject);
            //1.获取微信用户的openid
            String openid = jsonObject.getString("openid");
            if(openid==null||"".equals(openid)){
                //返回错误信息
                ajax = AjaxResult.error(201,jsonObject.getInt("errcode")+jsonObject.getString("errmsg"));
            }else{
                ajax = new AjaxResult(200,"操作成功",openid);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajax = AjaxResult.error(201,"系统异常");
        }

        return  ajax;
    }


    public  JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
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
            ajax = AjaxResult.error(201,"未绑定");
        }else{
            ajax = new AjaxResult(200,"已绑定",user);
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
    public AjaxResult wxBind(@PathVariable String openId,@PathVariable String SMSCode,@PathVariable String userName)
    {
        AjaxResult ajax=null;
        //检测手机号是否已注册
        SysUser user=userService.selectUserByUserName(userName);
        try{
            if(SMSCode==null||"".equals(SMSCode)){
                return AjaxResult.error(201,"验证码不能为空!");
            }
            //用手机号做为登录账号
            String smsCode=redisCache.getCacheObject(userName);
            if(smsCode==null||"".equals(smsCode)){
                return AjaxResult.error(201,"验证码已过期!");
            }
            if(!smsCode.equals(SMSCode)){
                return AjaxResult.error(201,"验证码错误!");
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
            ajax = AjaxResult.error(201,"系统异常");
        }
        return  ajax;
    }

}

package com.ruoyi.framework.sms;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ruoyi.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SMSUtils {
    /**
     * 主账户SID
     */
    public static String ACCOUNT_SID = "aaf98f894ef91b17014efb86c2140155";
    /**
     * 主账户token
     */
    public static String AUTH_TOKEN = "78808b3671824868b2e66b593209d65b";//主账户token
    /**
     *
     * 短信模版ID 需要申请获得 1表示测试模版
     */
    public static String DX_DEMO_ID = "741365";
    /**
     * APPID
     */
    public static String APP_ID = "8a216da8754a45d5017573993d990e18";
    /**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
    /**
     * 发送不同普通文本短信。（验证码）
     *  cellNumber 手机号 ，yzm 验证码,time_yx 有效时间 测试版单位为分钟
     */
    public  String sendSMSMessage(String cellNumber,String yzc){
        String statusCode = "";
        String yjxx="10分钟";//默认有效期为10分钟
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.setAccount(ACCOUNT_SID, AUTH_TOKEN);// 初始化主帐号名称和主帐号令牌
        restAPI.setAppId(APP_ID);// 初始化应用ID
        result = restAPI.sendTemplateSMS(cellNumber,DX_DEMO_ID ,new String[]{yzc,yjxx});
        statusCode=result.get("statusCode").toString();
        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(statusCode)){
            statusCode="000000";
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        return statusCode;
    }
}

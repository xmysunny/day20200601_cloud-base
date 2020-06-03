package com.wn.common.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.message.service
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 16:03
 * @Description: 阿里云短信服务
 */
public class SendSms {
    private int phoneCode;//手机验证码
    private String phoneMessage;//返回的信息
    /**
     * 发送短信
     * @param phoneNum:要接收短信的手机号
     */
    public static SendSms sendSMS(String phoneNum) {
        System.out.println("进入阿里云短信服务，要发送验证码的手机号为："+phoneNum);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GHAm72zwoQoYAVoDxSa", "BWQ6M7kILeROZwfrbL8cXB6nMCbaUN");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "xmysunny");
        request.putQueryParameter("TemplateCode", "SMS_192305240");
        //生成随机6位数字验证码
        Random random = new Random();
        int code = random.nextInt(999999);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        //要返回的参数
        SendSms sendSms=null;
        try {
            CommonResponse response = client.getCommonResponse(request);
            String getMessage = response.getData();//返回信息

            sendSms = new SendSms();
            sendSms.setPhoneCode(code);
            sendSms.setPhoneMessage(getMessage);
            System.out.println(getMessage);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendSms;
    }

    public int getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneMessage() {
        return phoneMessage;
    }

    public void setPhoneMessage(String phoneMessage) {
        this.phoneMessage = phoneMessage;
    }
}

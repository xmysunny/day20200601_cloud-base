package com.wn.message.service;

import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.PhoneMsg;
import com.wn.common.entity.TextEmailEntity;
import com.wn.common.util.EmailUtil;
import com.wn.common.util.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.message.service
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 16:03
 * @Description:信息模块业务层
 */
@Component
public class MessageService {
    @Autowired
    private StringRedisTemplate redisTemplate;//拿到redisTemplate对象


    /**
     * 发送邮件
     * @param textEmailEntity
     */
    public void serdTextMail(TextEmailEntity textEmailEntity){
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(from);//发件人
//        simpleMailMessage.setTo(textEmailEntity.getTo());//收件人
//        simpleMailMessage.setSubject("eureka服务通知");//主题
//        simpleMailMessage.setText(textEmailEntity.getContent());//内容
//
//        javaMailSender.send(simpleMailMessage);
        try {
            //发送邮件
            EmailUtil.sendMail("1457592194@qq.com", "dqqyvyactvhyijjc", "smtp.qq.com","1457592194@qq.com", "2239599266@qq.com", "eureka服务通知",
                    textEmailEntity.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送手机短信验证码
     * @return
     */
    public MyRes sendPhoneMsg(MyParam<PhoneMsg> myParam){
        SendSms sendSms = SendSms.sendSMS(myParam.getT().getPhoneNum());//发送短信验证码
        //判断是否发送成功
        if (sendSms.getPhoneMessage().contains("OK")){
            System.out.println("将手机号和验证码存储到redis数据库"+sendSms.getPhoneMessage());
            //发送成功,将手机号和验证码存储到redis数据库
            ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
            //以手机号作为键，以验证码作为值,过期时间为5分钟
            opsForValue.set(myParam.getT().getPhoneNum(),sendSms.getPhoneCode()+"",60*5, TimeUnit.SECONDS);
            return MyRes.success("验证码获取成功");
        }else{
            //发送失败
            return MyRes.fail().code(50000).msg("验证码获取失败");
        }
    }



    /**
     * 手机短信验证码登录
     * @param myParam
     * @return
     */
    public MyRes loginWithPhone(MyParam<PhoneMsg> myParam){
        //取出用户输入的手机号和验证码
        String phoneNum = myParam.getT().getPhoneNum();
        String phoneCode = myParam.getT().getPhoneCode();

        //取出redis中的以手机号为键的验证码对比
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String redisCode = opsForValue.get(phoneNum);
        System.out.println("redisCode="+redisCode);
        if (redisCode.equals(phoneCode)){
            deleteCode(myParam.getT().getPhoneNum());
            return MyRes.success("登录成功");
        }else {
            return MyRes.fail().msg("登录失败").code(50000);
        }
    }

    /**
     * 登录成功删除验证码
     * @param key
     */
    public void deleteCode(String key){
        redisTemplate.delete(key);
    }
}

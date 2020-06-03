package com.wn.message.controller;

import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.PhoneMsg;
import com.wn.common.entity.TextEmailEntity;
import com.wn.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.message.controller
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 16:13
 * @Description:信息模块表现层
 */
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    /**
     * 发送邮件
     * @param textEmailEntity
     * @return
     */
    @PostMapping("serdTextMail")
    public Object serdTextMail(@RequestBody TextEmailEntity textEmailEntity){
        int a=5/0;

        messageService.serdTextMail(textEmailEntity);
        return MyRes.success("发送成功");
    }

    /**
     * 发送手机短信验证码
     * @return
     */
    @PostMapping("sendPhoneMsg")
    public MyRes sendPhoneMsg(@RequestBody MyParam<PhoneMsg> myParam){
        System.out.println("messagetest");
        MyRes myRes = messageService.sendPhoneMsg(myParam);

        return myRes;
    }
}

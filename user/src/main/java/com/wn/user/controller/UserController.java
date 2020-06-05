package com.wn.user.controller;

import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.Order;
import com.wn.common.entity.PhoneMsg;
import com.wn.user.feign.MessageFeign;
import com.wn.user.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.user.controller
 * @Author: 殷俊
 * @CreateTime: 2020-06-01 15:20
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private MessageFeign messageFeign;

    /**
     * 访问订单服务
     * @return
     */
    @GetMapping("visitOrder")
    public MyRes visitOrder(){

        return orderFeign.visitOrder();
    }
    @PostMapping("getOrder")
    public MyRes getOrder(Order order){
        System.out.println("order="+order);
        MyRes order1 = orderFeign.getOrder(order);
        return MyRes.success(order1);
    }

    /**
     * 获取手机验证码
     * @param myParam
     * @return
     */
    @PostMapping("sendPhoneMsg")
    public MyRes sendPhoneMsg(@RequestBody MyParam<PhoneMsg> myParam){
        MyRes myRes = messageFeign.sendPhoneMsg(myParam);
        return myRes;
    }

    /**
     * 手机短信验证码登录
     * @param myParam
     * @return
     */
    @PostMapping("loginWithPhone")
    public MyRes loginWithPhone(@RequestBody MyParam<PhoneMsg> myParam){
        MyRes myRes = messageFeign.loginWithPhone(myParam);
        return myRes;
    }
}

package com.wn.user.controller;

import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.Order;
import com.wn.common.entity.PhoneMsg;
import com.wn.user.feign.MessageFeign;
import com.wn.user.feign.OrderFeign;
import io.swagger.annotations.Api;
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
@Api(tags = "eureka测试")//在要生成接口文档的java类上加注解
@RestController
public class UserController {
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private MessageFeign messageFeign;
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
        System.out.println(myParam);
        MyRes myRes = messageFeign.sendPhoneMsg(myParam);
        return myRes;
    }
}

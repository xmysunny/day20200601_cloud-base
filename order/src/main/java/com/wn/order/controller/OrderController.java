package com.wn.order.controller;

import com.github.pagehelper.PageInfo;
import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.Order;
import com.wn.order.feign.MessageFeign;
import com.wn.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.controller
 * @Author: 殷俊
 * @CreateTime: 2020-06-01 15:13
 * @Description:
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    MessageFeign messageFeign;

    @Value("${server.port}")
    private int post;//端口

    @Value("${spring.application.name}")
    private  String name;//名字

    @Value("${eureka.client.region}")
    private String region;//分区

    @Value("${eureka.instance.metadata-map.zone}")
    private String zone;//机房

    /**
     * 订单服务
     * @return
     */
    @GetMapping("hello")
    public MyRes visitOrder(){

        //MyRes mess = messageFeign.serdTextMail(new TextEmailEntity("2239599266@qq.com", "服务降级通知"));
        //return mess;
        String message="我是"+name+"服务,"+post+"端口,"+region+"分区"+zone+"机房";
        return MyRes.success(message);
    }

    @RequestMapping("getOrder")
    public MyRes getOrder(@RequestBody Order order){
        System.out.println("order类中的："+order);
        Order order1 = new Order(order.getOrderId(), "电视",new Date());
        return MyRes.success(order1);
    }

    /**
     * 分页查询
     * @return
     */
    @PostMapping("getAll")
    public MyRes getAll(@RequestBody MyParam<Order> myParam){
        PageInfo<Order> all = orderService.getAll(myParam);
        return MyRes.success(all);
    }

}

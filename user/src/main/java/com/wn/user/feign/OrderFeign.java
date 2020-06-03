package com.wn.user.feign;

import com.wn.common.entity.MyRes;
import com.wn.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.user
 * @Author: 殷俊
 * @CreateTime: 2020-06-01 15:17
 * @Description:
 */
@FeignClient(name="order",fallback = OrderFeignFallBack.class)//绑定要调用的模块名称为order
public interface OrderFeign {

    @GetMapping("hello")//和订单模块中的表现层接口接口路径一致
    MyRes visitOrder();

    @RequestMapping("getOrder")
    public MyRes getOrder(Order order);
}

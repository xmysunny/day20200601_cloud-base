package com.wn.user.feign;

import com.wn.common.entity.MyRes;
import com.wn.common.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.user.feign
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 14:04
 * @Description:
 */
@Component
public class OrderFeignFallBack implements OrderFeign {
    @Override
    public MyRes visitOrder() {
        //执行此方法的服务降级操作，返回消息通知

        return MyRes.fail().msg("服务跟丢了").code(5000);
    }

    @Override
    public MyRes getOrder(Order order) {
        return null;
    }
}

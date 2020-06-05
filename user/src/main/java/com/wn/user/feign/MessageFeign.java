package com.wn.user.feign;

import com.wn.common.entity.MyParam;
import com.wn.common.entity.MyRes;
import com.wn.common.entity.PhoneMsg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.feign
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 14:08
 * @Description: 调用message微服务的feign客户端
 */
@FeignClient(name = "message")
public interface MessageFeign {
    @RequestMapping("sendPhoneMsg")
    public MyRes sendPhoneMsg(MyParam<PhoneMsg> myParam);

    @RequestMapping("loginWithPhone")
    public MyRes loginWithPhone(MyParam<PhoneMsg> myParam);
}

package com.wn.eureka.feign;

import com.wn.common.entity.MyRes;
import com.wn.common.entity.TextEmailEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.eureka.feign
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 16:19
 * @Description:
 */
@FeignClient(name = "message")
public interface MessageFeign {
    @RequestMapping("serdTextMail")
    public MyRes serdTextMail(TextEmailEntity textEmailEntity);
}

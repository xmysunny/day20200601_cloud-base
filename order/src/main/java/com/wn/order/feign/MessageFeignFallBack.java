package com.wn.order.feign;

import com.wn.common.entity.MyRes;
import com.wn.common.entity.TextEmailEntity;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.feign
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 14:13
 * @Description:
 */
@Component
public class MessageFeignFallBack implements MessageFeign{
    @Override
    public MyRes serdTextMail(TextEmailEntity textEmailEntity) {
        return MyRes.fail().msg("发邮件失败").code(5000);
    }
}

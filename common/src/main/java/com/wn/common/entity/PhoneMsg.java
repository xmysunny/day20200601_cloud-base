package com.wn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 18:30
 * @Description: 手机信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneMsg {

    private String phoneNum;//手机号码

    private String phoneCode;//手机验证码
}

package com.wn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-01 18:25
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String summary;
    private Date createDate;
}

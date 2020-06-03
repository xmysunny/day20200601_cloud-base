package com.wn.order.service;

import com.github.pagehelper.PageInfo;
import com.wn.common.entity.MyParam;
import com.wn.common.entity.Order;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.service
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 10:36
 * @Description: 业务层接口
 */
public interface OrderService {

    public PageInfo<Order> getAll(MyParam<Order> myParam);
}

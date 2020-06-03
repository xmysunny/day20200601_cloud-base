package com.wn.order.dao;

import com.wn.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.dao
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 10:30
 * @Description: 持久化层接口
 */
@Mapper //实例化此类
public interface OrderMapper {

    @Select("select * from orderInfo")//使用注解进行简单的sql查询语句
    public List<Order> getAll();
}

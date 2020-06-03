package com.wn.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wn.common.entity.MyParam;
import com.wn.common.entity.Order;
import com.wn.order.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.order.service
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 10:37
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public PageInfo<Order> getAll(MyParam<Order> myParam) {
        //分页查询
        PageHelper.startPage(myParam.getPageParam().getPageNum(),myParam.getPageParam().getPageSize());//分页的页数和每页显示的条数
        //排序
        String[] orderParam = myParam.getOrderParam();//排序参数集合
        for (int i=0;i<orderParam.length;i++){
            PageHelper.orderBy(orderParam[i]);//遍历出排序参数数组，以此排序
        }
        List<Order> all = orderMapper.getAll();//调用dao层接口，获取查询后的数据集合
        //生成PageInfo信息
        PageInfo<Order> orderPageInfo = new PageInfo<>(all);//将查询到的集合传入到pageinfo中
        //返回pageinfo信息
        return orderPageInfo;
    }
}

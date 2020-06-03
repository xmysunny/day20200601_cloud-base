package com.wn.common.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 10:09
 * @Description: 参数封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyParam<T> {
    //分页参数
    @NotNull()
    private MyPage pageParam;
    //排序参数
    private String[] orderParam;
    //对象参数(因为不知道传的对象的类型，所以使用泛型)
    private T t;
}

package com.wn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-03 10:07
 * @Description: 分页参数实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    //页数
    private int pageNum;
    //每页的显示数量
    private int pageSize;
}

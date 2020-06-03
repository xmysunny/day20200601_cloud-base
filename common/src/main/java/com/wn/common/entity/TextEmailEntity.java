package com.wn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 16:05
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextEmailEntity {
    //发送的邮件地址
    private String to;
    //发送的内容
    private String content;

    public static TextEmailEntity setToAndText(String to,String content){
        TextEmailEntity textEmailEntity = new TextEmailEntity(to, content);
        return textEmailEntity;
    }
}

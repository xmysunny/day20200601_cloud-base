package com.wn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.common.entity
 * @Author: 殷俊
 * @CreateTime: 2020-06-01 17:52
 * @Description: 公共响应类
 */
@Data   //生成get、set方法、tostring方法
@AllArgsConstructor //生成有参构造方法
@NoArgsConstructor  //生成无参构造方法
public class MyRes {
    private int code;//状态码
    private String msg;//信息
    private Object content;//内容
    public static MyRes success(Object content){
        MyRes myRes = new MyRes();
        myRes.setCode(20000);//请求成功
        myRes.setMsg("请求成功");
        myRes.setContent(content);
        return myRes;
    }
    public MyRes msg(String msg){
        this.msg=msg;
        return this;
    }
    public MyRes code(int code){
        this.code=code;
        return this;
    }
    public static MyRes fail(){
        MyRes myRes = new MyRes();
        myRes.setCode(50000);
        myRes.setMsg("请求失败");
        myRes.setContent(null);
        return myRes;
    }
}

package com.wn.eureka.listener;

import com.netflix.appinfo.InstanceInfo;
import com.wn.common.entity.TextEmailEntity;
import com.wn.eureka.feign.MessageFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: day20200601_cloud-base
 * @BelongsPackage: com.wn.eureka.listener
 * @Author: 殷俊
 * @CreateTime: 2020-06-02 14:43
 * @Description: 事件监听
 */
@Component
public class EurekaListener {
    @Autowired
    MessageFeign messageFeign;
    /**
     * 服务下线事件
     */
    @EventListener
    public void listener1(EurekaInstanceCanceledEvent event){
        String appName = event.getAppName();
        long timestamp = event.getTimestamp();//下线时间
        String format = timeOut(timestamp);
        String text="服务下线通知：服务名称-"+appName+" 实例ID："+event.getServerId()+" 下线时间："+format;
        System.out.println(text);
        if (appName.equalsIgnoreCase("user")){
            //发送下线邮件
            TextEmailEntity textEmailEntity = TextEmailEntity.setToAndText("2239599266@qq.com", text);
            messageFeign.serdTextMail(textEmailEntity);
        }
    }

    /**
     * 服务注册事件
     * @param event
     */
    @EventListener
    public void listener2(EurekaInstanceRegisteredEvent event){
        InstanceInfo instanceInfo = event.getInstanceInfo();//获取服务信息
        long timestamp = event.getTimestamp();
        String timeOut = timeOut(timestamp);
        System.out.println("服务注册通知：服务名称-"+instanceInfo.getAppName()+" 实例ID："+instanceInfo.getId()+" 注册时间："+timeOut);

    }
    /**
     * 服务续约事件
     * @param event
     */
    @EventListener
    public void listener3(EurekaInstanceRenewedEvent event){
        long timestamp = event.getTimestamp();
        String timeOut = timeOut(timestamp);
        System.out.println("服务续约通知：服务名称-"+event.getAppName()+" 实例ID："+event.getServerId()+" 续约时间："+timeOut);

    }
    /**
     * 注册中心启动事件
     * @param event
     */
    @EventListener
    public void listener4(EurekaRegistryAvailableEvent event){
        long timestamp = event.getTimestamp();
        String timeOut = timeOut(timestamp);
        System.out.println("注册中心启动通知："+event.toString());

    }

    //转换过期时间
    public String timeOut(long timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(timestamp));//将下线时间转换为字符串
        return format;
    }
}

package com.dupake.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient//注册到consul
@EnableFeignClients//开启feign
@EnableHystrix//开启熔断
public class HmCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmCommodityApplication.class, args);
    }

}

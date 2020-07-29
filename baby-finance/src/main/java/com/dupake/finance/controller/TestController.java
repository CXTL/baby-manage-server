package com.dupake.finance.controller;

import com.dupake.finance.entity.Order;
import com.dupake.finance.service.IMessageProvider;
import com.dupake.finance.service.IdGeneratorService;
import com.dupake.finance.service.ReportAssetService;
import com.dupake.finance.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 9:40
 * @description
 */
@RestController
@Api(tags = "Test控制器")
@Slf4j
public class TestController {


    @Resource
    private RedisUtil redisUtil;

    @Resource
    private IdGeneratorService idGeneratorService;

    @Resource
    private IMessageProvider messageProvider;


    @Resource
    private ReportAssetService reportAssetService;

    /**
     * 每天一点生成昨天的数据
     */
//    @Scheduled(cron="*/6 * * * * ?")
    @GetMapping("/insertReportAsset")
    public void insertReportAsset(){
        reportAssetService.insertReportAsset();
    }


    @PostMapping("/helloProvider")
    public String helloProvider(@RequestParam(value = "name") String name){
        return "hello report______________________" + name;
    }


    @ApiOperation(value = "redis测试", notes = "redis测试 - for-web")
    @GetMapping("/testRedis")
    public void testRedis() {
        Order order = new Order();
        order.setId(1L);
        order.setName("测试REDIS");
        order.setCreateTime(System.currentTimeMillis());
        redisUtil.set("redistest", order);
        boolean exists = redisUtil.hasKey("redistest");
        System.out.println("redis是否存在相应的key" + exists);
        Order getUser = (Order) redisUtil.get("redistest");
        System.out.println("从redis数据库获取的user:" + getUser.toString());
    }

    @ApiOperation(value = "订单号测试", notes = "订单号测试 - for-web")
    @GetMapping("/testOrderNo")
    public Long testOrderNo() {
        Long order = idGeneratorService.generatorId("order");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            cachedThreadPool.execute(() -> {
//                for (int j = 0; j < 20000; j++) {
//                    System.out.println();
//                }
//
//            });
//        }
        return order;
    }


    @ApiOperation(value = "gateway测试", notes = "gateway测试 - for-web")
    @GetMapping(value = "/get")
    public String testGateway() {
        int i = 1 / 0;
        try {
            Thread.sleep(50000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello gateway";
    }


    @GetMapping("/sendMessage")
    public String sendMessage() {

        return messageProvider.send();
    }

    @GetMapping(value = "/hello/{str}", produces = "application/json")
    public String hello(@PathVariable String str) {
        log.info("-----------收到消费者请求-----------");
        log.info("收到消费者传递的参数：" + str);
        String result = "我是服务提供者，见到你很高兴==>" + str;
        log.info("提供者返回结果：" + result);
        return result;
    }



}

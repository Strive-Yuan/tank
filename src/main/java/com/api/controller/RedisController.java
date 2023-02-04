package com.api.controller;

import org.redisson.api.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {


    @Resource
    private RedissonClient redissonClient;

    public void setString() {
        System.out.println("进入redis测试~");
        var bucket = redissonClient.getBucket("token");
        bucket.set("我是token", 10, TimeUnit.HOURS);

        var anyList = redissonClient.getList("anyList");
        anyList.add("value1");

        var anySet = redissonClient.getSet("anySet");
        anySet.addAsync("value1");

        var anySortedSet = redissonClient.getSortedSet("sset");
        anySortedSet.add("value1");
        anySortedSet.add("value2");
        anySortedSet.readAll().forEach(System.out::println);
        RScoredSortedSet<Object> zset = redissonClient.getScoredSortedSet("zset");
        zset.add(80,"aaa");
        zset.add(90,"bbb");


        var anyMap = redissonClient.getMap("anyMap");
        anyMap.put("key1", "value1");
        anyMap.putAsync("key2", "value2");
        anyMap.fastPut("key3", "value3");

        anyMap.forEach((k, v) -> {
            System.out.println("k:" + k + "   v:" + v);
        });
    }
}

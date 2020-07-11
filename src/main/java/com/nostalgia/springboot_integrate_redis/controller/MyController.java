package com.nostalgia.springboot_integrate_redis.controller;


import com.nostalgia.springboot_integrate_redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
public class MyController {
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody Student student){
        System.out.println(student);
        redisTemplate.opsForValue().set("student",student);
    }

    @GetMapping("/get/{key}")
    @ResponseBody
    public Student get(@PathVariable String key){
        return (Student)redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("delete/{key}")
    public boolean delete(@PathVariable String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    @GetMapping("/list")
    public List<String> list(){
        ListOperations<String,String> list=redisTemplate.opsForList();
        list.leftPush("list","1");
        list.leftPush("list","2");
        list.leftPush("list","3");
        ListOperations<String,String> listOperations=redisTemplate.opsForList();
        return redisTemplate.opsForList().range("list",0,2);
    }

    @GetMapping("/set")
    public Set<String> set(){
        SetOperations<String,String> setOperations=redisTemplate.opsForSet();
        setOperations.add("set","hello");
        setOperations.add("set","java");
        setOperations.add("set","world");
        Set<String> set=redisTemplate.opsForSet().members("set");
        return set;
    }

    @GetMapping("/zSet")
    public Set<String> zSet(){
        ZSetOperations<String,String> setOperations=redisTemplate.opsForZSet();
        setOperations.add("zset","hello",1);
        setOperations.add("zset","java",2);
        setOperations.add("zset","world",3);
        Set<String> set=redisTemplate.opsForZSet().range("zset",0,2);
        return set;
    }

    @GetMapping("/hash")
    public void hash(){
        HashMap<String,String> map1=new HashMap<>();
        HashMap<String,String> map2=new HashMap<>();
        HashMap<String,String> map3=new HashMap<>();
        map1.put("1","henry");
        map1.put("2","何瑞");
        map2.put("3","lzy");
        map3.put("4","yj");
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        hashOperations.putAll("map1",map1);
        System.out.println(hashOperations.get("map1","2"));
    }
}

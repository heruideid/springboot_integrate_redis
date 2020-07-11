package com.nostalgia.springboot_integrate_redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {
    Integer id;
    String name;
    Double score;
    Date Birthday;
}

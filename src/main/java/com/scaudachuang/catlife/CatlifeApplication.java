package com.scaudachuang.catlife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.scaudachuang.catlife.dao")
public class CatlifeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatlifeApplication.class, args);
    }

}

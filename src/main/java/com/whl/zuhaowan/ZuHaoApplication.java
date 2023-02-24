package com.whl.zuhaowan;


import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
@MapperScan("com.whl.zuhaowan.mapper")
//@EnableDiscoveryClient
public class ZuHaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuHaoApplication.class, args);
    }

}

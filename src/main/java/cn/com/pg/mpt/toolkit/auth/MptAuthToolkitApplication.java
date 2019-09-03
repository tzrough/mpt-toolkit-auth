package cn.com.pg.mpt.toolkit.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;


@EntityScan(basePackages = "cn.com.pg.mpt.toolkit.auth.entity")
@MapperScan("cn.com.pg.mpt.toolkit.auth.dao")
@SpringBootApplication
public class MptAuthToolkitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MptAuthToolkitApplication.class, args);
    }

}

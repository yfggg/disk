package com.leadal.netdisk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class NetdiskApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetdiskApplication.class, args);
        log.info("http://127.0.0.1:8080/swagger-ui.html");
    }
}

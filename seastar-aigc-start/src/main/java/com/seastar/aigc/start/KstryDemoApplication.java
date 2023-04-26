package com.seastar.aigc.start;

import cn.kstry.framework.core.annotation.EnableKstry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKstry
@SpringBootApplication
public class KstryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KstryDemoApplication.class, args);
    }
}

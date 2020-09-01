package com.homedo.open.auth.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: eric
 * @Date: 2020/8/25
 * @Description:
 */
@Configuration
public class InitConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

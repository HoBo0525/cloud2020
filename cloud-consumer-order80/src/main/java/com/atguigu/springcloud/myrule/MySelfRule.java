package com.atguigu.springcloud.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hobo
 * @create 2020-12-07 20:27
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return
                new RandomRule();
    }
}

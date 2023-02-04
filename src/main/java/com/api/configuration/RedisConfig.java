package com.api.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class RedisConfig {

    @Resource
    private Environment env;

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        //设置编解码器
        var jsonJacksonCodec = JsonJacksonCodec.INSTANCE;
        var objectMapper = jsonJacksonCodec.getObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        config.setCodec(jsonJacksonCodec);
        config.useSingleServer()
                .setDatabase(0)
                .setConnectionMinimumIdleSize(1)
                .setAddress(env.getProperty("redisson.address"))
                .setPassword(env.getProperty("redisson.password"));
        return Redisson.create(config);
    }

    @Bean("init")
    public boolean init(RedissonClient rc) {
        return true;
    }
}

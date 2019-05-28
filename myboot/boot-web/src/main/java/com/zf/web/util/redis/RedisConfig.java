package com.zf.web.util.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.database}")
    private int db;

    public int getDb() {
        return db;
    }


    public String getHost() {
        return host;
    }


    public int getPort() {
        return port;
    }


    public int getTimeout() {
        return timeout;
    }


    public int getMaxIdle() {
        return maxIdle;
    }


    public int getMinIdle() {
        return minIdle;
    }


    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

}

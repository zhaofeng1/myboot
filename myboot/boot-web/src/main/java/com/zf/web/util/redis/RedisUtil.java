package com.zf.web.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtil {

    @Autowired
    RedisConfig config;

    JedisPool jedisPool;

    @Bean
    private JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(config.getMinIdle());
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(config.getMaxWaitMillis());

        jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(), config.getTimeout(), null);

        return jedisPool;
    }

    public Jedis getResource() {
        Jedis jedis = jedisPool.getResource();
        jedis.select(config.getDb());
        return jedis;
    }

    public void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}

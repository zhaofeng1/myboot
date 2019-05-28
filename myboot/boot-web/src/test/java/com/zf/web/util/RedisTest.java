package com.zf.web.util;

import com.zf.web.BaseTest;
import com.zf.web.util.redis.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @Author zhaofeng
 * @Date2019/5/27 11:55
 * @Version V1.0
 **/
public class RedisTest extends BaseTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test() {
        Jedis jedis = redisUtil.getResource();
        try {
            String result = jedis.get("auto_pb_com.raise.andraise_US");
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Jedis jedis = redisUtil.getResource();
        try {
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pipelined.set("test" + i, i + "");
                pipelined.expire("test" + i, 100);
            }
            pipelined.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.release(jedis);
        }
    }
}

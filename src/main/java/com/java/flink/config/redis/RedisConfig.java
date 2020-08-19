package com.java.flink.config.redis;

import com.java.flink.config.ConfigurationManager;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;


/**
 * redis 配置类
 *
 * @author Live.InPast
 * @date 2019-08-27
 */
public class RedisConfig {

    /**
     * redis配置
     *
     * @return
     */
    public static RedisProperty getFirstRedisProperty() {
        RedisProperty redisProperty = new RedisProperty();
        redisProperty.setUri(ConfigurationManager.getProperty("redis.first.uri"));
        redisProperty.setDb(ConfigurationManager.getInteger("redis.first.db"));
        redisProperty.setAuth(ConfigurationManager.getProperty("redis.first.auth"));
        return redisProperty;
    }

    /**
     * redis配置
     *
     * @return
     */
    public static RedisProperty getSecondRedisProperty() {
        RedisProperty redisProperty = new RedisProperty();
        redisProperty.setUri(ConfigurationManager.getProperty("redis.second.uri"));
        redisProperty.setDb(ConfigurationManager.getInteger("redis.second.db"));
        redisProperty.setAuth(ConfigurationManager.getProperty("redis.second.auth"));
        return redisProperty;
    }

    /**
     * redis配置
     *
     * @return
     */
    public static RedisProperty getThirdRedisProperty() {
        RedisProperty redisProperty = new RedisProperty();
        redisProperty.setUri(ConfigurationManager.getProperty("redis.third.uri"));
        redisProperty.setDb(ConfigurationManager.getInteger("redis.third.db"));
        redisProperty.setAuth(ConfigurationManager.getProperty("redis.third.auth"));
        return redisProperty;
    }

    /**
     * redis配置
     *
     * @return
     */
    public static RedisProperty getFourthRedisProperty() {
        RedisProperty redisProperty = new RedisProperty();
        redisProperty.setUri(ConfigurationManager.getProperty("redis.fourth.uri"));
        redisProperty.setDb(ConfigurationManager.getInteger("redis.fourth.db"));
        redisProperty.setAuth(ConfigurationManager.getProperty("redis.fourth.auth"));
        return redisProperty;
    }

    /**
     * redis配置
     *
     * @return
     */
    public static RedisProperty getFifthRedisProperty() {
        RedisProperty redisProperty = new RedisProperty();
        redisProperty.setUri(ConfigurationManager.getProperty("redis.fifth.uri"));
        redisProperty.setDb(ConfigurationManager.getInteger("redis.fifth.db"));
        redisProperty.setAuth(ConfigurationManager.getProperty("redis.fifth.auth"));
        return redisProperty;
    }

    /**
     * 获取jedis客户端
     *
     * @param redisProperty
     * @return
     */
    public static Jedis getJedisClient(RedisProperty redisProperty) {
        Jedis jedis = new Jedis(redisProperty.getUri());
        if (!StringUtils.isEmpty(redisProperty.getAuth())) {
            jedis.auth(redisProperty.getAuth());
        }
        jedis.select(redisProperty.getDb());
        return jedis;
    }

}

package com.dor.coupons.system.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.dor.coupons.system.entities.SystemVariables;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	  @Bean
	  public JedisConnectionFactory redisConnectionFactory() {
	    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

	    // Defaults
	    redisConnectionFactory.setPassword("JcyUO7nyPqjSAmNix1nilJ8xSRH9J7Me");
	    redisConnectionFactory.setHostName("redis-14544.c55.eu-central-1-1.ec2.cloud.redislabs.com");
	    redisConnectionFactory.setPort(14544);
	    return redisConnectionFactory;
	  }

	  @Bean
	  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory cf) {
	    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
	    redisTemplate.setConnectionFactory(cf);
	    return redisTemplate;
	  }

	  @Bean
	  public CacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(SystemVariables.secTtlCache);
	    cacheManager.setUsePrefix(true);
	    return cacheManager;
	  }
	}


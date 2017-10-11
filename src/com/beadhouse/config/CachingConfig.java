package com.beadhouse.config;

/**
 * Created by beadhouse on 2017/3/13.
 */

 /*@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public org.springframework.cache.CacheManager EhCacheManager(net.sf.ehcache.CacheManager cm, RedisTemplate redisTemplate) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<CacheManager> managers = new ArrayList<>();
        managers.add(new EhCacheCacheManager(cm));
        managers.add(new RedisCacheManager(redisTemplate));
        cacheManager.setCacheManagers(managers);
        return cacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("com/beadhouse/Cache/ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}*/


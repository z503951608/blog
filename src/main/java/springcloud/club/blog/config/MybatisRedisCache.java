package springcloud.club.blog.config;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import springcloud.club.blog.utils.SpringContextHolder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 3600 * 24; // redis过期时间

    private static final int SELECT_DB = 2;
    
    
    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
		boolean firstPut = false;
		if(getRedisTemplate().hasKey(id)){
			firstPut = true;
		}
		getRedisTemplate().opsForHash().put(id, key, value);
        if(firstPut) getRedisTemplate().expire(id, EXPIRE_TIME_IN_MINUTES, TimeUnit.SECONDS);
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        return getRedisTemplate().opsForHash().get(id, key);
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        getRedisTemplate().opsForHash().delete(id, key);
        return null;
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
    	getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
    @Bean
    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) SpringContextHolder.getBean("redisCacheTemplate");
            LettuceConnectionFactory factory =
                    (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            factory.setDatabase(SELECT_DB);//dbindex就是你想切换的db
            redisTemplate.setConnectionFactory(factory);
        }
        return redisTemplate;
    }
}

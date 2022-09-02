package il.co.fibi.comm.mqbridge.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;

@Configuration
public class CacheConfig {
@Value("${cache.cbxml.size}")
private int cbxmlSize;
@Value("${cache.cbxml.ttl}")
private int cbxmlTtl;

@Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache protoCache = buildProtoCache(ticker);
        CaffeineCache cbxmlCache = buildCbxmlCache(ticker);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(protoCache,cbxmlCache));
        return manager;
    }
     
    private CaffeineCache buildProtoCache(Ticker ticker) {
        return new CaffeineCache("Proto", Caffeine.newBuilder()
                    .ticker(ticker)
                    .build());
    }

    private CaffeineCache buildCbxmlCache(Ticker ticker) {
        return new CaffeineCache("Cbxml", Caffeine.newBuilder()
                    .expireAfterWrite(cbxmlTtl,TimeUnit.MINUTES)
                    .maximumSize(cbxmlSize)
                    .ticker(ticker)
                    .build());
    }
     
    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
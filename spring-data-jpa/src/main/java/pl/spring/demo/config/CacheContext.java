package pl.spring.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheContext {
	
	@Bean
	public EhCacheCacheManager cacheManager() {
		EhCacheCacheManager manager = new EhCacheCacheManager();
		manager.setCacheManager(ehcache().getObject());
		return manager;
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehcache() {
		EhCacheManagerFactoryBean ehcache = new EhCacheManagerFactoryBean();
		ehcache.setConfigLocation(new ClassPathResource("/cache/ehcache.xml"));
		ehcache.setShared(true);
		return ehcache;
	}
	
}

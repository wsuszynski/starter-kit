package pl.spring.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/pl/spring/demo/service/BookServiceImplCacheTest-context.xml")
public class BookServiceImplCacheTestContext {
}

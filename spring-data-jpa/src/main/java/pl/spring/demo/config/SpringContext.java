package pl.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import pl.spring.demo.aop.BookDaoAdvisor;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.mapper.AuthorMapper;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.impl.BookServiceImpl;

@Configuration
@PropertySource("classpath:/config/application.properties")
@Import(CacheContext.class)
@EnableAspectJAutoProxy
public class SpringContext {
	
	@Bean
	public BookService bookService() {
		BookServiceImpl bookService = new BookServiceImpl();
		bookService.setBookDao(bookDao());
		bookService.setBookMapper(bookMapper());
		return bookService;
	}
	
	@Bean
	public BookDao bookDao() {
		return new BookDaoImpl();
	}
	
	@Bean
	public Sequence sequence() {
		return new Sequence();
	}
	
	@Bean
	public BookMapper bookMapper() {
		BookMapper bookMapper = new BookMapper();
		bookMapper.setAuthorMapper(authorMapper());
		return bookMapper;
	}
	
	@Bean
	public AuthorMapper authorMapper() {
		return new AuthorMapper();
	}
	
	@Bean
	public BookDaoAdvisor bookDaoAdvisor() {
		BookDaoAdvisor advisor = new BookDaoAdvisor();
		advisor.setSequence(sequence());
		return advisor;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}

package pl.spring.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.config.SpringContext;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringContext.class)
public class MapperTest {
	
	@Autowired
	private BookMapper mapper;
	
	@Test
	public void shouldConvertBookEntityToBookTo() {
		//given
		BookEntity book = new BookEntity (1L, "Tumbo z Przylądka Dobrej Nadziei", "1,Alina,Centkiewicz;2,Czesław,Centkiewicz");
		
		//when
		BookTo result = mapper.map(book);
		
		//then
		assertEquals(1L, result.getId().longValue());
		assertEquals("Tumbo z Przylądka Dobrej Nadziei", result.getTitle());
		assertEquals(1L, result.getAuthors().get(0).getId().longValue());
		assertEquals("Alina", result.getAuthors().get(0).getFirstName());
		assertEquals(2L, result.getAuthors().get(1).getId().longValue());
		assertEquals("Czesław", result.getAuthors().get(1).getFirstName());
	}
		
	@Test
	public void shouldConvertBookToToBookEntity() {
		//given
    	List<AuthorTo> authorTos = new ArrayList<AuthorTo>();
    	authorTos.add(new AuthorTo(1L, "Alina", "Centkiewicz"));
    	authorTos.add(new AuthorTo(2L, "Czesław", "Centkiewicz"));
    	BookTo book = new BookTo(1L, "Tumbo z Przylądka Dobrej Nadziei", authorTos);
    	
    	//when
    	BookEntity result = mapper.map(book);
    	
    	//then
    	assertEquals(1L, result.getId().longValue());
    	assertEquals("Tumbo z Przylądka Dobrej Nadziei", result.getTitle());
		assertEquals("1,Alina,Centkiewicz;2,Czesław,Centkiewicz", result.getAuthors());
	}

}

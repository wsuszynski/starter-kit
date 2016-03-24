package pl.spring.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.config.SpringContext;
import pl.spring.demo.entity.BookEntity;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringContext.class)
public class AbstractDaoCommonCrudTest {
	
	private Set<BookEntity> booksBackup;
	
	@Autowired
	private Dao<BookEntity> bookDao;
	
	@Before
	public void setUp() {
		booksBackup = new HashSet<BookEntity>(bookDao.getEntities());
	}
	
	@Test
	public void shouldCreateBook() {
		//given
		BookEntity book = new BookEntity (null, "Tumbo z Przylądka Dobrej Nadziei", "7,Alina,Centkiewicz;8,Czesław,Centkiewicz");
		
		//when
		bookDao.create(book);
		
		//then
		assertEquals("Tumbo z Przylądka Dobrej Nadziei", bookDao.findOne(7L).getTitle());
	}
	
	@Test
	public void shouldUpdateBook() {
		//given
		BookEntity book = new BookEntity (2L, "Lalka", "9,Bolesław,Prus");
		
		//when
		bookDao.update(book);
		
		//then
		assertEquals("Lalka", bookDao.findOne(2L).getTitle());
	}
	
	@Test
	public void shouldDeleteBook() {
		//given
		
		//when
		bookDao.delete(5L);
		
		//then
		assertNull(bookDao.findOne(5L));
	}
	
	@After
	public void resetBooks() {
		bookDao.getEntities().clear();
		bookDao.getEntities().addAll(booksBackup);
	}
		
}

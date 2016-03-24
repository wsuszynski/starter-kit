package pl.spring.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;
    @Mock
    private BookMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
    	String authors = "1,authorFirstName,authorLastName";
    	List<AuthorTo> authorTos = new ArrayList<AuthorTo>();
    	authorTos.add(new AuthorTo(1L, "authorFirstName", "authorLastName"));
    	
        BookTo book = new BookTo(null, "title", authorTos);
        BookEntity mappedBook = new BookEntity(null, "title", authors);
        BookEntity savedBook = new BookEntity(1L, "title", authors);
        
        Mockito.when(mapper.map(book)).thenReturn(mappedBook);
        Mockito.when(bookDao.create(mappedBook)).thenReturn(savedBook);
        Mockito.when(mapper.map(savedBook)).thenReturn(new BookTo(savedBook.getId(), savedBook.getTitle(), authorTos));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).create(mappedBook);
        assertEquals(1L, result.getId().longValue());
    }
}

package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.config.SpringContext;
import pl.spring.demo.exception.EntityNotNullIdException;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringContext.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "OPIum W rOS";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
        // given
        final String author1 = "aLekSAnd";
        final String author2 = "wILliam sZEkS";
        final String author3 = "NiZiu";
        // when
        List<BookTo> books = bookService.findBooksByAuthor(author1);
        books.addAll(bookService.findBooksByAuthor(author2));
        books.addAll(bookService.findBooksByAuthor(author3));
        // then
        assertEquals(3, books.size());
        assertEquals("Fredro", books.get(0).getAuthors().get(0).getLastName());
        assertEquals("Szekspir", books.get(1).getAuthors().get(0).getLastName());
        assertEquals("Niziurski", books.get(2).getAuthors().get(0).getLastName());
    }

    @Test(expected = EntityNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
    	List<AuthorTo> authors = new ArrayList<AuthorTo>();
    	authors.add(new AuthorTo(7L, "FirstName", "LastName"));
        final BookTo bookToSave = new BookTo(22L, "Title", authors);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
}

package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
	private BookDao bookDao;
    
    @Autowired
    private BookMapper mapper;

    @Override
    @Cacheable("booksCache")
    public List<BookTo> findAllBooks() {
        return mapper.mapList2Tos(bookDao.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return mapper.mapList2Tos(bookDao.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return mapper.mapList2Tos(bookDao.findBooksByAuthor(author));
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return mapper.map(bookDao.create(mapper.map(book)));
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    public void setBookMapper(BookMapper mapper) {
        this.mapper = mapper;
    }
}

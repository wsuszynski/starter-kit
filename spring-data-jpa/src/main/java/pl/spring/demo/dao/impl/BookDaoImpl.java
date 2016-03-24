package pl.spring.demo.dao.impl;

import pl.spring.demo.dao.AbstractDao;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl extends AbstractDao<BookEntity> implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

    public BookDaoImpl() {
        addTestBooks();
    }
    
    @Override
    public Set<BookEntity> getEntities() {
    	return ALL_BOOKS;
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        List<BookEntity> books = new ArrayList<BookEntity>();
    	for (BookEntity book : ALL_BOOKS) {
    		if (book.hasMatchingTitle(title)) {
    			books.add(book);
    		}
        }
    	return books;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	List<BookEntity> books = new ArrayList<BookEntity>();
    	for (BookEntity book : ALL_BOOKS) {
    		if (book.hasMatchingAuthor(author)) {
    			books.add(book);
    		}
        }
    	return books;
    }

    private void addTestBooks() {
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "1,William,Szekspir"));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "2,Hanna,Ożogowska"));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "3,Jan,Parandowski"));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "4,Edmund,Niziurski"));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "5,Zbigniew,Nienacki"));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "6,Aleksander,Fredro"));
    }
    
}

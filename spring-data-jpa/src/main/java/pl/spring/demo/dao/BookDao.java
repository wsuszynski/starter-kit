package pl.spring.demo.dao;

import pl.spring.demo.entity.BookEntity;

import java.util.List;

public interface BookDao extends Dao<BookEntity> {

    List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);
    
}

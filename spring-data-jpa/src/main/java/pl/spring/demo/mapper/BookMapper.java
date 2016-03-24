package pl.spring.demo.mapper;

import pl.spring.demo.mapper.AuthorMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	public BookTo map(BookEntity bookEntity) {
        if (bookEntity != null) {
            return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authorMapper.authors2To(bookEntity.getAuthors()));
        }
        return null;
    }

    public BookEntity map(BookTo bookTo) {
        if (bookTo != null) {
            return new BookEntity(bookTo.getId(), bookTo.getTitle(), authorMapper.authors2String(bookTo.getAuthors()));
        }
        return null;
    }
    
    public List<BookEntity> mapList2Entities(List<BookTo> bookTos) {
    	List<BookEntity> bookEntities = new ArrayList<BookEntity>();
    	for (BookTo bookTo : bookTos) {
    		bookEntities.add(map(bookTo));
    	}
    	return bookEntities;
    }

    public List<BookTo> mapList2Tos(List<BookEntity> bookEntities) {
    	List<BookTo> bookTos = new ArrayList<BookTo>();
    	for (BookEntity bookEntity : bookEntities) {
    		bookTos.add(map(bookEntity));
    	}
    	return bookTos;
    }
    
    public void setAuthorMapper(AuthorMapper mapper) {
    	authorMapper = mapper;
    }
}

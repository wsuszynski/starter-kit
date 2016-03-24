package pl.spring.demo.entity;

import pl.spring.demo.to.IdAware;

public class BookEntity implements IdAware {
    private Long id;
    private String title;
    private String authors;

    public BookEntity() {
    }

    public BookEntity(Long id, String title, String authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
    
    public boolean hasMatchingTitle (String title) {
    	if (title != null) {
			return getTitle().regionMatches(true, 0, title, 0, title.length());
		}
    	return false;
    }
    
    public boolean hasMatchingAuthor (String toFind) {
    	if (authors != null) {
    		String[] authorsSeparate = authors.split(";");
    		for (String author : authorsSeparate) {
    			String[] authorProperties = author.split(",");
    			if (authorProperties.length != 3) {
    				throw new RuntimeException("Invalid value of the Authors field");
    			}
    			if (authorProperties[1].regionMatches(true, 0, toFind, 0, toFind.length())) {
    				return true;
    			}
    			if (authorProperties[2].regionMatches(true, 0, toFind, 0, toFind.length())) {
    				return true;
    			}
    			if((authorProperties[1] + " " + authorProperties[2]).regionMatches(true, 0, toFind, 0, toFind.length())) {
    				return true;
    			}
    		}
		}
    	return false;
    }

}

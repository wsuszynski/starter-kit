package pl.spring.demo.mapper;

import pl.spring.demo.to.AuthorTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public List<AuthorTo> authors2To(String authors) {
    	List<AuthorTo> authorTos = new ArrayList<AuthorTo>();
    	String[] authorsSeparate = authors.split(";");
    	for (String author : authorsSeparate) {
    		String[] authorProperties = author.split(",");
    		if (authorProperties.length != 3) {
				throw new RuntimeException("Invalid value of the Authors field");
			}
    		authorTos.add(new AuthorTo(Long.parseLong(authorProperties[0]), authorProperties[1], authorProperties[2]));
    	}
    	return authorTos;
    }
    
    public String authors2String(List<AuthorTo> authorTos) {
    	StringBuffer authors = new StringBuffer();
    	for (AuthorTo authorTo : authorTos) {
    		authors.append(authorTo.getId().toString() + "," + authorTo.getFirstName() + "," + authorTo.getLastName());
    		if (authorTos.indexOf(authorTo) < authorTos.size() - 1) {
    			authors.append(";");
    		}
    	}
    	return authors.toString();
    }

}

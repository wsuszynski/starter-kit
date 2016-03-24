package pl.spring.demo.entity;

import pl.spring.demo.to.IdAware;

public class AuthorEntity implements IdAware {
	
	private Long id;
	private String firstName;
	private String lastName;
	
	public AuthorEntity() {
	}
	
	public AuthorEntity(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean hasMatchingFullName (String name) {
    	if (firstName != null && lastName != null) {
    		String fullName = new String(firstName + " " + lastName);
    		return fullName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
    
    public boolean hasMatchingFirstName (String name) {
    	if (firstName != null) {
    		return firstName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
    
    public boolean hasMatchingLastName (String name) {
    	if (lastName != null) {
    		return lastName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
    
    public boolean equals(Object o) {
    	if (!(o instanceof AuthorEntity)) {
    		return false;
    	}
    	AuthorEntity author = (AuthorEntity) o;
		return (id.equals(author.getId()) && firstName.equals(author.getFirstName()) && lastName.equals(author.getLastName()));
	}
}

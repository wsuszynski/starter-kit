package pl.spring.demo.dao;

import java.util.List;
import java.util.Set;

import pl.spring.demo.to.IdAware;

public interface Dao<T extends IdAware> {
	
	public Set<T> getEntities();
	
	public T findOne(Long id);
	
	public List<T> findAll();
	
	public T create(T entity);
	
	public T update(T newEntity);
	
	public void delete(Long id);
	
}

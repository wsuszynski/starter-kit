package pl.spring.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.to.IdAware;

public abstract class AbstractDao<T extends IdAware> implements Dao<T> {
	
	public abstract Set<T> getEntities();
	
	public T findOne(Long id) {
		for (T entity : getEntities()) {
			if (entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}
	
	public List<T> findAll() {
		return new ArrayList<>(getEntities());
	}
	
	@NullableId
	public T create(T entity) {
		getEntities().add(entity);
		return entity;
	}
	
	public T update(T newEntity) {
		if (newEntity.getId() == null) {
			throw new IllegalArgumentException("The updated entity should have an ID");
		}
		for (T entity : getEntities()) {
			if (entity.getId().equals(newEntity.getId())) {
				getEntities().remove(entity);
				getEntities().add(newEntity);
				return newEntity;
			}
		}
		return null;
	}
	
	public void delete(Long id) {
		for (T entity : getEntities()) {
			if (entity.getId().equals(id)) {
				getEntities().remove(entity);
				break;
			}
		}
	}
	
}

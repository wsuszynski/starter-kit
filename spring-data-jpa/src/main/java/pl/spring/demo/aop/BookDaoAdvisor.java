package pl.spring.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.exception.EntityNotNullIdException;
import pl.spring.demo.to.IdAware;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.Dao;

import java.lang.reflect.Method;

@Aspect
@Component
public class BookDaoAdvisor {

	@Autowired
	private Sequence sequence;
	
	@Before("@annotation(pl.spring.demo.annotation.NullableId)")
	public void before(JoinPoint jp) throws Throwable {
		Object o = jp.getTarget();
		Object[] objects = jp.getArgs();
		MethodSignature ms = (MethodSignature) jp.getSignature();
		Method method = ms.getMethod();
		if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0]);
            assignId(objects[0], o);
        }
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new EntityNotNullIdException();
        }
    }
    
    private void assignId(Object o, Object dao) {
    	if (o instanceof IdAware) {
    		((IdAware) o).setId(sequence.nextValue(((Dao<?>) dao).findAll()));
        }
    }

    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
    
    public void setSequence(Sequence sequence) {
    	this.sequence = sequence;
    }
}

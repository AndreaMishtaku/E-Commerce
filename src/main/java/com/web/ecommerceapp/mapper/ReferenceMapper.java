package com.web.ecommerceapp.mapper;
import com.web.ecommerceapp.entity.BaseEntity;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@ApplicationScoped
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseEntity> T resolve(Long id, @TargetType Class<T> entityClass) {
        T entity= entityManager.find( entityClass,id);
        if(entity==null){
            throw new ResourceNotFoundException(entityClass.getName(),"id",id);
        }else{
            return entity;
        }
    }

    public Long resolve(BaseEntity entity){
        return entity==null?null:entity.getId();
    }
}

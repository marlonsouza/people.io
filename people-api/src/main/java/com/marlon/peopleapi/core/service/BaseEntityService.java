package com.marlon.peopleapi.core.service;

import com.marlon.peopleapi.core.database.BaseEntity;
import com.marlon.peopleapi.core.database.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseEntityService<T extends BaseRepository, V extends BaseEntity> {

    @Autowired
    protected T repository;

    protected boolean beforeSave(V toSave) throws Exception {
        return true;
    }

    public Optional<V> save(V toSave) throws Exception {
        if(!beforeSave(toSave)){
            return Optional.empty();
        }

        return Optional.ofNullable((V) repository.save(toSave));
    }

    public Optional<V> findById(UUID id){
        return repository.findById(id);
    }

    final public List<V> findAll(){
        return repository.findAll();
    }

    public void deleteById(UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
    }
}

package com.safien.code2015.disasterapp.service;

import com.safien.code2015.disasterapp.entity.CounterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by salman on 2014-11-15.
 */
@Repository
public interface CounterRepository extends MongoRepository<CounterEntity, String> {

    public List<CounterEntity> findByUsername(@Param("username") String username);

}

package com.safien.code2015.disasterapp.service;

import com.safien.code2015.disasterapp.entity.DataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by salman on 2014-11-15.
 */
@Repository
public interface DataEntityRepository extends MongoRepository<DataEntity, String> {

    public List<DataEntity> findByUsername(@Param("username") String username);

}

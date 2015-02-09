package com.safien.code2015.disasterapp.service;

import com.safien.code2015.disasterapp.entity.DisasterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by salman on 2014-11-15.
 */
@Repository
public interface DisasterRepository extends MongoRepository<DisasterEntity, String> {


}

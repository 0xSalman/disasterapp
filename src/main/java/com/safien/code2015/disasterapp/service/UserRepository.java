package com.safien.code2015.disasterapp.service;

import com.safien.code2015.disasterapp.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by salman on 2014-11-15.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    public UserEntity findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

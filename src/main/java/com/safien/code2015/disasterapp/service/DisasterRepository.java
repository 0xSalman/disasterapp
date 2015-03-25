package com.safien.code2015.disasterapp.service;

import com.safien.code2015.disasterapp.entity.DisasterEntity;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by salman on 2014-11-15.
 */
public interface DisasterRepository extends MongoRepository<DisasterEntity, String> {

//	GeoResult<DisasterEntity> findByPositionNear(Point point, Distance distance);
	List<DisasterEntity> findByLocationNear(Point point, Distance distance);

}

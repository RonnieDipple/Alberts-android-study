package com.stepasha.buildinglocator.repos;

import com.stepasha.buildinglocator.models.Map;
import org.springframework.data.repository.CrudRepository;

//TODO 35 make a repo for art

public interface MapRepository extends CrudRepository<Map, Long> {

    Map findByMapid(long mapid);

}

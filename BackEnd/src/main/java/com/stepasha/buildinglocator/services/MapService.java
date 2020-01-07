package com.stepasha.buildinglocator.services;


import com.stepasha.buildinglocator.models.Map;

import java.util.List;

//TODO 36 art service

public interface MapService {

    Map getMapById(long mapid);

    List<Map> findAll();

    Map save(Map map);

    Map update(Map map, long mapid);

    void delete(long mapid);


}

package com.stepasha.buildinglocator.services;

import com.stepasha.buildinglocator.models.Map;
import com.stepasha.buildinglocator.repos.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//TODO 37 art service impl

@Transactional
@Service(value = "artService")
public class MapServiceImpl implements MapService {

    @Autowired
    private MapService mapService;
    @Autowired
    private MapRepository mapRepository;

    @Override
    public Map getMapById(long id) {
        return mapRepository.findByMapid(id);
    }

    @Override
    public List<Map> findAll() {
        List<Map> list = new ArrayList<>();
        mapRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Map save(Map art) {

        Map newMap = new Map();
        newMap.setPosteddate(art.getPosteddate());
        newMap.setImageurl(art.getImageurl());
        newMap.setAddress(art.getAddress());
        newMap.setDescription(art.getDescription());
        newMap.setAdditionalInfo(art.getAdditionalInfo());


        return mapRepository.save(newMap);
    }
    @Transactional
    @Override
    public Map update(Map art, long id) {
        Map currentMap = getMapById(id);
        if (art.getPosteddate() != null) {
            currentMap.setPosteddate(art.getPosteddate());
        }
        if (art.getImageurl() != null) {
            currentMap.setImageurl(art.getImageurl());
        }
        if (art.getAddress() != null) {
            currentMap.setAddress(art.getAddress());
        }
        if (art.getDescription() != null) {
            currentMap.setDescription(art.getDescription());
        }
        if (art.getAdditionalInfo() != null) {
            currentMap.setAdditionalInfo(art.getAdditionalInfo());
        }


        return mapRepository.save(currentMap);
    }

    @Override
    public void delete(long id) {
        if (getMapById(id) != null){
            mapRepository.deleteById(id);
        }

    }
}

package com.stepasha.buildinglocator.repos;

import com.stepasha.buildinglocator.models.Useremail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//TODO 11b userEmail repo
public interface UseremailRepository extends CrudRepository<Useremail, Long>
{
    List<Useremail> findAllByUser_Username(String name);
}

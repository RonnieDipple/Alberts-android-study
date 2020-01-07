package com.stepasha.buildinglocator.services;



import com.stepasha.buildinglocator.exceptions.ResourceNotFoundException;
import com.stepasha.buildinglocator.logging.Loggable;
import com.stepasha.buildinglocator.models.Useremail;
import com.stepasha.buildinglocator.repos.UseremailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "useremailService")
public class UseremailServiceImpl implements UseremailService
{
    @Autowired
    private UseremailRepository useremailrepos;

    @Override
    public List<Useremail> findAll()
    {
        List<Useremail> list = new ArrayList<>();
        useremailrepos.findAll()
                      .iterator()
                      .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Useremail findUseremailById(long id)
    {
        return useremailrepos.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException("Useremail with id " + id + " Not Found!"));
    }

    @Override
    public List<Useremail> findByUserName(String username)
    {
        return useremailrepos.findAllByUser_Username(username.toLowerCase());
    }


    @Override
    public void delete(long id)
    {
        if (useremailrepos.findById(id)
                          .isPresent())
        {
            useremailrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + id + " Not Found!");
        }
    }

    @Override
    public Useremail update(long useremailid,
                            String emailaddress)
    {
        if (useremailrepos.findById(useremailid)
                          .isPresent())
        {
            Useremail useremail = findUseremailById(useremailid);
            useremail.setUseremail(emailaddress.toLowerCase());
            return useremailrepos.save(useremail);
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + useremailid + " Not Found!");
        }
    }
}

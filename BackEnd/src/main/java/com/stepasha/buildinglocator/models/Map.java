package com.stepasha.buildinglocator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stepasha.buildinglocator.logging.Loggable;
import javax.persistence.*;
import java.util.Date;


//TODO 31 Art Model Class

@Loggable
@Entity
@Table(name = "maps")
public class Map extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mapid;
    @Column(nullable = true)
    private Date posteddate;
    @Column(nullable = true)
    private String imageurl;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private String additionalInfo;

 //   @ManyToOne
 //   @JoinColumn(name = "categoryid")
 //   @JsonIgnoreProperties("arts")
 //   private Category category;



   @ManyToOne
   @JoinColumn(name = "users",
           nullable = true)
   @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   private User user;


    public Map(){}

    public Map(Date posteddate, String imageurl, String address, String description, String additionalInfo, User user) {
        this.posteddate = posteddate;
        this.imageurl = imageurl;
        this.address = address;
        this.description = description;
        this.additionalInfo = additionalInfo;
       this.user = user;

    }

    public long getMapid() {
        return mapid;
    }

    public void setMapid(long mapid) {
        this.mapid = mapid;
    }

    public Date getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//  public Category getCategory() {
  //      return category;
  //  }
//
  //  public void setCategory(Category category) {
     //   this.category = category;
   // }

}

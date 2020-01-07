package com.stepasha.buildinglocator.models;


//TODO 30 model for categories

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "category")
//public class Category extends Auditable{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long categoryid;
//    @Column(nullable = false, unique = true)
//    private String categoryname;
//
//    //TODO 30b one to many Art (pictures) relation
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("category")
//    private List<Art> arts = new ArrayList<>();
//
//    public Category(){}
//
//    public Category(String categoryname) {
//        this.categoryname = categoryname;
//    }
//
//    public long getCategoryid() {
//        return categoryid;
//    }
//
//    public void setCategoryid(long categoryid) {
//        this.categoryid = categoryid;
//    }
//
//    public String getCategoryname() {
//        return categoryname;
//    }
//
//    public void setCategoryname(String categoryname) {
//        this.categoryname = categoryname;
//    }
//
//    public List<Art> getArts() {
//        return arts;
//    }
//
//    public void setArts(List<Art> arts) {
//        this.arts = arts;
//    }
//}

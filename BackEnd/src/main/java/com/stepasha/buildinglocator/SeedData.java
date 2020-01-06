package com.stepasha.buildinglocator;


//import com.stepasha.buildinglocator.models.Role;
//import com.stepasha.buildinglocator.models.User;
//import com.stepasha.buildinglocator.models.UserRoles;
//import com.stepasha.buildinglocator.models.Useremail;
//import com.stepasha.buildinglocator.services.RoleService;
//import com.stepasha.buildinglocator.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UserService userService;
//
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        r1 = roleService.save(r1);
//        r2 = roleService.save(r2);
//        r3 = roleService.save(r3);
//
//        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//        admins.add(new UserRoles(new User(),
//                                 r1));
//        admins.add(new UserRoles(new User(),
//                                 r2));
//        admins.add(new UserRoles(new User(),
//                                 r3));
//        User u1 = new User("https://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png",
//                           "admin1",
//                           "password",
//                           "mrayakubov@hotmail.com",
//                           "Albert",
//                           "Yakubov",
//                           32,
//                           "Denver Co",
//                           admins);
//        u1.getUseremails()
//          .add(new Useremail(u1,
//                             "admin@email.local"));
//        u1.getUseremails()
//          .add(new Useremail(u1,
//                             "admin@mymail.local"));
//
//        userService.save(u1);
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(),
//                                r3));
//        datas.add(new UserRoles(new User(),
//                                r2));
//        User u2 = new User("https://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png",
//                "user1",
//                "password",
//                "gmai45l@gmail.com",
//                "Albert",
//                "Yakubov",
//                10,
//                "somewhere in the world",
//                datas);
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "cinnamon@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "hops@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "bunny@email.local"));
//        userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u3 = new User("https://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png",
//                "admin1r231",
//                "password",
//                "gma2il@gmail.com",
//                "Albert",
//                "Yakubov",
//                10,
//                "somewhere in the world",
//                users);
//        u3.getUseremails()
//          .add(new Useremail(u3,
//                             "barnbarn@email.local"));
//        userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u4 = new User("https://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png",
//                "user1231",
//                "password",
//                "gm2ail@gmail.com",
//                "Albert",
//                "Yakubov",
//                10,
//                "somewhere in the world",
//                users);
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u5 = new User("https://upload.wikimedia.org/wikipedia/en/d/dc/MichaelScott.png",
//                "adminuser1",
//                "password",
//                "gmail@gmail.com",
//                "Albert",
//                "Yakubov",
//                10,
//                "somewhere in the world",
//                users);
//        userService.save(u5);
//
//
//
//
//
//        // using JavaFaker create a bunch of regular users
//        // https://www.baeldung.com/java-faker
//        // https://www.baeldung.com/regular-expressions-java
//
//     //   FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//    //                                                                new RandomService());
//    //    Faker nameFaker = new Faker(new Locale("en-US"));
////
//    //    for (int i = 0; i < 100; i++)
//    //    {
//    //        new User();
//    //        User fakeUser;
////
//    //        users = new ArrayList<>();
//    //        users.add(new UserRoles(new User(),
//    //                                r2));
//        //    fakeUser = new User(nameFaker.name()
//        //                                 .username(),
//        //                        "password",
//        //                        nameFaker.internet()
//        //                                 .emailAddress(),
//        //                        nameFaker.bothify("ada"),
//        //
//        //                        users);
//        //    fakeUser.getUseremails()
//        //            .add(new Useremail(fakeUser,
//        //                               fakeValuesService.bothify("????##@gmail.com")));
//        //    userService.save(fakeUser);
//       // }
//    }
//}
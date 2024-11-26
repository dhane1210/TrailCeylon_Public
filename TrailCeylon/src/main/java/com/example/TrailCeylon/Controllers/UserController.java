package com.example.TrailCeylon.Controllers;

import com.example.TrailCeylon.Model.User;
import com.example.TrailCeylon.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userRepo.save(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Integer id){
        return userRepo.findById(id).orElse(null);
    }

    @GetMapping("/fetchUser")
    public List<User> fetchUser(){
        return userRepo.findAll();
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        User data = userRepo.findById(user.getId()).orElse(null);
        System.out.println(data);

        if(data!=null){
            data.setName(user.getName());
            data.setLocation(user.getLocation());
            userRepo.save(data);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public List<User> deleteUser(@PathVariable Integer id){
         userRepo.deleteById(id);
         return userRepo.findAll();
    }


}

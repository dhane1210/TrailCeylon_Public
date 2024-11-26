package com.example.TrailCeylon.Controllers;

import com.example.TrailCeylon.Model.User;
import com.example.TrailCeylon.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    // Add a new user
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userRepo.existsById(user.getId())) {
            return ResponseEntity.badRequest().body("User already exists with ID: " + user.getId());
        }
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userRepo.save(user);
        return ResponseEntity.ok("User added successfully!");
    }

    // Get a user by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> fetchAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

    // Update a user
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setLocation(updatedUser.getLocation());
            existingUser.setUpdatedAt(new Date());
            userRepo.save(existingUser);
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

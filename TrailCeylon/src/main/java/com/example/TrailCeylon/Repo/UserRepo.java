package com.example.TrailCeylon.Repo;

import com.example.TrailCeylon.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, Integer> {
}

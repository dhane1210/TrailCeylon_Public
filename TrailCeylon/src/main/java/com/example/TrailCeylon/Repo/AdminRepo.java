package com.example.TrailCeylon.Repo;

import com.example.TrailCeylon.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {
    Admin findByEmail(String email); // For login functionality by email.
}

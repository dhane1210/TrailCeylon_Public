package com.example.TrailCeylon.Repo;

import com.example.TrailCeylon.Model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends MongoRepository<Track, String> {
    // Additional custom query methods (if required) can be added here.
}

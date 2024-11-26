package com.example.TrailCeylon.Repo;

import com.example.TrailCeylon.Model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepo extends MongoRepository<Place, String> {
    List<Place> findByTrackId(String trackId); // Fetch all places for a specific track.
}

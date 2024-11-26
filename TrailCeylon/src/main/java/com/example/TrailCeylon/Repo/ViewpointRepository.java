package com.example.TrailCeylon.Repo;

import com.example.TrailCeylon.Model.Viewpoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewpointRepository extends MongoRepository<Viewpoint, String> {
    List<Viewpoint> findByTrackId(String trackId);
    List<Viewpoint> findByIsActiveTrue();
    List<Viewpoint> findByTrackIdAndIsActiveTrue(String trackId);
    Optional<Viewpoint> findByIdAndIsActiveTrue(String id);

    @Query("{'location': {$near: {$geometry: {type: 'Point', coordinates: [?0, ?1]}, $maxDistance: ?2}}}")
    List<Viewpoint> findNearbyViewpoints(double longitude, double latitude, double maxDistance);
}
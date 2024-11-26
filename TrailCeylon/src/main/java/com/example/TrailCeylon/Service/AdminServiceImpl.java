package com.example.TrailCeylon.Service;

import com.example.TrailCeylon.Model.Place;
import com.example.TrailCeylon.Model.Track;
import com.example.TrailCeylon.Model.User;
import com.example.TrailCeylon.Repo.PlaceRepo;
import com.example.TrailCeylon.Repo.TrackRepo;
import com.example.TrailCeylon.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrackRepo trackRepo;

    @Autowired
    private PlaceRepo placeRepo;

    @Override
    public boolean removeUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Track addTrack(Track track) {
        return trackRepo.save(track);
    }

    @Override
    public Track updateTrack(String id, Track updatedTrack) {
        Optional<Track> trackOpt = trackRepo.findById(id);
        if (trackOpt.isPresent()) {
            Track existingTrack = trackOpt.get();
            existingTrack.setName(updatedTrack.getName());
            existingTrack.setDescription(updatedTrack.getDescription());
            existingTrack.setStartPoint(updatedTrack.getStartPoint());
            existingTrack.setEndPoint(updatedTrack.getEndPoint());
            return trackRepo.save(existingTrack);
        }
        throw new RuntimeException("Track not found with ID: " + id);
    }

    @Override
    public boolean deleteTrack(String id) {
        if (trackRepo.existsById(id)) {
            trackRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Place addPlace(Place place) {
        return placeRepo.save(place);
    }

    @Override
    public Place updatePlace(String id, Place updatedPlace) {
        Optional<Place> placeOpt = placeRepo.findById(id);
        if (placeOpt.isPresent()) {
            Place existingPlace = placeOpt.get();
            existingPlace.setName(updatedPlace.getName());
            existingPlace.setDescription(updatedPlace.getDescription());
            existingPlace.setCoordinates(updatedPlace.getCoordinates());
            return placeRepo.save(existingPlace);
        }
        throw new RuntimeException("Place not found with ID: " + id);
    }

    @Override
    public boolean deletePlace(String id) {
        if (placeRepo.existsById(id)) {
            placeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }
}

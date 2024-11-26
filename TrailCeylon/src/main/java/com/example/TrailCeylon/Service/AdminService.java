package com.example.TrailCeylon.Service;

import com.example.TrailCeylon.Model.Place;
import com.example.TrailCeylon.Model.User;
import com.example.TrailCeylon.Model.Track;
import java.util.List;

public interface AdminService {
    boolean removeUser(String id);
    Track addTrack(Track track);
    Track updateTrack(String id, Track updatedTrack);
    boolean deleteTrack(String id);
    Place addPlace(Place place);
    Place updatePlace(String id, Place updatedPlace);
    boolean deletePlace(String id);
    List<User> fetchAllUsers();
}

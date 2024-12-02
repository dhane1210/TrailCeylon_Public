package com.example.TrailCeylon.Controllers;

import com.example.TrailCeylon.Model.Place;
import com.example.TrailCeylon.Model.Track;
import com.example.TrailCeylon.Model.User;
import com.example.TrailCeylon.Repo.TrackRepo;
import com.example.TrailCeylon.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrackRepo trackRepo;

    // Manage Users
    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<String> removeUser(@PathVariable String id) {
        if (adminService.removeUser(id)) {
            return ResponseEntity.ok("User removed successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> fetchAllUsers() {
        return ResponseEntity.ok(adminService.fetchAllUsers());
    }

    // Manage Tracks
    @PostMapping("/addTrack")
    public ResponseEntity<Track> addTrack(@RequestBody Track track) {
        return ResponseEntity.ok(adminService.addTrack(track));
    }

    @GetMapping("/getTrack/{id}")
    public ResponseEntity<Track> getTrack(@PathVariable String id) {
        Optional<Track> trackOpt = trackRepo.findById(id);
        return trackOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/updateTrack/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable String id, @RequestBody Track updatedTrack) {
        try {
            return ResponseEntity.ok(adminService.updateTrack(id, updatedTrack));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTrack/{id}")
    public ResponseEntity<String> deleteTrack(@PathVariable String id) {
        if (adminService.deleteTrack(id)) {
            return ResponseEntity.ok("Track deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    // Manage Places
    @PostMapping("/addPlace")
    public ResponseEntity<Place> addPlace(@RequestBody Place place) {
        return ResponseEntity.ok(adminService.addPlace(place));
    }

    @PutMapping("/updatePlace/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable String id, @RequestBody Place updatedPlace) {
        try {
            return ResponseEntity.ok(adminService.updatePlace(id, updatedPlace));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePlace/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable String id) {
        if (adminService.deletePlace(id)) {
            return ResponseEntity.ok("Place deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}

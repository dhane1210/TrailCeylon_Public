package com.example.TrailCeylon.Controllers;

import com.example.TrailCeylon.Model.Viewpoint;
import com.example.TrailCeylon.Service.ViewpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viewpoints")
@RequiredArgsConstructor
public class ViewpointController {
    private final ViewpointService viewpointService;

    @PostMapping
    public ResponseEntity<Viewpoint> createViewpoint(@RequestBody Viewpoint viewpoint) {
        return new ResponseEntity<>(viewpointService.addViewpoint(viewpoint), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Viewpoint>> getAllViewpoints() {
        return ResponseEntity.ok(viewpointService.getAllViewpoints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viewpoint> getViewpoint(@PathVariable String id) {
        return ResponseEntity.ok(viewpointService.getViewpointById(id));
    }

    @GetMapping("/track/{trackId}")
    public ResponseEntity<List<Viewpoint>> getViewpointsByTrack(@PathVariable String trackId) {
        return ResponseEntity.ok(viewpointService.getViewpointsByTrackId(trackId));
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Viewpoint>> getNearbyViewpoints(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "1000") double radius) {
        return ResponseEntity.ok(viewpointService.getNearbyViewpoints(latitude, longitude, radius));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viewpoint> updateViewpoint(
            @PathVariable String id,
            @RequestBody Viewpoint viewpoint) {
        return ResponseEntity.ok(viewpointService.updateViewpoint(id, viewpoint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViewpoint(@PathVariable String id) {
        viewpointService.deleteViewpoint(id);
        return ResponseEntity.noContent().build();
    }
}

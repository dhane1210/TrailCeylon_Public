package com.example.TrailCeylon.Service;

import com.example.TrailCeylon.Model.Viewpoint;
import com.example.TrailCeylon.Repo.ViewpointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.TrailCeylon.Exceptions.ResourceNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewpointService {
    private final ViewpointRepository viewpointRepository;

    // Create
    public Viewpoint addViewpoint(Viewpoint viewpoint) {
        validateViewpoint(viewpoint);
        viewpoint.setCreatedAt(new Date());
        viewpoint.setUpdatedAt(new Date());
        viewpoint.setActive(true);
        return viewpointRepository.save(viewpoint);
    }

    // Read
    public List<Viewpoint> getAllViewpoints() {
        return viewpointRepository.findByIsActiveTrue();
    }

    public Viewpoint getViewpointById(String id) {
        return viewpointRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viewpoint not found with id: " + id));
    }

    public List<Viewpoint> getViewpointsByTrackId(String trackId) {
        return viewpointRepository.findByTrackIdAndIsActiveTrue(trackId);
    }

    // Update
    public Viewpoint updateViewpoint(String id, Viewpoint updatedViewpoint) {
        Viewpoint existingViewpoint = getViewpointById(id);

        existingViewpoint.setName(updatedViewpoint.getName());
        existingViewpoint.setDescription(updatedViewpoint.getDescription());
        existingViewpoint.setLocation(updatedViewpoint.getLocation());
        existingViewpoint.setImageUrls(updatedViewpoint.getImageUrls());
        existingViewpoint.setBestVisitingTime(updatedViewpoint.getBestVisitingTime());
        existingViewpoint.setFacilities(updatedViewpoint.getFacilities());
        existingViewpoint.setWarnings(updatedViewpoint.getWarnings());
        existingViewpoint.setType(updatedViewpoint.getType());
        existingViewpoint.setUpdatedAt(new Date());

        return viewpointRepository.save(existingViewpoint);
    }

    // Delete (Soft Delete)
    public void deleteViewpoint(String id) {
        Viewpoint viewpoint = getViewpointById(id);
        viewpoint.setActive(false);
        viewpoint.setUpdatedAt(new Date());
        viewpointRepository.save(viewpoint);
    }

    // Nearby Viewpoints
    public List<Viewpoint> getNearbyViewpoints(double latitude, double longitude, double radiusInMeters) {
        return viewpointRepository.findNearbyViewpoints(longitude, latitude, radiusInMeters);
    }

    private void validateViewpoint(Viewpoint viewpoint) {
        if (viewpoint.getName() == null || viewpoint.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Viewpoint name cannot be empty");
        }
        if (viewpoint.getLocation() == null) {
            throw new IllegalArgumentException("Viewpoint location is required");
        }
        if (viewpoint.getTrackId() == null || viewpoint.getTrackId().trim().isEmpty()) {
            throw new IllegalArgumentException("Track ID is required");
        }
    }
}

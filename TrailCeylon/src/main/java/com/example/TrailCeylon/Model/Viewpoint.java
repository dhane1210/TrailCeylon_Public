package com.example.TrailCeylon.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "viewpoints")
@Data
public class Viewpoint {
    @Id
    private String id;
    private String name;
    private String description;
    private Location location;
    private String trackId;
    private List<String> imageUrls;
    private String bestVisitingTime;
    private List<String> facilities;
    private List<String> warnings;
    private ViewpointType type;
    private boolean isActive = true;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
}

@Data
class Location {
    private double latitude;
    private double longitude;

    // Constructor for easy creation
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // No-args constructor
    public Location() {}
}

enum ViewpointType {
    SCENIC,
    HISTORICAL,
    CULTURAL,
    NATURAL,
    ARCHITECTURAL
}
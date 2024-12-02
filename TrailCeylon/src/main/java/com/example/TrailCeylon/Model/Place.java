package com.example.TrailCeylon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "places")
public class Place {
    @Id
    private String id;
    private String name;
    private String description;
    private String coordinates;
    private double latitude;
    private double longitude;
    @DBRef  // Reference to a track
    private Track track; // Place belongs to a specific track
}

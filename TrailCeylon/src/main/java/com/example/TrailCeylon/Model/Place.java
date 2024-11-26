package com.example.TrailCeylon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private String trackId; // Reference to the Track the place belongs to.
}

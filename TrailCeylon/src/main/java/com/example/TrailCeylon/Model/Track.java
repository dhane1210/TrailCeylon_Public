package com.example.TrailCeylon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tracks")
public class Track {
    @Id
    private String id;
    private String name;
    private String description;
    private String startPoint;
    private String endPoint;
    private List<Place> places; // Each track contains a list of places.
}

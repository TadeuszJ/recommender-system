package com.engineerproject.recommendationsystem.app.neighbors.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "neighbors")
public class NeighborsDTO {

    @Id
    private String activeUserId;

    private List<CorrelationDTO> correlations = new ArrayList<>();
}

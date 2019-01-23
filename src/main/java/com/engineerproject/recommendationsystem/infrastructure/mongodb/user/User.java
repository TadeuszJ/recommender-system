package com.engineerproject.recommendationsystem.infrastructure.mongodb.user;

import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.CorrelationDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String activeUserId;

    private List<CorrelationDTO> correlations = new ArrayList<>();

    private List<PredictionDTO> predictions = new ArrayList<>();
}

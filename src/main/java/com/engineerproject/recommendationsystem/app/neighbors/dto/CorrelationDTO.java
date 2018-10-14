package com.engineerproject.recommendationsystem.app.neighbors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class CorrelationDTO {
    @Indexed(unique = true)
    private String userId;
    private Double correlation;
}

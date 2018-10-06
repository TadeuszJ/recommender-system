package com.engineerproject.recommendationsystem.app.neighbors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCorrelationDTO {
    private String userId;
    private Double correlation;
}

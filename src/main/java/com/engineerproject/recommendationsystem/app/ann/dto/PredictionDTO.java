package com.engineerproject.recommendationsystem.app.ann.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PredictionDTO {
    private String businessId;
    private Double rate;
}

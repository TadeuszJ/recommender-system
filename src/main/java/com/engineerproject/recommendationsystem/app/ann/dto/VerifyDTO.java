package com.engineerproject.recommendationsystem.app.ann.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyDTO {
    private Double maxDifference;
    private Double minDifference;
    private Double avgDifference;
}

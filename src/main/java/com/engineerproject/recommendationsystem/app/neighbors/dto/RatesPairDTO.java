package com.engineerproject.recommendationsystem.app.neighbors.dto;

import lombok.Data;

@Data
public class RatesPairDTO {
    private final Double activeUserRate;
    private final Double selectedUserRate;
}

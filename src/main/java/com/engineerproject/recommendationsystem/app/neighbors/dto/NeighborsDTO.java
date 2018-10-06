package com.engineerproject.recommendationsystem.app.neighbors.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NeighborsDTO {

    public NeighborsDTO(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    private String activeUserId;
    private List<UserCorrelationDTO> correlations = new ArrayList<>();
}

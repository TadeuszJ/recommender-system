package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AnnService {

    private final AnnRepository annRepository;

    public void learn(String activeUser) {

    }

    public List<PredictionDTO> getPrediction(String activeUser) {
        return new ArrayList<>();
    }
}

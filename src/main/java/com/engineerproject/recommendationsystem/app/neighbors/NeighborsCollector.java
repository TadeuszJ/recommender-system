package com.engineerproject.recommendationsystem.app.neighbors;


import com.engineerproject.recommendationsystem.app.neighbors.dto.CorrelationDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class NeighborsCollector {

    private final NeighborsRepository neighborsRepository;

    public NeighborsDTO updateNeighbors(String activeUserId) {
        NeighborsDTO result = new NeighborsDTO();
        result.setActiveUserId(activeUserId);

        List<String> potentialNeighbors = neighborsRepository.getPotentialNeighbors(activeUserId);
        log.info("select potential neighbors: {}", potentialNeighbors.size());

        float count = 0;
        for (String selectedUserId : potentialNeighbors) {
            if (!selectedUserId.equals(activeUserId)) {

                log.info("Started calculating correlation for user id {}", selectedUserId);
                Double correlation = getCorrelationForUser(activeUserId, selectedUserId);
                log.info("Calculated correlation for user {} is {}", selectedUserId, correlation);

                if (correlation > 0) {
                    CorrelationDTO userCorrelation = new CorrelationDTO(selectedUserId, correlation);
                    result.getCorrelations().add(userCorrelation);
                }
            }
            count++;
            Float progress = (count / potentialNeighbors.size()) * 100;
            log.info("Progress: {}%", progress.intValue());
        }
        neighborsRepository.saveNeighbor(result);
        return result;
    }

    private Double getCorrelationForUser(String activeUserId, String selectedUserId) {
        List<Double> activeUserRates = new ArrayList<>();
        List<Double> selectedUserRates = new ArrayList<>();

        List<RatesPairDTO> pairsStars = neighborsRepository.getCommonsStars(activeUserId, selectedUserId);

        if (pairsStars.size() <= 3) {
            return 0d;
        }

        for (RatesPairDTO pairStars : pairsStars) {
            selectedUserRates.add(pairStars.getActiveUserRate());
            activeUserRates.add(pairStars.getSelectedUserRate());
        }
        return getCorrelation(activeUserRates, selectedUserRates);
    }

    private Double getCorrelation(List<Double> listX, List<Double> listY) {
        double[] arrayX = listX.stream().mapToDouble(value -> value).toArray();
        double[] arrayY = listY.stream().mapToDouble(value -> value).toArray();

        return new PearsonsCorrelation().correlation(arrayX, arrayY);
    }
}

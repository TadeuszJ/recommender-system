package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.app.ann.AnnRepository;
import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.CorrelationDTO;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.User;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.UserRepositoryDB;
import com.engineerproject.recommendationsystem.infrastructure.properties.AnnProperties;
import com.engineerproject.recommendationsystem.infrastructure.rest.AnnRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnnRepositoryImpl implements AnnRepository {

    private final AnnRestClient annRestClient;

    private final UserRepositoryDB userRepositoryDB;

    private final AnnProperties annProperties;

    @Override
    public List<DataSetRowDTO> getDataSetForLearn(List<String> userIds) {
        return annRestClient.getDataSetForLearn(userIds);
    }

    @Override
    public List<String> getBestNeighbors(String userId, Integer quantity) {
        User user = userRepositoryDB.getByActiveUserId(userId);
        return user.getCorrelations().stream()
                .sorted(Comparator.comparing(CorrelationDTO::getCorrelation))
                .map(CorrelationDTO::getUserId)
                .limit(quantity)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBestNeighbors(String userId, Double threshold) {
        User user = userRepositoryDB.getByActiveUserId(userId);
        return user.getCorrelations().stream()
                .filter(correlationDTO -> correlationDTO.getCorrelation() >= threshold)
                .map(CorrelationDTO::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public List<DataSetRowDTO> getDataSetForPrediction(String userId, List<String> neighborIds) {
        return annRestClient.getDataSetForPrediction(userId, neighborIds);
    }

    @Override
    public List<DataSetRowDTO> getDataSet(Pageable pageable) {
        return annRestClient.getDataSet(pageable);
    }

    @Override
    public String getPathToSave() {
        return annProperties.getPathToSave();
    }
}

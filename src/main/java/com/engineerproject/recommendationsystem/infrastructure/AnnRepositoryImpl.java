package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.app.ann.AnnRepository;
import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.app.ann.dto.WeightsDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.CorrelationDTO;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.User;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.UserRepositoryDB;
import com.engineerproject.recommendationsystem.infrastructure.properties.AnnProperties;
import com.engineerproject.recommendationsystem.infrastructure.rest.AnnRestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class AnnRepositoryImpl implements AnnRepository {

    private final AnnRestClient annRestClient;

    private final UserRepositoryDB userRepositoryDB;

    private final AnnProperties annProperties;

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
    public List<DataSetRowDTO> getDataSetForVerification(String userId) {
        return annRestClient.getDataSetForVerification(userId);
    }

    @Override
    public List<DataSetRowDTO> getDataSetForLearn(Pageable pageable) {
        return annRestClient.getDataSetForLearn(pageable);
    }

    @Override
    public String getPathToSave() {
        return annProperties.getPathToSave();
    }

    @Override
    public WeightsDTO getWeights() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<WeightsDTO> typeReference = new TypeReference<WeightsDTO>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/model.json");
        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            log.error("Unable to get ann weights: " + e.getMessage());
        }
        return new WeightsDTO();
    }
}

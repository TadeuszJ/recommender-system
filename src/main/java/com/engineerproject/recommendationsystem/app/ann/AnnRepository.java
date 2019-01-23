package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.app.ann.dto.WeightsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnnRepository {

    List<String> getBestNeighbors(String userId, Double threshold);

    List<DataSetRowDTO> getDataSetForLearn(Pageable pageable);

    List<DataSetRowDTO> getDataSetForPrediction(String userId, List<String> neighborIds);

    List<DataSetRowDTO> getDataSetForVerification(String userId);

    String getPathToSave();

    WeightsDTO getWeights();
}
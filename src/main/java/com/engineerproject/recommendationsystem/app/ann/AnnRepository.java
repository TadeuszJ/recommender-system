package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnnRepository {

    List<DataSetRowDTO> getDataSetForLearn(List<String> userIds);

    List<String> getBestNeighbors(String userId, Integer quantity);

    List<String> getBestNeighbors(String userId, Double threshold);

    List<DataSetRowDTO> getDataSetForPrediction(String userId, List<String> neighborIds);

    List<DataSetRowDTO> getDataSet(Pageable pageable);

    String getPathToSave();
}
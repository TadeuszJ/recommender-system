package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.RowDataSetDTO;

import java.util.List;

public interface AnnRepository {
    List<RowDataSetDTO> getDataSet(List<String> userIds, String businessId);

    Integer getRate(String userId, String businessId);
}

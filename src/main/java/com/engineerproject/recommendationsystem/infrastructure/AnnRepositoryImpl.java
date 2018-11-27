package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.app.ann.AnnRepository;
import com.engineerproject.recommendationsystem.app.ann.dto.RowDataSetDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.ReviewRestClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AnnRepositoryImpl implements AnnRepository {

    private final ReviewRestClient reviewRestClient;

    @Override
    public List<RowDataSetDTO> getDataSet(List<String> userIds, String businessId) {
        return reviewRestClient.getDataSet(userIds, businessId);
    }

    @Override
    public Integer getRate(String userId, String businessId) {
        return reviewRestClient.getRate(userId, businessId);
    }
}

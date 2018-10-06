package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsRepository;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.rest.ReviewRestClient;
import com.engineerproject.recommendationsystem.infrastructure.rest.UserRestClient;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NeighborsRepositoryImpl implements NeighborsRepository {
    @Override
    public List<String> getPotentialNeighbors(String userId) {
        return new UserRestClient().getPotentialNeighbors(userId);
    }

    @Override
    public List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId) {
        return new ReviewRestClient().getCommonsStars(activeUserId, selectedUserId);
    }
}

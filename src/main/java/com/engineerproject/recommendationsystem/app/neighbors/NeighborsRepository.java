package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.Users;

import java.util.List;

public interface NeighborsRepository {

    void saveNeighbor(Users neighbors);

    List<String> getPotentialNeighbors(String userId);

    List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId);
}

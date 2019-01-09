package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.User;

import java.util.List;

public interface NeighborsRepository {

    void saveNeighbor(User neighbors);

    List<String> getPotentialNeighbors(String userId);

    List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId);
}

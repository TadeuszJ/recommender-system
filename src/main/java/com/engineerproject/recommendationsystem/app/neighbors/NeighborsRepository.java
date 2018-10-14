package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;

import java.util.List;

public interface NeighborsRepository {

    void saveNeighbor(NeighborsDTO neighbors);

    List<String> getPotentialNeighbors(String userId);

    List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId);
}

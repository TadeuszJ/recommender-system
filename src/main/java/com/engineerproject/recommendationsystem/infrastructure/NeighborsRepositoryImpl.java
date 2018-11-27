package com.engineerproject.recommendationsystem.infrastructure;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsRepository;
import com.engineerproject.recommendationsystem.app.neighbors.dto.RatesPairDTO;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.UserRepositoryDB;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.Users;
import com.engineerproject.recommendationsystem.infrastructure.rest.ReviewRestClient;
import com.engineerproject.recommendationsystem.infrastructure.rest.UserRestClient;
import com.engineerproject.recommendationsystem.infrastructure.rest.dto.UsersPairDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NeighborsRepositoryImpl implements NeighborsRepository {

    private final UserRepositoryDB userRepositoryDB;
    private final UserRestClient userRestClient;
    private final ReviewRestClient reviewRestClient;

    @Override
    public void saveNeighbor(Users neighbors) {
        userRepositoryDB.save(neighbors);
    }

    @Override
    public List<String> getPotentialNeighbors(String userId) {
        return userRestClient.getPotentialNeighbors(userId);
    }

    @Override
    public List<RatesPairDTO> getCommonsStars(String activeUserId, String selectedUserId) {
        UsersPairDTO pairDTO = new UsersPairDTO(activeUserId, selectedUserId);
        return reviewRestClient.getCommonsStars(pairDTO);
    }
}

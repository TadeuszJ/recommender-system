package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.infrastructure.NeighborsRepositoryImpl;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.UserRepositoryDB;
import com.engineerproject.recommendationsystem.infrastructure.rest.ReviewRestClient;
import com.engineerproject.recommendationsystem.infrastructure.rest.UserRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NeighborsServiceFactory {

    @Bean
    private NeighborsService getNeighborsCollector(UserRepositoryDB userRepositoryDB) {
        NeighborsRepository neighborsRepository = new NeighborsRepositoryImpl(userRepositoryDB, new UserRestClient(), new ReviewRestClient());
        return new NeighborsService(neighborsRepository);
    }
}

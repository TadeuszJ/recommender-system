package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.infrastructure.NeighborsRepositoryImpl;
import com.engineerproject.recommendationsystem.infrastructure.db.neighbors.NeighborsRepositoryDB;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NeighborsCollectorFactory {

    @Bean
    private NeighborsCollector getNeighborsCollector(NeighborsRepositoryDB neighborsRepositoryDB) {
        NeighborsRepository neighborsRepository = new NeighborsRepositoryImpl(neighborsRepositoryDB);
        return new NeighborsCollector(neighborsRepository);
    }
}

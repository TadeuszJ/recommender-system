package com.engineerproject.recommendationsystem.app.neighbors;

import com.engineerproject.recommendationsystem.infrastructure.NeighborsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NeighborsCollectorFactory {

    @Bean
    private NeighborsCollector getNeighborsCollector() {
        NeighborsRepository neighborsRepository = new NeighborsRepositoryImpl();
        return new NeighborsCollector(neighborsRepository);
    }
}

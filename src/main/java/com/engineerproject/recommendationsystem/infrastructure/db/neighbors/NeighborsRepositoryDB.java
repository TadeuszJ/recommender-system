package com.engineerproject.recommendationsystem.infrastructure.db.neighbors;

import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeighborsRepositoryDB extends MongoRepository<NeighborsDTO, Long> {
}

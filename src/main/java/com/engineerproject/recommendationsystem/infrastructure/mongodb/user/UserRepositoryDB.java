package com.engineerproject.recommendationsystem.infrastructure.mongodb.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryDB extends MongoRepository<Users, Long> {
}

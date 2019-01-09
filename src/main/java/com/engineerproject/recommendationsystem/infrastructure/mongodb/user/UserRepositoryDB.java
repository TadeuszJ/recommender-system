package com.engineerproject.recommendationsystem.infrastructure.mongodb.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryDB extends MongoRepository<User, Long> {
    User getByActiveUserId(String userId);
}

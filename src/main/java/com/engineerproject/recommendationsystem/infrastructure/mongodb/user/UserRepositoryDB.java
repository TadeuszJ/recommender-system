package com.engineerproject.recommendationsystem.infrastructure.mongodb.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepositoryDB extends MongoRepository<User, Long>, QuerydslPredicateExecutor<User> {
    User getByActiveUserId(String userId);
}

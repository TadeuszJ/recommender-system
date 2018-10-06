package com.engineerproject.recommendationsystem.infrastructure.db.utils;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;
}

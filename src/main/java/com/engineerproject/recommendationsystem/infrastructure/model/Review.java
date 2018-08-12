package com.engineerproject.recommendationsystem.infrastructure.model;

import com.engineerproject.recommendationsystem.app.utils.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Review extends AbstractEntity {

    private Integer stars;

    private String text;

}

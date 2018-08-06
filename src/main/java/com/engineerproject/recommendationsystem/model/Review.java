package com.engineerproject.recommendationsystem.model;

import com.engineerproject.recommendationsystem.model.utils.AbstractEntity;
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

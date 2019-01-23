package com.engineerproject.recommendationsystem.app.ann.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeightsDTO {
    List<List<List<Double>>> layers;
    List<List<Double>> biases;
}

package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PredictionMapper {
    static PredictionDTO map(String businessId, double... outputs) {
        List<Double> list = DoubleStream.of(outputs)
                .map(o -> o * 5.0)
                .boxed()
                .collect(Collectors.toList());

        return PredictionDTO.builder()
                .businessId(businessId)
                .rate(list)
                .build();
    }
}

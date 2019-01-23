package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PredictionMapper {
    static PredictionDTO map(String businessId, double... outputs) {
        return PredictionDTO.builder()
                .businessId(businessId)
                .rate(getRate(outputs))
                .build();
    }

    private static double getRateAvg(double[] outputs) {
        double sum = 0d;
        double sumWeighs = 0d;

        for (int i = 0; i < outputs.length; i++) {
            sum += outputs[i] * (i + 1);
            sumWeighs += i + 1;
        }

        sumWeighs = sumWeighs == 0 ? 1 : sumWeighs;

        return sum / sumWeighs;
    }

    private static double getRate(double[] outputs) {
        double max = 0;
        int maxRate = 0;

        for (int i = 0; i < outputs.length; i++) {
            if (max < outputs[i]) {
                max = outputs[i];
                maxRate = i + 1;
            }
        }

        return maxRate;
    }
}

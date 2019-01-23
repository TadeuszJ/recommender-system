package com.engineerproject.recommendationsystem.app.ann;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.neuroph.nnet.learning.BackPropagation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BackPropagationGenerator {
    static BackPropagation create(Double maxError, Integer maxInterations) {
        BackPropagation backPropagation = new BackPropagation();
        backPropagation.setMaxIterations(maxInterations);
        backPropagation.setMaxError(maxError);
        return backPropagation;
    }
}

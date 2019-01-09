package com.engineerproject.recommendationsystem.app.ann;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.neuroph.nnet.learning.BackPropagation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BackPropagationGenerator {
    static BackPropagation create() {
        BackPropagation backPropagation = new BackPropagation();
        backPropagation.setMaxIterations(100);
        return backPropagation;
    }
}

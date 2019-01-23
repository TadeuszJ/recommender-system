package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.WeightsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.neuroph.core.*;
import org.neuroph.core.transfer.Sigmoid;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NeuralNetworkMapper {

    static NeuralNetwork map(WeightsDTO weights) {
        List<Integer> layersSize = getLayersSize(weights);
        layersSize.add(1);
        MultiLayerPerceptron ann = new MultiLayerPerceptron(layersSize, TransferFunctionType.RAMP);

        fillWeights(ann, weights);

        return ann;
    }

    private static List<Integer> getLayersSize(WeightsDTO weights) {
        return weights.getLayers().stream()
                .map(List::size)
                .collect(Collectors.toList());
    }

    private static void fillWeights(NeuralNetwork<BackPropagation> ann, WeightsDTO weights) {
        List<List<List<Double>>> layers = weights.getLayers();
        List<List<Double>> biases = weights.getBiases();

        for (int i = 0; i < layers.size() - 1; i++) {
            Layer annLayer = ann.getLayers()[i];
            List<List<Double>> weightsLayer = layers.get(i);
            List<Double> biasesLayer = biases.get(i);
            fillLayer(annLayer, weightsLayer, biasesLayer);
        }
        ann.getLayers()[3].getNeurons()[0].setTransferFunction(new Sigmoid());
    }

    private static void fillLayer(Layer annLayer, List<List<Double>> weightsLayer, List<Double> biasesLayer) {
        for (int i = 0; i < weightsLayer.size(); i++) {
            Neuron neuron = annLayer.getNeurons()[i];
            List<Double> outsWeights = weightsLayer.get(i);
            fillOutputWeights(neuron, outsWeights);
        }
        Neuron biasNeuron = annLayer.getNeurons()[weightsLayer.size()];
        fillOutputWeights(biasNeuron, biasesLayer);
    }

    private static void fillOutputWeights(Neuron neuron, List<Double> outsWeights) {
        for (int i = 0; i < outsWeights.size(); i++) {
            Double weight = outsWeights.get(i);
            Connection connection = neuron.getOutConnections()[i];
            connection.setWeight(new Weight(weight));
        }
    }
}

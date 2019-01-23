package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import com.engineerproject.recommendationsystem.app.ann.dto.WeightsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.exceptions.NeurophException;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class AnnService {

    private static final Double THRESHOLD = 0.9;
    private static final Double MAX_ERROR = 0.04;
    private static final Integer MAX_ITERATION = 1000;
    private static final Integer SIZE = 100;
    private static final Integer START_PAGE = 1;
    private static final Integer END_PAGE = 10;

    private final AnnRepository annRepository;

    private NeuralNetwork neuralNetwork;

    public void createNeuralNetwork() {
        WeightsDTO weights = annRepository.getWeights();
        neuralNetwork = NeuralNetworkMapper.map(weights);
    }

    @SuppressWarnings("unchecked")
    public Double learn() {
        NeuralNetwork neuralNetwork = loadNeuralNetwork();
        BackPropagation backPropagation = BackPropagationGenerator.create(MAX_ERROR, MAX_ITERATION);
        DataSetWrapper dataSetWrapper = new DataSetWrapper(getLearnDataSet(0, START_PAGE * SIZE));

        int page = START_PAGE;

        while (dataSetWrapper.size() != 0 && page < END_PAGE) {
            page++;
            dataSetWrapper.add(getLearnDataSet(page, SIZE));

            int epoch = 0;

            do {
                epoch++;
                neuralNetwork.learn(dataSetWrapper.getDataSet(), backPropagation);
                log.info("Learned ann: error {}; Size data set: {}; Epoch: {}", backPropagation.getTotalNetworkError(), dataSetWrapper.getDataSet().size(), epoch);

                saveNeuralNetwork(neuralNetwork);
            } while (backPropagation.getTotalNetworkError() > MAX_ERROR && epoch <= 1);
        }

        log.info("Learn ann finished: error {}", backPropagation.getTotalNetworkError());
        return backPropagation.getTotalNetworkError();
    }

    public List<PredictionDTO> getPrediction(String activeUserId) {
        return getPredictDataSet(activeUserId).stream()
                .map(row -> {
                    neuralNetwork.setInput(DataSetMapper.getInputArray(row));
                    neuralNetwork.calculate();
                    return PredictionMapper.map(row.getBusinessId(), neuralNetwork.getOutput());
                })
                .collect(Collectors.toList());
    }

    private NeuralNetwork loadNeuralNetwork() {
        String path = annRepository.getPathToSave();

        NeuralNetwork neuralNetwork;
        try {
            neuralNetwork = NeuralNetwork.createFromFile(path);
            log.info("Loaded ann from file {}", path);
        } catch (NeurophException e) {
            neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 15, 4, 8, 8, 5);
            log.info("Created ann, start learn");
        }
        return neuralNetwork;
    }

    private void saveNeuralNetwork(NeuralNetwork neuralNetwork) {
        String path = annRepository.getPathToSave();
        neuralNetwork.save(path);
    }

    private DataSet getLearnDataSet(int page, int size) {
        log.info("Data set to learn get started");
        List<DataSetRowDTO> dataSet = annRepository.getDataSetForLearn(PageRequest.of(page, size));
        dataSet.forEach(DataSetRowDTO::normalize);

        log.info("Get data set: {} elements to learn", dataSet.size());
        return DataSetMapper.map(dataSet);
    }

    private List<DataSetRowDTO> getPredictDataSet(String userId) {
        log.info("Data set to learn get started");
        List<String> neighbors = annRepository.getBestNeighbors(userId, THRESHOLD);
        List<DataSetRowDTO> dataSet = annRepository.getDataSetForPrediction(userId, neighbors);

        dataSet.forEach(DataSetRowDTO::normalize);
        log.info("Get data set: {} elements to prediction", dataSet.size());

        return dataSet;
    }
}

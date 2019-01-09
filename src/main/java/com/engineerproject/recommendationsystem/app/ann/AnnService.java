package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class AnnService {

    private static final Double THRESHOLD = 0.9;
    private static final Double MAX_ERROR = 0.03;
    private static final Integer SIZE = 100;
    private static final Integer PAGES_QUANTITY = 1000;

    private final AnnRepository annRepository;

    @SuppressWarnings("unchecked")
    public Double learn() {
        BackPropagation backPropagation = BackPropagationGenerator.create();

        NeuralNetwork neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 15, 4, 8, 8, 1);
        log.info("Created ann, start learn");

        int page = 0;
        DataSetWrapper dataSetWrapper = new DataSetWrapper(getLearnDataSet(page, 300));

        String path = annRepository.getPathToSave();
        while (dataSetWrapper.size() != 0 && page < PAGES_QUANTITY) {
            page++;
            dataSetWrapper.add(getLearnDataSet(page, SIZE));

            log.info("Total elements in data set: {}", dataSetWrapper.getDataSet().size());

            int epoch = 0;

            do {
                epoch++;
                neuralNetwork.learn(dataSetWrapper.getDataSet(), backPropagation);
                log.info("Learned ann: error {}; Epoch: {}", backPropagation.getTotalNetworkError(), page);
            } while (backPropagation.getTotalNetworkError() > MAX_ERROR && epoch < 1);

            neuralNetwork.save(path);
            log.info("Saved ann to file");
        }


        return backPropagation.getTotalNetworkError();
    }

    public List<PredictionDTO> getPrediction(String activeUserId) {
        List<DataSetRowDTO> dataSet = getPredictDataSet(activeUserId);
        log.info("Get data set: {} elements to prediction", dataSet.size());

        String path = annRepository.getPathToSave();
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(path);
        log.info("Loaded ann from file {}", path);

        return dataSet.stream()
                .map(row -> {
                    neuralNetwork.setInput(DataSetMapper.getInputArray(row));
                    neuralNetwork.calculate();
                    return PredictionMapper.map(row.getBusinessId(), neuralNetwork.getOutput());
                })
                .collect(Collectors.toList());
    }

    private DataSet getLearnDataSet(int page, int size) {
        List<DataSetRowDTO> dataSet = annRepository.getDataSet(PageRequest.of(page, size));
        dataSet.forEach(DataSetRowDTO::normalize);

        return DataSetMapper.map(dataSet);
    }

    private List<DataSetRowDTO> getPredictDataSet(String userId) {
        List<String> neighbors = annRepository.getBestNeighbors(userId, THRESHOLD);
        List<DataSetRowDTO> dataSet = annRepository.getDataSetForPrediction(userId, neighbors);

        dataSet.forEach(DataSetRowDTO::normalize);

        return dataSet;
    }
}

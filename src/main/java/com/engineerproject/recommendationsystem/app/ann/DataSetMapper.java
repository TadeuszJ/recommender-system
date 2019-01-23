package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.app.ann.dto.DataSetRowDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DataSetMapper {

    static DataSet map(List<DataSetRowDTO> domainDataSet) {
        DataSet dataSet = new DataSet(15, 5);

        for (DataSetRowDTO row : domainDataSet) {
            double[] input = getInputArray(row);
            double[] output = getOutputArray(row);

            dataSet.addRow(new DataSetRow(input, output));
        }

        return dataSet;
    }

    static double[] getInputArray(DataSetRowDTO row) {
        return new double[]{
                row.getBusinessAvgRate(),
                row.getBusinessReviewsCount(),
                row.getBusinessUseful(),
                row.getBusinessFunny(),
                row.getBusinessCool(),
                row.getReviewAvgDays(),
                row.getUserReviewsCount(),
                row.getUserReceivedUseful(),
                row.getUserReceivedCool(),
                row.getUserReceivedFunny(),
                row.getFansCount(),
                row.getDaysYelping(),
                row.getReceivedComplimentsCount(),
                row.getUserSentVotesCount(),
                row.getFriendsCount()
        };
    }

    private static double[] getOutputArray(DataSetRowDTO row) {
        return new double[]{
                row.getRate() == 1 ? 1 : 0,
                row.getRate() == 2 ? 1 : 0,
                row.getRate() == 3 ? 1 : 0,
                row.getRate() == 4 ? 1 : 0,
                row.getRate() == 5 ? 1 : 0
        };
    }
}

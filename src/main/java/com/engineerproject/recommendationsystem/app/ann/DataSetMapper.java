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
        DataSet dataSet = new DataSet(15, 1);

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
                row.getFansCount(),
                row.getFriendsCount(),
                row.getDaysYelping(),
                row.getReceivedComplimentsCount(),
                row.getUserSentVotesCount(),
                row.getUserReviewsCount(),
                row.getReviewAvgDays(),
                row.getUserReceivedUseful(),
                row.getUserReceivedCool(),
                row.getUserReceivedFunny()
        };
    }

    private static double[] getOutputArray(DataSetRowDTO row) {
        return new double[]{row.getRate()};
    }
}

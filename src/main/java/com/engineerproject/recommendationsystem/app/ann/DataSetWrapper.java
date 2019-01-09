package com.engineerproject.recommendationsystem.app.ann;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.neuroph.core.data.DataSet;

@Getter
@AllArgsConstructor
class DataSetWrapper {
    private DataSet dataSet;

    void add(DataSet dataSet) {
        dataSet.getRows().forEach(r -> this.dataSet.addRow(r));
    }

    int size() {
        return dataSet.size();
    }
}

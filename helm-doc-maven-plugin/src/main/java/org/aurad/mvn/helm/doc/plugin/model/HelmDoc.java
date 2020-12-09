package org.aurad.mvn.helm.doc.plugin.model;

import java.util.List;

public class HelmDoc {

    Chart chart;

    List<ValuesParameter> valuesParameters;

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public List<ValuesParameter> getValuesParameters() {
        return valuesParameters;
    }

    public void setValuesParameters(List<ValuesParameter> valuesParameters) {
        this.valuesParameters = valuesParameters;
    }
}

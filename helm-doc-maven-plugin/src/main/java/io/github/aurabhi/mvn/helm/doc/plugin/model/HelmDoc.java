package io.github.aurabhi.mvn.helm.doc.plugin.model;

import java.util.List;

public class HelmDoc {

    Chart chart;

    List<ValuesParameter> valuesParameters;

    List<String> source;

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

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }
}

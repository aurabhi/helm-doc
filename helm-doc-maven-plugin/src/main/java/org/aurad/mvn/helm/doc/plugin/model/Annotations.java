package org.aurad.mvn.helm.doc.plugin.model;

import java.util.HashMap;
import java.util.Map;

public class Annotations {

    private String example;

    private String category;

    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

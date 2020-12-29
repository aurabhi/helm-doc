package io.github.aurabhi.mvn.helm.doc.plugin.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dependency {

    private String name;

    private String version;

    private String repository;

    private String condition;

    private List<String> tags = null;

    private List<String> importValues = null;

    private String alias;

    private Map<String, Object> additionalProperties = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getImportValues() {
        return importValues;
    }

    public void setImportValues(List<String> importValues) {
        this.importValues = importValues;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

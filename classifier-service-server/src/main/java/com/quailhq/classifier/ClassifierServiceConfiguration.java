package com.quailhq.classifier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
/**
 * Server configuration for the classifier-service API server.
 */
public final class ClassifierServiceConfiguration extends Configuration {

    @JsonCreator
    public ClassifierServiceConfiguration() {
    }

    @JsonProperty("modelPath")
    protected String modelPath;
    public String getModelPath() {
        return modelPath;
    }
}

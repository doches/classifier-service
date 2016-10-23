package com.quailhq.classifier.api.classifier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LearnRequest {
    protected String example;
    protected String category;

    public LearnRequest(@JsonProperty("example") String example, @JsonProperty("category") String category) {
        this.example = example;
        this.category = category.toLowerCase();
    }

    public String getExample() {
        return example;
    }

    public String getCategory() {
        return category;
    }
}

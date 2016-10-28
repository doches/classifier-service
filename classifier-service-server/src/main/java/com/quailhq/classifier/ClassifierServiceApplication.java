package com.quailhq.classifier;

import com.quailhq.classifier.api.classifier.ClassifierResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Main entry point to the classifier-service API server.
 */
public final class ClassifierServiceApplication extends Application<ClassifierServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ClassifierServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ClassifierServiceConfiguration> bootstrap) {

    }

    @Override
    public void run(final ClassifierServiceConfiguration configuration, final Environment environment) {
        environment.jersey().register(new ClassifierResource(configuration.getModelPath(), configuration.getMemory()));
    }
}

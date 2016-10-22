package com.quailhq.classifier;

import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.quailhq.classifier.backend.DatabaseBackend;

/**
 * Main entry point to the classifier-service API server.
 */
public final class ClassifierServiceApplication extends Application<ClassifierServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ClassifierServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ClassifierServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(final ClassifierServiceConfiguration configuration, final Environment environment) {
        //DatabaseBackend databaseBackend = configuration.getDatabaseBackend(environment);
    }
}

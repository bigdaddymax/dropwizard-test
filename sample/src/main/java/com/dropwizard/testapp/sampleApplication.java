package com.dropwizard.testapp;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class sampleApplication extends Application<sampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new sampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "sample";
    }

    @Override
    public void initialize(final Bootstrap<sampleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final sampleConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

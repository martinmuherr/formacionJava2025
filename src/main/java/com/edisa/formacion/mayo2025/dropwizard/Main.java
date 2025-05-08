package com.edisa.formacion.mayo2025.dropwizard;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<DropWizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(args);

    }
    @Override
    public void initialize(Bootstrap<DropWizardConfiguration> bootstrap) {
        // Configuración adicional si es necesaria
    }

    @Override
    public void run(DropWizardConfiguration configuration, Environment environment) {
        final Recursos resource = new Recursos();
        environment.jersey().register(resource);
        environment.jersey().register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
    }
}

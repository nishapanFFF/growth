package com.fabfitfun.abtest.app;

import com.fabfitfun.auth.AuthBundle;
import com.fabfitfun.sharedutils.config.ApplicationPropertyLoader;

import io.dropwizard.Application;
import io.dropwizard.bundles.webjars.WebJarBundle;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class AbtestApplication extends Application<AbtestConfiguration> {

  /** Entry point. */
  public static void main(String... args) throws Exception {
    ApplicationPropertyLoader.loadProperties();
    if (args == null || args.length == 0) {
      args = new String[]{"server", "app.yml"};
    }

    new com.fabfitfun.abtest.app.AbtestApplication().run(args);
  }

  @Override
  public String getName() {
    return "abtest";
  }

  @Override
  public void initialize(Bootstrap<AbtestConfiguration> bootstrap) {
    ConfigurationSourceProvider sourceProvider = new SubstitutingSourceProvider(
        new ResourceConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor(false)
    );

    bootstrap.setConfigurationSourceProvider(sourceProvider);
    bootstrap.addBundle(new JdbiExceptionsBundle());
    bootstrap.addBundle(new WebJarBundle());
    bootstrap.addBundle(new AuthBundle());
  }

  @Override
  public void run(AbtestConfiguration config, Environment env) throws Exception {
    log.info("Building dependency graph...");
    DependencyManager deps = new DependencyManager(config, env);

    log.info("Registering resource...");
      env.jersey().register(deps.abtestResource);

    // Swagger setup
    env.jersey().register(new OpenApiResource());
    new JaxrsOpenApiContextBuilder<>().openApiConfiguration(config.getSwaggerConfiguration())
        .buildContext(true);
  }
}

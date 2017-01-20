package com.palmithor.sparkplayground.http;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.http.v1.TodoEndpointsRegistry;
import com.qmetric.spark.metrics.MetricsRoute;
import org.pac4j.core.config.Config;

import static spark.Spark.*;

/**
 * Route registration
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class SparkServerInitializer implements ServerInitializer {


    private final TodoEndpointsRegistry todoEndpointsRegistry;
    private final MetricRegistry metricRegistry;

    @Inject
    public SparkServerInitializer(final TodoEndpointsRegistry todoEndpointsRegistry, final MetricRegistry metricRegistry) {
        this.todoEndpointsRegistry = todoEndpointsRegistry;
        this.metricRegistry = metricRegistry;
    }


    @Override
    public void setup() {
        staticFileLocation("/client");

        final Config config = new AuthConfig().build();
        before("/api/*", ((request, response) -> response.type("application/json")));
        // TODO apply before("/api/*", new SecurityFilter(config, "DirectBasicAuthClient"));

        registerAccessControlHeaders();

        // Register all endpoints
        todoEndpointsRegistry.registerRoutes();

        get("/api/metrics", new MetricsRoute(metricRegistry));
    }

    private void registerAccessControlHeaders() {
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}


package com.palmithor.sparkplayground.http;

import com.codahale.metrics.MetricRegistry;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.http.exceptions.ObjectNotFoundException;
import com.palmithor.sparkplayground.http.response.ApiError;
import com.palmithor.sparkplayground.http.response.ErrorResponse;
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
    private final Gson gson;

    @Inject
    public SparkServerInitializer(final Gson gson,
                                  final TodoEndpointsRegistry todoEndpointsRegistry,
                                  final MetricRegistry metricRegistry) {
        this.gson = gson;
        this.metricRegistry = metricRegistry;
        this.todoEndpointsRegistry = todoEndpointsRegistry;
    }


    @Override
    public void setup() {
        staticFileLocation("/client");

        final Config config = new AuthConfig().build();
        before("/api/*", ((request, response) -> response.type("application/json")));
        // TODO apply before("/api/*", new SecurityFilter(config, "DirectBasicAuthClient"));

        registerAccessControlHeaders();

        setupExceptionHandling();

        // Register all endpoints
        todoEndpointsRegistry.registerRoutes();

        get("/api/metrics", new MetricsRoute(metricRegistry));
    }

    private void setupExceptionHandling() {
        exception(ObjectNotFoundException.class, (exception, request, response) -> {
            response.status(404);
            response.body(gson.toJson(ErrorResponse.create().withApiError(ApiError.ITEM_NOT_FOUND).build()));
        });

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


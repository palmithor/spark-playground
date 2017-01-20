package com.palmithor.sparkplayground.http.v1;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.http.v1.todo.TodoGetAllRoute;
import com.palmithor.sparkplayground.http.v1.todo.TodoPostRoute;
import com.palmithor.sparkplayground.util.JsonTransformer;
import com.qmetric.spark.metrics.RouteMeterWrapper;
import com.qmetric.spark.metrics.RouteTimerWrapper;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoEndpointsRegistry {


    private static final String BASE_PATH = "/todos";
    private final MetricRegistry metricRegistry;
    private final TodoGetAllRoute todosRouteGetRoute;
    private final TodoPostRoute todoPostRoute;

    @Inject
    public TodoEndpointsRegistry(final MetricRegistry metricRegistry,
                                 final TodoGetAllRoute todosRouteGetRoute,
                                 final TodoPostRoute todoPostRoute) {
        this.metricRegistry = metricRegistry;
        this.todosRouteGetRoute = todosRouteGetRoute;
        this.todoPostRoute = todoPostRoute;
    }

    public void registerRoutes() {
        get(
                V1Utils.preparePath(BASE_PATH),
                new RouteMeterWrapper(metricRegistry, new RouteTimerWrapper(metricRegistry, todosRouteGetRoute)),
                new JsonTransformer()
        );

        post(
                V1Utils.preparePath(BASE_PATH),
                new RouteMeterWrapper(metricRegistry, new RouteTimerWrapper(metricRegistry, todoPostRoute)),
                new JsonTransformer()
        );
    }
}

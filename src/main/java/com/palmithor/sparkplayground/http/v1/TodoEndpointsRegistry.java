package com.palmithor.sparkplayground.http.v1;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.http.RouteParams;
import com.palmithor.sparkplayground.http.v1.todo.TodoDeleteRoute;
import com.palmithor.sparkplayground.http.v1.todo.TodoGetAllRoute;
import com.palmithor.sparkplayground.http.v1.todo.TodoPostRoute;
import com.palmithor.sparkplayground.http.v1.todo.TodoPutRoute;
import com.palmithor.sparkplayground.util.JsonTransformer;
import com.qmetric.spark.metrics.RouteMeterWrapper;
import com.qmetric.spark.metrics.RouteTimerWrapper;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoEndpointsRegistry {


    private static final String BASE_PATH = "todos";

    private final MetricRegistry metricRegistry;
    private final TodoGetAllRoute todosRouteGetRoute;
    private final TodoPostRoute todoPostRoute;
    private final TodoDeleteRoute todoDeleteRoute;
    private final TodoPutRoute todoPutRoute;

    @Inject
    public TodoEndpointsRegistry(final MetricRegistry metricRegistry,
                                 final TodoGetAllRoute todosRouteGetRoute,
                                 final TodoPostRoute todoPostRoute,
                                 final TodoPutRoute todoPutRoute,
                                 final TodoDeleteRoute todoDeleteRoute) {
        this.metricRegistry = metricRegistry;
        this.todosRouteGetRoute = todosRouteGetRoute;
        this.todoPostRoute = todoPostRoute;
        this.todoDeleteRoute = todoDeleteRoute;
        this.todoPutRoute = todoPutRoute;
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

        delete(
                V1Utils.preparePath(BASE_PATH, RouteParams.PathParams.ID),
                new RouteMeterWrapper(metricRegistry, new RouteTimerWrapper(metricRegistry, todoDeleteRoute)),
                new JsonTransformer()
        );

        put(
                V1Utils.preparePath(BASE_PATH, RouteParams.PathParams.ID),
                new RouteMeterWrapper(metricRegistry, new RouteTimerWrapper(metricRegistry, todoPutRoute)),
                new JsonTransformer()
        );

    }


}

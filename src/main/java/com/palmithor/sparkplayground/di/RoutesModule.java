package com.palmithor.sparkplayground.di;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.palmithor.sparkplayground.http.v1.TodoEndpointsRegistry;

/**
 * Guice Module to provide all routes
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class RoutesModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(TodoEndpointsRegistry.class);
    }


    @Provides
    @Singleton
    public MetricRegistry provideMetricRegistry() {
        return new MetricRegistry();
    }
}

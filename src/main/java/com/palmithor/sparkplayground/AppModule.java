package com.palmithor.sparkplayground;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.palmithor.sparkplayground.db.FlywayMigrationUtils;
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.di.BaseAppModule;
import com.palmithor.sparkplayground.di.DaoModule;
import com.palmithor.sparkplayground.di.RoutesModule;
import com.palmithor.sparkplayground.http.ServerInitializer;
import com.palmithor.sparkplayground.http.SparkServerInitializer;
import com.palmithor.sparkplayground.util.Rfc339DateJsonAdapter;

import java.util.Date;

/**
 * Guice dependency injection - Main Module
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class AppModule extends BaseAppModule {


    AppModule(final String databaseUrl, final String databaseUsername, final String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    public AppModule(final String databaseUrl) {
        super(databaseUrl);
    }

    @Override
    protected void configure() {
        install(new DaoModule());
        install(new RoutesModule());
        bind(MigrationUtils.class).to(FlywayMigrationUtils.class);
        bind(ServerInitializer.class).to(SparkServerInitializer.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
    }
}

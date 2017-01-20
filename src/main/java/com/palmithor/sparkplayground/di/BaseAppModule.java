package com.palmithor.sparkplayground.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * A base AppModule class which provides database connection params
 * <p>
 * Should be extended once for the production app and then possibly multiple
 * times for testing purposes e.g.
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class BaseAppModule extends AbstractModule {

    private final String databaseUrl;
    private final String databaseUsername;
    private final String databasePassword;

    public BaseAppModule(final String databaseUrl) {
        this.databaseUrl = databaseUrl;
        this.databaseUsername = null;
        this.databasePassword = null;
    }

    public BaseAppModule(final String databaseUrl, final String databaseUsername, final String databasePassword) {
        this.databaseUrl = databaseUrl;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
    }

    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public Sql2o provideSql2o(final DataSource ds) {
        Sql2o sql2o = new Sql2o(ds, new PostgresQuirks());

        Map<String, String> columnMappings = new HashMap<>();
        sql2o.setDefaultColumnMappings(columnMappings);

        return sql2o;
    }


    @Provides
    @Singleton
    private DataSource provideDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(databaseUrl);
        config.setUsername(databaseUsername);
        config.setPassword(databasePassword);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}

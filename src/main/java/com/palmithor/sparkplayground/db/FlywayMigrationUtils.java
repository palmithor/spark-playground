package com.palmithor.sparkplayground.db;

import com.google.inject.Inject;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

/**
 * Utilities to run database migrations using Flyway
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class FlywayMigrationUtils implements MigrationUtils {

    private final Flyway flyway;

    @Inject
    public FlywayMigrationUtils(final DataSource dataSource) {
        this.flyway = new Flyway();
        flyway.setDataSource(dataSource);
    }

    @Override
    public void migrate() {
        flyway.migrate();
    }

    @Override
    public void startFresh() {
        flyway.clean();
        flyway.migrate();
    }
}

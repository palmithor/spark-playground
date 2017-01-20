package com.palmithor.sparkplayground.db;

/**
 * An interface to allow using different database version manager than flyway later on
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public interface MigrationUtils {

    /**
     * Run any pending migrations
     */
    void migrate();


    /**
     * Clear database and run all migrations
     */
    void startFresh();
}

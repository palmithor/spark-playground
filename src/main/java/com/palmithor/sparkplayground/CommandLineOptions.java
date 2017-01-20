package com.palmithor.sparkplayground;

import com.beust.jcommander.Parameter;

/**
 * Class containing the values needed for starting the server
 *
 * @author palmithor
 * @since 13.1.2017.
 */
class CommandLineOptions {

    @Parameter(names = "--debug")
    boolean debug = false;

    @Parameter(names = {"--server-port"})
    Integer servicePort = 4567;

    @Parameter(names = {"--url"})
    String databaseUrl = "jdbc:postgresql://localhost/spark_playground";

    @Parameter(names = {"--db-username"})
    String databaseUsername = "spark_playground";

    @Parameter(names = {"--db-password"})
    String databasePassword = "spark_playground";
}


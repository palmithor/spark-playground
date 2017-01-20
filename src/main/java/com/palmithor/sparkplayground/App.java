package com.palmithor.sparkplayground;

import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.http.ServerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Main class
 * <p>
 * Starts server, registerRoutes routes, initializes the database connection etc.
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);
        Injector injector = Guice.createInjector(new AppModule(options.databaseUrl, options.databaseUsername, options.databasePassword));

        injector.getInstance(MigrationUtils.class).migrate(); // TODO set some kind of control production vs test

        printOptions(options);
        initThreadPool();
        port(options.servicePort);

        injector.getInstance(ServerInitializer.class).setup();

        exception(Exception.class, (e, request, response) -> logger.error("Unexpected exception", e));
    }

    private static void printOptions(CommandLineOptions options) {
        logger.info("Options.debug = " + options.debug);
        logger.info("Options.database = " + options.databaseUrl);
        logger.info("Options.databaseUsername = " + options.databaseUsername);
        logger.info("Options.servicePort = " + options.servicePort);
    }

    private static void initThreadPool() {
        int maxThreads = Runtime.getRuntime().availableProcessors() + 1;
        int minThreads = Runtime.getRuntime().availableProcessors();
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);
    }
}

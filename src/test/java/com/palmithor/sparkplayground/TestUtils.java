package com.palmithor.sparkplayground;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.palmithor.sparkplayground.http.ServerInitializer;
import spark.servlet.SparkApplication;

/**
 * @author palmithor
 * @since 15.1.2017.
 */
public class TestUtils {

    private static Injector IN_MEMORY_DB_INJECTOR = Guice.createInjector(new AppModule(TestUtils.DATABASE_URL_H2));
    public static final String DATABASE_URL_H2 = "jdbc:h2:~/sparkplayground_test";

    public static class MemoryDBSparkApp implements SparkApplication {


        @Override
        public void init() {
            IN_MEMORY_DB_INJECTOR.getInstance(ServerInitializer.class).setup();
        }

    }
}

package com.palmithor.sparkplayground.http.v1;

import spark.utils.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utilities for building paths for V1
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class V1Utils {

    private static final String PATH_PREFIX = "/api/v1";

    private V1Utils() {

    }

    public static String preparePath(final String... pathItems) {
        return PATH_PREFIX + "/" + Arrays.stream(pathItems).collect(Collectors.joining("/"));
    }
}

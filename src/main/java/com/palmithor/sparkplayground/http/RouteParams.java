package com.palmithor.sparkplayground.http;

import javax.management.Query;

/**
 * Container for all query param constant names
 *
 * @author palmithor
 * @since 20.1.2017.
 */
public class RouteParams {

    private RouteParams() {

    }

    public static class QueryParams {

        private QueryParams() {
        }

        public static final String FILTER_BY = "filter_by";
    }

    public static class PathParams {

        private PathParams() {
        }

        public static final String ID = ":id";
    }

}

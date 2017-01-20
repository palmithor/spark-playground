package com.palmithor.sparkplayground.http.response;

/**
 * An object containing attributes for pagination
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class Pagination {

    private final String nextMaxId;
    private final String nextUrl;

    public Pagination() {
        this.nextMaxId = null;
        this.nextUrl = null;
    }

    public Pagination(final String nextMaxId, final String nextUrl) {
        this.nextMaxId = nextMaxId;
        this.nextUrl = nextUrl;
    }

    public String getNextMaxId() {
        return nextMaxId;
    }

    public String getNextUrl() {
        return nextUrl;
    }
}

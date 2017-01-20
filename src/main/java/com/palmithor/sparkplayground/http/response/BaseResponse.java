package com.palmithor.sparkplayground.http.response;

/**
 * All responses should extend this object
 * It contains information about the results of the request.
 *
 * @author palmithor
 * @since 13/1/17.
 */

public abstract class BaseResponse {
    private final Meta meta;

    BaseResponse(final Meta meta) {
        this.meta = meta;
    }

    BaseResponse() {
        this.meta = null;
    }

    public Meta getMeta() {
        return meta;
    }
}

package com.palmithor.sparkplayground.http.exceptions;

import com.palmithor.sparkplayground.http.response.ErrorResponse;

/**
 * Exception to throw from routes when an error occurs
 *
 * @author palmithor
 * @since 23.1.2017.
 */
public abstract class AbstractRouteException extends Exception {

    private final ErrorResponse errorResponse;

    public AbstractRouteException(final ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public AbstractRouteException(final Throwable cause, final ErrorResponse errorResponse) {
        super(cause);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}

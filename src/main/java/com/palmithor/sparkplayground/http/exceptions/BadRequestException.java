package com.palmithor.sparkplayground.http.exceptions;

import com.palmithor.sparkplayground.http.response.ErrorResponse;

/**
 * @author palmithor
 * @since 23.1.2017.
 */
public class BadRequestException extends AbstractRouteException {


    public BadRequestException(final ErrorResponse errorResponse) {
        super(errorResponse);
    }

}

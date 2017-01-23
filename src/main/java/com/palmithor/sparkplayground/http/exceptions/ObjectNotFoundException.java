package com.palmithor.sparkplayground.http.exceptions;

import com.palmithor.sparkplayground.http.response.ApiError;
import com.palmithor.sparkplayground.http.response.ErrorResponse;

/**
 * @author palmithor
 * @since 20.1.2017.
 */
public class ObjectNotFoundException extends AbstractRouteException {

    public ObjectNotFoundException() {
        super(ErrorResponse.create().withApiError(ApiError.ITEM_NOT_FOUND).build());
    }

}

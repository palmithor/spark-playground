package com.palmithor.sparkplayground.http.response;

/**
 * Generic object for all responses where the data element is empty
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class EmptyResponse extends BaseResponse {

    public EmptyResponse() {
        super();
    }

    public EmptyResponse(final Meta meta) {
        super(meta);
    }
}

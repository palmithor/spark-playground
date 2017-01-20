package com.palmithor.sparkplayground.http.response;

import org.pac4j.core.context.HttpConstants;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class Meta {

    public static final String MSG_OK = "Successful - OK";
    private final Integer code; // TODO introduce new codes instead of using http status codes - to allow more detailed response codes
    private final String message;

    public Meta() {
        this.code = null;
        this.message = null;
    }

    public Meta(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    static Meta success() {
        return new Meta(HttpConstants.OK, MSG_OK);
    }
}

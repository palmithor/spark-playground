package com.palmithor.sparkplayground.http.response;

import org.pac4j.core.context.HttpConstants;

/**
 * Enum which contains the default values for certain API errors
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public enum ApiError {

    UNAUTHORIZED(HttpConstants.UNAUTHORIZED, "Unauthorized"),
    BAD_REQUEST(HttpConstants.BAD_REQUEST, "Bad Request"),
    FORBIDDEN(HttpConstants.FORBIDDEN, "Forbidden"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private Integer code;
    private String message;

    ApiError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    /**
     * Converts code to ApiError.
     * <p>
     * Notice that if the code isn't found null value is returned
     */
    public static ApiError convertFromCode(final int code) {
        for (ApiError value : ApiError.values()) {
            if (value.getCode() == code) return value;
        }
        return null;
    }
}

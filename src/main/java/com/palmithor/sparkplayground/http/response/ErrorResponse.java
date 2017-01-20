package com.palmithor.sparkplayground.http.response;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class ErrorResponse extends BaseResponse {

    private ErrorResponse(Meta meta) {
        super(meta);
    }

    public static Builder create() {
        return new Builder();
    }

    public static final class Builder {
        private Integer code;
        private String message;

        /**
         * Sets / Overrides the meta code attribute
         *
         * @param code to set
         */
        public Builder withCode(final Integer code) {
            this.code = code;
            return this;
        }

        /**
         * Sets / Overrides the meta message attribute
         *
         * @param message to set
         */
        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        /**
         * Sets the error using the error enum. Overrides all values of code and message.
         *
         * @param apiError to set
         */
        public Builder withApiError(final ApiError apiError) {
            this.message = apiError.getMessage();
            this.code = apiError.getCode();
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(new Meta(code, message));
        }
    }

}

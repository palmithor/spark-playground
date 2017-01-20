package com.palmithor.sparkplayground.http.response;

/**
 * Generic data for all responses where the data element is an data
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class ObjectResponse<T> extends BaseResponse {

    private final T data;

    public ObjectResponse() {
        super();
        this.data = null;
    }

    public ObjectResponse(final T data) {
        super(Meta.success());
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

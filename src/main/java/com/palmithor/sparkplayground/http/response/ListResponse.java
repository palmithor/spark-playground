package com.palmithor.sparkplayground.http.response;


import java.util.ArrayList;
import java.util.List;

/**
 * Generic object for all responses where the data element is data
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class ListResponse<T> extends PaginatedResponse {

    private final List<T> data;

    public ListResponse() {
        super();
        this.data = new ArrayList<>();
    }

    public ListResponse(final List<T> data, final Pagination pagination) {
        super(pagination);
        this.data = data;
    }

    public ListResponse(List<T> all) {
        super();
        this.data = all;
    }

    public List<T> getData() {
        return data;
    }

}

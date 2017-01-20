package com.palmithor.sparkplayground.http.response;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public abstract class PaginatedResponse extends BaseResponse {
    private final Pagination pagination;


    public PaginatedResponse() {
        super(Meta.success());
        this.pagination = null;
    }

    public PaginatedResponse(final Pagination pagination) {
        super(Meta.success());
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public boolean hasPagination() {
        return pagination != null;
    }
}

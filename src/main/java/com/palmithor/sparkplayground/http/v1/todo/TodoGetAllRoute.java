package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dao.TodoFilter;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.ApiRoute;
import com.palmithor.sparkplayground.http.QueryParams;
import com.palmithor.sparkplayground.http.request.EmptyRequest;
import com.palmithor.sparkplayground.http.response.ListResponse;

import java.util.Map;

/**
 * Spark REST service for returning all todos
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoGetAllRoute extends ApiRoute<EmptyRequest, ListResponse<TodoDTO>> {

    private final TodoDao todoDao;

    @Inject
    public TodoGetAllRoute(final Gson gson, final TodoDao todoDao) {
        super(EmptyRequest.class, gson);
        this.todoDao = todoDao;
    }

    @Override
    protected ListResponse<TodoDTO> processImpl(final EmptyRequest payload, final Map<String, String[]> queryParams) {
        TodoFilter filter = TodoFilter.ALL;
        if (queryParams.containsKey(QueryParams.FILTER_BY)) {
            TodoFilter filterFromQuery = TodoFilter.convertFromString(queryParams.get(QueryParams.FILTER_BY)[0].toUpperCase());
            filter = filterFromQuery != null ? filterFromQuery : TodoFilter.ALL;
        }
        return new ListResponse<>(todoDao.getAll(filter));
    }

}


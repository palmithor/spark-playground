package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.ApiRoute;
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
    protected ListResponse<TodoDTO> processImpl(final EmptyRequest payload, final Map<String, String> urlParams) {
        return new ListResponse<>(todoDao.getAll());
    }

}


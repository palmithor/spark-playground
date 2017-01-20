package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.ApiRoute;
import com.palmithor.sparkplayground.http.request.TodoRequest;
import com.palmithor.sparkplayground.http.response.ObjectResponse;
import com.palmithor.sparkplayground.util.Iso8601Utils;

import java.util.Map;

/**
 * Spark REST service for creating a new todos
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoPostRoute extends ApiRoute<TodoRequest, ObjectResponse<TodoDTO>> {

    private final TodoDao todoDao;

    @Inject
    public TodoPostRoute(final Gson gson, final TodoDao todoDao) {
        super(TodoRequest.class, gson);
        this.todoDao = todoDao;
    }

    @Override
    protected ObjectResponse<TodoDTO> processImpl(final TodoRequest payload, final Map<String, String> pathParams, final Map<String, String[]> queryParams) {
        final TodoDTO dto = new TodoDTO();
        dto.setDone(payload.getDone());
        dto.setTitle(payload.getTitle());
        dto.setDue(payload.getDue() != null ? Iso8601Utils.parse(payload.getDue()) : null);
        TodoDTO created = todoDao.create(dto);
        return new ObjectResponse<>(created);
    }

}


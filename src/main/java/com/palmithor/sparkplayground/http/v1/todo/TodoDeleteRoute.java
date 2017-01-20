package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.ApiRoute;
import com.palmithor.sparkplayground.http.RouteParams;
import com.palmithor.sparkplayground.http.exceptions.ObjectNotFoundException;
import com.palmithor.sparkplayground.http.request.TodoRequest;
import com.palmithor.sparkplayground.http.response.EmptyResponse;
import com.palmithor.sparkplayground.http.response.Meta;
import com.palmithor.sparkplayground.http.response.ObjectResponse;
import com.palmithor.sparkplayground.util.Iso8601Utils;

import java.util.Map;

/**
 * Spark REST service for deleting a todos
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoDeleteRoute extends ApiRoute<TodoRequest, EmptyResponse> {

    private final TodoDao todoDao;

    @Inject
    public TodoDeleteRoute(final Gson gson, final TodoDao todoDao) {
        super(TodoRequest.class, gson);
        this.todoDao = todoDao;
    }

    @Override
    protected EmptyResponse processImpl(final TodoRequest payload, final Map<String, String> pathParams, final Map<String, String[]> queryParams) throws Exception {
        boolean deleted = todoDao.delete(Long.valueOf(pathParams.get(RouteParams.PathParams.ID)));
        if (deleted) {
            return new EmptyResponse();
        } else {
            throw new ObjectNotFoundException();
        }
    }

}


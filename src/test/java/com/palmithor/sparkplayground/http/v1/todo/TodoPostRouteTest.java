package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.request.TodoRequest;
import com.palmithor.sparkplayground.http.response.BaseResponse;
import com.palmithor.sparkplayground.http.response.ObjectResponse;
import com.palmithor.sparkplayground.util.Rfc339DateJsonAdapter;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertGeneratedValuesExist;
import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertMetaSuccess;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author palmithor
 * @since 18.1.2017.
 */
public class TodoPostRouteTest {

    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
    }

    @Test
    public void testBadRequest() throws Exception {
        final TodoDao todoDaoMock = mock(TodoDao.class);
        final TodoPostRoute route = new TodoPostRoute(gson, todoDaoMock);
        BaseResponse response = route.process(new TodoRequest(), new HashMap<>());
        assertThat(response.getMeta().getCode(), is(400));
    }

    @Test
    public void testOK() throws Exception {
        final TodoDao todoDaoMock = mock(TodoDao.class);
        final String title = "message";
        final TodoRequest todoRequest = new TodoRequest(title);
        Date now = new Date();
        final TodoDTO createdDto = TodoDTO.create()
                .withTitle(title)
                .withId(1L)
                .withCreated(now).withUpdated(now)
                .build();

        when(todoDaoMock.create(any())).thenReturn(createdDto);
        final TodoPostRoute route = new TodoPostRoute(gson, todoDaoMock);
        ObjectResponse<TodoDTO> response = route.processImpl(todoRequest, new HashMap<>());
        assertMetaSuccess(response);
        assertThat(response.getData().getDue(), is(nullValue()));
        assertThat(response.getData().isDone(), is(false));
        assertGeneratedValuesExist(response.getData());
    }
}
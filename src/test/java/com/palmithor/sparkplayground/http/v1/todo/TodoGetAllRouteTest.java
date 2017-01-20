package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.request.EmptyRequest;
import com.palmithor.sparkplayground.http.response.ListResponse;
import com.palmithor.sparkplayground.util.Rfc339DateJsonAdapter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertMetaSuccess;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author palmithor
 * @since 18.1.2017.
 */
public class TodoGetAllRouteTest {

    private Gson gson;

    @Before
    public void setUp() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
    }

    @Test
    public void testOKEmpty() throws Exception {
        final TodoDao todoDaoMock = mock(TodoDao.class);
        when(todoDaoMock.getAll()).thenReturn(new ArrayList<>());
        final TodoGetAllRoute route = new TodoGetAllRoute(gson, todoDaoMock);
        ListResponse<TodoDTO> response = route.processImpl(new EmptyRequest(), new HashMap<>());
        assertMetaSuccess(response);
        assertThat(response.getData(), hasSize(0));
    }

    @Test
    public void testOK() throws Exception {
        final TodoDao todoDaoMock = mock(TodoDao.class);
        ArrayList<TodoDTO> responseList = new ArrayList<>();
        responseList.add(TodoDTO.create().withTitle("message").build());
        when(todoDaoMock.getAll()).thenReturn(responseList);
        final TodoGetAllRoute route = new TodoGetAllRoute(gson, todoDaoMock);
        ListResponse<TodoDTO> response = route.processImpl(new EmptyRequest(), new HashMap<>());
        assertMetaSuccess(response);
        assertThat(response.getData(), hasSize(1));
    }

}
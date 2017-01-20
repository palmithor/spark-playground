package com.palmithor.sparkplayground.http.v1.todo;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.palmithor.sparkplayground.AppModule;
import com.palmithor.sparkplayground.TestUtils;
import com.palmithor.sparkplayground.dao.impl.TodoDaoImpl;
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.request.TodoRequest;
import com.palmithor.sparkplayground.http.response.ObjectResponse;
import org.junit.Before;
import org.junit.Test;
import spark.QueryParamsMap;

import java.util.HashMap;
import java.util.HashSet;

import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertGeneratedValuesExist;
import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertMetaSuccess;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Integration tests for POST /api/v1/todos
 *
 * @author palmithor
 * @since 18.1.2017.
 */
public class TodoPostRouteIT {

    private Injector injector = Guice.createInjector(new AppModule(TestUtils.DATABASE_URL_H2));


    // Class under test
    private TodoPostRoute route;

    @Inject private TodoDaoImpl todoDao;
    @Inject private Gson gson;


    @Before
    public void setup() {
        injector.injectMembers(this);
        injector.getInstance(MigrationUtils.class).startFresh();
        route = new TodoPostRoute(gson, todoDao);
    }

    @Test
    public void testOK() throws Exception {
        final String title = "message";
        ObjectResponse<TodoDTO> response = route.process(new TodoRequest(title), new HashMap<>(), new HashMap<>());
        assertMetaSuccess(response);
        assertGeneratedValuesExist(response.getData());
        assertThat(response.getData().getTitle(), is(title));
        final TodoDTO todoFromDB = todoDao.getById(response.getData().getId());
        assertThat(todoFromDB, is(notNullValue()));
        assertThat(todoFromDB.getTitle(), is(title));
    }
}
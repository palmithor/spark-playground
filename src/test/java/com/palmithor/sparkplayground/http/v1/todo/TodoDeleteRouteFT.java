package com.palmithor.sparkplayground.http.v1.todo;

import com.despegar.http.client.DeleteMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkClient;
import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.palmithor.sparkplayground.AppModule;
import com.palmithor.sparkplayground.TestUtils;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.request.TodoRequest;
import com.palmithor.sparkplayground.http.response.EmptyResponse;
import com.palmithor.sparkplayground.http.response.ObjectResponse;
import com.palmithor.sparkplayground.http.v1.V1Utils;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.lang.reflect.Type;

import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertGeneratedValuesExist;
import static com.palmithor.sparkplayground.http.HttpAssertionUtils.assertMetaSuccess;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 18.1.2017.
 */
public class TodoDeleteRouteFT {

    private Injector injector = Guice.createInjector(new AppModule(TestUtils.DATABASE_URL_H2));

    @ClassRule
    public static SparkServer<TestUtils.MemoryDBSparkApp> testServer = new SparkServer<>(TestUtils.MemoryDBSparkApp.class, 58888);

    @Inject private Gson gson;
    @Inject private TodoDao todoDao;
    private TodoDTO dtoToDelete;

    @Before
    public void setUp() {
        injector.injectMembers(this);
        injector.getInstance(MigrationUtils.class).startFresh();
        dtoToDelete = todoDao.create(TodoDTO.create().withTitle("title").build());
    }

    @Test
    public void testDeleteTodo() throws Exception {
        SparkClient sparkClient = testServer.getClient();
        DeleteMethod delete = sparkClient.delete(V1Utils.preparePath("todos", String.valueOf(dtoToDelete.getId())));
        // TODO add auth
        HttpResponse httpResponse = sparkClient.execute(delete);
        assertThat(httpResponse.code(), is(200));
        final Type type = new TypeToken<EmptyResponse>() {
        }.getType();
        EmptyResponse responseDto = gson.fromJson(new String(httpResponse.body()), type);
        assertMetaSuccess(responseDto);
    }

    @Test
    public void testDeleteNonExistingTodo() throws Exception {
        SparkClient sparkClient = testServer.getClient();
        DeleteMethod delete = sparkClient.delete(V1Utils.preparePath("todos", "9999"));
        // TODO add auth
        HttpResponse httpResponse = sparkClient.execute(delete);
        assertThat(httpResponse.code(), is(404));
        final Type type = new TypeToken<EmptyResponse>() {
        }.getType();
        EmptyResponse responseDto = gson.fromJson(new String(httpResponse.body()), type);
        assertThat(responseDto.getMeta().getCode(), is(404));
        assertThat(responseDto.getMeta().getMessage(), is("Item not found"));
    }

}
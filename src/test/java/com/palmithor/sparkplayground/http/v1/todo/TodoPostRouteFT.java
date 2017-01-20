package com.palmithor.sparkplayground.http.v1.todo;

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
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.dto.TodoDTO;
import com.palmithor.sparkplayground.http.request.TodoRequest;
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
public class TodoPostRouteFT {

    private Injector injector = Guice.createInjector(new AppModule(TestUtils.DATABASE_URL_H2));

    @ClassRule
    public static SparkServer<TestUtils.MemoryDBSparkApp> testServer = new SparkServer<>(TestUtils.MemoryDBSparkApp.class, 58888);

    @Inject private Gson gson;

    @Before
    public void setUp() {
        injector.injectMembers(this);
        injector.getInstance(MigrationUtils.class).startFresh();
    }

    @Test
    public void testCreateTodo() throws Exception {
        SparkClient sparkClient = testServer.getClient();
        TodoRequest request = new TodoRequest("request");
        PostMethod post = sparkClient.post(V1Utils.preparePath("/todos"), gson.toJson(request));
        // TODO add auth
        HttpResponse httpResponse = sparkClient.execute(post);
        assertEquals(200, httpResponse.code());
        final Type type = new TypeToken<ObjectResponse<TodoDTO>>() {
        }.getType();
        ObjectResponse<TodoDTO> responseDto = gson.fromJson(new String(httpResponse.body()), type);
        assertMetaSuccess(responseDto);
        assertGeneratedValuesExist(responseDto.getData());
        assertThat(responseDto.getData().getTitle(), is(request.getTitle()));
    }

}
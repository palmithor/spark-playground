package com.palmithor.sparkplayground.dao.impl;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.palmithor.sparkplayground.AppModule;
import com.palmithor.sparkplayground.TestUtils;
import com.palmithor.sparkplayground.dao.TodoFilter;
import com.palmithor.sparkplayground.db.MigrationUtils;
import com.palmithor.sparkplayground.dto.TodoDTO;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2oException;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author palmithor
 * @since 15.1.2017.
 */
public class TodoDaoImplTest {

    // Class under test
    @Inject private TodoDaoImpl todoDao;

    private Injector injector = Guice.createInjector(new AppModule(TestUtils.DATABASE_URL_H2));

    @Before
    public void setup() {
        injector.injectMembers(this);
        injector.getInstance(MigrationUtils.class).startFresh();
        todoDao.create(TodoDTO.create().withTitle("remember to...").build());
    }

    @Test
    public void testGetAll() {
        final List<TodoDTO> list = todoDao.getAll(TodoFilter.ALL);
        assertThat(list.size(), is(1));
    }

    @Test
    public void testGetById() {
        final TodoDTO dto = todoDao.getById(1L);
        assertThat(dto, is(notNullValue()));
    }

    @Test
    public void testCreateWithDue() {
        final TodoDTO dto = new TodoDTO();
        dto.setDue(new Date());
        dto.setTitle("Todo title");
        TodoDTO created = todoDao.create(dto);
        assertThat(created.getDue().getTime(), is(dto.getDue().getTime()));
        assertThat(created.getTitle(), is(dto.getTitle()));
    }

    @Test
    public void testCreateWithoutDue() {
        final TodoDTO dto = TodoDTO.create().withTitle("title").build();
        TodoDTO created = todoDao.create(dto);
        assertThat(created.getDue(), is(nullValue()));
        assertThat(created.getTitle(), is(dto.getTitle()));
        assertThat(created.isDone(), is(false));
    }

    @Test(expected = Sql2oException.class)
    public void testCreateWithoutMessage() {
        final TodoDTO dto = new TodoDTO();
        TodoDTO created = todoDao.create(dto);
        assertThat(created.getDue(), is(nullValue()));
        assertThat(created.getTitle(), is(dto.getTitle()));
        assertThat(created.isDone(), is(false));
    }

    @Test
    public void testUpdate() {
        final TodoDTO dto = TodoDTO.create().withTitle("title").build();
        TodoDTO created = todoDao.create(dto);
        assertThat(created.isDone(), is(false));
        created.setDone(true);
        TodoDTO updated = todoDao.update(created);
        assertThat(updated.isDone(), is(true));
    }

    @Test(expected = Sql2oException.class)
    public void testUpdateWithoutMessage() {
        final TodoDTO dto = TodoDTO.create().withTitle("title").build();
        TodoDTO created = todoDao.create(dto);
        created.setTitle(null);
        todoDao.update(created);
    }

    @Test
    public void testDeleteOK() {
        final TodoDTO dto = TodoDTO.create().withTitle("title").build();
        TodoDTO created = todoDao.create(dto);
        todoDao.delete(created.getId());
        TodoDTO foundDto = todoDao.getById(created.getId());
        assertThat(foundDto, is(nullValue()));
    }


}
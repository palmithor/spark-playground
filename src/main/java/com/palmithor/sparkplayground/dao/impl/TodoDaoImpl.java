package com.palmithor.sparkplayground.dao.impl;

import com.google.inject.Inject;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dao.TodoFilter;
import com.palmithor.sparkplayground.db.Column;
import com.palmithor.sparkplayground.db.SelectBuilder;
import com.palmithor.sparkplayground.db.Sql2oParam;
import com.palmithor.sparkplayground.db.Table;
import com.palmithor.sparkplayground.dto.TodoDTO;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoDaoImpl extends BaseDao<TodoDTO> implements TodoDao {


    @Inject
    public TodoDaoImpl(final Sql2o sql2o) {
        super(sql2o);
    }

    @Override
    public TodoDTO getById(final Long id) {
        final String sql = new SelectBuilder(Table.TODO).where("id = :id").toString();
        return findOne(sql, new Sql2oParam(Column.ID, id));
    }

    @Override
    public List<TodoDTO> getAll(final TodoFilter filter) {
        SelectBuilder selectBuilder = new SelectBuilder(Table.TODO).orderBy("id desc");
        switch (filter) {
            case ACTIVE:
                selectBuilder.where("done = false");
                break;
            case DONE:
                selectBuilder.where("done = true");
                break;
        }
        return find(selectBuilder.toString());
    }

    @Override
    public List<TodoDTO> get(final Integer nextMaxId) {
        return null;
    }

    @Override
    public TodoDTO create(final TodoDTO dto) {
        final String sql = "insert into todos(title, due, done, created, updated) " +
                "values (:title, :due, :done, :created, :updated)";
        return insert(sql, dto);
    }

    @Override
    public TodoDTO update(final TodoDTO dto) {
        final String sql = "update todos " +
                "set title = :title, due = :due, done = :done, created = :created, updated = :updated " +
                "where id = :id";
        return executeUpdate(sql, dto);
    }

    @Override
    public Class<TodoDTO> getClazz() {
        return TodoDTO.class;
    }
}

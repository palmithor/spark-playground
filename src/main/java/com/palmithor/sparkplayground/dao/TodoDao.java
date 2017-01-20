package com.palmithor.sparkplayground.dao;

import com.palmithor.sparkplayground.dto.TodoDTO;

import java.util.List;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public interface TodoDao {

    TodoDTO getById(final Long id);

    List<TodoDTO> get(final Integer nextMaxId);

    TodoDTO create(final TodoDTO dto);

    TodoDTO update(final TodoDTO dto);

    List<TodoDTO> getAll(final TodoFilter filter);
}

package com.palmithor.sparkplayground.dao;

import com.google.inject.AbstractModule;
import com.palmithor.sparkplayground.dao.impl.TodoDaoImpl;

/**
 * @author palmithor
 * @since 15.1.2017.
 */
public class DaoTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TodoDaoImpl.class);
    }
}

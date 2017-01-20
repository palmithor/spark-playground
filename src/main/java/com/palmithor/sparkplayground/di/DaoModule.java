package com.palmithor.sparkplayground.di;

import com.google.inject.AbstractModule;
import com.palmithor.sparkplayground.dao.TodoDao;
import com.palmithor.sparkplayground.dao.impl.TodoDaoImpl;

/**
 * Guice Module to provide all DAOs
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class DaoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TodoDao.class).to(TodoDaoImpl.class);
    }
}

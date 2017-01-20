package com.palmithor.sparkplayground.dao.impl;

import com.palmithor.sparkplayground.db.Column;
import com.palmithor.sparkplayground.db.Sql2oParam;
import com.palmithor.sparkplayground.dto.BaseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.Date;
import java.util.List;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
abstract class BaseDao<T extends BaseDTO> {

    public static final Integer PAGE_COUNT = 10;

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    protected final Sql2o sql2o;

    BaseDao(final Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    List<T> find(final String sql, final Sql2oParam... params) {
        try (Connection con = sql2o.open()) {
            Query query = con.createQuery(sql);
            for (final Sql2oParam param : params) {
                query = query.addParameter(param.getKey(), param.getValue());
            }
            return query.executeAndFetch(getClazz());
        } catch (final Sql2oException e) {
            logger.warn("An error occurred fetching from database", e);
            throw e;
        }
    }

    T findOne(final String sql, final Sql2oParam... params) {
        try (Connection con = sql2o.open()) {
            Query query = con.createQuery(sql);
            for (final Sql2oParam param : params) {
                query = query.addParameter(param.getKey(), param.getValue());
            }
            return query.executeAndFetchFirst(getClazz());
        } catch (final Sql2oException e) {
            logger.warn("An error occurred fetching from database", e);
            throw e;
        }
    }

    T insert(final String sql, final T toBind) {
        try (Connection con = sql2o.open()) {
            final Date now = new Date();
            toBind.setCreated(now);
            toBind.setUpdated(now);
            Connection result = con.createQuery(sql, true)
                    .bind(toBind)
                    .executeUpdate();
            toBind.setId((Long) result.getKey());
            return toBind;
        } catch (final Sql2oException e) {
            logger.trace("An error occurred creating", e);
            throw e;
        }
    }

    boolean delete(final String sql, final Sql2oParam... params) {
        try (Connection con = sql2o.open()) {
            Query query = con.createQuery(sql);
            for (final Sql2oParam param : params) {
                query = query.addParameter(param.getKey(), param.getValue());
            }
            Connection connection = query.executeUpdate();
            return 0 < connection.getResult();
        } catch (final Sql2oException e) {
            logger.warn("An error occurred fetching from database", e);
            throw e;
        }
    }

    T update(final String sql, final T toBind) {
        try (Connection con = sql2o.open()) {
            toBind.setUpdated(new Date());
            con.createQuery(sql, true)
                    .bind(toBind)
                    .executeUpdate();
            return toBind;
        } catch (final Sql2oException e) {
            logger.trace("An error occurred creating", e);
            throw e;
        }
    }


    public abstract Class<T> getClazz();
}


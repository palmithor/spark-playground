package com.palmithor.sparkplayground.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for StringBuilder for building SQL queries
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class SelectBuilder {

    private List<String> tableList = new ArrayList<>();
    private List<String> columnList = new ArrayList<>();
    private List<String> whereList = new ArrayList<>();
    private List<String> groupByList = new ArrayList<>();
    private List<String> orderByList = new ArrayList<>();
    private List<String> leftJoinList = new ArrayList<>();
    private List<String> joinList = new ArrayList<>();


    public SelectBuilder(final String table) {
        this.tableList.add(table);
    }

    private void appendList(StringBuilder sql, List<String> list, String init,
                            String sep) {
        boolean first = true;
        for (String s : list) {
            if (first) {
                sql.append(init);
            } else {
                sql.append(sep);
            }
            sql.append(s);
            first = false;
        }
    }

    public SelectBuilder column(String name) {
        columnList.add(name);
        return this;
    }

    public SelectBuilder column(String name, boolean groupBy) {
        columnList.add(name);
        if (groupBy) {
            groupByList.add(name);
        }
        return this;
    }

    public SelectBuilder from(String table) {
        tableList.add(table);
        return this;
    }

    public SelectBuilder groupBy(String expr) {
        groupByList.add(expr);
        return this;
    }


    public SelectBuilder join(String join) {
        joinList.add(join);
        return this;
    }

    public SelectBuilder leftJoin(String join) {
        leftJoinList.add(join);
        return this;
    }

    public SelectBuilder orderBy(String name) {
        orderByList.add(name);
        return this;
    }

    public SelectBuilder where(String expr) {
        whereList.add(expr);
        return this;
    }

    @Override
    public String toString() {

        StringBuilder sql = new StringBuilder("select ");

        if (columnList.size() == 0) {
            sql.append("*");
        } else {
            appendList(sql, columnList, "", ", ");
        }

        appendList(sql, tableList, " from ", ", ");
        appendList(sql, joinList, " join ", " join ");
        appendList(sql, leftJoinList, " left join ", " left join ");
        appendList(sql, whereList, " where ", " and ");
        appendList(sql, groupByList, " group by ", ", ");
        appendList(sql, orderByList, " order by ", ", ");

        return sql.toString();
    }
}

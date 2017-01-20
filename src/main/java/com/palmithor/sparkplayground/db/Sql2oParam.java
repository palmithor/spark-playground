package com.palmithor.sparkplayground.db;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class Sql2oParam {
    private final String key;
    private final Object value;

    public Sql2oParam(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}

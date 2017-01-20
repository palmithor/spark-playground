package com.palmithor.sparkplayground.dao;

import spark.utils.StringUtils;

/**
 * Enum used to define what filters should be set on todos select request
 *
 * @author palmithor
 * @since 20.1.2017.
 */
public enum TodoFilter {
    ALL,
    ACTIVE,
    DONE;

    public static TodoFilter convertFromString(final String value) {
        if (StringUtils.isNotBlank(value)) {
            for (TodoFilter f : TodoFilter.values()) {
                if (f.equals(value.toUpperCase())) {
                    return f;
                }
            }
        }
        return null;
    }
}

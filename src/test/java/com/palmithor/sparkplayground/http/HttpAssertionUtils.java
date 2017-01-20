package com.palmithor.sparkplayground.http;

import com.palmithor.sparkplayground.dto.BaseDTO;
import com.palmithor.sparkplayground.http.response.BaseResponse;
import com.palmithor.sparkplayground.http.response.Meta;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Assertion utils for routes and such
 *
 * @author palmithor
 * @since 18.1.2017.
 */
public final class HttpAssertionUtils {

    private HttpAssertionUtils() {
    }

    public static void assertMetaSuccess(final BaseResponse response) {
        assertThat(response.getMeta().getCode(), is(200));
        assertThat(response.getMeta().getMessage(), is(Meta.MSG_OK));
    }

    public static void assertGeneratedValuesExist(final BaseDTO dto) {
        assertThat(dto.getId(), is(notNullValue()));
        assertThat(dto.getCreated(), is(notNullValue()));
        assertThat(dto.getUpdated(), is(notNullValue()));
    }
}

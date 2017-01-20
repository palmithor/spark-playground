package com.palmithor.sparkplayground.http.v1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author palmithor
 * @since 20.1.2017.
 */
public class V1UtilsTest {

    @Test
    public void testPathPreparation() throws Exception {
        assertThat(V1Utils.preparePath("hello", "world", ":id"),
                is("/api/v1/hello/world/:id"));

    }
}
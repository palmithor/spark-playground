package com.palmithor.sparkplayground.http;

import com.palmithor.sparkplayground.http.response.BaseResponse;

import java.util.Map;

/**
 * All routes should implement this class
 *
 * @author palmithor
 * @since 17.1.2017.
 */
public interface RequestHandler<V> {

    BaseResponse process(final V payload, Map<String, String> urlParams);

}

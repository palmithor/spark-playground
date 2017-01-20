package com.palmithor.sparkplayground.http;

import com.palmithor.sparkplayground.http.response.ApiError;
import com.palmithor.sparkplayground.http.response.ErrorResponse;
import com.palmithor.sparkplayground.util.JsonTransformer;
import org.pac4j.sparkjava.DefaultHttpActionAdapter;
import org.pac4j.sparkjava.SparkWebContext;

import static spark.Spark.halt;

/**
 * Action adapter for API requests
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class ApiActionAdapter extends DefaultHttpActionAdapter {


    private final JsonTransformer jsonTransformer;

    ApiActionAdapter() {
        this.jsonTransformer = new JsonTransformer();
    }

    @Override
    public Object adapt(final int code, final SparkWebContext context) {
        final ApiError apiError = ApiError.convertFromCode(code);
        if (apiError != null) {
            halt(code, jsonTransformer.render(ErrorResponse.create().withApiError(apiError).build()));
        } else {
            return super.adapt(code, context);
        }
        return null;
    }

}

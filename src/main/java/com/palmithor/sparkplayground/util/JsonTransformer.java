package com.palmithor.sparkplayground.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson;

    public JsonTransformer() {
        this.gson = new Gson();
    }

    @Override
    public String render(final Object model) {
        return gson.toJson(model);
    }

}

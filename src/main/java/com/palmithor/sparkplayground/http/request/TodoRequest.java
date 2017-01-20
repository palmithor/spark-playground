package com.palmithor.sparkplayground.http.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A POJO for adding / updating todos
 *
 * @author palmithor
 * @since 17.1.2017.
 */
public class TodoRequest {


    @NotNull()
    @Size(min = 1, max = 255)
    private final String title;

    // TODO add pattern validation
    private final String due;
    private final Boolean done;


    public TodoRequest() {
        this.title = null;
        this.due = null;
        this.done = null;
    }

    public TodoRequest(final String title) {
        this.title = title;
        this.due = null;
        this.done = null;
    }

    public TodoRequest(final String title, final String due, final Boolean done) {
        this.title = title;
        this.due = due;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public String getDue() {
        return due;
    }

    public Boolean getDone() {
        return done;
    }
}

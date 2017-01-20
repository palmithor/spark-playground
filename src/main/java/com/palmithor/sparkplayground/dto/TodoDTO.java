package com.palmithor.sparkplayground.dto;

import java.util.Date;

/**
 * @author palmithor
 * @since 13.1.2017.
 */
public class TodoDTO extends BaseDTO {

    private String title;
    private Date due;
    private boolean done;

    public TodoDTO() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(final Date due) {
        this.due = due;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final Boolean done) {
        this.done = done != null ? done : false;
    }

    public static Builder create() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String title;
        private Date created;
        private Date due;
        private Date updated;
        private boolean done;

        private Builder() {
        }


        public Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withCreated(final Date created) {
            this.created = created;
            return this;
        }

        public Builder withDue(final Date due) {
            this.due = due;
            return this;
        }

        public Builder withUpdated(final Date updated) {
            this.updated = updated;
            return this;
        }

        public Builder withDone(boolean done) {
            this.done = done;
            return this;
        }

        public TodoDTO build() {
            TodoDTO todoDTO = new TodoDTO();
            todoDTO.setId(id);
            todoDTO.setTitle(title);
            todoDTO.setCreated(created);
            todoDTO.setDue(due);
            todoDTO.setUpdated(updated);
            todoDTO.setDone(done);
            return todoDTO;
        }
    }
}

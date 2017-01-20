package com.palmithor.sparkplayground.dto;

import java.util.Date;

/**
 * Class extended for all DTOs that include IDs and timestamps
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public abstract class BaseDTO {

    private Long id;
    private Date created;
    private Date updated;

    BaseDTO() {
        this.id = null;
        this.created = null;
        this.updated = null;
    }

    BaseDTO(final Date created, final Date updated) {
        this.created = updated;
        this.updated = updated;
    }


    BaseDTO(final Long id, final Date updated) {
        this.id = id;
        this.updated = updated;
    }

    BaseDTO(final Long id, final Date created, final Date updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }
}

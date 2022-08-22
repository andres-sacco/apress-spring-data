package com.apress.catalog.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Base implements Serializable {

    @Id
    private Long id;

    private Audit audit;

    private Long version;

    public Base(){}

    public Base(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void fillCreatedOn() {
        audit.setCreatedOn(LocalDateTime.now());
    }

    public void fillUpdatedOn() {
        audit.setUpdatedOn(LocalDateTime.now());
    }
}

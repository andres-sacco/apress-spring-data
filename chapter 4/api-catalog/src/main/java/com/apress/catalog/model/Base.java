package com.apress.catalog.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Base {

    @Id //Identify which is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicate the way to generate the ID
    private Long id;

    @Embedded
    private Audit audit;

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

    @PrePersist
    public void fillCreatedOn() {
        audit.setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    public void fillUpdatedOn() {
        audit.setUpdatedOn(LocalDateTime.now());
    }
}

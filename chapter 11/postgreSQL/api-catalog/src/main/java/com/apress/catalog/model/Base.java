package com.apress.catalog.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Base {

    @Id //Identify which is the primary key
    @GeneratedValue(strategy = GenerationType.TABLE) //Indicate the way to generate the ID
    private Long id;

    @Version
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


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}

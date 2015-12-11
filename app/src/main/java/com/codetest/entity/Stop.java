package com.codetest.entity;

import java.io.Serializable;

/**
 * represents a Stop entity
 */
public class Stop implements Serializable{

    private Long id;
    private String name;
    private int sequence;
    private Long route_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    @Override
    public String toString() {
        return name;
    }
}

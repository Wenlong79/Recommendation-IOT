package com.wl.entity;

public class Parent {
    private int id;
    private String alias;

    public Parent() {
    }

    public Parent(int id, String alias) {
        this.id = id;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                '}';
    }
}

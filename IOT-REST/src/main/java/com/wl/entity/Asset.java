package com.wl.entity;

public class Asset {

    private int id;
    private String alias;

    public Asset() {
    }

    public Asset(int id, String alias) {
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
        return "Asset{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                '}';
    }
}

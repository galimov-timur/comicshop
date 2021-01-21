package kz.comicshop.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private String name;

    public Category() {
        this.name = "";
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

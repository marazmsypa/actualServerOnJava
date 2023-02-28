package org.example.data.model;

public class Divisions {
    private Integer id;

    private String name;

    public Divisions() {
    }

    public Divisions(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "divisions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}

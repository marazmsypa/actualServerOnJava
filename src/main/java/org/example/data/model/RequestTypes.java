package org.example.data.model;

public class RequestTypes {

    private Integer id;

    private String name;

    public RequestTypes() {

    }

    public RequestTypes(Integer id, String name) {
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
        return "RequestTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}

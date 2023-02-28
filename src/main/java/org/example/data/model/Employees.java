package org.example.data.model;

public class Employees {
    private Integer id;

    private String surname;

    private String name;

    private String patronymic;

    private Integer code;

    private Integer subdivision_id;

    private Integer division_id;

    public Employees() {
    }

    public Employees(Integer id, String surname, String name, String patronymic, Integer code, Integer subdivision_id, Integer division_id) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.code = code;
        this.subdivision_id = subdivision_id;
        this.division_id = division_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getSubdivision_id() {
        return subdivision_id;
    }

    public void setSubdivision_id(Integer subdivision_id) {
        this.subdivision_id = subdivision_id;
    }

    public Integer getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", code='" + code + '\'' +
                ", subdivision_id=" + subdivision_id +
                ", division_id=" + division_id +
                '}';
    }
}

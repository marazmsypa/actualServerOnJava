package org.example.data.model;

import java.util.Date;

public class Visitors {

    private Integer id;

    private String surname;

    private String name;

    private String patronymic;

    private String phone;

    private String email;

    private String organization;

    private String note;

    private Date birth_date;

    private String passport_series;

    private String passport_number;

    private String image_path;

    private String passport_scan_path;

    private String login;

    private String password;

    public Visitors() {
    }

    public Visitors(Integer id, String surname, String name, String patronymic, String phone, String email, String organization, String note, Date birth_date, String passport_series, String passport_number, String image_path, String passport_scan_path, String login, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.organization = organization;
        this.note = note;
        this.birth_date = birth_date;
        this.passport_series = passport_series;
        this.passport_number = passport_number;
        this.image_path = image_path;
        this.passport_scan_path = passport_scan_path;
        this.login = login;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPassport_series() {
        return passport_series;
    }

    public void setPassport_series(String passport_series) {
        this.passport_series = passport_series;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPassport_scan_path() {
        return passport_scan_path;
    }

    public void setPassport_scan_path(String passport_scan_path) {
        this.passport_scan_path = passport_scan_path;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Visitors{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", note='" + note + '\'' +
                ", birth_date=" + birth_date +
                ", passport_series='" + passport_series + '\'' +
                ", passport_number='" + passport_number + '\'' +
                ", image_path='" + image_path + '\'' +
                ", passport_scan_path='" + passport_scan_path + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

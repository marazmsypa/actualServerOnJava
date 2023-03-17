package org.example.data.model;

import java.sql.Time;
import java.util.Date;

public class Visits {
    private Integer id;

    private Integer request_id;

    private Date visit_date;

    private Time visit_start_time;

    private Time visit_end_time;

    private Time actual_visit_time;

    private Time subdivision_visit_end_time;

    private Time subdivision_visit_start_time;

    public Visits() {
    }

    public Visits(Integer id, Integer request_id, Date visit_date, Time visit_start_time, Time visit_end_time, Time actual_visit_time, Time subdivision_visit_end_time, Time subdivision_visit_start_time) {
        this.id = id;
        this.request_id = request_id;
        this.visit_date = visit_date;
        this.visit_start_time = visit_start_time;
        this.visit_end_time = visit_end_time;
        this.actual_visit_time = actual_visit_time;
        this.subdivision_visit_end_time = subdivision_visit_end_time;
        this.subdivision_visit_start_time = subdivision_visit_start_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }

    public Time getVisit_start_time() {
        return visit_start_time;
    }

    public void setVisit_start_time(Time visit_start_time) {
        this.visit_start_time = visit_start_time;
    }

    public Time getVisit_end_time() {
        return visit_end_time;
    }

    public void setVisit_end_time(Time visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public Time getActual_visit_time() {
        return actual_visit_time;
    }

    public void setActual_visit_time(Time actual_visit_time) {
        this.actual_visit_time = actual_visit_time;
    }

    public Time getSubdivision_visit_end_time() {
        return subdivision_visit_end_time;
    }

    public void setSubdivision_visit_end_time(Time subdivision_visit_end_time) {
        this.subdivision_visit_end_time = subdivision_visit_end_time;
    }

    public Time getSubdivision_visit_start_time() {
        return subdivision_visit_start_time;
    }

    public void setSubdivision_visit_start_time(Time subdivision_visit_start_time) {
        this.subdivision_visit_start_time = subdivision_visit_start_time;
    }

    @Override
    public String toString() {
        return "Visits{" +
                "id=" + id +
                ", request_id=" + request_id +
                ", visit_date=" + visit_date +
                ", visit_start_time=" + visit_start_time +
                ", visit_end_time=" + visit_end_time +
                ", actual_visit_time=" + actual_visit_time +
                ", subdivision_visit_end_time=" + subdivision_visit_end_time +
                ", subdivision_visit_start_time=" + subdivision_visit_start_time +
                '}';
    }
}

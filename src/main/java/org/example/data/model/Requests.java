package org.example.data.model;

import java.util.Date;

public class Requests {
    private Integer id;

    private Integer request_type_id;
    private Integer request_status_id;

    private Date date_start;

    private Date date_end;

    private Integer visit_purpose_id;

    private Integer employee_id;

    private  Integer group_id;

    private Integer visitor_id;

    private boolean is_group;

    private String message;

    public Requests() {
    }

    public Requests(Integer id, Integer request_type_id, Integer request_status_id, Date date_start, Date date_end, Integer visit_purpose_id, Integer employee_id, Integer group_id, Integer visitor_id, boolean is_group, String message) {
        this.id = id;
        this.request_type_id = request_type_id;
        this.request_status_id = request_status_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.visit_purpose_id = visit_purpose_id;
        this.employee_id = employee_id;
        this.group_id = group_id;
        this.visitor_id = visitor_id;
        this.is_group = is_group;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequest_type_id() {
        return request_type_id;
    }

    public void setRequest_type_id(Integer request_type_id) {
        this.request_type_id = request_type_id;
    }

    public Integer getRequest_status_id() {
        return request_status_id;
    }

    public void setRequest_status_id(Integer request_status_id) {
        this.request_status_id = request_status_id;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Integer getVisit_purpose_id() {
        return visit_purpose_id;
    }

    public void setVisit_purpose_id(Integer visit_purpose_id) {
        this.visit_purpose_id = visit_purpose_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(Integer visitor_id) {
        this.visitor_id = visitor_id;
    }

    public boolean isIs_group() {
        return is_group;
    }

    public void setIs_group(boolean is_group) {
        this.is_group = is_group;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", request_type_id=" + request_type_id +
                ", request_status_id=" + request_status_id +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", visit_purpose_id=" + visit_purpose_id +
                ", employee_id=" + employee_id +
                ", group_id=" + group_id +
                ", visitor_id=" + visitor_id +
                ", is_group=" + is_group +
                ", message='" + message + '\'' +
                '}';
    }
}

package org.example.data.model;

public class GroupsUsers {
    private Integer id;
    private Integer group_id;
    private Integer visitor_id;

    public GroupsUsers() {
    }

    public GroupsUsers(Integer id, Integer group_id, Integer visitor_id) {
        this.id = id;
        this.group_id = group_id;
        this.visitor_id = visitor_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "GroupsUsers{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", visitor_id=" + visitor_id +
                '}';
    }
}

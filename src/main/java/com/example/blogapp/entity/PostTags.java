package com.example.blogapp.entity;


import javax.persistence.*;

@Entity
@Table(name = "post_tags")
public class PostTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "tag_id")
    public int tag_id;

    @Column(name = "created_at")
    public String created_at;

    @Column(name = "updated_at")
    public String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public PostTags(int id, int tag_id, String created_at, String updated_at) {
        this.id = id;
        this.tag_id = tag_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public PostTags(int tag_id, String created_at, String updated_at) {
        this.tag_id = tag_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public PostTags() {
    }

    @Override
    public String toString() {
        return "PostTags{" +
                "id=" + id +
                ", tag_id=" + tag_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}

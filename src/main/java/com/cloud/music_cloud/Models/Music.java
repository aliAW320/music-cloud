package com.cloud.music_cloud.Models;

import jakarta.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String stored_name;
    @ManyToOne
    private Users user;

    public Music(long id, String title, String context, Users user) {
        this.id = id;
        this.title = title;
        stored_name = context;
        this.user = user;
    }

    public Music(String title, String stored_name, Users user) {
        this.title = title;
        this.stored_name = stored_name;
        this.user = user;
    }

    public Music() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStored_name() {
        return stored_name;
    }

    public void setStored_name(String stored_name) {
        this.stored_name = stored_name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

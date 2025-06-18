package com.cloud.music_cloud.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    private Users owner;
    @OneToMany
    private List<Music> music;
    private boolean privatePlaylist;

    public PlayList() {
    }

    public PlayList(long id, String name, Users owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
    public PlayList(long id, String name, Users owner, List<Music> music) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.music = music;
    }

    public PlayList(long id, String name, Users owner, List<Music> music, boolean privatePlaylist) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.music = music;
        this.privatePlaylist = privatePlaylist;
    }

    public PlayList(String name, Users owner, List<Music> music, boolean privatePlaylist) {
        this.name = name;
        this.owner = owner;
        this.music = music;
        this.privatePlaylist = privatePlaylist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }



    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }

    public boolean isPrivatePlaylist() {
        return privatePlaylist;
    }

    public void setPrivatePlaylist(boolean privatePlaylist) {
        this.privatePlaylist = privatePlaylist;
    }
}

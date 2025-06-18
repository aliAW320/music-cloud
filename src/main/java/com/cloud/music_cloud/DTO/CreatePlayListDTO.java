package com.cloud.music_cloud.DTO;

import java.util.ArrayList;

public class CreatePlayListDTO {
    private String name;
    private ArrayList<Long> musicIds;
    private boolean privatePlaylist;

    public CreatePlayListDTO(String name, ArrayList<Long> musicIds, boolean privatePlaylist) {
        this.name = name;
        this.musicIds = musicIds;
        this.privatePlaylist = privatePlaylist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getMusicIds() {
        return musicIds;
    }

    public void setMusicIds(ArrayList<Long> musicIds) {
        this.musicIds = musicIds;
    }

    public boolean isPrivatePlaylist() {
        return privatePlaylist;
    }

    public void setPrivatePlaylist(boolean privatePlaylist) {
        this.privatePlaylist = privatePlaylist;
    }
}

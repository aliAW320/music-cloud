package com.cloud.music_cloud.DTO;

public class UploadDTO {
    private String title;
    private String Context ;

    public UploadDTO(String fileName, String context) {
        this.title = fileName;
        Context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }
}

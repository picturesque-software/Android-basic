package com.example.simpletito;

import android.net.Uri;

public class videoItem {
    private String id;
    private String nickname;
    private String description;
    private Uri thumbnailRes;
    private Uri videoResource;

    public videoItem(String id,String nickname,String description, Uri thumbnailRes,Uri videoResource){
        this.id=id;
        this.nickname=nickname;
        this.description=description;
        this.thumbnailRes=thumbnailRes;
        this.videoResource=videoResource;

    }

    public Uri getThumbnailRes() {
        return thumbnailRes;
    }

    public Uri getVideoResource() {
        return videoResource;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}

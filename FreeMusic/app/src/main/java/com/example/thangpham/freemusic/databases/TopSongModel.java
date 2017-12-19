package com.example.thangpham.freemusic.databases;

/**
 * Created by ThangPham on 11/25/2017.
 */

public class TopSongModel {
    public String song;
    public String singer;
    public String smallImage;
    public String url;
    public String largeImage;

    public String getSong() {
        return song;
    }

    public String getSinger() {
        return singer;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }
}

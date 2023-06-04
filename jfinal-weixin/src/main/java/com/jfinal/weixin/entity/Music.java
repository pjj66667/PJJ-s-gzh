package com.jfinal.weixin.entity;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-01 9:55
 */
public class Music {
    private String rid;
    private String musicName;
    private String playUrl;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @Override
    public String toString() {
        return "Music{" +
            "rid='" + rid + '\'' +
            ", musicName='" + musicName + '\'' +
            ", playUrl='" + playUrl + '\'' +
            '}';
    }
}

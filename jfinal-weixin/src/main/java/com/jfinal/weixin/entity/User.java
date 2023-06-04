package com.jfinal.weixin.entity;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-01 16:27
 */
public class User {
    private int musicPage;
    private String userId;
    private String searchName;


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getMusicPage() {
        return musicPage;
    }

    public void setMusicPage(int musicPage) {
        this.musicPage = musicPage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

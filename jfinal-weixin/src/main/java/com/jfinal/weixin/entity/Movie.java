package com.jfinal.weixin.entity;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-03 14:07
 */
public class Movie {
    private String vodId;
    private String vodName;
    private String vodUrl;

    public Movie(String vodId, String vodName, String vodUrl) {
        this.vodId = vodId;
        this.vodName = vodName;
        this.vodUrl = vodUrl;
    }

    public Movie() {
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName;
    }

    public String getVodUrl() {
        return vodUrl;
    }

    public void setVodUrl(String vodUrl) {
        this.vodUrl = vodUrl;
    }
}

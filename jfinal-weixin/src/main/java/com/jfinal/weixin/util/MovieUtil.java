package com.jfinal.weixin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.entity.Movie;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-03 13:46
 */
public class MovieUtil {

    public static String searchMovie(String MovieName) {
        String movieStr="电影列表：\n";
        try {
            int page = 1;
            int pageCont = 0;
            do {
                //拼接搜索电影连接
                String searchUrl = "http://api.tiankongapi.com/api.php/provide/vod/?ac=list&wd=" + MovieName + "&pg=" + page;
                String MovieData = Jsoup.connect(searchUrl).ignoreContentType(true).get().body().text();
                JSONObject movieJsonObject = JSONObject.parseObject(MovieData);

                //获取总页数
                pageCont = movieJsonObject.getInteger("pagecount");

                //没有找到此电影
                if(pageCont == 0){
                    return "-1";
                }

                JSONArray movieJsonArray = movieJsonObject.getJSONArray("list");
                for (int i = 0; i < movieJsonArray.size(); i++) {
                    JSONObject jsonObject = movieJsonArray.getJSONObject(i);

                    //拼接返回给用户的信息
                    movieStr += jsonObject.getString("vod_id") + ",\t" +jsonObject.getString("vod_name") + "\n";
                }
                page++;
            } while (page <= pageCont);


        } catch (Exception e) {
            e.printStackTrace();
        }

        movieStr += "以'!' + 电影前的数字可以播放电影";
        return movieStr;
    }




    //电影详情
    public static Movie movieDetail(String vodId){
        Movie movie = new Movie();

        try {
            String movieUrl = "http://api.tiankongapi.com/api.php/provide/vod/?ac=detail&ids=" + vodId;
            String movieDetailData = Jsoup.connect(movieUrl).ignoreContentType(true).get().body().text();

            JSONObject movieJsonObject = JSONObject.parseObject(movieDetailData);
            int total = movieJsonObject.getInteger("total");

            if (total > 0){
                JSONObject movieList = movieJsonObject.getJSONArray("list").getJSONObject(0);

                movie = new Movie(movieList.getString("vod_id"),movieList.getString("vod_name"),movieList.getString("vod_play_url"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }
}

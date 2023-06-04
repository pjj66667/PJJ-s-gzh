package com.jfinal.weixin.controller;

import com.jfinal.core.Controller;
import com.jfinal.weixin.entity.Movie;
import com.jfinal.weixin.util.MovieUtil;

import java.util.ArrayList;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-03 17:27
 */
public class MovieController extends Controller {


    public String index(){

        Movie movie = MovieUtil.movieDetail(this.getPara("vodId"));
        //解析集数
        String moviePlayUrl = movie.getVodUrl();

        String[] moviePlayUrls = moviePlayUrl.split("\\$\\$\\$");

        String[] n_play = moviePlayUrls[1].split("#");

        ArrayList list = new ArrayList();

        for (int i = 0; i < n_play.length; i++) {
            String nn = n_play[i].split("\\$")[1];
            list.add(nn);
        }

        this.setAttr("movieList", list);
        this.setAttr("firstMoviePlayUrl", list.get(0));
        return "index";
    }
}

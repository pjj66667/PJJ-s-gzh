package com.jfinal.weixin;

import com.jfinal.weixin.entity.Music;
import com.jfinal.weixin.mapper.MusicMapper;
import com.jfinal.weixin.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.util.List;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-01 10:54
 */
public class test {

    @Test
    public void test(){

        String rid = "123123123123123123123";
        try{
            String searchUrl = "https://www.kuwo.cn/api/v1/www/music/playUrl?mid=" + rid +"&type=MP3&httpsStatus=1&reqId=72012d61-ff5b-11ed-aeab-7942446cc2efhttps://www.kuwo.cn/api/v1/www/music/playUrl?mid=15212054&type=music&httpsStatus=1&reqId=72012d61-ff5b-11ed-aeab-7942446cc2ef";
            String str = Jsoup.connect(searchUrl).ignoreContentType(true).get().body().text();
            System.out.println("str = " + str);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

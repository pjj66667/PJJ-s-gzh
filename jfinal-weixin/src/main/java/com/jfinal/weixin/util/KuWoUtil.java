package com.jfinal.weixin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.json.Json;
import com.jfinal.weixin.entity.Music;
import com.jfinal.weixin.entity.User;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import org.jsoup.Jsoup;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author PJJ
 * @description ：
 * @create 2023-05-31 20:27
 */
public class KuWoUtil {

    public static ArrayList searchMusicBySearchName(String searchName,int musicPage){

        ArrayList arrayList = new ArrayList();


        try{
            //将用户传入的数据进行url编码
            searchName = URLEncoder.encode(searchName, "UTF-8");
            System.out.println("searchName = " + searchName);
            //拼接key值
            String searchUrl = "https://www.kuwo.cn/api/www/search/searchMusicBykeyWord?key=" + searchName +"&pn="+ musicPage +"&rn=20&httpsStatus=1&reqId=6a818b90-ffbd-11ed-a593-1171deb9d176";
            String str = Jsoup.connect(searchUrl)
                .cookie("kw_token", "PJJ66667")
                .header("csrf", "PJJ66667")
                .referrer("https://www.kuwo.cn/")
                .ignoreContentType(true)
                .get().body().text();

            System.out.println("str=" + str);


            //获取json中的data数据
            JSONObject jsonObjectData = JSONObject.parseObject(str).getJSONObject("data");
            System.out.println(jsonObjectData);
            //从data数据中获取total
            int total = jsonObjectData.getInteger("total");
            //从data数据中获取歌曲集合
            JSONArray jsonMusicArray = jsonObjectData.getJSONArray("list");

            for (int i = 0; i < jsonMusicArray.size(); i++) {
                JSONObject jsonMusic = jsonMusicArray.getJSONObject(i);
                Music music = new Music();
                music.setMusicName(jsonMusic.getString("name"));
                music.setRid(jsonMusic.getString("rid"));
                arrayList.add(music);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return arrayList;
    }


    public static String getMP3Url(String rid){
        String MP3Url="";

        //{"code":200,"msg":"success","reqId":"a7bb94629b4b4a4358c2f9098915dd7a","data":{"url":"https://other-web-ra01-sycdn.kuwo.cn/d9e3050d2ab412ebc15b7a6f855a86f1/647847fa/resource/n1/320/90/1/1788494289.mp3"},"profileId":"site","curTime":1685604497794,"success":true}
        //{"code":-1,"msg":"获取数据异常","reqId":"91380e153d09c9935500e437197ce5ae","profileId":"site","curTime":1685604663079,"success":false}
        try{
            String searchUrl = "https://www.kuwo.cn/api/v1/www/music/playUrl?mid=" + rid +"&type=MP3&httpsStatus=1&reqId=72012d61-ff5b-11ed-aeab-7942446cc2efhttps://www.kuwo.cn/api/v1/www/music/playUrl?mid=15212054&type=music&httpsStatus=1&reqId=72012d61-ff5b-11ed-aeab-7942446cc2ef";
            String str = Jsoup.connect(searchUrl).ignoreContentType(true).get().body().text();

            //转换为json
            JSONObject jsonObject = JSONObject.parseObject(str);
            if (jsonObject.getInteger("code") != -1){
                //从返回的json中获取歌曲的url
                MP3Url = jsonObject.getJSONObject("data").getString("url");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return MP3Url;
    }




    public static String searchMusicByPage(String searchName,int searchPage){

        ArrayList<Music> arrayList = searchMusicBySearchName(searchName,searchPage);

        //拼接返回给用户的文本
        String musicStr = "";
        for (int i = 0; i < arrayList.size(); i++) {
            //获取单个歌曲信息
            Music music = arrayList.get(i);
            //拼接歌曲rid和歌曲名
            musicStr += music.getRid() + ",\t" + music.getMusicName() + "\n";
        }

        return musicStr;
    }


    public static String handlePage(String musicStr, int musicPage){

        if (musicPage > 1){
            musicStr += "@! 代表上一页\t@# 代表下一页";
        }else if (musicPage == 1){
            musicStr += "@# 代表下一页";
        }
        return musicStr;
    }


    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}

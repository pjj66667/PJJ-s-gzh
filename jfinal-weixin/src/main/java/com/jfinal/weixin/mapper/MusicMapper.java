package com.jfinal.weixin.mapper;

import com.jfinal.core.paragetter.Para;
import com.jfinal.weixin.entity.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PJJ
 * @description ：
 * @create 2023-06-01 10:45
 */
public interface MusicMapper {

    List<Music> getMusicBySingerAndMusicName(@Param("singer") String singer,@Param("musicName") String MusicName);
}

package com.cloud.music_cloud.Service;

import com.cloud.music_cloud.DAOS.MusicDAO;
import com.cloud.music_cloud.DAOS.PlayListDAO;
import com.cloud.music_cloud.DTO.CreatePlayListDTO;
import com.cloud.music_cloud.Models.Music;
import com.cloud.music_cloud.Models.PlayList;
import com.cloud.music_cloud.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {
    @Autowired
    PlayListDAO playListDAO;
    @Autowired
    private MusicDAO musicDAO;

    public PlayList getPlayList(long id, Users user) {
        PlayList playList = playListDAO.findById(id);
        if ( playList == null  ||  (playList.getOwner() != user && playList.isPrivatePlaylist()) ) {
            return null;
        }
        return playList;
    }

    public PlayList createPlayList(CreatePlayListDTO createPlayListDTO, Users user) {
        List<Music> musicList = musicDAO.findAllById(createPlayListDTO.getMusicIds());
        PlayList playList = new PlayList(createPlayListDTO.getName() ,user , musicList , createPlayListDTO.isPrivatePlaylist() );
        return playListDAO.save(playList);
    }
}

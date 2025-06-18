package com.cloud.music_cloud.Service;

import com.cloud.music_cloud.DAOS.MusicDAO;
import com.cloud.music_cloud.DTO.SearchDTO;
import com.cloud.music_cloud.DTO.UploadDTO;
import com.cloud.music_cloud.Models.Music;
import com.cloud.music_cloud.Models.Users;
import com.cloud.music_cloud.Tools.UploadTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class MusicService {

    @Autowired
    private MusicDAO musicDAO;
    public ResponseEntity<?> upload(Users user, ArrayList<UploadDTO> uploadDTOS) {
        Music music ;
        for (UploadDTO uploadDTO : uploadDTOS) {
            try {
                music = new Music(uploadDTO.getTitle() , UploadTools.base64ToMp3(uploadDTO.getContext()) , user);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
            musicDAO.save(music);
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> Search(String musicName, Users user) {
        ArrayList<Music> musics = (ArrayList<Music>) musicDAO.findByTitleContains(musicName);
        ArrayList<SearchDTO> searchDTOS = new ArrayList<>();
        for (Music music : musics) {
            searchDTOS.add(new SearchDTO(music.getId() , music.getTitle()));
        }
        return ResponseEntity.ok().body(searchDTOS);


    }
}

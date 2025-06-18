package com.cloud.music_cloud.Controllers;

import com.cloud.music_cloud.DAOS.MusicDAO;
import com.cloud.music_cloud.DTO.UploadDTO;
import com.cloud.music_cloud.Models.Music;
import com.cloud.music_cloud.Models.Users;
import com.cloud.music_cloud.Service.MusicService;
import com.cloud.music_cloud.Tools.UploadTools;
import com.cloud.music_cloud.Tools.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class MusicController {

    @Autowired
    MusicDAO musicDAO;
        @Autowired
        private final UserExtractor userExtractor;
        @Autowired
        private MusicService musicService;


        public MusicController(UserExtractor userExtractor) {
            this.userExtractor = userExtractor;
        }

        @PostMapping("/upload")
        public ResponseEntity<?> upload(@RequestBody ArrayList<UploadDTO> uploadDTOS, Principal principal) throws IOException {
            Users user = userExtractor.extract(principal);
            return musicService.upload(user , uploadDTOS);
        }


    @GetMapping("/download")
    public ResponseEntity<?> getAllMusics(Principal principal) throws IOException {
        Users user = userExtractor.extract(principal);
        ArrayList<Music> musics = (ArrayList<Music>) musicDAO.findByUser_id(user.getId());
        ArrayList<UploadDTO> uploadDTOS = new ArrayList<>();
        for (Music music : musics) {
            uploadDTOS.add(new UploadDTO(music.getTitle(), UploadTools.mp3ToBase64FromMusicFiles(music.getStored_name())));
        }
        return ResponseEntity.ok().body(uploadDTOS);
    }
    @GetMapping("/search/{music_name}")
    public ResponseEntity<?> search(@PathVariable String music_name, Principal principal) {
            Users user = userExtractor.extract(principal);
            return musicService.Search(music_name, user);
    }



}



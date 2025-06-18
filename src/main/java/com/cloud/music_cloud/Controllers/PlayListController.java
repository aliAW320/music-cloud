package com.cloud.music_cloud.Controllers;

import com.cloud.music_cloud.DTO.CreatePlayListDTO;
import com.cloud.music_cloud.Models.PlayList;
import com.cloud.music_cloud.Models.Users;
import com.cloud.music_cloud.Service.PlayListService;
import com.cloud.music_cloud.Tools.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/playList")
public class PlayListController {
    @Autowired
    private UserExtractor userExtractor;
    @Autowired
    PlayListService playListService;
    @GetMapping("/getPlayList/{id}")
    public PlayList getPlayList(@PathVariable long id , Principal principal) {
        Users user = userExtractor.extract(principal);
        return playListService.getPlayList(id , user);

    }
    @PostMapping("/creatPlayList")
    public PlayList createPlayList(@RequestBody CreatePlayListDTO createPlayListDTO,  Principal principal) {
        Users user = userExtractor.extract(principal);
        return playListService.createPlayList( createPlayListDTO ,user);

    }



}

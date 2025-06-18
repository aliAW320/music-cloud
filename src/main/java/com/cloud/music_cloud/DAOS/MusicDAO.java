package com.cloud.music_cloud.DAOS;

import com.cloud.music_cloud.Models.Music;
import com.cloud.music_cloud.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicDAO extends JpaRepository<Music, Long> {
    List<Music> findByUser_id(Long user_id);
    List<Music> findByTitleContains(String title);

}

package com.cloud.music_cloud.DAOS;

import com.cloud.music_cloud.Models.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListDAO extends JpaRepository<PlayList, Long> {
    PlayList findById(long id);
    PlayList findByName(String name);

}

package com.cloud.music_cloud.DAOS;

import com.cloud.music_cloud.DTO.PlayListSearchDTO;
import com.cloud.music_cloud.Models.PlayList;
import com.cloud.music_cloud.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListDAO extends JpaRepository<PlayList, Long> {
    PlayList findById(long id);
    PlayList findByName(String name);

    @Query("SELECT new com.cloud.music_cloud.DTO.PlayListSearchDTO(p.id, p.name) " +
            "FROM PlayList p " +
            "WHERE p.name LIKE :searchName AND (p.owner.id = :owner OR p.privatePlaylist = false)")
    List<PlayListSearchDTO> search(@Param("owner") long owner, @Param("searchName") String searchName);

}

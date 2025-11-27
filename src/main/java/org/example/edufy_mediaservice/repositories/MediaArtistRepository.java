package org.example.edufy_mediaservice.repositories;

import org.example.edufy_mediaservice.entities.MediaArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaArtistRepository extends JpaRepository<MediaArtist, Long> {
    List<MediaArtist> findAllByArtistId(Long id);

    List<MediaArtist> findAllByMediaId(Long mediaId);
}

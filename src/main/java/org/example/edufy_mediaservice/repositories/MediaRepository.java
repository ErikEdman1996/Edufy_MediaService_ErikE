package org.example.edufy_mediaservice.repositories;

import org.example.edufy_mediaservice.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>
{
    List<Media> findByType(Media.MediaType type);
}

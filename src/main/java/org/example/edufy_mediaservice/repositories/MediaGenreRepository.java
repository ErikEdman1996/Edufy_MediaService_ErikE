package org.example.edufy_mediaservice.repositories;

import org.example.edufy_mediaservice.entities.MediaGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaGenreRepository extends JpaRepository<MediaGenre, Long>
{

}

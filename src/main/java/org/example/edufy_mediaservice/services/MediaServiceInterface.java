package org.example.edufy_mediaservice.services;

import org.example.edufy_mediaservice.dtos.MediaArtistDTO;
import org.example.edufy_mediaservice.dtos.MediaDetailsDTO;
import org.example.edufy_mediaservice.dtos.MediaGenreDTO;
import org.example.edufy_mediaservice.entities.Media;
import org.example.edufy_mediaservice.entities.MediaArtist;
import org.example.edufy_mediaservice.entities.MediaGenre;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface MediaServiceInterface {
    Media getMedia(Long id);

    Media addMedia(Media media, Jwt jwt);

    Media updateMedia(Long id, Media media);

    void deleteMedia(Long id);

    List<Media> getAllMediaByType(Media.MediaType mediaType);

    List<MediaGenreDTO> getAllMediaByGenre(String name, Jwt jwt);

    List<MediaArtistDTO> getAllMediaByArtist(String name, Jwt jwt);

    List<MediaArtist> getAllMediaArtist();

    List<MediaGenre> getAllMediaGenre();

    MediaDetailsDTO getMediaDetails(Long id, Jwt jwt);
}

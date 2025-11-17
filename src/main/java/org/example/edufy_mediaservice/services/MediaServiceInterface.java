package org.example.edufy_mediaservice.services;

import org.example.edufy_mediaservice.entities.Media;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface MediaServiceInterface
{
    Media getMedia(Long id);
    Media addMedia(Media media, Jwt jwt);
    Media updateMedia(Long id, Media media);
    void deleteMedia(Long id);
    List<Media> getAllMediaByType(Media.MediaType mediaType);
}

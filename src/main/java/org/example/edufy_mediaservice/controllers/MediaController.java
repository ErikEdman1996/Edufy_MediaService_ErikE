package org.example.edufy_mediaservice.controllers;

import jakarta.websocket.server.PathParam;
import org.example.edufy_mediaservice.dtos.MediaArtistDTO;
import org.example.edufy_mediaservice.dtos.MediaDetailsDTO;
import org.example.edufy_mediaservice.dtos.MediaGenreDTO;
import org.example.edufy_mediaservice.entities.Media;
import org.example.edufy_mediaservice.entities.MediaArtist;
import org.example.edufy_mediaservice.entities.MediaGenre;
import org.example.edufy_mediaservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edufy/v1/media")
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<Media> addMedia(@RequestBody Media media, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(mediaService.addMedia(media, jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMedia(@PathVariable Long id) {
        Media media = mediaService.getMedia(id);

        return ResponseEntity.ok(media);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<MediaDetailsDTO> getMediaDetails(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        MediaDetailsDTO mediaDetails = mediaService.getMediaDetails(id, jwt);
        return ResponseEntity.ok(mediaDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> updateMedia(@PathVariable Long id, @RequestBody Media media) {
        Media updatedMedia = mediaService.updateMedia(id, media);

        return ResponseEntity.ok(updatedMedia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<Media>> getAllMediaByType(@RequestParam("type") Media.MediaType type) {
        List<Media> mediaList = mediaService.getAllMediaByType(type);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<MediaGenreDTO>> getAllMediaByGenre(@RequestParam("genre") String name,
            @AuthenticationPrincipal Jwt jwt) {
        List<MediaGenreDTO> mediaList = mediaService.getAllMediaByGenre(name, jwt);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/by-artist")
    public ResponseEntity<List<MediaArtistDTO>> getAllMediaByArtist(@RequestParam("artist") String name,
            @AuthenticationPrincipal Jwt jwt) {

        System.out.println("Getting all media by artist: " + name);

        List<MediaArtistDTO> mediaList = mediaService.getAllMediaByArtist(name, jwt);

        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/allMediaArtist")
    public ResponseEntity<List<MediaArtist>> getAllMediaArtists() {
        List<MediaArtist> list = mediaService.getAllMediaArtist();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/allMediaGenre")
    public ResponseEntity<List<MediaGenre>> getAllMediaGenre() {
        List<MediaGenre> list = mediaService.getAllMediaGenre();

        return ResponseEntity.ok(list);
    }
}

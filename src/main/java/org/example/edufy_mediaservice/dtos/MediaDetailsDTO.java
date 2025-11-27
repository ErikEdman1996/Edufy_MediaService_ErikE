package org.example.edufy_mediaservice.dtos;

import org.example.edufy_mediaservice.entities.Media;

import java.util.List;

public class MediaDetailsDTO {
    private Long id;
    private String title;
    private Long albumId;
    private Media.MediaType type;
    private String url;
    private List<ArtistFetchResponse> artists;
    private List<GenreFetchResponse> genres;

    public MediaDetailsDTO() {

    }

    public MediaDetailsDTO(Long id, String title, Long albumId, Media.MediaType type, String url,
            List<ArtistFetchResponse> artists, List<GenreFetchResponse> genres) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.type = type;
        this.url = url;
        this.artists = artists;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Media.MediaType getType() {
        return type;
    }

    public void setType(Media.MediaType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ArtistFetchResponse> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistFetchResponse> artists) {
        this.artists = artists;
    }

    public List<GenreFetchResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreFetchResponse> genres) {
        this.genres = genres;
    }
}

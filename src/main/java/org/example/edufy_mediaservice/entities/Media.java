package org.example.edufy_mediaservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Media
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = true)
    private Long album_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type;

    @Column(nullable = false, unique = true)
    private String url;

    @Transient
    private List<Long> genreIds;

    @Transient
    private List<Long> artistIds;

    public Media()
    {

    }

    public Media(Long id, String title, Long album_id, MediaType type, String url, List<Long> genreIds, List<Long> artistIds)
    {
        this.id = id;
        this.title = title;
        this.album_id = album_id;
        this.type = type;
        this.url = url;
        this.genreIds = genreIds;
        this.artistIds = artistIds;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Long getAlbum_id()
    {
        return album_id;
    }

    public void setAlbum_id(Long album_id)
    {
        this.album_id = album_id;
    }

    public MediaType getType()
    {
        return type;
    }

    public void setType(MediaType type)
    {
        this.type = type;
    }

    public String getURL()
    {
        return url;
    }

    public void setURL(String url)
    {
        this.url = url;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Long> getArtistIds()
    {
        return artistIds;
    }

    public void setArtistIds(List<Long> artistIds)
    {
        this.artistIds = artistIds;
    }

    public enum MediaType
    {
        SONG,
        VIDEO,
        PODCAST
    }
}

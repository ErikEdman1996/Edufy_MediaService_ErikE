package org.example.edufy_mediaservice.entities;

import jakarta.persistence.*;

@Entity
public class MediaGenre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "genre_id")
    private Long genreId;

    public MediaGenre()
    {

    }

    public MediaGenre(Long mediaId, Long genreId)
    {
        this.mediaId = mediaId;
        this.genreId = genreId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getMediaId()
    {
        return mediaId;
    }

    public void setMediaId(Long mediaId)
    {
        this.mediaId = mediaId;
    }

    public Long getGenreId()
    {
        return genreId;
    }

    public void setGenreId(Long genreId)
    {
        this.genreId = genreId;
    }
}

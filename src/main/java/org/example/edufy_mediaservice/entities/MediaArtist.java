package org.example.edufy_mediaservice.entities;

import jakarta.persistence.*;

@Entity
public class MediaArtist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "artist_id")
    private Long artistId;

    public MediaArtist()
    {

    }

    public MediaArtist(Long mediaId, Long artistId)
    {
        this.mediaId = mediaId;
        this.artistId = artistId;
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

    public Long getArtistId()
    {
        return artistId;
    }

    public void setArtistId(Long artistId)
    {
        this.artistId = artistId;
    }
}

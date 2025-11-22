package org.example.edufy_mediaservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaArtistDTO
{
    private String artistName;

    private String mediaTitle;

    public MediaArtistDTO()
    {

    }

    public MediaArtistDTO(String artistName, String mediaTitle)
    {
        this.artistName = artistName;
        this.mediaTitle = mediaTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }
}

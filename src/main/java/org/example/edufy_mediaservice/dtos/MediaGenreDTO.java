package org.example.edufy_mediaservice.dtos;

public class MediaGenreDTO
{
    private String mediaTitle;
    private String genreName;

    public MediaGenreDTO()
    {

    }

    public MediaGenreDTO(String mediaTitle, String genreName)
    {
        this.mediaTitle = mediaTitle;
        this.genreName = genreName;
    }

    public String getMediaTitle()
    {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle)
    {
        this.mediaTitle = mediaTitle;
    }

    public String getGenreName()
    {
        return genreName;
    }

    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }
}

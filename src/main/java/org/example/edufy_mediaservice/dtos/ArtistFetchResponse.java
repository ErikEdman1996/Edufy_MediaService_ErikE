package org.example.edufy_mediaservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistFetchResponse
{
    @JsonProperty
    public Long id;

    @JsonProperty
    public String name;
}

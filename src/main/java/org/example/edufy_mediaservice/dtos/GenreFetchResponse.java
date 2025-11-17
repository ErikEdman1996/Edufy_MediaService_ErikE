package org.example.edufy_mediaservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenreFetchResponse
{
    @JsonProperty
    public Long id;

    @JsonProperty
    public String name;
}

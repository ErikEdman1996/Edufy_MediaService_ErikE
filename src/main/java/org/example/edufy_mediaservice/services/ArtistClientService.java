package org.example.edufy_mediaservice.services;

import org.example.edufy_mediaservice.dtos.ArtistFetchResponse;
import org.example.edufy_mediaservice.dtos.GenreFetchResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistClientService
{
    private RestClient restClient;

    public ArtistClientService(RestClient.Builder clientBuilder)
    {
        restClient = clientBuilder
                .baseUrl("http://localhost:7777/edufy/v1/artist")
                .build();
    }

    boolean checkIfArtistExist(Long id, Jwt jwt)
    {
        try
        {
            ArtistFetchResponse response = restClient.get()
                    .uri("/by_id/"+id)
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(ArtistFetchResponse.class);

            return response != null;
        }
        catch(HttpClientErrorException.NotFound e)
        {
            return false;
        }
        catch(HttpClientErrorException.Unauthorized e)
        {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }

    ArtistFetchResponse getArtistByName(String name, Jwt jwt)
    {
        try
        {
            ArtistFetchResponse response = restClient.get()
                    .uri("/by_name/"+name)
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(ArtistFetchResponse.class);

            if(response != null)
            {
                return response;
            }

            return null;
        }
        catch(HttpClientErrorException.NotFound e)
        {
            return null;
        }
        catch(HttpClientErrorException.Unauthorized e)
        {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }

    List<ArtistFetchResponse> getAllArtists(Jwt jwt)
    {
        try
        {
            List<ArtistFetchResponse> response = restClient.get()
                    .uri("/all")
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ArtistFetchResponse>>(){});

            if(response != null)
            {
                return response;
            }

            return null;
        }
        catch(HttpClientErrorException.NotFound e)
        {
            return null;
        }
        catch(HttpClientErrorException.Unauthorized e)
        {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }
}

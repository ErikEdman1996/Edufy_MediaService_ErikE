package org.example.edufy_mediaservice.services;
import org.example.edufy_mediaservice.dtos.GenreFetchResponse;
import org.example.edufy_mediaservice.exceptions.UnauthorizedActionException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class GenreClientService
{
    private RestClient restClient;

    public GenreClientService(RestClient.Builder clientBuilder)
    {
        restClient = clientBuilder
                .baseUrl("http://localhost:8081/edufy/v1/genre")
                .build();
    }

    boolean checkIfGenreExist(Long id, Jwt jwt)
    {

        try
        {
            GenreFetchResponse response = restClient.get()
                    .uri("/"+id)
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(GenreFetchResponse.class);

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
}

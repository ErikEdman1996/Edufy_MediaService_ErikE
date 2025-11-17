package org.example.edufy_mediaservice.services;
import org.example.edufy_mediaservice.dtos.GenreFetchResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
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
        boolean exist = false;

        String uri = "/" + id;

        GenreFetchResponse response = restClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + jwt.getTokenValue())
                .retrieve()
                .body(GenreFetchResponse.class);

        if(response != null)
        {
            exist = true;
        }

        return exist;
    }
}

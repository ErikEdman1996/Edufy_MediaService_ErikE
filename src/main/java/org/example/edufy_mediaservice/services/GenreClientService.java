package org.example.edufy_mediaservice.services;

import org.example.edufy_mediaservice.dtos.ArtistFetchResponse;
import org.example.edufy_mediaservice.dtos.GenreFetchResponse;
import org.example.edufy_mediaservice.exceptions.UnauthorizedActionException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GenreClientService {
    private RestClient restClient;

    public GenreClientService(RestClient.Builder clientBuilder) {
        restClient = clientBuilder
                .baseUrl("http://localhost:8081/edufy/v1/genre")
                .build();
    }

    GenreFetchResponse getGenreById(Long id, Jwt jwt) {
        try {
            GenreFetchResponse response = restClient.get()
                    .uri("/" + id)
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(GenreFetchResponse.class);

            return response;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }

    boolean checkIfGenreExist(Long id, Jwt jwt) {
        return getGenreById(id, jwt) != null;
    }

    GenreFetchResponse getGenreByName(String name, Jwt jwt) {
        try {
            GenreFetchResponse response = restClient.get()
                    .uri("/by-name/" + name)
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(GenreFetchResponse.class);

            if (response != null) {
                return response;
            }

            return null;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }

    List<GenreFetchResponse> getAllGenres(Jwt jwt) {
        try {
            List<GenreFetchResponse> response = restClient.get()
                    .uri("/all")
                    .header("Authorization", "Bearer " + jwt.getTokenValue())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<GenreFetchResponse>>() {
                    });

            if (response != null) {
                return response;
            }

            return null;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new RuntimeException("Unauthorized when calling GenreService", e);
        }
    }
}

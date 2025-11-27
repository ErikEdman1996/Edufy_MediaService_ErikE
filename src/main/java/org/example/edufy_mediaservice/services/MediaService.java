package org.example.edufy_mediaservice.services;

import org.example.edufy_mediaservice.dtos.ArtistFetchResponse;
import org.example.edufy_mediaservice.dtos.GenreFetchResponse;
import org.example.edufy_mediaservice.dtos.MediaArtistDTO;
import org.example.edufy_mediaservice.dtos.MediaGenreDTO;
import org.example.edufy_mediaservice.entities.Media;
import org.example.edufy_mediaservice.entities.MediaArtist;
import org.example.edufy_mediaservice.entities.MediaGenre;
import org.example.edufy_mediaservice.exceptions.ResourceNotFoundException;
import org.example.edufy_mediaservice.exceptions.UnauthorizedActionException;
import org.example.edufy_mediaservice.repositories.MediaArtistRepository;
import org.example.edufy_mediaservice.repositories.MediaGenreRepository;
import org.example.edufy_mediaservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements MediaServiceInterface
{
    private final MediaRepository mediaRepository;
    private final MediaGenreRepository mediaGenreRepository;
    private final MediaArtistRepository mediaArtistRepository;

    private final GenreClientService genreClientService;
    private final ArtistClientService artistClientService;

    @Autowired
    public MediaService(final MediaRepository mediaRepository, final MediaGenreRepository mediaGenreRepository,
                        final MediaArtistRepository mediaArtistRepository, final GenreClientService genreClientService,
                        final ArtistClientService artistClientService)
    {
        this.mediaRepository = mediaRepository;
        this.mediaGenreRepository = mediaGenreRepository;
        this.mediaArtistRepository = mediaArtistRepository;
        this.genreClientService = genreClientService;
        this.artistClientService = artistClientService;
    }

    @Override
    public Media getMedia(Long id)
    {
        Optional<Media> media = mediaRepository.findById(id);

        if(!media.isPresent())
        {
            throw new ResourceNotFoundException("Media", "id", id);
        }

        return media.get();
    }

    @Override
    public Media addMedia(Media media, Jwt jwt)
    {
        //Check if Media already exist
        if(mediaRepository.findByUrl(media.getURL()).isPresent())
        {
            throw new RuntimeException("Media already exists");
        }

        Media savedMedia = mediaRepository.save(media);

        for(Long genreId : media.getGenreIds())
        {
            if(genreClientService.checkIfGenreExist(genreId, jwt))
            {
                System.out.println("Genre exist!");
                mediaGenreRepository.save(new MediaGenre(savedMedia.getId(), genreId));
            }
            else
            {
                throw new ResourceNotFoundException("Genre", "id", genreId);
            }
        }

        for(Long artistId : media.getArtistIds())
        {
            if(artistClientService.checkIfArtistExist(artistId, jwt))
            {
                System.out.println("Artist exist!");
                mediaArtistRepository.save(new MediaArtist(savedMedia.getId(), artistId));
            }
            else
            {
                throw new ResourceNotFoundException("Artist", "id", artistId);
            }
        }

        return savedMedia;
    }

    @Override
    public Media updateMedia(Long id, Media media)
    {
        Optional<Media> mediaToUpdate = mediaRepository.findById(id);

        if(!mediaToUpdate.isPresent())
        {
            throw new ResourceNotFoundException("Media", "id", id);
        }

        if(!mediaToUpdate.get().getId().equals(id))
        {
            throw new UnauthorizedActionException("Unauthorized");
        }

        return mediaRepository.save(media);
    }

    @Override
    public void deleteMedia(Long id)
    {
        Optional<Media> mediaToDelete = mediaRepository.findById(id);

        if(!mediaToDelete.isPresent())
        {
            throw new ResourceNotFoundException("Media", "id", id);
        }

        mediaRepository.delete(mediaToDelete.get());
    }

    @Override
    public List<Media> getAllMediaByType(Media.MediaType mediaType)
    {
        return mediaRepository.findByType(mediaType);
    }

    @Override
    public List<MediaGenreDTO> getAllMediaByGenre(String name, Jwt jwt)
    {
        //1. Få genre från GenreService via namnet
        GenreFetchResponse dto = genreClientService.getGenreByName(name, jwt);

        //2. Hämta en lista med alla MediaGenre som har samma genreId som genre-objektet
        List<MediaGenre> mediaGenres = mediaGenreRepository.findAllByGenreId(dto.id);

        //3. Skapa en lista av all Media från alla MediaGenre-objekt
        List<Media> mediaList = new ArrayList<>();

        for(MediaGenre mediaGenre : mediaGenres)
        {
            Media media = mediaRepository.findById(mediaGenre.getMediaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaGenre.getMediaId()));
            mediaList.add(media);
        }

        //4. Skapa en lista av DTOs som bara visar media_titel och genre_namn, returnera den listan
        List<MediaGenreDTO> dtoList = new ArrayList<>();

        for(Media media : mediaList)
        {
            MediaGenreDTO newDTO = new MediaGenreDTO(media.getTitle(), dto.name);
            dtoList.add(newDTO);
        }

        return dtoList;
    }

    @Override
    public List<MediaArtistDTO> getAllMediaByArtist(String name, Jwt jwt)
    {
        ArtistFetchResponse dto = artistClientService.getArtistByName(name, jwt);

        System.out.println(name);
        System.out.println(dto.name);
        System.out.println(dto.id);

        List<MediaArtist> mediaArtists = mediaArtistRepository.findAllByArtistId(dto.id);

        List<Media> mediaList = new ArrayList<>();

        for(MediaArtist mediaArtist : mediaArtists)
        {
            Media media = mediaRepository.findById(mediaArtist.getMediaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaArtist.getMediaId()));
            mediaList.add(media);
        }

        List<MediaArtistDTO> dtoList = new ArrayList<>();

        for(Media media : mediaList)
        {
            MediaArtistDTO newDTO = new MediaArtistDTO(name, media.getTitle());
            dtoList.add(newDTO);
        }

        return dtoList;
    }

    @Override
    public List<MediaArtist> getAllMediaArtist()
    {
        return mediaArtistRepository.findAll();
    }

    @Override
    public List<MediaGenre> getAllMediaGenre()
    {
        return mediaGenreRepository.findAll();
    }


}

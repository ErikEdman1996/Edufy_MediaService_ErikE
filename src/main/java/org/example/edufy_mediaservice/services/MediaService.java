package org.example.edufy_mediaservice.services;

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

import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements MediaServiceInterface
{
    private final MediaRepository mediaRepository;
    private final MediaGenreRepository mediaGenreRepository;
    private final MediaArtistRepository mediaArtistRepository;

    private final GenreClientService genreClientService;

    @Autowired
    public MediaService(final MediaRepository mediaRepository, final MediaGenreRepository mediaGenreRepository,
                        final MediaArtistRepository mediaArtistRepository, final GenreClientService genreClientService)
    {
        this.mediaRepository = mediaRepository;
        this.mediaGenreRepository = mediaGenreRepository;
        this.mediaArtistRepository = mediaArtistRepository;
        this.genreClientService = genreClientService;
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
            mediaArtistRepository.save(new MediaArtist(savedMedia.getId(), artistId));
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
}

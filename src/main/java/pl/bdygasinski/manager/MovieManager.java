package pl.bdygasinski.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pl.bdygasinski.dto.MovieInputDTO;
import pl.bdygasinski.dto.MovieOutputDTO;
import pl.bdygasinski.dto.MovieUpdateDTO;
import pl.bdygasinski.exception.manager.MovieNotFoundManagerException;
import pl.bdygasinski.exception.repository.EntityNotFoundException;
import pl.bdygasinski.model.Movie;
import pl.bdygasinski.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieManager {
    @Inject
    private MovieRepository repository;

    public MovieOutputDTO createMovie(MovieInputDTO dto) {
        Movie movie = dto.createMovie();
        repository.add(movie);
        return new MovieOutputDTO(movie);
    }

    public void deleteMovieById(Long id) throws MovieNotFoundManagerException {
        try {
            Movie movie = repository.findById(id);
            repository.delete(movie);

        } catch (EntityNotFoundException e) {
            throw new MovieNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public MovieOutputDTO findMovieById(Long id) throws MovieNotFoundManagerException {
        try {
            Movie movie = repository.findById(id);

            return new MovieOutputDTO(movie);
        } catch (EntityNotFoundException e) {
            throw new MovieNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public MovieOutputDTO updateMovie(Long id, MovieUpdateDTO dto) throws MovieNotFoundManagerException {
        try {
            Movie movie = repository.findById(id);
            movie = dto.updateMovie(movie);
            return new MovieOutputDTO(repository.update(movie));

        } catch (EntityNotFoundException e) {
            throw new MovieNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public List<MovieOutputDTO> getAllMovies() {
        return repository.getAll().stream()
                .map(MovieOutputDTO::new)
                .collect(Collectors.toList());
    }
}

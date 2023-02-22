package pl.bdygasinski.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.mapper.MovieGenreMapper;
import pl.bdygasinski.model.Movie;
import pl.bdygasinski.model.submodel.MovieGenre;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MovieUpdateDTO {
    private String title;
    private String director;
    private String genre;
    @Min(value = 2, message = "Age restriction can't be less than 2")
    @Max(value = 18, message = "Age restriction can't be more than 18")
    private Integer ageRestriction;
    @Positive(message = "Seats for show must be positive")
    private Integer seatsForShow;
    @Positive(message = "Price for show must be positive")
    private Double priceForShow;

    public Movie updateMovie(Movie movie) {
        setValues(movie);
        return movie;
    }

    private void setValues(Movie movie) {
        updateTitle(movie);
        updateDirector(movie);
        updateGenre(movie);
        updateAgeRestriction(movie);
        updateSeatsForShow(movie);
        updatePriceforShow(movie);
    }

    private void updateGenre(Movie movie) {
        if (genre != null) {
            MovieGenre genre = MovieGenreMapper.convertGenre(this.genre);
            movie.setGenre(genre);
        }
    }

    private void updatePriceforShow(Movie movie) {
        if (priceForShow != null) {
            movie.setPriceForShow(priceForShow);
        }
    }

    private void updateSeatsForShow(Movie movie) {
        if (seatsForShow != null) {
            movie.setSeatsForShow(seatsForShow);
        }
    }

    private void updateAgeRestriction(Movie movie) {
        if (ageRestriction != null) {
            movie.setAgeRestriction(ageRestriction);
        }
    }

    private void updateDirector(Movie movie) {
        if (director != null) {
            movie.setDirector(director);
        }
    }

    private void updateTitle(Movie movie) {
        if (title != null) {
            movie.setTitle(title);
        }
    }


}

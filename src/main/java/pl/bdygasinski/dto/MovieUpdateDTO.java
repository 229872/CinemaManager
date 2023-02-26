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
import static pl.bdygasinski.util.Util.setIfNotNull;

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
        setIfNotNull(title, movie::setTitle);
        setIfNotNull(director, movie::setDirector);
        updateGenre(movie);
        setIfNotNull(ageRestriction, movie::setAgeRestriction);
        setIfNotNull(seatsForShow, movie::setSeatsForShow);
        setIfNotNull(priceForShow, movie::setPriceForShow);
    }

    private void updateGenre(Movie movie) {
        if (genre != null) {
            MovieGenre genre = MovieGenreMapper.convertGenre(this.genre);
            movie.setGenre(genre);
        }
    }
}

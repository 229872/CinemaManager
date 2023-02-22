package pl.bdygasinski.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Movie;

import static pl.bdygasinski.mapper.MovieGenreMapper.convertGenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInputDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Director cannot be empty")
    private String director;
    @NotBlank(message = "Genre cannot be empty")
    private String genre;
    @NotNull(message = "Age restriction cannot be null")
    @Min(value = 2, message = "Age restriction cannot be less than 2")
    @Max(value = 18, message = "Age restriction cannot be more than 18")
    private Integer ageRestriction;
    @NotNull(message = "Seats for show cannot be null")
    @Positive(message = "Seats for show must be positive")
    private Integer seatsForShow;
    @NotNull(message = "Price for show cannot be null")
    @Positive(message = "Price for show must be positive")
    private Double priceForShow;

    public Movie createMovie() {
        return new Movie(title, director, convertGenre(genre), ageRestriction, seatsForShow, priceForShow);
    }


}

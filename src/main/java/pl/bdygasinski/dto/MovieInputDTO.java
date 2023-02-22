package pl.bdygasinski.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Movie;
import pl.bdygasinski.model.submodel.MovieGenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInputDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Directory cannot be empty")
    private String directory;
    @NotBlank(message = "Genre cannot be empty")
    private String genre;
    @Min(value = 2, message = "Age restriction cannot be less than 2")
    @Max(value = 18, message = "Age restriction cannot be more than 18")
    private Integer ageRestriction;
    @Positive(message = "Seats for show must be positive")
    private Integer seatsForShow;
    @Positive(message = "Price for show must be positive")
    private Double priceForShow;

    public Movie createMovie() {
        return new Movie(title, directory, convertGenre(genre), ageRestriction, seatsForShow, priceForShow);
    }

    private MovieGenre convertGenre(String genre) {
        switch (genre.toUpperCase()) {
            case "HORROR":
                return MovieGenre.HORROR;
            case "COMEDY":
                return MovieGenre.COMEDY;
            case "SCI_FI":
                return MovieGenre.SCI_FI;
            case "FANTASY":
                return MovieGenre.FANTASY;
            case "THRILLER":
                return MovieGenre.THRILLER;
            case "ANIMATED":
                return MovieGenre.ANIMATED;
            default:
                return MovieGenre.NOT_SET;
        }
    }
}

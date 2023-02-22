package pl.bdygasinski.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Movie;
import pl.bdygasinski.model.submodel.MovieGenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieOutputDTO {
    private String title;
    private String director;
    private MovieGenre genre;
    private int ageRestriction;
    private int seatsForShow;
    private double priceForShow;

    public MovieOutputDTO(Movie movie) {
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.genre = movie.getGenre();
        this.ageRestriction = movie.getAgeRestriction();
        this.seatsForShow = movie.getSeatsForShow();
        this.priceForShow = movie.getPriceForShow();
    }
}

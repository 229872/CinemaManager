package pl.bdygasinski.model;

import jakarta.persistence.*;
import lombok.*;
import pl.bdygasinski.model.submodel.MovieGenre;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "movie_id"))
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Movie.GET_ALL, query = "SELECT movie FROM Movie movie")
@NamedQuery(name = Movie.FIND_BY_ID, query = "SELECT movie FROM Movie movie WHERE movie.id = :id")
public class Movie extends AbstractEntity {
    public static final String GET_ALL = "Movie.getAll";
    public static final String FIND_BY_ID = "Movie.findById";
    private String title;
    private String director;
    @Enumerated(value = EnumType.STRING)
    private MovieGenre genre;
    @Column(name = "age_restriction")
    private int ageRestriction;
    @Column(name = "seats_for_show")
    private int seatsForShow;
    @Column(name = "price_for_show")
    private double priceForShow;
}

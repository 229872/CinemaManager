package pl.bdygasinski.mapper;

import pl.bdygasinski.model.submodel.MovieGenre;

public class MovieGenreMapper {
    //Java 11 :(
    public static MovieGenre convertGenre(String genre) {
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

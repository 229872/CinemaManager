package pl.bdygasinski.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pl.bdygasinski.repository.MovieRepository;

@ApplicationScoped
public class MovieManager {
    @Inject
    private MovieRepository repository;


}

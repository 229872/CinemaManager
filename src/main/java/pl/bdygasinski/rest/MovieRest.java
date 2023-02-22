package pl.bdygasinski.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.bdygasinski.dto.MovieInputDTO;
import pl.bdygasinski.dto.MovieOutputDTO;
import pl.bdygasinski.dto.MovieUpdateDTO;
import pl.bdygasinski.exception.manager.MovieNotFoundManagerException;
import pl.bdygasinski.manager.MovieManager;

import java.util.List;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieRest {

    @Inject
    private MovieManager movieManager;

    @GET
    public Response getAllMovies() {
        List<MovieOutputDTO> allMovies = movieManager.getAllMovies();

        return Response.ok(allMovies).build();
    }

    @POST
    public Response createMovie(@NotNull @Valid MovieInputDTO dto) {
        MovieOutputDTO movie = movieManager.createMovie(dto);

        return Response.status(Response.Status.CREATED).entity(movie).build();
    }

    @GET
    @Path("/{id}")
    public Response findMovieById(@PathParam("id") Long id) {
        try {
            MovieOutputDTO movie = movieManager.findMovieById(id);

            return Response.ok(movie).build();
        } catch (MovieNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovieById(@PathParam("id") Long id) {
        try {
            movieManager.deleteMovieById(id);

            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (MovieNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMovieById(@PathParam("id") Long id, @NotNull @Valid MovieUpdateDTO dto) {
        try {
            MovieOutputDTO movie = movieManager.updateMovie(id, dto);

            return Response.ok(movie).build();
        } catch (MovieNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

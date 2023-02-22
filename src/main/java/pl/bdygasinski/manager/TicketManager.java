package pl.bdygasinski.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pl.bdygasinski.dto.TicketInputDTO;
import pl.bdygasinski.dto.TicketOutputDTO;
import pl.bdygasinski.exception.manager.ClientNotFoundManagerException;
import pl.bdygasinski.exception.manager.MovieNotFoundManagerException;
import pl.bdygasinski.exception.manager.TicketNotFoundManagerException;
import pl.bdygasinski.exception.manager.WrongTicketException;
import pl.bdygasinski.exception.repository.ClientNotFoundRepositoryException;
import pl.bdygasinski.exception.repository.MovieNotFoundRepositoryException;
import pl.bdygasinski.exception.repository.TicketNotFoundRepositoryException;
import pl.bdygasinski.model.Client;
import pl.bdygasinski.model.Movie;
import pl.bdygasinski.model.Ticket;
import pl.bdygasinski.repository.ClientRepository;
import pl.bdygasinski.repository.MovieRepository;
import pl.bdygasinski.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TicketManager {

    @Inject
    private TicketRepository ticketRepository;
    @Inject
    private ClientRepository clientRepository;
    @Inject
    private MovieRepository movieRepository;

    public TicketOutputDTO createTicket(TicketInputDTO dto) throws ClientNotFoundManagerException,
            MovieNotFoundManagerException, WrongTicketException {
        try {
            Client client = clientRepository.findById(dto.getClientId());
            Movie movie = movieRepository.findById(dto.getMovieId());
            validate(client, movie, dto.getSeatNumber());

            Ticket ticket = new Ticket(client, movie, dto.getSeatNumber());
            ticketRepository.add(ticket);

            return new TicketOutputDTO(ticket);

        } catch (ClientNotFoundRepositoryException e) {
            throw new ClientNotFoundManagerException(e.getMessage(), e.getCause());
        } catch (MovieNotFoundRepositoryException e) {
            throw new MovieNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public void deleteTicketById(Long id) throws TicketNotFoundManagerException {
        try {
            Ticket ticket = ticketRepository.findById(id);
            ticketRepository.delete(ticket);

        } catch (TicketNotFoundRepositoryException e) {
            throw new TicketNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public TicketOutputDTO findTicketById(Long id) throws TicketNotFoundManagerException {
        try {
            Ticket ticket = ticketRepository.findById(id);
            return new TicketOutputDTO(ticket);

        } catch (TicketNotFoundRepositoryException e) {
            throw new TicketNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }

    public List<TicketOutputDTO> getAllTickets() {
        return ticketRepository.getAll().stream()
                .map(TicketOutputDTO::new)
                .collect(Collectors.toList());
    }

    private void validate(Client client, Movie movie, Integer seatNumber) throws WrongTicketException {
        validateAge(client, movie);
        validateSeatBounds(movie, seatNumber);
        validateSeatAvailability(movie, seatNumber);
    }

    private void validateSeatAvailability(Movie movie, Integer seatNumber) throws WrongTicketException {
        List<Ticket> allTickets = ticketRepository.getAll();
        boolean isSeatAlreadyTaken = allTickets.stream()
                .anyMatch(ticket -> ticket.getMovie().equals(movie)
                        && ticket.getSeatNumber() == seatNumber);

        if (isSeatAlreadyTaken) {
            throw new WrongTicketException("Seat is already taken");
        }
    }

    private static void validateSeatBounds(Movie movie, Integer seatNumber) throws WrongTicketException {
        if (seatNumber > movie.getSeatsForShow()) {
            throw new WrongTicketException("Seat number out of bound");
        }
    }

    private static void validateAge(Client client, Movie movie) throws WrongTicketException {
        if (client.getAge() < movie.getAgeRestriction()) {
            throw new WrongTicketException("Client is too young for this movie");
        }
    }


}

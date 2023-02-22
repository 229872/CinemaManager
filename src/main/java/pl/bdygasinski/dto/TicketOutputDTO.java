package pl.bdygasinski.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Ticket;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TicketOutputDTO {
    private ClientOutputDTO client;
    private MovieOutputDTO movie;
    private int seatNumber;
    private double price;

    public TicketOutputDTO(Ticket ticket) {
        this.client = new ClientOutputDTO(ticket.getClient());
        this.movie = new MovieOutputDTO(ticket.getMovie());
        this.seatNumber = ticket.getSeatNumber();
        this.price = ticket.getTicketPrice();
    }
}

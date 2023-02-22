package pl.bdygasinski.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "ticket_id"))
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NamedQuery(name = Ticket.GET_ALL, query = "SELECT ticket FROM Ticket ticket")
@NamedQuery(name = Ticket.FIND_BY_ID, query = "SELECT ticket FROM Ticket ticket WHERE ticket.id = :id")
public class Ticket extends AbstractEntity {
    public static final String GET_ALL = "Ticket.getAll";
    public static final String FIND_BY_ID = "Ticket.findById";
    @ManyToOne
    private Client client;
    @ManyToOne
    private Movie movie;
    private int seatNumber;

    public double getTicketPrice() {
        return movie.getPriceForShow();
    }
}

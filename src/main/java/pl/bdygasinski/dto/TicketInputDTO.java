package pl.bdygasinski.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TicketInputDTO {
    @NotNull(message = "Client id cannot be null")
    @Positive(message = "Client id must be positive")
    private Long clientId;
    @NotNull(message = "Movie id cannot be null")
    @Positive(message = "Movie id must be positive")
    private Long movieId;
    @NotNull(message = "Seat number cannot be null")
    @Positive(message = "Seat number must be positive")
    private Integer seatNumber;
}

package pl.bdygasinski.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Client;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientOutputDTO {
    private Long id;
    private String login;
    private String fullName;
    private LocalDate dateOfBirth;
    private String country;
    private String city;

    public ClientOutputDTO(Client client) {
        this.id = client.getId();
        this.login = client.getLogin();
        this.fullName = client.getFullName();
        this.dateOfBirth = client.getDateOfBirth();
        this.city = client.getCity();
        this.country = client.getCountry();
    }
}

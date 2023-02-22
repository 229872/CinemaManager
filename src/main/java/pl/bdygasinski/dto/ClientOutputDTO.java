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
    private String login;
    private String fullName;
    private LocalDate dateOfBirth;
    private String country;
    private String city;

    public ClientOutputDTO(Client client) {
        this.login = client.getLogin();
        this.fullName = client.getFullName();
        this.dateOfBirth = client.getDateOfBirth();
        this.city = client.getAddress().getCity();
        this.country = client.getAddress().getCountry();
    }
}

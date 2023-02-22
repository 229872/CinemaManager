package pl.bdygasinski.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Client;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDTO {
    @Size(min = 8, message = "Password cannot have less than 8 characters")
    private String password;
    private String name;
    private String lastName;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth;
    private String country;
    private String city;
    private String street;
    private Integer numberOfHouse;

    public Client updateClient(Client client) {
        setValues(client);
        return client;
    }

    private void setValues(Client client) {
        updatePassword(client);
        updateName(client);
        updateLastName(client);
        updateDateOfBirth(client);
        updateAddress(client);
    }

    private void updateAddress(Client client) {
        updateCountry(client);
        updateCity(client);
        updateStreet(client);
        updateNumberOfHouse(client);
    }

    private void updateNumberOfHouse(Client client) {
        if (numberOfHouse != null) {
            client.setNumberOfHouse(numberOfHouse);
        }
    }

    private void updateStreet(Client client) {
        if (street != null) {
            client.setStreet(street);
        }
    }

    private void updateCity(Client client) {
        if (city != null) {
            client.setCity(city);
        }
    }

    private void updateCountry(Client client) {
        if (country != null) {
            client.setCountry(country);
        }
    }

    private void updateDateOfBirth(Client client) {
        if (dateOfBirth != null) {
            client.setDateOfBirth(dateOfBirth);
        }
    }

    private void updateLastName(Client client) {
        if (lastName != null) {
            client.setLastName(lastName);
        }
    }

    private void updateName(Client client) {
        if (name != null) {
            client.setName(name);
        }
    }

    private void updatePassword(Client client) {
        if (password != null) {
            client.setPassword(password);
        }
    }


}

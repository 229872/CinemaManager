package pl.bdygasinski.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Client;
import static pl.bdygasinski.util.Util.setIfNotNull;

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
    @Size(min = 9, max = 9, message = "Phone number must be build from 9 digits")
    private String phoneNumber;

    public Client updateClient(Client client) {
        setValues(client);
        return client;
    }

    private void setValues(Client client) {
        setIfNotNull(name, client::setName);
        setIfNotNull(lastName, client::setLastName);
        setIfNotNull(password, client::setPassword);
        setIfNotNull(dateOfBirth, client::setDateOfBirth);
        updateAddress(client);
    }

    private void updateAddress(Client client) {
        setIfNotNull(numberOfHouse, client::setNumberOfHouse);
        setIfNotNull(street, client::setStreet);
        setIfNotNull(city, client::setCity);
        setIfNotNull(country, client::setCountry);
    }


}

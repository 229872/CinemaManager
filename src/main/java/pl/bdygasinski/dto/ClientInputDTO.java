package pl.bdygasinski.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bdygasinski.model.Client;
import pl.bdygasinski.model.submodel.Address;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientInputDTO {
    @NotBlank(message = "Login cannot be empty")
    private String login;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password cannot have less than 8 characters")
    private String password;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Country cannot be empty")
    private String country;
    @NotBlank(message = "City cannot be empty")
    private String city;
    @NotBlank(message = "Street cannot be empty")
    private String street;
    @NotNull(message = "Number of house cannot be null")
    private int numberOfHouse;
    @NotBlank(message = "Number cannot be empty")
    @Size(min = 9, max = 9, message = "Phone number must be build from 9 digits")
    private String phoneNumber;

    public Client createClient() {
        return new Client(login, password, name, lastName,
                new Address(country, city, street, numberOfHouse), dateOfBirth, phoneNumber);
    }
}

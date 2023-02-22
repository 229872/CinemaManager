package pl.bdygasinski.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.bdygasinski.model.submodel.Address;

import java.time.LocalDate;
import java.time.Period;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Client.GET_ALL, query = "SELECT client FROM Client client")
public class Client extends AbstractEntity {
    public static final String GET_ALL = "Client.getAll";
    @Column(unique = true, nullable = false, updatable = false)
    private String login;
    private String password;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Embedded
    private Address address;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "creation_of_account_date")
    private LocalDate creationOfAccountDate;

    public Client(String login, String password, String name, String lastName,
                  Address address, LocalDate dateOfBirth) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    @PrePersist
    public void init() {
        creationOfAccountDate = LocalDate.now();
    }

    public String getFullName() {
        return getName() + " " + getLastName();
    }

    public int getAge() {
        Period period = dateOfBirth.until(LocalDate.now());
        return period.getYears();
    }
}

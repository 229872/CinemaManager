package pl.bdygasinski.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Delegate;
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
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Embedded
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Delegate
    private Address address;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "creation_of_account_date")
    private LocalDate creationOfAccountDate;

    public Client(String login, String password, String name, String lastName,
                  Address address, LocalDate dateOfBirth, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
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

package pl.bdygasinski.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
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
public class Client extends AbstractEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String login;
    private String password;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private Address address;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "creation_of_account_date")
    private LocalDate creationOfAccountDate;


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

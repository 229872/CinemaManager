package pl.bdygasinski.model.submodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String street;
    @Column(name = "street_number")
    private int numberOfStreet;
}

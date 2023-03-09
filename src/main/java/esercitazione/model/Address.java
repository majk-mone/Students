package esercitazione.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String country;
    private String PostCode;
    private String city;
}

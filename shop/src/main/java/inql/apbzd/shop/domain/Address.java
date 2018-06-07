package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "adres")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ulica")
    @Size(min = 3,max = 50)
    private String streetName;
    @NotNull
    @Size(min = 1,max = 15)
    @Column (name = "nr_domu")
    private String hoNumber;

    @Size(min = 1,max = 15)
    @Column(name = "nr_mieszkania")
    private String apaNumber;
    @NotNull
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]")
    @Size(min = 6,max = 6)
    @Column(name = "kod_pocztowy")
    private String postalCode;
    @NotNull
    @Size(min = 3,max = 50)
    @Column(name = "miasto")
    private String city;

    @Override
    public String toString() {
        return streetName + " "+hoNumber+"/"+apaNumber+"\n" +
                postalCode+"\n" +
                city;
    }
}

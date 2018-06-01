package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "adres")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ulica")
    private String streetName;
    @Column (name = "nr_domu")
    private String hoNumber;
    @Column(name = "nr_mieszkania")
    private String apaNumber;
    @Column(name = "kod_pocztowy")
    private String postalCode;
    @Column(name = "miasto")
    private String city;


}

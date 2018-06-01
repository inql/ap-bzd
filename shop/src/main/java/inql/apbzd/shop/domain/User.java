package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    @Column(name = "haslo")
    private String password;

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "rola_id")
    private Role role;


}

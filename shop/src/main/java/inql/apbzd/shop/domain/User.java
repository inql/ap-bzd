package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    @OrderColumn
    @NotNull
    @Size(min = 3,max = 50)
    private String login;

    @Column(name = "haslo")
    @NotNull
    @Size(min=8, max = 25)
    private String password;

    @NotNull
    @Size(min = 3,max = 50)
    @Column(name = "imie")
    private String name;

    @NotNull
    @Size(min = 3,max = 50)
    @Column(name = "nazwisko")
    private String surname;

    @NotNull
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "rola_id")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adres_id")
    private Address address;


}

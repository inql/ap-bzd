package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "zamowienie")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uzytkownik_id")
    private User user;

    @Column(name = "data_zlozenia")
    private LocalDate orderDate;

    @Column(name = "data_wysylki")
    private LocalDate shipDate;

    @Column(name = "czy_oplacone")
    private boolean isPaid;

    @OneToMany(mappedBy = "order")
    private Set<Cart> cartSet = new HashSet<>();





}

package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Column(name = "data_wysylki")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shipDate;

    @Column(name = "czy_oplacone")
    private Boolean isPaid;

    @OneToMany(mappedBy = "order")
    private Set<Cart> cartSet = new HashSet<>();





}

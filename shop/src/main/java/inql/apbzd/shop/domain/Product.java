package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "produkt")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nazwa")
    private String name;
    @Column(name = "opis", columnDefinition = "TEXT")
    private String description;
    @Column(name = "cena")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "podkategoria_id")
    private Subcategory subcategory;
    @OneToMany(mappedBy = "product")
    private Set<Cart> cartSet = new HashSet<>();
}

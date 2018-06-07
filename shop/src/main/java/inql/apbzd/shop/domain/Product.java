package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @OrderColumn
    @Column(name = "nazwa")
    @NotNull
    @Size(min = 3,max = 50)
    private String name;

    @Column(name = "opis", columnDefinition = "TEXT")
    @NotNull
    private String description;

    @Column(name = "cena")
    @NotNull
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "podkategoria_id")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Cart> cartSet = new HashSet<>();
}

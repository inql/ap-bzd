package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "koszyk")
@IdClass(CartId.class)
public class Cart{
    @Id
    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "produkt_id")
    Product product;
    @Column(name = "ilosc")
    @Min(1)
    @Max(999)
    @NotNull
    private int quantity;


}

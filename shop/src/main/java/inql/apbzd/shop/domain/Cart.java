package inql.apbzd.shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "koszyk")
public class Cart {

    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "produkt_id")
    private Product product;

    @Column(name = "ilosc")
    private int quantity;


}

package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "podkategoria")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "opis", columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "kategoria_id")
    private Category category;
    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @Lob
    @Column(name = "obrazek")
    private Byte[] image;

    @Override
    public String toString() {
        return name;
    }
}

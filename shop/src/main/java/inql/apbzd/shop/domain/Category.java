package inql.apbzd.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "kategoria")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OrderColumn
    @Size(min = 3,max = 25)
    @Column(name = "nazwa")
    private String name;
    @NotNull
    @Column(name ="opis", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Subcategory> subcategories = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}

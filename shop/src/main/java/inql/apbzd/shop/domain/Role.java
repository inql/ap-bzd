package inql.apbzd.shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "rola")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderColumn
    @Column(name = "nazwa")
    @NotNull
    @Size(min = 3,max = 255)
    private String name;
    @Column(name = "opis", columnDefinition = "TEXT")
    @NotNull
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}

package inql.apbzd.shop.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand {

    private Long id;
    private Long user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Błędna wartość")
    private LocalDate orderDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shipDate;
    private Boolean isPaid;

    private Set<CartCommand> cartCommands = new HashSet<>();


}

package inql.apbzd.shop.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand {

    private Long id;
    private Long user;
    @Past
    private LocalDate orderDate;
    private LocalDate shipDate;
    private boolean isPaid;


}

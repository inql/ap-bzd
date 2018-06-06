package inql.apbzd.shop.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class CartCommand {

    private Long orderId;
    private Long productId;

    @Min(1)
    @Max(999)
    private int quantity;


}

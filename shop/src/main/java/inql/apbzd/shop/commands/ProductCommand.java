package inql.apbzd.shop.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductCommand {

    private Long id;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String name;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 255,message = "Długość musi być w zakresie: <3;255>")
    private String description;
    private Long subcategory;
    @Digits(integer = 6, fraction = 2, message = "Zły format")
    private BigDecimal price;
}

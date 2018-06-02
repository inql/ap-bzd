package inql.apbzd.shop.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SubcategoryCommand {

    private Long id;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 25,message = "Długość musi być w zakresie: <3;25>")
    private String name;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 255,message = "Długość musi być w zakresie: <3;255>")
    private String description;

    private Long category;

    private Byte[] image;



}

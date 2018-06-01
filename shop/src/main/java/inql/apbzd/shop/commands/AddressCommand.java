package inql.apbzd.shop.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class AddressCommand {

    private Long id;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String streetName;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String hoNumber;
    private String apaNumber;
    @NotBlank(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]", message = "Błędny typ danych")
    private String postalCode;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String city;


}

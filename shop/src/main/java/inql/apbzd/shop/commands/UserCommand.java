package inql.apbzd.shop.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {

    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String login;

    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 8, max = 25, message = "Hasło musi być w zakresie: <8;25>")
    private String password;

    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    @NotBlank(message = "Pole nie może być puste")
    private String name;

    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    @NotBlank(message = "Pole nie może być puste")
    private String surname;

    @Pattern(regexp = ".+@.+\\.[a-z]+")
    @NotBlank(message = "Pole nie może być puste")
    private String email;

    private Long role;

    private Long addressId;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String streetName;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 1, max = 15,message = "Długość musi być w zakresie: <3;50>")
    private String hoNumber;
    private String apaNumber;
    @NotBlank(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]", message = "Błędny typ danych")
    private String postalCode;
    @NotBlank(message = "Pole nie może być puste")
    @Size(min = 3, max = 50,message = "Długość musi być w zakresie: <3;50>")
    private String city;




}

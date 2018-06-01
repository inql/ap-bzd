package inql.apbzd.shop.commands;

import inql.apbzd.shop.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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

    private RoleCommand role;
    private AddressCommand address;




}

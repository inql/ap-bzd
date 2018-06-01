package inql.apbzd.shop.commands;

import javax.validation.constraints.NotBlank;

public class CategoryCommand {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

}

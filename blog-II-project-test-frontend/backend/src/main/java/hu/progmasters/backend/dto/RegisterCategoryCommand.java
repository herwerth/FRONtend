package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterCategoryCommand {

    @NotBlank(message = "A kategória neve nem lehet üres!")
    @Size(min = 3, message = "A kategória nevének legalább 3 karakternek kell lennie!")
    private String name;
}

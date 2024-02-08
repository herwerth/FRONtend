package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class RegisterAccountCommand {

    @NotNull
    @NotEmpty
    @NotBlank(message = "A felhasználónév nem lehet üres!")
    @Size(min = 3, message = "A felhasználónévnek legalább 3 karakternek kell lennie!")
    private String username;

    @NotNull
    @NotEmpty
    @NotBlank(message = "Az email nem lehet üres!")
    @Size(min = 5, message = "Az email címnek legalább 5 karakternek kell lennie!")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank(message = "A megjelenített név nem lehet üres!")
    @Size(min = 3, message = "A megjelenített névnek legalább 3 karakternek kell lennie!")
    private String displayName;

    @NotNull
    @NotEmpty
    @NotBlank(message = "A jelszó nem lehet üres!")
    private String password;
}

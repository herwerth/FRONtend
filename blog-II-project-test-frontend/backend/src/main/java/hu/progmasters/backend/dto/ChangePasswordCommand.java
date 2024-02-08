package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ChangePasswordCommand {

    private String oldPassword;

    @NotNull
    @NotEmpty
    @NotBlank(message = "Az új jelszó nem lehet üres!")
    @Size(min = 3, message = "Az új jelszónak legalább 3 karakternek kell lennie!")
    private String newPassword;

    private String newPasswordAgain;
}

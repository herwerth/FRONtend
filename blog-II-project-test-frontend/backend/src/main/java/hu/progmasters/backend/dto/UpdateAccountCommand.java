package hu.progmasters.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateAccountCommand {

    private String username;

    private String email;

    private String displayName;
}

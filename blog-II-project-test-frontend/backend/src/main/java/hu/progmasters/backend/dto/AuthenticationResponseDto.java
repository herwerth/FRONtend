package hu.progmasters.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {

    private String accessToken;

    private String refreshToken;

    private AccountDetails accountDetails;
}

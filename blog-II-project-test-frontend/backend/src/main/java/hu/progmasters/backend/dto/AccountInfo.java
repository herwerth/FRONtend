package hu.progmasters.backend.dto;

import hu.progmasters.backend.domain.Role;
import hu.progmasters.backend.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountInfo {

    private Long id;

    private String username;

    private String email;

    private String displayName;

    private LocalDate dateOfRegistration;

    private Status userStatus;

    private List<Role> role;

    public AccountInfo(Long id, String username, String email, String displayName, LocalDate dateOfRegistration, Status userStatus) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.dateOfRegistration = dateOfRegistration;
        this.userStatus = userStatus;
    }
}

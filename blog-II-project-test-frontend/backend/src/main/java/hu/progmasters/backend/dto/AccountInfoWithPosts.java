package hu.progmasters.backend.dto;

import hu.progmasters.backend.domain.Role;
import hu.progmasters.backend.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountInfoWithPosts {

    private String username;

    private String displayName;

    private String email;

    private LocalDate dateOfRegistration;

    private Status userStatus;

    private List<Role> role;

    private List<PostInfo> postInfos;
}

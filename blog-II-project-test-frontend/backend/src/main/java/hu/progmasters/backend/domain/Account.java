package hu.progmasters.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private Status userStatus;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> role;
}

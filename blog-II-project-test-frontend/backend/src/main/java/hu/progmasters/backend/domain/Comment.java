package hu.progmasters.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_comment")
    private LocalDate dateOfComment;

    @Column(name = "comment_body")
    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

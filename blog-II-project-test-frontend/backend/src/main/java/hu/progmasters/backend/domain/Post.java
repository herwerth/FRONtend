package hu.progmasters.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_post")
    private String dateOfPost;

    @Column(name = "post_title")
    private String postTitle;

    @Column(columnDefinition = "TEXT", name = "post_body")
    private String postBody;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    private Status postStatus;

    @Column(name = "comment_status")
    private Boolean commentStatus;

    private String tag;

    private Boolean highlighted;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
